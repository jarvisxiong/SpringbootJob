package com.stixcloud.cart;

/**
 * Created by chetan on 04/01/2017.
 */
public class PostCommitCartException extends CartException {
  public PostCommitCartException(String message) {
    super(message);
  }

  public PostCommitCartException(String message, Throwable cause) {
    super(message, cause);
  }
}
