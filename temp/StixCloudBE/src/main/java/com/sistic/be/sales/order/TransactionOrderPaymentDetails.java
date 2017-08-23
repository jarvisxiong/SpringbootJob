package com.sistic.be.sales.order;

import java.io.Serializable;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.app.util.PaymentUtil;

public class TransactionOrderPaymentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2927771175388966866L;

	private String paymentType;
	private MonetaryAmount subAmount;
	
	public TransactionOrderPaymentDetails() {}
	
	public TransactionOrderPaymentDetails (String paymentType, MonetaryAmount subAmount) {
		this.paymentType = paymentType;
		this.subAmount = subAmount;
	}
	
	@JsonIgnore
	public String getPaymentTypeFormatted() {
		return PaymentUtil.getOrderPaymentDisplayType(this.paymentType);
	}
	
	@JsonIgnore
	public CurrencyUnit getCurrencyUnit() {
		return subAmount.getCurrency();
	}
	
	@JsonIgnore
	public String getPaymentPriceFormatted() {
		return MoneyUtil.getFormattedMonetaryString(subAmount);
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public MonetaryAmount getSubAmount() {
		return subAmount;
	}
	public void setSubAmount(MonetaryAmount subAmount) {
		this.subAmount = subAmount;
	}
	
	@Override
	public String toString() {
		return "TransactionOrderPaymentDetails [paymentType=" + paymentType + ", subAmount=" + subAmount + "]";
	}
	
}
