package com.sistic.be.patron.model;

import com.sistic.be.cart.api.model.JsonResponse;

public class PatronJsonResponse extends JsonResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1374743411741937187L;

	private Long accountNo;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "PatronJsonResponse [accountNo=" + accountNo + "]";
	}
	
}
