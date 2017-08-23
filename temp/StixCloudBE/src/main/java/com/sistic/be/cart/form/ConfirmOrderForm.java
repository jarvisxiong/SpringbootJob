/**
 * 
 */
package com.sistic.be.cart.form;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sistic.be.app.util.MaskSensitiveUtill;

/**
 * @author jianhong
 * This is the Class that thymeleaf will bind submitted form parameters to.
 * Related to shoppingcartAction
 * The parameters in this attribute will be bind back into ShoppingCart
 *
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConfirmOrderForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3885129553058206881L;
	
	// Delivery Method parameters -> ShoppingCartModel.UserSelectedDeliveryMethod
	private String deliveryMethodCode;	// chosen delivery code
	private String sameAsMailingAddrFlag;
	
	// Ticket Protector -> Per LineItem or Per Cart?
	private String purchaseTp;	// ticket protector flag
	
	// Payment Amount -> ShoppingCartModel.UserSelectedPaymentMethod
//	private String denominationValues;	// what is this?
//	private String denominationFullPayment;
//	private String totalAmount;
	
	// E-Voucher -> ShoppingCartModel.UserSelectedPaymentMethod
	private List<String> evoucherIds;
//	private String evoucherType;
//	private String promoVoucherIds; // promo voucher duplicate?
//	private String voucherFullPayment;	// full payment using voucher. needed?
	
	// Payment Method Details -> ShoppingCartModel.UserSelectedPaymentMethod.PaymentMethodInfo
	private String cardType;
	private String creditCardNumber;
	private String cardSecurityCode;
	private String cardExpiryMonth;
	private String cardExpiryYear;
//	private String cardHoldername;	// duplicate?
	private String creditCardHolderName;	// creditCardName
	private String creditCardIssuingBank;	// bankName
//	private String cardHolderId;	// what is this?
//	private String prevUsedCreditCardForm;
	
	// Billing Address
	private String billingCountry;
	private String billingBlockHouseStreet;
	private String billingUnitNo;
	private String billingBuildingName;
	private String billingPostalCode;
	
	// Mailing Address
	private String mailingCountry;
	private String mailingBlockHouseStreet;
	private String mailingUnitNo;
	private String mailingBuildingName;
	private String mailingPostalCode;
	
	// Subscription -> SalesOrder.Patron
	private List<String> solicitation;
	
	// Membership Profile 
	private String membershipProfileFields;
	
	// Terms and Conditions
	private String termsAccepted;

	// Getter Setter
	public String getDeliveryMethodCode() {
		return deliveryMethodCode;
	}

	public void setDeliveryMethodCode(String deliveryMethodCode) {
		this.deliveryMethodCode = deliveryMethodCode;
	}

	public String getSameAsMailingAddrFlag() {
		return sameAsMailingAddrFlag;
	}

	public void setSameAsMailingAddrFlag(String sameAsMailingAddrFlag) {
		this.sameAsMailingAddrFlag = sameAsMailingAddrFlag;
	}

	public String getPurchaseTp() {
		return purchaseTp;
	}

	public void setPurchaseTp(String purchaseTp) {
		this.purchaseTp = purchaseTp;
	}

	public List<String> getEvoucherIds() {
		return evoucherIds;
	}

	public void setEvoucherIds(List<String> evoucherIds) {
		this.evoucherIds = evoucherIds;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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

	public String getCardExpiryMonth() {
		return cardExpiryMonth;
	}

	public void setCardExpiryMonth(String cardExpiryMonth) {
		this.cardExpiryMonth = cardExpiryMonth;
	}

	public String getCardExpiryYear() {
		return cardExpiryYear;
	}

	public void setCardExpiryYear(String cardExpiryYear) {
		this.cardExpiryYear = cardExpiryYear;
	}

	public String getCreditCardHolderName() {
		return creditCardHolderName;
	}

	public void setCreditCardHolderName(String creditCardHolderName) {
		this.creditCardHolderName = creditCardHolderName;
	}

	public String getCreditCardIssuingBank() {
		return creditCardIssuingBank;
	}

	public void setCreditCardIssuingBank(String creditCardIssuingBank) {
		this.creditCardIssuingBank = creditCardIssuingBank;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getBillingBlockHouseStreet() {
		return billingBlockHouseStreet;
	}

	public void setBillingBlockHouseStreet(String billingBlockHouseStreet) {
		this.billingBlockHouseStreet = billingBlockHouseStreet;
	}

	public String getBillingUnitNo() {
		return billingUnitNo;
	}

	public void setBillingUnitNo(String billingUnitNo) {
		this.billingUnitNo = billingUnitNo;
	}

	public String getBillingBuildingName() {
		return billingBuildingName;
	}

	public void setBillingBuildingName(String billingBuildingName) {
		this.billingBuildingName = billingBuildingName;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getMailingCountry() {
		return mailingCountry;
	}

	public void setMailingCountry(String mailingCountry) {
		this.mailingCountry = mailingCountry;
	}

	public String getMailingBlockHouseStreet() {
		return mailingBlockHouseStreet;
	}

	public void setMailingBlockHouseStreet(String mailingBlockHouseStreet) {
		this.mailingBlockHouseStreet = mailingBlockHouseStreet;
	}

	public String getMailingUnitNo() {
		return mailingUnitNo;
	}

	public void setMailingUnitNo(String mailingUnitNo) {
		this.mailingUnitNo = mailingUnitNo;
	}

	public String getMailingBuildingName() {
		return mailingBuildingName;
	}

	public void setMailingBuildingName(String mailingBuildingName) {
		this.mailingBuildingName = mailingBuildingName;
	}

	public String getMailingPostalCode() {
		return mailingPostalCode;
	}

	public void setMailingPostalCode(String mailingPostalCode) {
		this.mailingPostalCode = mailingPostalCode;
	}

	public List<String> getSolicitation() {
		return solicitation;
	}

	public void setSolicitation(List<String> solicitation) {
		this.solicitation = solicitation;
	}
	
	public String getMembershipProfileFields() {
		return membershipProfileFields;
	}

	public void setMembershipProfileFields(String membershipProfileFields) {
		this.membershipProfileFields = membershipProfileFields;
	}

	public String getTermsAccepted() {
		return termsAccepted;
	}

	public void setTermsAccepted(String termsAccepted) {
		this.termsAccepted = termsAccepted;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfirmOrderForm [deliveryMethodCode=").append(deliveryMethodCode)
				.append(", sameAsMailingAddrFlag=").append(sameAsMailingAddrFlag).append(", purchaseTp=")
				.append(purchaseTp).append(", evoucherIds=").append(evoucherIds).append(", cardType=").append(cardType)
				.append(", creditCardNumber=").append(MaskSensitiveUtill.maskCreditCardNo(creditCardNumber)).append(", cardSecurityCode=")
				.append(MaskSensitiveUtill.maskCreditCardNo(cardSecurityCode)).append(", cardExpiryMonth=").append(cardExpiryMonth)
				.append(", cardExpiryYear=").append(cardExpiryYear).append(", creditCardHolderName=")
				.append(creditCardHolderName).append(", creditCardIssuingBank=").append(creditCardIssuingBank)
				.append(", billingCountry=").append(billingCountry).append(", billingBlockHouseStreet=")
				.append(billingBlockHouseStreet).append(", billingUnitNo=").append(billingUnitNo)
				.append(", billingBuildingName=").append(billingBuildingName).append(", billingPostalCode=")
				.append(billingPostalCode).append(", mailingCountry=").append(mailingCountry)
				.append(", mailingBlockHouseStreet=").append(mailingBlockHouseStreet).append(", mailingUnitNo=")
				.append(mailingUnitNo).append(", mailingBuildingName=").append(mailingBuildingName)
				.append(", mailingPostalCode=").append(mailingPostalCode).append(", solicitation=").append(solicitation)
				.append(", membershipProfileFields=").append(membershipProfileFields)
				.append(", termsAccepted=").append(termsAccepted).append("]");
		return builder.toString();
	}	
	
}
