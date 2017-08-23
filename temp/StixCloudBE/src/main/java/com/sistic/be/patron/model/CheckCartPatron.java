package com.sistic.be.patron.model;

import java.io.Serializable;

public class CheckCartPatron implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8221182957895563965L;
	
	private String firstName;
	private String lastName;
	
	public CheckCartPatron() {
		super();
	}
	
	public CheckCartPatron(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	@Override
	public String toString() {
		return "CheckCartPatron [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
