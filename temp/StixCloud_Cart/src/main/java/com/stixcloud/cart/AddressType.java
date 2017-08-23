package com.stixcloud.cart;

public enum AddressType {
  BILLING("Billing Address"), MAILING("Mailing Address");

  String description;

  /**
   * Instantiates a new address type.
   * @param description the description
   */
  private AddressType(String description) {
    this.description = description;
  }

  /**
   * Gets the description.
   * @return the description
   */
  public String getDescription() {
    return description;
  }
}