package com.stixcloud.policyagent.token;

import com.stixcloud.policyagent.constant.OAuthConstant;

public class RefreshToken extends OAuth2Grant {

  private static final String GRANT_TYPE = "refresh_token";
  
  public RefreshToken(String refreshToken){
    super(null, refreshToken);
  }

  @Override
  public String getOAuthPath() {
    return new StringBuilder().append(OAuthConstant.ENDPOINT_CREATE_TOKEN).append("?grant_type=")
        .append(GRANT_TYPE).append("&refresh_token=").append(getRefreshToken()).toString();
  }
  
  @Override
  public String getGrantKey() {
    return GRANT_TYPE;
  }
}
