package com.sistic.be.sales.order;

import java.io.Serializable;

public class TransactionOrderDeliveryDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707049010566563387L;
	
	private String deliveryType;
	
	public TransactionOrderDeliveryDetails() {}
	
	public TransactionOrderDeliveryDetails(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	@Override
	public String toString() {
		return "TransactionOrderDeliveryDetails [deliveryType=" + deliveryType + "]";
	}
	
}
