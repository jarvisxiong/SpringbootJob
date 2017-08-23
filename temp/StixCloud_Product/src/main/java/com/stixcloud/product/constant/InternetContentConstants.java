package com.stixcloud.product.constant;

public enum InternetContentConstants {
  AVAILABLE(19),
  SOLD_OUT(16),
  SINGLE_SEAT(17),
  LIMITED_SEAT(18),
  SHOW_RECONFIG(-6),
  SHOW_CANCELLED(-7),
  SHOW_POSTPONED(-8);
  
  int value;
  
  InternetContentConstants(int value) {
    this.value = value;
  }

  /**
   * @return the value
   */
  public int getValue() {
    return value;
  }
  
  
}
