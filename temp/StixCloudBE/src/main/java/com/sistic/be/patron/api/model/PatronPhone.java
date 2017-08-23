package com.sistic.be.patron.api.model;

import java.io.Serializable;

public class PatronPhone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3630609681991433913L;
	
	private String contactType;
	private CountryCode country;
	private String phoneNumber;
	
	public PatronPhone() {
		this.country = new CountryCode();
	}
	
	public PatronPhone(String contactType, CountryCode country, String phoneNumber) {
		this.contactType = contactType;
		this.country = country;
		this.phoneNumber = phoneNumber;
	}
	
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public CountryCode getCountry() {
		return country;
	}
	public void setCountry(CountryCode country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PatronPhone [contactType=").append(contactType).append(", country=").append(country)
				.append(", phoneNumber=").append(phoneNumber).append("]");
		return builder.toString();
	}

}
