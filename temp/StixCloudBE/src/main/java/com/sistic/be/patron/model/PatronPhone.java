package com.sistic.be.patron.model;

import java.io.Serializable;

@Deprecated
public class PatronPhone implements Serializable {
	
	private String phoneAreaCode;
	private String phoneCountryCode;
	private String phoneNumber;
	
	private String type;	// "Home", "Office", "Mobile", "Fax", "Pager"
	private boolean isPrimary;
	
	// Getter Setter
	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}
	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}
	public String getPhoneCountryCode() {
		return phoneCountryCode;
	}
	public void setPhoneCountryCode(String phoneCountryCode) {
		this.phoneCountryCode = phoneCountryCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	

}
