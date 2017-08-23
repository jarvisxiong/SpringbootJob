package com.sistic.be.patron.api.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lineOne", "lineTwo", "lineThree", "postalCode", "city", "state", "country" })
public class AddressJson implements Serializable {
	private static final long serialVersionUID = -1010036679489109915L;
	@JsonProperty("lineOne")
	private String lineOne;
	@JsonProperty("lineTwo")
	private String lineTwo;
	@JsonProperty("lineThree")
	private String lineThree;
	@JsonProperty("postalCode")
	private String postalCode;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country")
	private String country;

	public AddressJson() {
	}

	public AddressJson(String lineOne, String lineTwo, String lineThree, String postalCode, String city, String state,
			String country) {
		super();
		this.lineOne = lineOne;
		this.lineTwo = lineTwo;
		this.lineThree = lineThree;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getLineOne() {
		return lineOne;
	}

	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}

	public String getLineTwo() {
		return lineTwo;
	}

	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}

	public String getLineThree() {
		return lineThree;
	}

	public void setLineThree(String lineThree) {
		this.lineThree = lineThree;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		AddressJson that = (AddressJson) o;
		return new EqualsBuilder().append(lineOne, that.getLineOne()).append(lineTwo, that.getLineTwo())
				.append(lineThree, that.getLineThree()).append(postalCode, that.getPostalCode())
				.append(city, that.getCity()).append(state, that.getState()).append(country, that.getCountry())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(lineOne).append(lineTwo).append(lineThree).append(postalCode)
				.append(city).append(state).append(country).toHashCode();
	}

	@Override
	public String toString() {
		return "AddressJson [lineOne=" + lineOne + ", lineTwo=" + lineTwo + ", lineThree=" + lineThree + ", postalCode="
				+ postalCode + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}

}