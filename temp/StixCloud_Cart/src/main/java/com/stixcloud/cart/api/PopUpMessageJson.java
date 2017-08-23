package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.money.MonetaryAmount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bookingFee",
    "message"
})
public class PopUpMessageJson {
  @JsonProperty("bookingFee")
  private MonetaryAmount bookingFee;
  private String message;

  public PopUpMessageJson(MonetaryAmount bookingFee, String message) {
    this.bookingFee = bookingFee;
    this.message = message;
  }

  @JsonProperty("bookingFee")
  public MonetaryAmount getBookingFee() {
    return bookingFee;
  }

  public void setBookingFee(MonetaryAmount bookingFee) {
    this.bookingFee = bookingFee;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


}
