package com.stixcloud.policyagent.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PAUtil {
  private static final Logger logger = LogManager.getLogger(PAUtil.class);
  private static ObjectMapper mapper = new ObjectMapper();
  /**
   * get current patron id from security context holder
   * 
   * @return: patron id if token type is password owner null if token type is client credentials,
   *          there is no patron id
   */
  public static Long getPatronId() {
    try {
      OAuth2Authentication authentication =
          (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String token = details.getTokenValue();
        if(token == null){
          throw new InvalidTokenException("Token is null");
        }
        // parse token to get expiration time
        String content = JwtHelper.decode(token).getClaims();
        JsonNode json = mapper.readValue(content, JsonNode.class);
        return json.get("patronLogin").get("patronId").asLong();
      }
    } catch (Exception ex) {
      // there is exception
      logger.error(ex.getMessage());
    }
    return null;
  }
}
