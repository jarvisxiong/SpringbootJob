package com.sistic.be.cart.api.model;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
		this.message = message;
		this.message = message;
	}
	
	public PopUpMessageJson(){
		
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
