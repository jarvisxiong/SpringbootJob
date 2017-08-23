package com.sistic.be.cart.api.model;

import java.io.Serializable;

public class SolicitationUpdateFlags implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5314841699972989539L;
	
	private boolean receiveSistic;
	private boolean receivePromoter;
	private boolean receiveVenue;
	private boolean noNotSignUp;
	
	
	public boolean isReceiveSistic() {
		return receiveSistic;
	}
	public void setReceiveSistic(boolean receiveSistic) {
		this.receiveSistic = receiveSistic;
	}
	public boolean isReceivePromoter() {
		return receivePromoter;
	}
	public void setReceivePromoter(boolean receivePromoter) {
		this.receivePromoter = receivePromoter;
	}
	public boolean isReceiveVenue() {
		return receiveVenue;
	}
	public void setReceiveVenue(boolean receiveVenue) {
		this.receiveVenue = receiveVenue;
	}
	public boolean isNoNotSignUp() {
		return noNotSignUp;
	}
	public void setNoNotSignUp(boolean noNotSignUp) {
		this.noNotSignUp = noNotSignUp;
	}
	
	@Override
	public String toString() {
		return "SolicitationUpdateFlags [receiveSistic=" + receiveSistic + ", receivePromoter=" + receivePromoter
				+ ", receiveVenue=" + receiveVenue + ", noNotSignUp=" + noNotSignUp + "]";
	}

}
