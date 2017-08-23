package com.sistic.be.cart.model;

import java.io.Serializable;

public class PaymentMethod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -419131063703678748L;
	// Retrieved from API
	private String creditCardType;	// MasterCard, VISA, American Express, Diners, China Unionpay, JCB
	private String code;	// MASTER, VISA, AMEX, DINERS, CUP, JCB
	
	private static String cardPaymentLabel = "label.payment.card.";
	
	public PaymentMethod() {
		
	}
	
	public PaymentMethod(String code) {
		super();
		this.code = code;
		this.initCreditCardType(code);
	}
	
	public PaymentMethod(String code, String creditCardType) {
		super();
		this.code = code;
		this.creditCardType = creditCardType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	
	// Initialize Methods
	public void initCreditCardType(String code) {
		this.creditCardType = this.cardPaymentLabel + code;
	}
	
}
