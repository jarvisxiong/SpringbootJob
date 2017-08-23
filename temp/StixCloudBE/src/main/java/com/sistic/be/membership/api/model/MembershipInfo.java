package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class MembershipInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826076370028311382L;
	
	private Long organizationId; //deegix-test to be removed
	private String organizationAlias; //deegix-test to be removed
	//private MembershipProfileConfigList profiles;
	private List<MembershipPatron> membershipPatron;
	
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationAlias() {
		return organizationAlias;
	}

	public void setOrganizationAlias(String organizationAlias) {
		this.organizationAlias = organizationAlias;
	}

	/*public MembershipProfileConfigList getProfiles() {
		return profiles;
	}

	public void setProfiles(MembershipProfileConfigList profiles) {
		this.profiles = profiles;
	}*/

	public List<MembershipPatron> getMembershipPatron() {
		return membershipPatron;
	}

	public void setMembershipPatron(List<MembershipPatron> membershipPatron) {
		this.membershipPatron = membershipPatron;
	}

	@Override
	public String toString() {
		return "MembershipInfo [organizationId=" + organizationId + ", organizationAlias=" + organizationAlias
				+ ", membershipPatron=" + membershipPatron + "]";
	}


}
