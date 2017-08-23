/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;

import com.sistic.be.app.util.MaskSensitiveUtill;

/**
 * @author jianhong
 * Class holds the Payment Method input by the patron
 * Name of class to be refactored as needed
 *
 */

public class PaymentMethodInfo implements Serializable {

	// Retrieved from API
	private String creditCardType;	// MasterCard, VISA, American Express, Diners, China Unionpay, JCB
	private String code;	// MASTER, VISA, AMEX, DINERS, CUP, JCB
	
	// Retrieved from User input
	private String creditCardNumber;	// 5453010000064154
	private String cardSecurityCode;	// 825
	private int creditCardExpiryMth;	// 11
	private int creditCardExpiryYear;	// 17
	private String creditCardName;		// Tan Jian Hong
	
	private String bankName;			// DBS?
	
	public PaymentMethodInfo() {
		
	}

	public PaymentMethodInfo(String creditCardType, String code) {
		super();
		this.creditCardType = creditCardType;
		this.code = code;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCardSecurityCode() {
		return cardSecurityCode;
	}

	public void setCardSecurityCode(String cardSecurityCode) {
		this.cardSecurityCode = cardSecurityCode;
	}

	public int getCreditCardExpiryMth() {
		return creditCardExpiryMth;
	}

	public void setCreditCardExpiryMth(int creditCardExpiryMth) {
		this.creditCardExpiryMth = creditCardExpiryMth;
	}

	public int getCreditCardExpiryYear() {
		return creditCardExpiryYear;
	}

	public void setCreditCardExpiryYear(int creditCardExpiryYear) {
		this.creditCardExpiryYear = creditCardExpiryYear;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return "PaymentMethodInfo [creditCardType=" + creditCardType + ", code=" + code + ", creditCardNumber="
				+ MaskSensitiveUtill.maskCreditCardNo(creditCardNumber) + ", cardSecurityCode=" + MaskSensitiveUtill.maskCreditCardNo(cardSecurityCode) + ", creditCardExpiryMth="
				+ creditCardExpiryMth + ", creditCardExpiryYear=" + creditCardExpiryYear + ", creditCardName="
				+ creditCardName + ", bankName=" + bankName + "]";
	}
	
}