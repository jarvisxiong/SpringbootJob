package com.stixcloud.patron.constant;

public enum TransactionType {
  PURCHASE("Purchase", 1, true),
  RETURN("Return", 2, false),
  REPLACE("Replace", 3, true),
  RESEND("Resend", 4, false),
  CHANGE_DELIVERY_METHOD("Delivery Method Change", 5,true),
  RESET("Reset", 6, false),
  INVALIDATE("Invalidate", 7, false);

  String type;

  Integer value;

  boolean regenerate;

  /**
   * Instantiates a new transaction type.
   *
   * @param type the type
   * @param value the value
   */
  TransactionType(String type, Integer value, boolean regenerate) {
    this.type = type;
    this.value = value;
    this.regenerate = regenerate;
  }


  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public Integer getValue() {
    return this.value;
  }

  public boolean getRegenerate() {
    return this.regenerate;
  }
}
