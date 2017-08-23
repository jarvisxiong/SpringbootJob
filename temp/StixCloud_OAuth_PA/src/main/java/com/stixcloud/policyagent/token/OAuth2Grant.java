package com.stixcloud.policyagent.token;

public class OAuth2Grant {
  
  private String grantKey;
    
  public OAuth2Grant(){}
  
  public OAuth2Grant(String accessToken, String refreshToken){
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
  
  private String accessToken;
  
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
  
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getOAuthPath(){
    return null;
  }
  
  public String getGrantKey(){
    return grantKey;
  }
  
  public void setGrantKey(String grantKey){
    this.grantKey = grantKey;
  }

}
