package com.sistic.be.cart.model;

import java.io.Serializable;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.sistic.be.app.util.MoneyUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryMethod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7468089394991498148L;

	private String code;
	private String alias;
	private String description;
	private String deliveryType;
	private MonetaryAmount charge;
	private Integer order;	// default 0
	private Boolean isAddressRequired;
	@Deprecated
	private Boolean isMobileNumberRequired;
	private Boolean isFeeWaived;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public MonetaryAmount getCharge() {
		return charge;
	}
	public void setCharge(MonetaryAmount charge) {
		this.charge = charge;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Boolean getIsAddressRequired() {
		return isAddressRequired;
	}
	@JsonSetter("addressRequired")
	public void setIsAddressRequired(Boolean isAddressRequired) {
		this.isAddressRequired = isAddressRequired;
	}
	public Boolean getIsMobileNumberRequired() {
		return isMobileNumberRequired;
	}
	public void setIsMobileNumberRequired(Boolean isMobileNumberRequired) {
		this.isMobileNumberRequired = isMobileNumberRequired;
	}
	public Boolean getIsFeeWaived() {
		return isFeeWaived;
	}
	@JsonSetter("feeWaived")
	public void setIsFeeWaived(Boolean isFeeWaived) {
		this.isFeeWaived = isFeeWaived;
	}

	@JsonIgnore
	public String getChargeValue() {
		return MoneyUtil.getFormattedMonetaryString(this.charge);
	}

}
