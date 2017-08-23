package com.sistic.be.sales.order;

import java.io.Serializable;

import com.sistic.be.app.util.MaskSensitiveUtill;

public class CreditCardInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8677191958024022479L;
	
	/**
	 * creditCardType is MasterCard, Visa, UnionPay eg.
	 * paymentMethodCode is MASTER, MASTER_RWS, VISA, AMEX e.g.
	 */
	private String paymentMethodCode;
	private String creditCardType;
	private String creditCardNo;
	private String creditCardExpiryMonth;
	private String creditCardExpiryYear;
	private String creditCardCSC;
	
	
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getCreditCardExpiryMonth() {
		return creditCardExpiryMonth;
	}
	public void setCreditCardExpiryMonth(String creditCardExpiryMonth) {
		this.creditCardExpiryMonth = creditCardExpiryMonth;
	}
	public String getCreditCardExpiryYear() {
		return creditCardExpiryYear;
	}
	public void setCreditCardExpiryYear(String creditCardExpiryYear) {
		this.creditCardExpiryYear = creditCardExpiryYear;
	}
	public String getCreditCardCSC() {
		return creditCardCSC;
	}
	public void setCreditCardCSC(String creditCardCSC) {
		this.creditCardCSC = creditCardCSC;
	}
	
	@Override
	public String toString() {
		return "CreditCardInfo [paymentMethodCode=" + paymentMethodCode + ", creditCardType=" + creditCardType
				+ ", creditCardNo=" + MaskSensitiveUtill.maskCreditCardNo(creditCardNo) + ", creditCardExpiryMonth=" + creditCardExpiryMonth
				+ ", creditCardExpiryYear=" + creditCardExpiryYear + ", creditCardCSC=" + MaskSensitiveUtill.maskCreditCardCsc(creditCardCSC) + "]";
	}
		
}
