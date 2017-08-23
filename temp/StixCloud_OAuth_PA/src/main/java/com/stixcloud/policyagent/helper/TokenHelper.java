package com.stixcloud.policyagent.helper;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import com.stixcloud.policyagent.constant.OAuthConstant;
import com.stixcloud.policyagent.exception.RefreshTokenExpirationException;
import com.stixcloud.policyagent.helper.PAConfigProperties.Client;
import com.stixcloud.policyagent.model.SessionTokenModel;
import com.stixcloud.policyagent.repo.SessionTokenRepositoryImpl;
import com.stixcloud.policyagent.token.ClientCredentials;
import com.stixcloud.policyagent.token.OAuth2Grant;
import com.stixcloud.policyagent.token.PasswordOwner;
import com.stixcloud.policyagent.token.RefreshToken;

@Component
public class TokenHelper {
  
  private static final Logger logger = LogManager.getLogger(TokenHelper.class);

  @Autowired
  private PAConfigProperties app;

  private static final long DEFAULT_SESSION_TOKEN_TTL = 15 * 60 * 1000; // default is 15 mins
  
  private static final String OAUTH2_ERROR_REFRESH_TOKEN_EXPIRED = "Bearer error=\"invalid_token\", error_description=\"Invalid refresh token (expired):";

  @Autowired
  private SessionTokenRepositoryImpl sessionTokenRepository;

  public OAuth2Grant getAccessToken(String... strings) throws Exception {
    String jSessionId = RequestContextHolder.getRequestAttributes().getSessionId();
    SessionTokenModel sessionToken = sessionTokenRepository.findSessionToken(jSessionId);
    
    if(strings != null && strings.length > 0 && OAuthConstant.GUEST_USER.equalsIgnoreCase(strings[0])){
      // guest login
      return getAccessToken(new ClientCredentials(OAuthConstant.GUEST_USER, strings.length > 1 ? strings[1] : null));
    }
    
    if(strings.length == 2){
      // a new login -> create new password owner token
      return getAccessToken(new PasswordOwner(strings[0], strings[1]));
    }
    
    if (sessionToken == null || sessionToken.getAccessToken() == null) {
      // invalid session or token does not exist -> create new token
      if (strings.length == 1) {
        // refresh token
        return getAccessToken(new RefreshToken(strings[0]));
      } else {
        // default is client credentials token
        return getAccessToken(new ClientCredentials());
      }
      
    }
    // token is in redis means token is not expired
    return new OAuth2Grant(sessionToken.getAccessToken(), null);
  }

  public OAuth2Grant getAccessToken(OAuth2Grant granter) throws Exception {
    String accessToken = null;
    // generate token
    SessionTokenModel sessionToken = new SessionTokenModel();
    long start = System.currentTimeMillis();
    
    RestTemplate restTemplate = new RestTemplate();
    // load client details based on grant type
    restTemplate.getInterceptors().add(getClientHttpRequestInterceptor(granter));
    try{
      ResponseEntity<String> response = restTemplate
          .exchange(app.getServer() + granter.getOAuthPath(), HttpMethod.POST, null, String.class);
      accessToken = response.getBody();
    }catch(HttpClientErrorException exception){
      // check if refresh token expired -> login required
      List<String> wwwAuthenticateHeaders = exception.getResponseHeaders().get("WWW-Authenticate");
      if(exception.getStatusCode() == HttpStatus.UNAUTHORIZED &&
          wwwAuthenticateHeaders != null){
        for(String header : wwwAuthenticateHeaders){
          if(header != null && header.startsWith(OAUTH2_ERROR_REFRESH_TOKEN_EXPIRED) && (header.indexOf(granter.getRefreshToken()) > -1)){
            logger.info("Refresh token expired: " + granter.getRefreshToken());
            throw new RefreshTokenExpirationException("Refresh token expired: " + granter.getRefreshToken());
          }
        }
      }
      throw exception;
    } catch (HttpServerErrorException e) {
      logger.error("Internal Server error when getAccessToken in PA: ", e);
      throw e;
    } catch(Exception e){
      logger.error("Error while requesting new token " + granter.getGrantKey() + e.getMessage());
      throw e;
    }
    
    JSONObject json = new JSONObject(accessToken);
    if (json.has(OAuthConstant.ACCESS_TOKEN)) {
      accessToken = json.getString(OAuthConstant.ACCESS_TOKEN);
    }
    // the remaining ttl of token is subtracted the time waiting response from oauth server
    long ttl = getTokenTTL(accessToken);
    long end = System.currentTimeMillis();
    ttl = ttl - (end - start);
    ttl = ttl > 0 ? ttl : DEFAULT_SESSION_TOKEN_TTL;
    sessionToken.setTtl(ttl);
    sessionToken.setAccessToken(accessToken);

    logger.info("New token created: jSessionId="
        + RequestContextHolder.getRequestAttributes().getSessionId() + ", ttl="
        + sessionToken.getTtl() + ", type=" + granter.getClass() + ", grantKey=" + granter.getGrantKey() + ", value=" + accessToken);
    
    // save to redis
    sessionTokenRepository.saveSessionToken(sessionToken);
    
    granter.setAccessToken(accessToken);
    // extract refresh token
    if(json.has(OAuthConstant.REFRESH_TOKEN)){
      String refreshToken = json.getString(OAuthConstant.REFRESH_TOKEN);
      granter.setRefreshToken(refreshToken);
    }
    return granter;
  }

  private BasicAuthorizationInterceptor getClientHttpRequestInterceptor(OAuth2Grant granter)
      throws BadClientCredentialsException {
    if (app.getClients() == null) {
      logger.error("No client settings found");
      throw new BadClientCredentialsException();
    }
    Client client = (Client) app.getClients().get(granter.getGrantKey());
    return new BasicAuthorizationInterceptor(client.getClientId(), client.getSecret());
  }

  private long getTokenTTL(String token) {
    long ttl = DEFAULT_SESSION_TOKEN_TTL;
    JsonParser objectMapper = JsonParserFactory.create();
    try {
      // parse token to get expiration time
      Jwt jwt = JwtHelper.decode(token);
      String content = jwt.getClaims();
      Map<String, Object> map = objectMapper.parseMap(content);
      long expiration = 0l;
      if (map.containsKey(AccessTokenConverter.EXP)
          && map.get(AccessTokenConverter.EXP) instanceof Integer) {
        expiration = (Integer) map.get(AccessTokenConverter.EXP);
      }
      // time to live is the remaining milliseconds from current time
      ttl = expiration * 1000 - System.currentTimeMillis();
    } catch (Exception e) {
      logger.debug("Invalid access token. " + e.getMessage());
    }
    return ttl;
  }

  /**
   * get access token information
   * 
   * @param jSessionId
   * @return
   */
  public SessionTokenModel getSessionToken() {
    SessionTokenModel sessionToken = sessionTokenRepository.findSessionToken(RequestContextHolder.getRequestAttributes().getSessionId());
    return sessionToken;
  }
  
  

  /**
   * remove token from redis
   * 
   * @param jSessionId
   * @param sessionTokenModel
   */
  public void removeToken(String jSessionId) {
    if (sessionTokenRepository.findSessionToken(jSessionId) != null) {
      sessionTokenRepository.deleteSessionToken(jSessionId);
    }
  }

}
