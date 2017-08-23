package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;

public class TransactionLineItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5430364668446614017L;
	
	private String type;		// eg PRODUCT or DELIVERYMETHOD
	private TransactionProduct product;
	private String name;		// Used for DELIVERYMETHOD type
	private String code;		// Used for DELIVERYMETHOD type
	private String description;	// Used for PRODUCT type
	private String productName;
	private Integer quantity;
	private MonetaryAmount unitPrice;
	private MonetaryAmount bookingFee;
	private MonetaryAmount subTotal;
	private AddressJson mailingAddress;
	
	public static final Comparator<TransactionLineItem> transactionLineItemComparator = new TransactionLineItemComparator();

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public TransactionProduct getProduct() {
		return product;
	}
	public void setProduct(TransactionProduct product) {
		this.product = product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProductName() {
		return productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public MonetaryAmount getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(MonetaryAmount unitPrice) {
		this.unitPrice = unitPrice;
	}
	public MonetaryAmount getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(MonetaryAmount bookingFee) {
		this.bookingFee = bookingFee;
	}
	public MonetaryAmount getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(MonetaryAmount subTotal) {
		this.subTotal = subTotal;
	}
	public AddressJson getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(AddressJson mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	@JsonIgnore
	public String getUnitPriceFormatted() {
		return MoneyUtil.getFormattedMonetaryString(unitPrice);
	}
	@JsonIgnore
	public String getBookingFeeFormatted() {
		return MoneyUtil.getFormattedMonetaryString(bookingFee);
	}
	@JsonIgnore
	public String getSubTotalFormatted() {
		return MoneyUtil.getFormattedMonetaryString(subTotal);
	}
	
	public static class TransactionLineItemComparator implements Comparator<TransactionLineItem> {

		@Override
		public int compare(TransactionLineItem o1, TransactionLineItem o2) {
			if (o1.type.equals(o2.type)) {
				return 0;
			} else if (o1.type.equals("PRODUCT")) {
				return -1;
			} else {
				return 1;
			}
		}

	}

	@Override
	public String toString() {
		return "TransactionLineItem [type=" + type + ", product=" + product + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", productName=" + productName + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + ", bookingFee=" + bookingFee + ", subTotal=" + subTotal
				+ ", mailingAddress=" + mailingAddress + "]";
	}
	
}
