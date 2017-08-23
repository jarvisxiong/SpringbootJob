package com.sistic.be.countries;

import java.io.Serializable;

public class Country implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2793566128294503407L;
	
	private String countryCode;
	private String countryName;
	private String ISO3Country;
	private String countryCallingCode;
	
	public Country() {
		super();
	}
	
	public Country(String countryCode, String countryName, String ISO3Country) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.ISO3Country = ISO3Country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getISO3Country() {
		return ISO3Country;
	}

	public void setISO3Country(String iSO3Country) {
		ISO3Country = iSO3Country;
	}

	public String getCountryCallingCode() {
		return countryCallingCode;
	}

	public void setCountryCallingCode(String countryCallingCode) {
		this.countryCallingCode = countryCallingCode;
	}

	@Override
	public String toString() {
		return "Country [countryCode=" + countryCode + ", countryName=" + countryName + ", ISO3Country=" + ISO3Country
				+ ", countryCallingCode=" + countryCallingCode + "]";
	}

}
