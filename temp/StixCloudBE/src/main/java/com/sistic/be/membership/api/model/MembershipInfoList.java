package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class MembershipInfoList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3415251183411314473L;
	
	private List<MembershipInfo> membershipInfo;
	private Integer totalPage;
	private Integer currentPage;
	private Integer totalRow;

	public List<MembershipInfo> getMembershipInfo() {
		return membershipInfo;
	}

	public void setMembershipInfo(List<MembershipInfo> membershipInfo) {
		this.membershipInfo = membershipInfo;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}

	@Override
	public String toString() {
		return "PatronMembership [membershipInfo=" + membershipInfo 
				+ ", totalPage=" + totalPage
				+ ", currentPage=" + currentPage
				+ ", totalRow=" + totalRow
				+ "]";
	}
	

}
