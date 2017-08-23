package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class CheckCartProduct implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2332413379730237397L;
	
	
	Long productId;
	String productName;
	OffsetDateTime productDate;
	String venue;
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public OffsetDateTime getProductDate() {
		return productDate;
	}
	public void setProductDate(OffsetDateTime productDate) {
		this.productDate = productDate;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	@Override
	public String toString() {
		return "CheckCartProduct [productId=" + productId + ", productName=" + productName + ", productDate="
				+ productDate + ", venue=" + venue + "]";
	}

}
