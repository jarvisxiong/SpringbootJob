package com.sistic.be.patron.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordResetForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1329553123207298015L;
	
	@NotEmpty
	private String email;
	@NotEmpty
	private String token;
	@NotEmpty
	private String password;
	@NotEmpty
	private String confirmPwd;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	
	@Override
	public String toString() {
		return "PasswordResetForm [email=" + email + ", token=" + token + "]";
	}

}
