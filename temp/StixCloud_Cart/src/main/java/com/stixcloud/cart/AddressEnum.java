package com.stixcloud.cart;

/**
 * Created by sengkai on 12/22/2016.
 */
public enum AddressEnum {
  BILLING("Billing Address", "BILLING"), MAILING("Mailing Address", "MAILING"),
  SINGAPORE("SG", "SG");

  String description;
  String BEDescription;

  /**
   * Instantiates a new address type.
   * @param description the description
   */
  private AddressEnum(String description, String BEDescription) {

    this.description = description;
    this.BEDescription = BEDescription;
  }

  /**
   * Gets the description.
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  public String getBEDescription() {
    return BEDescription;
  }
}
