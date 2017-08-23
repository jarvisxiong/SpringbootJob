package com.stixcloud.product.api;

public enum SeatSelectionType {

  //    Self-pick
  SELF_PICK("SP"),

  //    Best Available
  BEST_AVAILABLE("BA"),

  //    Hot Show
  HOT_SHOW("HS");

  String seatSelectionValue;

  SeatSelectionType(String seatSelectionValue) {
    this.seatSelectionValue = seatSelectionValue;
  }

  public String getSeatSelectionValue() {
    return this.seatSelectionValue;
  }

}
