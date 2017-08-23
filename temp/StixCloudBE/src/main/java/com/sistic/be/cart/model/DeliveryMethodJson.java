package com.sistic.be.cart.model;

import java.io.Serializable;
import java.util.List;

import javax.money.MonetaryAmount;

public class DeliveryMethodJson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8484847599154845693L;
	
	private List<MonetaryAmount> charges;

	public List<MonetaryAmount> getCharges() {
		return charges;
	}

	public void setCharges(List<MonetaryAmount> charges) {
		this.charges = charges;
	}

	@Override
	public String toString() {
		return String.format("DeliveryMethodJson [charges=%s]", charges);
	}
	
}
