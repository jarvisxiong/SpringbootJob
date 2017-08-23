package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.List;

public class EVoucherRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572508439159350212L;
	
	private List<String> evoucherIdList;
	private String paymentMethodCode;
	private String creditCardNo;
	
	public EVoucherRequest() {}
	
	public EVoucherRequest(List<String> evoucherIds) {
		this.evoucherIdList = evoucherIds;
	}
	
	public EVoucherRequest(List<String> evoucherIds, String paymentMethodCode, String creditCardNo) {
		this.evoucherIdList = evoucherIds;
		this.paymentMethodCode = paymentMethodCode;
		this.creditCardNo = creditCardNo;
	}
	
	public List<String> getEvoucherIdList() {
		return evoucherIdList;
	}
	public void setEvoucherIdList(List<String> evoucherIdList) {
		this.evoucherIdList = evoucherIdList;
	}
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	
}
