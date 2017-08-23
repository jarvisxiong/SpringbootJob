package com.sistic.be.cart.seatmap;

import java.io.Serializable;

public class Seat implements Serializable {
	
	private Long inventoryId;
	@Deprecated private String seatRowAlias;
	@Deprecated private String seatAlias;
	@Deprecated private String seatSectionAlias;
	@Deprecated private int seatType;
	
	public Seat() {
		
	}
	
	public Seat(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
	
	public Long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getSeatRowAlias() {
		return seatRowAlias;
	}
	public void setSeatRowAlias(String seatRowAlias) {
		this.seatRowAlias = seatRowAlias;
	}
	public String getSeatAlias() {
		return seatAlias;
	}
	public void setSeatAlias(String seatAlias) {
		this.seatAlias = seatAlias;
	}
	public String getSeatSectionAlias() {
		return seatSectionAlias;
	}
	public void setSeatSectionAlias(String seatSectionAlias) {
		this.seatSectionAlias = seatSectionAlias;
	}
	public int getSeatType() {
		return seatType;
	}
	public void setSeatType(int seatType) {
		this.seatType = seatType;
	}

	@Override
	public String toString() {
		return "Seat [inventoryId=" + inventoryId + ", seatRowAlias=" + seatRowAlias + ", seatAlias=" + seatAlias
				+ ", seatSectionAlias=" + seatSectionAlias + ", seatType=" + seatType + "]";
	}
	
}
