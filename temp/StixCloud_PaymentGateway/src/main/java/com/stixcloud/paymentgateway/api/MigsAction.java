package com.stixcloud.paymentgateway.api;

public enum MigsAction {
  PURCHASE("pay"),
  VOID("voidPurchase"),
  REFUND("refund");

  private String action;

  MigsAction(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
}