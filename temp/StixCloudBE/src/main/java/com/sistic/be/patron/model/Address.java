package com.sistic.be.patron.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Deprecated
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3781203218013978540L;
	private String city;
	private String country;
	private String countrySubdivision;
	private String line1;
	private String line2;
	private String line3;
	private String postalCode;
	
	private String type;	//MAILING or BILLING
	private Boolean isPrimary;	// Primary address for the Patron
	// In Stix (not cloud), primary is set as the mailing address. In stixcloud, primary and mailing can be separate address
	
	// Getter Setter
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountrySubdivision() {
		return countrySubdivision;
	}
	public void setCountrySubdivision(String countrySubdivision) {
		this.countrySubdivision = countrySubdivision;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("isPrimary")
	public Boolean isPrimary() {
		return isPrimary;
	}
	@JsonProperty("isPrimary")
	public void setPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", countrySubdivision=" + countrySubdivision
				+ ", line1=" + line1 + ", line2=" + line2 + ", line3=" + line3 + ", postalCode=" + postalCode
				+ ", type=" + type + ", isPrimary=" + isPrimary + "]";
	}
}
