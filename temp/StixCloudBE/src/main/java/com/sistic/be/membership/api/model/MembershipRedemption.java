package com.sistic.be.membership.api.model;

import java.io.Serializable;

public class MembershipRedemption implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7827076350028311392L;
	
	private String type;
	private String alias;
	private String current;
	private String redeem;

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

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getRedeem() {
		return redeem;
	}

	public void setRedeem(String redeem) {
		this.redeem = redeem;
	}

	@Override
	public String toString() {
		return "MembershipRedemption [type=" + type + ", alias=" + alias + ", current=" + current + ", redeem=" + redeem
				+ "]";
	}	
}
