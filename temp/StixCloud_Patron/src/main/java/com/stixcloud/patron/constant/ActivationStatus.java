package com.stixcloud.patron.constant;

public enum ActivationStatus {

  ACTIVE("Activated", 1), INACTIVE("Deactivated", 2), NONE("None", 3), DELETED("Deleted", 4);

  public final static int STATUS_DEFAULT = 1;

  String activationstatus;

  int status;

  /**
   * Gets the status.
   *
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * Gets the status as long.
   *
   * @return the status as long
   */
  public Long getStatusAsLong() {
    return new Long(status);
  }

  /**
   * Gets the activationstatus.
   *
   * @return the activationstatus
   */
  public String getActivationstatus() {
    return activationstatus;
  }


  /**
   * Instantiates a new activation status.
   *
   * @param activationstatus the activationstatus
   * @param status the status
   */
  ActivationStatus(String activationstatus, int status) {
    this.activationstatus = activationstatus;
    this.status = status;
  }
}
