package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class MembershipProfileConfigList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826076370028311382L;
	
	private List<ProfileConfig> profileConfig;
	
	public List<ProfileConfig> getProfileConfig() {
		return profileConfig;
	}

	public void setProfileConfig(List<ProfileConfig> profileConfig) {
		this.profileConfig = profileConfig;
	}

	@Override
	public String toString() {
		return "MembershipProfileConfigList [profileConfig=" + profileConfig 
				 + "]";
	}

}
