package com.stixcloud.cart;

public enum SalesStatus {

  AVAILABLE("Available", 0),
  CANCELLED("Cancelled", 1),
  POSTPONED("Postponed", 2),
  RECONFIGURE("Reconfigure", 3),
  NOT_IN_USE("Not in use", 4);

  public final static int STATUS_DEFAULT = 0;

  String status;
  int value;

  /**
   * Gets the status.
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Gets the value.
   * @return the value
   */
  public Integer getValue() {
    return value;
  }

  /**
   * Gets the value as long.
   * @return the value as long
   */
  public Long getValueAsLong() {
    return (long) value;
  }

  /**
   * Instantiates a new sales status.
   * @param status the status
   * @param value the value
   */
  SalesStatus(String status, int value) {
    this.status = status;
    this.value = value;
  }
}
