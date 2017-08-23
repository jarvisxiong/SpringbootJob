package com.sistic.be.cart.api.model;

import java.io.Serializable;

import javax.money.MonetaryAmount;

public class EvoucherValidation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4968009907518126684L;
	
	private String eVoucherID;
	private MonetaryAmount redeemValue;
	private String evoucherType;
	private Boolean validationResult;
	private String validationConstant;
	
	
	public String geteVoucherID() {
		return eVoucherID;
	}
	public void seteVoucherID(String eVoucherID) {
		this.eVoucherID = eVoucherID;
	}
	public MonetaryAmount getRedeemValue() {
		return redeemValue;
	}
	public void setRedeemValue(MonetaryAmount redeemValue) {
		this.redeemValue = redeemValue;
	}
	public String getEvoucherType() {
		return evoucherType;
	}
	public void setEvoucherType(String evoucherType) {
		this.evoucherType = evoucherType;
	}
	public Boolean getValidationResult() {
		return validationResult;
	}
	public void setValidationResult(Boolean validationResult) {
		this.validationResult = validationResult;
	}
	public String getValidationConstant() {
		return validationConstant;
	}
	public void setValidationConstant(String validationConstant) {
		this.validationConstant = validationConstant;
	}
	@Override
	public String toString() {
		return "EvoucherValidation [eVoucherID=" + eVoucherID + ", evoucherType=" + evoucherType + ", validationResult="
				+ validationResult + ", validationConstant=" + validationConstant + "]";
	}

}
