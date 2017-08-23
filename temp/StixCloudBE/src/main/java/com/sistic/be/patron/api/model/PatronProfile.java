package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatronProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5836536873763686353L;
	
	private Long organizationId;
	private String preferLanguage;
	private String patronType;
	private String email;
	private String acctNum;
	private String title;
	private String firstName;
	private String lastName;
	private List<PatronPhone> contacts;
	private Integer yearOfBirth;
	private String nationality;
	private String countryOfResidence;
	private PatronIdentification identification;
	private List<Address> addresses;
	private String password;
	private String newPassword;
	@JsonProperty("isBillingAddressSameAsMailing")
	private boolean isBillingAddressSameAsMailing;
	@JsonProperty("isReceiveTicketingAgent")
	private boolean isReceiveTicketingAgent;
	@JsonProperty("isReceiveVenue")
	private boolean isReceiveVenue;
	@JsonProperty("isReceivePromoter")
	private boolean isReceivePromoter;
	
	public PatronProfile() {
		this.contacts = new ArrayList<PatronPhone>();
		this.identification = new PatronIdentification();
		this.addresses = new ArrayList<Address>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<PatronPhone> getContacts() {
		return contacts;
	}

	public void setContacts(List<PatronPhone> contacts) {
		this.contacts = contacts;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public PatronIdentification getIdentification() {
		return identification;
	}

	public void setIdentification(PatronIdentification identification) {
		this.identification = identification;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isBillingAddressSameAsMailing() {
		return isBillingAddressSameAsMailing;
	}

	public void setBillingAddressSameAsMailing(boolean isBillingAddressSameAsMailing) {
		this.isBillingAddressSameAsMailing = isBillingAddressSameAsMailing;
	}

	public boolean isReceiveTicketingAgent() {
		return isReceiveTicketingAgent;
	}

	public void setReceiveTicketingAgent(boolean isReceiveTicketingAgent) {
		this.isReceiveTicketingAgent = isReceiveTicketingAgent;
	}

	public boolean isReceiveVenue() {
		return isReceiveVenue;
	}

	public void setReceiveVenue(boolean isReceiveVenue) {
		this.isReceiveVenue = isReceiveVenue;
	}

	public boolean isReceivePromoter() {
		return isReceivePromoter;
	}

	public void setReceivePromoter(boolean isReceivePromoter) {
		this.isReceivePromoter = isReceivePromoter;
	}

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getPreferLanguage() {
		return preferLanguage;
	}

	public void setPreferLanguage(String preferLanguage) {
		this.preferLanguage = preferLanguage;
	}

	public String getPatronType() {
		return patronType;
	}

	public void setPatronType(String patronType) {
		this.patronType = patronType;
	}

	@Override
	public String toString() {
		return "PatronProfile [organizationId=" + organizationId + ", preferLanguage=" + preferLanguage
				+ ", patronType=" + patronType + ", email=" + email + ", acctNum=" + acctNum + ", title=" + title
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", contacts=" + contacts + ", yearOfBirth="
				+ yearOfBirth + ", nationality=" + nationality + ", countryOfResidence=" + countryOfResidence
				+ ", identification=" + identification + ", addresses=" + addresses + ", isBillingAddressSameAsMailing="
				+ isBillingAddressSameAsMailing + ", isReceiveTicketingAgent=" + isReceiveTicketingAgent
				+ ", isReceiveVenue=" + isReceiveVenue + ", isReceivePromoter=" + isReceivePromoter + "]";
	}
	
}
