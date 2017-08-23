package com.stixcloud.cart;

/**
 * Created by chongye on 30/9/2016.
 */
public class PreCommitCartException extends CartException {
  public PreCommitCartException(String message) {
    super(message);
  }

  public PreCommitCartException(String message, Throwable cause) {
    super(message, cause);
  }
}
