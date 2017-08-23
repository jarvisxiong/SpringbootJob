package com.sistic.be.patron.api.auth.service;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.InternalServerErrorException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;

import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.exception.InvalidPatronLoginException;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.session.OnlineUserSession;
import com.stixcloud.policyagent.constant.OAuthConstant;
import com.stixcloud.policyagent.exception.RefreshTokenExpirationException;
import com.stixcloud.policyagent.helper.TokenHelper;
import com.stixcloud.policyagent.model.SessionTokenModel;
import com.stixcloud.policyagent.token.OAuth2Grant;
import com.stixcloud.policyagent.token.PasswordOwner;

@Service
@ComponentScan("com.stixcloud.policyagent.helper")
public class AuthorisationService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  private TokenHelper helper;

  @Autowired
  private HttpSession httpSession;
  
  @Autowired
  private HttpServletRequest request;

  @Autowired
  private MessageSource messageSource;

  public String getAccessToken(String... args) {
    PatronLogin patronLogin = null;
    if (args.length == 0) {
      // if token is in redis, use that token
      String token = null;
      SessionTokenModel tokenModel = helper.getSessionToken();
      if(tokenModel != null){
        token = tokenModel.getAccessToken();
      }
      if (token != null) {
        return token;
      }
      // token does not exist in redis -> if no argument passed, check if patron login is in session
      // -> pass refresh token
      OnlineUserSession userSession =
          SessionUtil.getOnlineUserSession(httpSession, "onlineUserSessionInfo");
      if (userSession != null) {
        patronLogin = userSession.getPatronLogin();
        if (patronLogin != null) {
          args = new String[1];
          args[0] = patronLogin.getRefreshToken();
        }
      }
    }

    try {
      OAuth2Grant granter = helper.getAccessToken(args);
      if (patronLogin != null) {
        // update refresh token
        patronLogin.setRefreshToken(granter.getRefreshToken());
      }
      if (granter instanceof PasswordOwner) {
        return granter.getRefreshToken();
      }
      return granter.getAccessToken();
    } catch (RefreshTokenExpirationException e) {
      logger.info("Refresh token is expired. Redirect to login");
      // refresh token expired -> need a login
      httpSession.invalidate();
      throw new InvalidPatronLoginException(request.getRequestURL());
    } catch(HttpClientErrorException exception){
      logger.debug(exception.getRawStatusCode() + exception.getMessage());
      throw exception;
    } catch(Exception ex){
      logger.error("Exception retrieving access token: " + ex.getMessage());
      throw new InternalServerErrorException(ex);
    }
  }
  
  public int getTokenTTL(){
    SessionTokenModel tokenModel = helper.getSessionToken();
    return (int) (tokenModel != null ? tokenModel.getTtl()/1000 : 0);
  }

  /**
   * Get PatronLogin from access token
   * 
   * @param accessToken
   * @return PatronLogin Info of patron who logged in to system
   * @throws JSONException
   */
  public PatronLogin getPatronLogin(String accessToken) throws JSONException {
    logger.info("Get patron login from access token: " + accessToken);
    if (StringUtils.isBlank(accessToken)) {
      logger.error("Access token is blank.");
      throw new AuthenticationException(messageSource.getMessage("oauth.accesstoken.blank.error",
          null, LocaleContextHolder.getLocale()));
    }

    if (accessToken.startsWith(OAuthConstant.BEARER)) {
      accessToken =
          accessToken.substring(OAuthConstant.BEARER.length(), accessToken.length()).trim();
    }

    JsonParser objectMapper = JsonParserFactory.create();
    JSONObject jsonPatronLogin = new JSONObject();
    try {
      Jwt jwt = JwtHelper.decode(accessToken);
      String content = jwt.getClaims();
      Map<String, Object> map = objectMapper.parseMap(content);
      if (map.containsKey(AccessTokenConverter.EXP)
          && map.get(AccessTokenConverter.EXP) instanceof Integer) {
        Integer intValue = (Integer) map.get(AccessTokenConverter.EXP);
        map.put(AccessTokenConverter.EXP, new Long(intValue));
      }
      JSONObject json = new JSONObject(map);

      jsonPatronLogin = json.getJSONObject("patronLogin");

    } catch (Exception e) {
      logger.error("Invalid access token.");
      throw new AuthenticationException(messageSource.getMessage("oauth.accesstoken.invalid.error",
          null, LocaleContextHolder.getLocale()), e);
    }

    String firstName = jsonPatronLogin.getString("firstName");
    String lastName = jsonPatronLogin.getString("lastName");

    boolean isFirstNameNotBlank = StringUtils.isNotBlank(firstName);
    boolean isLastNameNotBlank = StringUtils.isNotBlank(lastName);
    String fullName = "";
    if (isFirstNameNotBlank && isLastNameNotBlank) {
      fullName = firstName + " " + lastName;
    } else if (isFirstNameNotBlank) {
      fullName = firstName;
    } else if (isLastNameNotBlank) {
      fullName = lastName;
    }
    
    //deegix-todo
    //boolean hasMembership = jsonPatronLogin.getBoolean("hasMembership");
    // commented off my jianhong
    boolean hasMembership = false;
    
    return new PatronLogin(jsonPatronLogin.getLong("patronId"), jsonPatronLogin.getLong("accountNo"), jsonPatronLogin.getString("email"),
        firstName, lastName, fullName, accessToken, hasMembership);
  }
  
  public Cookie createTokenCookie(){
    String token = getAccessToken();
    Cookie cookie = new Cookie("token", token);
    cookie.setMaxAge(getTokenTTL());
    logger.info("Cookie: value=" + token + ", max-age=" + cookie.getMaxAge());
    return cookie;
  }

  /**
   * Remove access token that corresponds to this session
   */
  public void popSessionToken() {
    String jSessionId = RequestContextHolder.getRequestAttributes().getSessionId();
    try {
      helper.removeToken(jSessionId);
    } catch (Exception e) {
      logger.error("Cannot remove access token.", e);
      throw new AuthenticationException(messageSource.getMessage("oauth.remove.accesstoken.error",
          null, LocaleContextHolder.getLocale()), e);
    }
  }

}
