package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class MembershipInfoDetailsList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7826076370028311382L;
	
	private List<MembershipInfoDetails> membershipInfoDetails;
	
	public List<MembershipInfoDetails> getMembershipInfoDetails() {
		return membershipInfoDetails;
	}

	public void setMembershipInfoDetails(List<MembershipInfoDetails> membershipInfoDetails) {
		this.membershipInfoDetails = membershipInfoDetails;
	}

	@Override
	public String toString() {
		return "MembershipInfoDetailsList [membershipInfoDetails=" + membershipInfoDetails +  "]";
	}

}
