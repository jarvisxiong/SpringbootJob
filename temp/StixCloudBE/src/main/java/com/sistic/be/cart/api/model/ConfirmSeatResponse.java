package com.sistic.be.cart.api.model;

import java.time.OffsetDateTime;

public class ConfirmSeatResponse extends JsonResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5870072171759111989L;
	
	private OffsetDateTime reservedTime;

	public OffsetDateTime getReservedTime() {
		return reservedTime;
	}

	public void setReservedTime(OffsetDateTime reservedTime) {
		this.reservedTime = reservedTime;
	}

	@Override
	public String toString() {
		return "ConfirmSeatResponse [reservedTime=" + reservedTime + "]";
	}
	
}
