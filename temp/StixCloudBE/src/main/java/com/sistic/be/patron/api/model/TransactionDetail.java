package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;

public class TransactionDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 230722498352631147L;
	
	private String transactionRefNumber;
	private String type;
	private OffsetDateTime dateOfPurchase;
	private String purchasedWith;
	private List<TransactionLineItem> lineItems;
	private MonetaryAmount totalAmount;
	

	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}

	public void setTransactionRefNumber(String transactionRefNumber) {
		this.transactionRefNumber = transactionRefNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public OffsetDateTime getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(OffsetDateTime dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public String getPurchasedWith() {
		return purchasedWith;
	}

	public void setPurchasedWith(String purchasedWith) {
		this.purchasedWith = purchasedWith;
	}

	public List<TransactionLineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<TransactionLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public MonetaryAmount getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(MonetaryAmount totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@JsonIgnore
	public String getTotalAmountFormatted() {
		return MoneyUtil.getFormattedMonetaryString(totalAmount);
	}

	@Override
	public String toString() {
		return "TransactionDetail [transactionRefNumber=" + transactionRefNumber + ", type=" + type
				+ ", dateOfPurchase=" + dateOfPurchase + ", purchasedWith=" + purchasedWith + ", lineItems=" + lineItems
				+ ", totalAmount=" + totalAmount + "]";
	}

}
