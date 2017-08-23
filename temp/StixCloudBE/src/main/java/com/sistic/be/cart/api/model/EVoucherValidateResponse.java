package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.List;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EVoucherValidateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7419197731735102187L;
	
	private Boolean exceedMaxQtyAllowed;
	private List<EvoucherValidation> validationResultList;
	private MonetaryAmount totalRedeemValue;
	
	
	public Boolean getExceedMaxQtyAllowed() {
		return exceedMaxQtyAllowed;
	}
	public void setExceedMaxQtyAllowed(Boolean exceedMaxQtyAllowed) {
		this.exceedMaxQtyAllowed = exceedMaxQtyAllowed;
	}
	public List<EvoucherValidation> getValidationResultList() {
		return validationResultList;
	}
	public void setValidationResultList(List<EvoucherValidation> validationResultList) {
		this.validationResultList = validationResultList;
	}
	public MonetaryAmount getTotalRedeemValue() {
		return totalRedeemValue;
	}
	public void setTotalRedeemValue(MonetaryAmount totalRedeemValue) {
		this.totalRedeemValue = totalRedeemValue;
	}
	
	/**
	 * @return true if all evouchers were validated success
	 */
	@JsonIgnore
	public boolean allRulesPassed() {
		if (exceedMaxQtyAllowed) {
			return false;
		}
		else {
			for (EvoucherValidation result : validationResultList) {
				if (result.getValidationResult().equals(false)) {
					return false;
				}
			}
			return true;
		}
	}
	
	@Override
	public String toString() {
		return "EVoucherValidateResponse [exceedMaxQtyAllowed=" + exceedMaxQtyAllowed + ", validationResultList="
				+ validationResultList + ", totalRedeemValue=" + totalRedeemValue + "]";
	}

}
