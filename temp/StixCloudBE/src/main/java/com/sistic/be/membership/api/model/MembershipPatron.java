package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public class MembershipPatron implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5802945267441596377L;
	
	private Long organizationId;
	private String organizationAlias;
	private Long membershipPatronId;
	private String membershipTier;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private String cardStatus;
	private Boolean status;
	private List<MembershipSubMemberInfo> subMemberInfoList;
	private Boolean isExpired;

	
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

	public Long getMembershipPatronId() {
		return membershipPatronId;
	}

	public void setMembershipPatronId(Long membershipPatronId) {
		this.membershipPatronId = membershipPatronId;
	}

	public String getMembershipTier() {
		return membershipTier;
	}

	public void setMembershipTier(String membershipTier) {
		this.membershipTier = membershipTier;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

	public List<MembershipSubMemberInfo> getSubMemberInfoList() {
		return subMemberInfoList;
	}

	public void setSubMemberInfoList(List<MembershipSubMemberInfo> subMemberInfoList) {
		this.subMemberInfoList = subMemberInfoList;
	}

	@Override
	public String toString() {
		return "MembershipPatron ["
				+ "organizationId=" + organizationId
				+ ", organizationAlias=" + organizationAlias
				+ ", membershipPatronId=" + membershipPatronId 
				+ ", membershipTier=" + membershipTier
				+ ", startDate=" + startDate
				+ ", endDate=" + endDate
				+ ", cardStatus=" + cardStatus
				+ ", status=" + status
				+ ", isExpired=" + isExpired
				+ ", subMemberInfoList=" + subMemberInfoList
				 + "]";
	}	
}
