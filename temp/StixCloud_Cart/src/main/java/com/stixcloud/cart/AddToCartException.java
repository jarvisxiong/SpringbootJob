package com.stixcloud.cart;

/**
 * Created by chongye on 30/9/2016.
 */
public class AddToCartException extends CartException {
  public AddToCartException(String message) {
    super(message);
  }

  public AddToCartException(String message, Throwable cause) {
    super(message, cause);
  }
}
