/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;

/**
 * @author jianhong
 *
 */
public abstract class LineItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2914587134772128484L;
	
	private String type;
	private int quantity;
	private MonetaryAmount unitPrice;
	private MonetaryAmount subTotal;
	
	public LineItem(String type) {
		this.type = type;
	}
	
	@JsonIgnore
	public String getUnitPriceFormatted() {
		return MoneyUtil.getFormattedMonetaryString(unitPrice);
	}
	
	@JsonIgnore
	public String getSubTotalFormatted() {
		return MoneyUtil.getFormattedMonetaryString(subTotal);
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
	    this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MonetaryAmount getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(MonetaryAmount unitPrice) {
		this.unitPrice = unitPrice;
	}

	public MonetaryAmount getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(MonetaryAmount subTotal) {
		this.subTotal = subTotal;
	}

	@Override
	public String toString() {
		return "LineItem [type=" + type + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", subTotal="
				+ subTotal + "]";
	}

}
