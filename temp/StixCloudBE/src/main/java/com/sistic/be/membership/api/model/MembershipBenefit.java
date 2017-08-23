package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class MembershipBenefit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826076370028311382L;
	
	private String type;
	private String alias;
	private Integer quantity;
	private String validity;
	private Double award;
	private Double redeem;
	private Double balance;
	private String evoucherId;
	private Double value;
	private Boolean redeemStatus;
	private OffsetDateTime expiry;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Double getAward() {
		return award;
	}

	public void setAward(Double award) {
		this.award = award;
	}

	public Double getRedeem() {
		return redeem;
	}

	public void setRedeem(Double redeem) {
		this.redeem = redeem;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getEvoucherId() {
		return evoucherId;
	}

	public void setEvoucherId(String evoucherId) {
		this.evoucherId = evoucherId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getRedeemStatus() {
		return redeemStatus;
	}

	public void setRedeemStatus(Boolean redeemStatus) {
		this.redeemStatus = redeemStatus;
	}

	public OffsetDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(OffsetDateTime expiry) {
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return "MembershipBenefit [type=" + type + ", alias=" + alias + ", quantity=" + quantity + ", validity="
				+ validity + ", award=" + award + ", redeem=" + redeem + ", balance=" + balance + ", evoucherId="
				+ evoucherId + ", value=" + value + ", redeemStatus=" + redeemStatus + ", expiry=" + expiry + "]";
	}

}
