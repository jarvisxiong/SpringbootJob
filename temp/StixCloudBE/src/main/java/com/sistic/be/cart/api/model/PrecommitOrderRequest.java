package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sistic.be.app.util.MaskSensitiveUtill;
import com.sistic.be.membership.api.model.MembershipProfile;
import com.sistic.be.patron.model.Address;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PrecommitOrderRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3028780174535048533L;
	
	private String deliveryMethodCode;
	private String paymentMethodCode;
	private boolean sameAsMailingAddrFlag;
	private boolean purchaseTp;
	private List<Address> address;
	private PatronSolicitationUpdateRequest patronSolicitation;
	private List<String> evoucherIdList;
	private String creditCardNo;
	private String creditCardMonth;
	private String creditCardYear;
	@Deprecated
	private String creditCardExpiry;
	private String creditCardCSC;
	private List<MembershipProfile> profiles;
	
	public String getDeliveryMethodCode() {
		return deliveryMethodCode;
	}
	public void setDeliveryMethodCode(String deliveryMethodCode) {
		this.deliveryMethodCode = deliveryMethodCode;
	}
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public boolean isSameAsMailingAddrFlag() {
		return sameAsMailingAddrFlag;
	}
	public void setSameAsMailingAddrFlag(boolean sameAsMailingAddrFlag) {
		this.sameAsMailingAddrFlag = sameAsMailingAddrFlag;
	}
	public boolean isPurchaseTp() {
		return purchaseTp;
	}
	public void setPurchaseTp(boolean purchaseTp) {
		this.purchaseTp = purchaseTp;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public PatronSolicitationUpdateRequest getPatronSolicitation() {
		return patronSolicitation;
	}
	public void setPatronSolicitation(PatronSolicitationUpdateRequest patronSolicitation) {
		this.patronSolicitation = patronSolicitation;
	}
	public List<String> getEvoucherIdList() {
		return evoucherIdList;
	}
	public void setEvoucherIdList(List<String> evoucherIdList) {
		this.evoucherIdList = evoucherIdList;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getCreditCardMonth() {
		return creditCardMonth;
	}
	public void setCreditCardMonth(String creditCardMonth) {
		this.creditCardMonth = creditCardMonth;
	}
	public String getCreditCardYear() {
		return creditCardYear;
	}
	public void setCreditCardYear(String creditCardYear) {
		this.creditCardYear = creditCardYear;
	}
	public String getCreditCardExpiry() {
		return creditCardExpiry;
	}
	public void setCreditCardExpiry(String creditCardExpiry) {
		this.creditCardExpiry = creditCardExpiry;
	}
	public String getCreditCardCSC() {
		return creditCardCSC;
	}
	public void setCreditCardCSC(String creditCardCSC) {
		this.creditCardCSC = creditCardCSC;
	}

	public List<MembershipProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<MembershipProfile> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfiles(MembershipProfile profile) {
		if (this.profiles == null) {
			this.profiles = new ArrayList<MembershipProfile>();
		}
		this.profiles.add(profile);
	}
	
	@Override
	public String toString() {
		return "PrecommitOrderRequest [deliveryMethodCode=" + deliveryMethodCode + ", paymentMethodCode="
				+ paymentMethodCode + ", sameAsMailingAddrFlag=" + sameAsMailingAddrFlag + ", purchaseTp=" + purchaseTp
				+ ", address=" + address + ", patronSolicitation=" + patronSolicitation + ", evoucherIdList="
				+ evoucherIdList + ", creditCardNo=" + MaskSensitiveUtill.maskCreditCardNo(creditCardNo) + ", creditCardMonth=" + creditCardMonth
				+ ", creditCardYear=" + creditCardYear + ", creditCardExpiry=" + creditCardExpiry + ", creditCardCSC="
				+ MaskSensitiveUtill.maskCreditCardCsc(creditCardCSC) + ", profiles=" + profiles + "]";
	}


}
