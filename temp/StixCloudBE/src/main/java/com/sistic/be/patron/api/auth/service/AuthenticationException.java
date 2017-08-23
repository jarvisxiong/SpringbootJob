package com.sistic.be.patron.api.auth.service;

import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;

public class AuthenticationException extends ClientAuthenticationException {
  /**
   * 
   */
  private static final long serialVersionUID = -8195817176502537056L;

  private Integer httpCode;
  private String OAuth2ErrorCode;

  public AuthenticationException(String message) {
    super(message);
  }

  public AuthenticationException(String message, Throwable e) {
    super(message, e);
  }

  public AuthenticationException(Integer httpCode, String oAuth2ErrorCode, String msg,
      Throwable e) {
    super(msg, e);
    this.httpCode = httpCode;
    this.OAuth2ErrorCode = oAuth2ErrorCode;
  }

  public AuthenticationException(Integer httpCode, String msg) {
    super(msg);
  }

  public AuthenticationException(Integer httpCode, String oAuth2ErrorCode, String msg) {
    super(msg);
    this.httpCode = httpCode;
    this.OAuth2ErrorCode = oAuth2ErrorCode;
  }

  public Integer getHttpCode() {
    return httpCode;
  }

  public void setHttpCode(Integer httpCode) {
    this.httpCode = httpCode;
  }

  @Override
  public String getOAuth2ErrorCode() {
    return OAuth2ErrorCode;
  }

  public void setOAuth2ErrorCode(String oAuth2ErrorCode) {
    OAuth2ErrorCode = oAuth2ErrorCode;
  }

}
