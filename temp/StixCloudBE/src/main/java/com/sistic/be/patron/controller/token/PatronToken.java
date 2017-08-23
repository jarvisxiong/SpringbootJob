package com.sistic.be.patron.controller.token;

import java.io.Serializable;

public class PatronToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 776692311381595770L;
	
	private String patronName;
	private String firstName;
	private String lastName;
	private String token;
	
	public PatronToken() {
		
	}
	
	public PatronToken(String firstName, String lastName, String patronName, String token) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronName = patronName;
		this.token = token;
	}

	public String getPatronName() {
		return patronName;
	}

	public void setPatronName(String patronName) {
		this.patronName = patronName;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
