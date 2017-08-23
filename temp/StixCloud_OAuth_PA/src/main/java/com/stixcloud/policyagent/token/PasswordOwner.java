package com.stixcloud.policyagent.token;

import com.stixcloud.policyagent.constant.OAuthConstant;
import com.stixcloud.policyagent.util.EncryptionBlowfish;

public class PasswordOwner extends OAuth2Grant {
  private static final String GRANT_TYPE = "password";
  private String userName;
  private String password;

  public PasswordOwner(String userName, String password) {
    this.userName = userName;
    this.password = password;
    super.setGrantKey(GRANT_TYPE);
  }

  @Override
  public String getOAuthPath() {
    return new StringBuilder().append(OAuthConstant.ENDPOINT_CREATE_TOKEN).append("?grant_type=")
        .append(GRANT_TYPE).append("&username=").append(userName).append("&password=")
        .append(EncryptionBlowfish.encrypt(password)).toString();
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String getGrantKey() {
    return GRANT_TYPE;
  }
}
