package com.sistic.be.patron.api.model;

import java.io.Serializable;

public class CountryCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5172690498982463767L;
	
	private String code;
	private Short callingCode;
	
	public CountryCode() {
		super();
	}
	
	public CountryCode(String code, Short callingCode) {
		this.code = code;
		this.callingCode = callingCode;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Short getCallingCode() {
		return callingCode;
	}
	public void setCallingCode(Short callingCode) {
		this.callingCode = callingCode;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountryCode [code=").append(code).append(", callingCode=").append(callingCode).append("]");
		return builder.toString();
	}

}
