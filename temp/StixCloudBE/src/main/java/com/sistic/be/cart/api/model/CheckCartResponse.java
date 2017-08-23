package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.List;

public class CheckCartResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7623667728201224757L;
	
	private List<CheckCartLineItem> lineItemList;

	public List<CheckCartLineItem> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<CheckCartLineItem> lineItemList) {
		this.lineItemList = lineItemList;
	} 

}
