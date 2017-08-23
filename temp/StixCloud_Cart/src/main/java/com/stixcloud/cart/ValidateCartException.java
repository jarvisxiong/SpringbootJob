package com.stixcloud.cart;

/**
 * Created by chongye on 30/9/2016.
 */
public class ValidateCartException extends CartException {
  public ValidateCartException(String message) {
    super(message);
  }

  public ValidateCartException(String message, Throwable cause) {
    super(message, cause);
  }
}
