package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class MembershipInfoDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826076370028311382L;
	
	private Long organizationId;
	private String organizationAlias;
	private List<ProfileConfig> profileConfig;
	private List<MembershipPatron> membershipPatron;
	private List<MembershipBenefit> membershipInfoBenefit;
	
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

	public List<ProfileConfig> getProfileConfig() {
		return profileConfig;
	}

	public void setProfileConfig(List<ProfileConfig> profileConfig) {
		this.profileConfig = profileConfig;
	}

	public List<MembershipPatron> getMembershipPatron() {
		return membershipPatron;
	}

	public void setMembershipPatron(List<MembershipPatron> membershipPatron) {
		this.membershipPatron = membershipPatron;
	}

	public List<MembershipBenefit> getMembershipInfoBenefit() {
		return membershipInfoBenefit;
	}

	public void setMembershipInfoBenefit(List<MembershipBenefit> membershipInfoBenefit) {
		this.membershipInfoBenefit = membershipInfoBenefit;
	}

	@Override
	public String toString() {
		return "MembershipInfo [organizationId=" + organizationId + ", organizationAlias=" + organizationAlias
				+ ", profileConfig=" + profileConfig + ", membershipPatron=" + membershipPatron 
				+ ", membershipInfoBenefit=" + membershipInfoBenefit
				+ "]";
	}

}
