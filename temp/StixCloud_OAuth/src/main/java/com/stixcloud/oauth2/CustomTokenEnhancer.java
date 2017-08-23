package com.stixcloud.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.stixcloud.oauth2.domain.OAuth2GrantType;
import com.stixcloud.oauth2.domain.PatronLogin;
import com.stixcloud.oauth2.repo.IPatronLoginRepository;

/**
 * Last Modified by : ZhenHui <br />
 * Last Modified : 1 Feb 2017 <br />
 * Purpose : Include Patron Login object for "password" Grant type only(not "client_credentials"
 * Grant type)
 * 
 * @author phuongnm
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {

  @Autowired
  private IPatronLoginRepository repo;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    OAuth2Request request = authentication.getOAuth2Request();
    String requestGrantType = request.getGrantType();

    // Add PatronLogin object for the password grant type.
    if (OAuth2GrantType.RESOURCE_OWNER_PASSWORD_GRANT_TYPE.equalsIgnoreCase(requestGrantType) || request.isRefresh()) {
      PatronLogin patronLogin =
          repo.retrievePatronLogin(authentication.getName());
      Map<String, Object> additionalInfo = new HashMap<>();
      additionalInfo.put("patronLogin", patronLogin);
      ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    }
    return accessToken;
  }
}
