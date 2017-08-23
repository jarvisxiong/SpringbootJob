package com.stixcloud.cart.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class Utils {
  private static final Logger logger = LogManager.getLogger(Utils.class);
  public static String getToken(){
    RequestAttributes atts = RequestContextHolder.getRequestAttributes();
    String token = (String)atts.getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,  RequestAttributes.SCOPE_REQUEST) + " " + (String)atts.getAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE, RequestAttributes.SCOPE_REQUEST);
    logger.info("Token: " + token);
    return token;
  }
  
  public static boolean validateRegularExpressions(String input, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    if (matcher.find()) {
      return true;
    }
    return false;
  }
}
