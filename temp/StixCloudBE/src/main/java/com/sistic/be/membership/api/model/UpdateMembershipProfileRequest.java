package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UpdateMembershipProfileRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3417019585988620802L;

	private Long organizationId;
	List<MembershipProfile> profiles;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<MembershipProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<MembershipProfile> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfiles(MembershipProfile profile) {
		if (this.profiles == null) {
			this.profiles = new ArrayList<MembershipProfile>();
		}
		this.profiles.add(profile);
	}

	@Override
	public String toString() {
		return "UpdateMembershipProfileRequest [organizationId=" + organizationId + ", profiles=" + profiles
				 + "]";
	}

}
