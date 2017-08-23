package com.sistic.be.cart.model;

public class FeeLineItem extends LineItem {
	
	// Static Constants
	public static final String DELIVERYFEE = "DELIVERY";

	/**
	 * 
	 */
	private static final long serialVersionUID = 437836050288366727L;
	
	private String code;
	private String description;
	
	public FeeLineItem(String type) {
		super(type);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
