package com.sistic.be.cart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConfirmSeat implements Serializable {

	private static final long serialVersionUID = -2882784050400416409L;
	
	private List<Long> releasedSeatList;
	private List<Long> reservedSeatList;
	
	// Default Constructor
	public ConfirmSeat() {
		this.reservedSeatList = new ArrayList<Long>();
		this.releasedSeatList = new ArrayList<Long>();
	}

	public ConfirmSeat(List<Long> reservedSeatList, List<Long> releasedSeatList) {
		this.reservedSeatList = reservedSeatList;
		this.releasedSeatList = releasedSeatList;
	}

	public List<Long> getReleasedSeatList() {
		return releasedSeatList;
	}

	public void setReleasedSeatList(List<Long> releasedSeatList) {
		this.releasedSeatList = releasedSeatList;
	}

	public List<Long> getReservedSeatList() {
		return reservedSeatList;
	}

	public void setReservedSeatList(List<Long> reservedSeatList) {
		this.reservedSeatList = reservedSeatList;
	}

	@Override
	public String toString() {
		return "ConfirmSeat [releasedSeatList=" + releasedSeatList + ", reservedSeatList=" + reservedSeatList + "]";
	}

}
