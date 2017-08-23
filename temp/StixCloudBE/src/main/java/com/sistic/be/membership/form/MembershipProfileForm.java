package com.sistic.be.membership.form;

import java.io.Serializable;

public class MembershipProfileForm implements Serializable {

	private static final long serialVersionUID = -2254325110026586612L;
	
	// All the fields submitted by the form, binded by thymeleaf
	
	private String membershipProfileFields;
	
	public String getMembershipProfileFields() {
		return membershipProfileFields;
	}

	public void setMembershipProfileFields(String membershipProfileFields) {
		this.membershipProfileFields = membershipProfileFields;
	}

	@Override
	public String toString() {
		return "MembershipProfileForm [membershipProfileFields=" + membershipProfileFields
				+ "]";
	}
	
}
