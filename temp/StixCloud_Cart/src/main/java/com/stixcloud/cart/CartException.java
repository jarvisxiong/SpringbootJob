package com.stixcloud.cart;

/**
 * Created by chongye on 30/9/2016.
 */
public class CartException extends Exception {
  public CartException(String message) {
    super(message);
  }

  public CartException(String message, Throwable cause) {
    super(message, cause);
  }
}
