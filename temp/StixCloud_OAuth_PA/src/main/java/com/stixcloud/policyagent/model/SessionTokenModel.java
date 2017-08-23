package com.stixcloud.policyagent.model;

import java.io.Serializable;

public class SessionTokenModel implements Serializable {
  private static final long serialVersionUID = 245671684276662562L;
  private String jSessionId;
  private String accessToken;
  private long ttl;

  public long getTtl() {
    return ttl;
  }

  public void setTtl(long ttl) {
    this.ttl = ttl;
  }

  public String getJSessionId() {
    return jSessionId;
  }

  public void setJSessionId(String jSessionId) {
    this.jSessionId = jSessionId;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
