package com.sistic.be.patron.api.model;

import java.io.Serializable;

public class PatronIdentification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4667580843257833300L;
	
	private String type;
	private String no;
	
	public PatronIdentification() {
		super();
	}
	
	public PatronIdentification(String type, String no) {
		this.type = type;
		this.no = no;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PatronIdentification [type=").append(type).append(", no=").append(no).append("]");
		return builder.toString();
	}

}
