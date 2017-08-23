package com.sistic.be.sales.order;

import java.io.Serializable;

import com.sistic.be.app.util.MaskSensitiveUtill;

public class CreditCardCsc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8915566087377990000L;
	
	private String creditCardCSC;

	public String getCreditCardCSC() {
		return creditCardCSC;
	}

	public void setCreditCardCSC(String creditCardCSC) {
		this.creditCardCSC = creditCardCSC;
	}

	@Override
	public String toString() {
		return "CreditCardCsc [creditCardCSC=" + MaskSensitiveUtill.maskCreditCardCsc(creditCardCSC) + "]";
	}

}
