package com.stixcloud.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends Exception {

  public DataNotFoundException(String message) {
    super(message);
  }

  public DataNotFoundException(Throwable cause) {
    super(cause);
  }

  public DataNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
