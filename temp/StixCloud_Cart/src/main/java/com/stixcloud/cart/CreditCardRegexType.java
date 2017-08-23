package com.stixcloud.cart;

/**
 * Created by chongye on 28/11/2016.
 */
public enum CreditCardRegexType {
  TYPE_D("d", 1);

  private String type;
  private int value;

  /**
   * Instantiates a new credit card regex type.
   * @param type the type
   * @param value the value
   */
  CreditCardRegexType(String type, int value) {
    this.type = type;
    this.value = value;
  }

  /**
   * Gets the type.
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the value.
   * @return the value
   */
  public int getValue() {
    return value;
  }

  /**
   * Get the type of regex based on the value (taken from DB).
   * @param value the value
   * @return the regex type
   */
  public static String getRegexType(int value) {
    for (CreditCardRegexType regexType : CreditCardRegexType.values()) {
      if (regexType.getValue() == value) {
        return regexType.getType();
      }
    }
    return "";
  }
}
