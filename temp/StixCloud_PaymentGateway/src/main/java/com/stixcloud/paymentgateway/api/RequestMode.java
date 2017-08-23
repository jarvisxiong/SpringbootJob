package com.stixcloud.paymentgateway.api;

public enum RequestMode {
  PRECOMMIT("PRECOMMIT"), COMMMIT("COMMIT");

  private String mode;

  RequestMode(String mode) {
    this.mode = mode;
  }
}