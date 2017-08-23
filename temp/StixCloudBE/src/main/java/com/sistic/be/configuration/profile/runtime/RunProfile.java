package com.sistic.be.configuration.profile.runtime;

import java.io.Serializable;

public class RunProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3700321281486575541L;
	
	private String profile;
	
	public RunProfile() {}
	
	public RunProfile(String profile) {
		this.profile = profile;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "PaymentProfile [profile=" + profile + "]";
	}
	
}
