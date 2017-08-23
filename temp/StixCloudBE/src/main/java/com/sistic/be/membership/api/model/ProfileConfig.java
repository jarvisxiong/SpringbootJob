package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class ProfileConfig implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826076370028311382L;
	
	private String key;
	private List<MembershipConfigProfile> profiles;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<MembershipConfigProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<MembershipConfigProfile> profiles) {
		this.profiles = profiles;
	}

	@Override
	public String toString() {
		return "MembershipInfo [key=" + key + ", profiles=" + profiles
				 + "]";
	}

}
