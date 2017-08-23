package com.sistic.be.cart.model;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;

public class ProductLineItem extends LineItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2355707663303368379L;
	
	private String cartItemId;
	private String icc;
	private ProductItemInfo product;
	private PriceClassInfo priceclass;
	private MonetaryAmount bookingFee;
	
	public ProductLineItem() {
		super("PRODUCT");
	}
	
	@JsonIgnore
	public String getBookingFeeFormatted() {
		return MoneyUtil.getFormattedMonetaryString(bookingFee);
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public String getIcc() {
		return icc;
	}

	public void setIcc(String icc) {
		this.icc = icc;
	}

	public ProductItemInfo getProduct() {
		return product;
	}

	public void setProduct(ProductItemInfo product) {
		this.product = product;
	}

	public PriceClassInfo getPriceclass() {
		return priceclass;
	}

	public void setPriceclass(PriceClassInfo priceclass) {
		this.priceclass = priceclass;
	}

	public MonetaryAmount getBookingFee() {
		return bookingFee;
	}

	public void setBookingFee(MonetaryAmount bookingFee) {
		this.bookingFee = bookingFee;
	}

	@Override
	public String toString() {
		return "ProductLineItem [cartItemId=" + cartItemId + ", icc=" + icc + ", product=" + product + ", priceclass="
				+ priceclass + ", bookingFee=" + bookingFee + "]";
	}

}
