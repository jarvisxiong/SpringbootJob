package com.sistic.be.cart.api.model;

import java.io.Serializable;

public class CheckCartLineItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1034520586066466479L;
	
	
	CheckCartProduct product;
	Integer quantity;
	
	
	public CheckCartProduct getProduct() {
		return product;
	}
	public void setProduct(CheckCartProduct product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public String toString() {
		return "CheckCartLineItem [product=" + product + ", quantity=" + quantity + "]";
	}
	
}
