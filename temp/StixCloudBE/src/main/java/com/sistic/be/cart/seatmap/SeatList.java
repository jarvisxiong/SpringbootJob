package com.sistic.be.cart.seatmap;

import java.io.Serializable;
import java.util.List;

@Deprecated
public class SeatList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4098858944700605791L;
	private List<Long> reservedSeatList;
	private List<Long> releasedSeatList;
	
	public List<Long> getReservedSeatList() {
		return reservedSeatList;
	}
	public void setReservedSeatList(List<Long> reservedSeatList) {
		this.reservedSeatList = reservedSeatList;
	}
	public List<Long> getReleasedSeatList() {
		return releasedSeatList;
	}
	public void setReleasedSeatList(List<Long> releasedSeatList) {
		this.releasedSeatList = releasedSeatList;
	}
	
	

}
