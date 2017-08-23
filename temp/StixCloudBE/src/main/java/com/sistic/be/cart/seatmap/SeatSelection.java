/**
 * 
 */
package com.sistic.be.cart.seatmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jianhong
 *
 */
public class SeatSelection implements Serializable {

	private static final long serialVersionUID = -2163398466252953004L;
	
	public SeatSelection() {
		this.seats= new ArrayList<Seat>();
	}
	
	private List<Seat> seats;
	
	// Helper
	@JsonIgnore
	public List<Long> getInventoryIds() {
		List<Long> inventoryIds = new ArrayList<Long>();
		for (Seat seat : seats) {
			inventoryIds.add(seat.getInventoryId());
		}
		return inventoryIds;
	}

	// Getter Setter
	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "SeatSelection [seats=" + seats + "]";
	}
	
}
