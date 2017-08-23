package com.stixcloud.patron.api.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"memberId", "membershipTier", "vipStatus"})
public class PatronAttributesJson {
	@JsonProperty("memberId")
	private String memberId;
	@JsonProperty("membershipTier")
	private String membershipTier;
	@JsonProperty("vipStatus")
	private String vipStatus;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMembershipTier() {
		return membershipTier;
	}
	public void setMembershipTier(String membershipTier) {
		this.membershipTier = membershipTier;
	}
	public String getVipStatus() {
		return vipStatus;
	}
	public void setVipStatus(String vipStatus) {
		this.vipStatus = vipStatus;
	}
	
	
}
