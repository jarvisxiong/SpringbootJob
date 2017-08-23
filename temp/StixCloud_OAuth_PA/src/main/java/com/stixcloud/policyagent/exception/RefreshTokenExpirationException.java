package com.stixcloud.policyagent.exception;

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

public class RefreshTokenExpirationException extends InvalidTokenException {
  public RefreshTokenExpirationException(String msg, Throwable t) {
    super(msg, t);
  }

  public RefreshTokenExpirationException(String msg) {
    super(msg);
  }
  
}
