package com.sistic.be.sales.order;

import java.io.Serializable;

import com.sistic.be.app.util.MaskSensitiveUtill;

public class CreditCardNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1873055335057892517L;

	private String creditCardNo;
	
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	
	@Override
	public String toString() {
		return "CreditCardNumber [creditCardNo=" + MaskSensitiveUtill.maskCreditCardNo(creditCardNo) + "]";
	}

}
