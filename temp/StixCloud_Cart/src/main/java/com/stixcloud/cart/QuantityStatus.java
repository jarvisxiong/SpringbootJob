package com.stixcloud.cart;

/**
 * Created by chongye on 30/9/2016.
 */
public enum QuantityStatus {
  EXACT(1),
  MINIMUM(2);

  private Integer value;

  private QuantityStatus(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

}
