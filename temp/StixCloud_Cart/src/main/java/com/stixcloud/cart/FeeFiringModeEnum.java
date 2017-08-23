package com.stixcloud.cart;

/**
 * Created by sengkai on 12/15/2016.
 */
public enum FeeFiringModeEnum {
  NOT_FIRED("Not Fired", 1),
  FIRED("Fired", 2),
  IN_PROGRESS("In Progress", 3),;

  String type;

  Integer value;

  /**
   * Instantiates a new fee firing mode.
   * @param type the type
   * @param value the value
   */
  FeeFiringModeEnum(String type, Integer value) {
    this.type = type;
    this.value = value;
  }

  /**
   * Gets the value.
   * @return the value
   */
  public Integer getValue() {
    return this.value;
  }

  /**
   * Gets the type.
   * @return the type
   */
  public String getType() {
    return this.type;
  }
}
