package com.sistic.be.patron.api.model;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6206302073247504089L;
	
	private String type;
	private String countryCode;
	private String addressLineOne;
	private String addressLineTwo;
	private String addressLineThree;
	private String postalCode;
	
	public Address() {
		super();
	}
	
	public Address(String type, String countryCode, String blockNo, String unitNo, String buildingName, String postalCode) {
		super();
		this.type = type;
		this.countryCode = countryCode;
		this.addressLineOne = blockNo;
		this.addressLineTwo = unitNo;
		this.addressLineThree = buildingName;
		this.postalCode = postalCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getAddressLineThree() {
		return addressLineThree;
	}

	public void setAddressLineThree(String addressLineThree) {
		this.addressLineThree = addressLineThree;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [type=" + type + ", countryCode=" + countryCode + ", addressLineOne=" + addressLineOne
				+ ", addressLineTwo=" + addressLineTwo + ", addressLineThree=" + addressLineThree + ", postalCode="
				+ postalCode + "]";
	}
	
}
