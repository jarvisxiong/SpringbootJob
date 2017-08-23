package com.sistic.be.patron.form;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PatronForm implements Serializable {

	private static final long serialVersionUID = -2254325110026586612L;
	
	// All the fields submitted by the form, binded by thymeleaf
	
	@NotEmpty(message = "Please enter an email address")
	@Email(message="Please enter a valid email address")
	private String emailAddress;
	private String title;
	private String firstName;
	private String lastName;

	private String mobileNo;
	private String mobileCallingCode;
	private String homeNo;
	private String homeCallingCode;
	private String otherNo;
	private String otherCallingCode;
	
	private String birthYear;
	private String nationality;
	private String country;
	private String idType;	//NRIC or passport
	private String identityNo;
	
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
	
	// Password
	private String password;
	private String confirmPassword;
	private String newPassword;
	
	// Subscription
	private List<String> subscriptions;
	
	// Terms & Condition
	private String termsAccepted;
	
	private String billingAddressSameAsMailing;
	private String receiveTicketingAgent;
	private String receiveVenue;
	private String receivePromoter;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMobileCallingCode() {
		return mobileCallingCode;
	}
	public void setMobileCallingCode(String mobileCallingCode) {
		this.mobileCallingCode = mobileCallingCode;
	}
	public String getHomeNo() {
		return homeNo;
	}
	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}
	public String getHomeCallingCode() {
		return homeCallingCode;
	}
	public void setHomeCallingCode(String homeCallingCode) {
		this.homeCallingCode = homeCallingCode;
	}
	public String getOtherNo() {
		return otherNo;
	}
	public void setOtherNo(String otherNo) {
		this.otherNo = otherNo;
	}
	public String getOtherCallingCode() {
		return otherCallingCode;
	}
	public void setOtherCallingCode(String otherCallingCode) {
		this.otherCallingCode = otherCallingCode;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public List<String> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<String> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public String getTermsAccepted() {
		return termsAccepted;
	}
	public void setTermsAccepted(String termsAccepted) {
		this.termsAccepted = termsAccepted;
	}
	public String getBillingAddressSameAsMailing() {
		return billingAddressSameAsMailing;
	}
	public void setBillingAddressSameAsMailing(String billingAddressSameAsMailing) {
		this.billingAddressSameAsMailing = billingAddressSameAsMailing;
	}
	public String getReceiveTicketingAgent() {
		return receiveTicketingAgent;
	}
	public void setReceiveTicketingAgent(String receiveTicketingAgent) {
		this.receiveTicketingAgent = receiveTicketingAgent;
	}
	public String getReceiveVenue() {
		return receiveVenue;
	}
	public void setReceiveVenue(String receiveVenue) {
		this.receiveVenue = receiveVenue;
	}
	public String getReceivePromoter() {
		return receivePromoter;
	}
	public void setReceivePromoter(String receivePromoter) {
		this.receivePromoter = receivePromoter;
	}
	
	@Override
	public String toString() {
		return "PatronForm [emailAddress=" + emailAddress + ", title=" + title + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", mobileCallingCode=" + mobileCallingCode
				+ ", homeNo=" + homeNo + ", homeCallingCode=" + homeCallingCode + ", otherNo=" + otherNo
				+ ", otherCallingCode=" + otherCallingCode + ", birthYear=" + birthYear + ", nationality=" + nationality
				+ ", country=" + country + ", idType=" + idType + ", identityNo=" + identityNo + ", billingCountry="
				+ billingCountry + ", billingBlockHouseStreet=" + billingBlockHouseStreet + ", billingUnitNo="
				+ billingUnitNo + ", billingBuildingName=" + billingBuildingName + ", billingPostalCode="
				+ billingPostalCode + ", mailingCountry=" + mailingCountry + ", mailingBlockHouseStreet="
				+ mailingBlockHouseStreet + ", mailingUnitNo=" + mailingUnitNo + ", mailingBuildingName="
				+ mailingBuildingName + ", mailingPostalCode=" + mailingPostalCode + ", subscriptions=" + subscriptions
				+ ", termsAccepted=" + termsAccepted + ", billingAddressSameAsMailing=" + billingAddressSameAsMailing
				+ ", receiveTicketingAgent=" + receiveTicketingAgent + ", receiveVenue=" + receiveVenue
				+ ", receivePromoter=" + receivePromoter + "]";
	}
	

}
