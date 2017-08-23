package com.sistic.be.cart.model;

import java.io.Serializable;

public class MasterpassPaymentMethod implements Serializable {

	private static final long serialVersionUID = 4075229941675275652L;

	private String cardType;
	private String ccHolderName;
	private String ccNumber;
	private Integer expiredMonth;
	private Integer expiredYear;

	public MasterpassPaymentMethod() {
	}

	public MasterpassPaymentMethod(String cardType, String ccHolderName, String ccNumber, Integer expiredMonth,
			Integer expiredYear) {
		this.cardType = cardType;
		this.ccHolderName = ccHolderName;
		this.ccNumber = ccNumber;
		this.expiredMonth = expiredMonth;
		this.expiredYear = expiredYear;
	}

	public String getCcHolderName() {
		return ccHolderName;
	}

	public void setCcHolderName(String ccHolderName) {
		this.ccHolderName = ccHolderName;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public Integer getExpiredMonth() {
		return expiredMonth;
	}

	public void setExpiredMonth(Integer expiredMonth) {
		this.expiredMonth = expiredMonth;
	}

	public Integer getExpiredYear() {
		return expiredYear;
	}

	public void setExpiredYear(Integer expiredYear) {
		this.expiredYear = expiredYear;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
