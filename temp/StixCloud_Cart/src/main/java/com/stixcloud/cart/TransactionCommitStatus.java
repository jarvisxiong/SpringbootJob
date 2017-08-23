package com.stixcloud.cart;

public enum TransactionCommitStatus {
  COMMITTED("Committed", 1),
  PENDING("Pending", 2),
  NOT_COMMITTED("Not Committed", 3);

  String type;

  Integer value;

  /**
   * Instantiates a new transaction commit status.
   * @param type the type
   * @param value the value
   */
  TransactionCommitStatus(String type, Integer value) {
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