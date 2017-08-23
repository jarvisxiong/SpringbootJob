package com.stixcloud.policyagent.token;

import com.stixcloud.policyagent.constant.OAuthConstant;

public class ClientCredentials extends OAuth2Grant {
  private static final String GRANT_TYPE = "client_credentials";
  private String username; 
  
  public ClientCredentials(){
    super.setGrantKey(GRANT_TYPE);
  }
  
  public ClientCredentials(String key){
    super.setGrantKey(key);
  }
  
  public ClientCredentials(String key, String username){
    super.setGrantKey(key);
    this.username = username;
  }

  @Override
  public String getOAuthPath() {
    StringBuilder sb = new StringBuilder().append(OAuthConstant.ENDPOINT_CREATE_TOKEN).append("?grant_type=")
        .append(GRANT_TYPE);
    if(username != null){
      sb.append("&username=").append(username);
    }
    return sb.toString();
  }
  
  public String getUserName() {
    return username;
  }

  public void setUserName(String userName) {
    this.username = userName;
  }
}
