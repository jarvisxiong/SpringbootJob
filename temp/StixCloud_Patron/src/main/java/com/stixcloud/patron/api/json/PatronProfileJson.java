package com.stixcloud.patron.api.json;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"email", "acctNum", "accountNo", "password", "patronType", "preferLanguage", "title",
    "firstName", "lastName", "contacts", "yearOfBirth", "nationality", "countryOfResidence",
    "identification", "isBillingAddressSameAsMailing", "addresses", "isReceiveTicketingAgent",
    "isReceivePromoter", "isReceiveVenue", "externalCustomerID","patronAttributes"})
@RedisHash(value = "PatronProfileJson", timeToLive = 900)
public class PatronProfileJson implements Serializable {

  private static final long serialVersionUID = 5881946294206567520L;

  @Id
  @JsonProperty("email")
  protected String email;
  @JsonProperty("acctNum")
  protected Long acctNum;
  @JsonProperty("accountNo")
  protected String accountNo;
  @JsonProperty("password")
  private String password;
  @JsonProperty(value = "patronType", required = true)
  private String patronType;
  @JsonProperty(value = "preferLanguage")
  private String preferLanguage;
  @JsonProperty("title")
  protected String title;
  @JsonProperty("firstName")
  protected String firstName;
  @JsonProperty("lastName")
  protected String lastName;
  @JsonProperty("contacts")
  protected List<ContactJson> contacts;
  @JsonProperty("yearOfBirth")
  protected Integer yearOfBirth;
  @JsonProperty("nationality")
  protected String nationality;
  @JsonProperty("countryOfResidence")
  protected String countryOfResidence;
  @JsonProperty("identification")
  protected IdentificationJson identification;
  @JsonProperty("isBillingAddressSameAsMailing")
  protected boolean isBillingAddressSameAsMailing;
  @JsonProperty("addresses")
  protected List<AddressJson> addresses;
  @JsonProperty("isReceiveTicketingAgent")
  private Boolean isReceiveTicketingAgent;
  @JsonProperty("isReceivePromoter")
  private Boolean isReceivePromoter;
  @JsonProperty("isReceiveVenue")
  private Boolean isReceiveVenue;
  @JsonProperty(value = "externalCustomerID")
  private String externalCustomerID;
  @JsonProperty(value = "patronAttributes")
  private Map<String, String> patronAttributes;

  public PatronProfileJson() {

  }

  public PatronProfileJson(String email, Long acctNum, String title, String firstName,
      String lastName, List<ContactJson> contacts, Integer yearOfBirth, String nationality,
      String countryOfResidence, IdentificationJson identification,
      boolean isBillingAddressSameAsMailing, List<AddressJson> addresses) {
    this.email = email;
    this.acctNum = acctNum;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.contacts = contacts;
    this.yearOfBirth = yearOfBirth;
    this.nationality = nationality;
    this.countryOfResidence = countryOfResidence;
    this.identification = identification;
    this.isBillingAddressSameAsMailing = isBillingAddressSameAsMailing;
    this.addresses = addresses;
  }

  public PatronProfileJson(String title, String firstName, String lastName,
      List<ContactJson> contacts, Integer yearOfBirth, String nationality,
      String countryOfResidence, IdentificationJson identification,
      boolean isBillingAddressSameAsMailing, List<AddressJson> addresses, Boolean isReceiveTicketingAgent, Boolean isReceivePromoter, Boolean isReceiveVenue) {
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.contacts = contacts;
    this.yearOfBirth = yearOfBirth;
    this.nationality = nationality;
    this.countryOfResidence = countryOfResidence;
    this.identification = identification;
    this.isBillingAddressSameAsMailing = isBillingAddressSameAsMailing;
    this.addresses = addresses;
    this.isReceiveTicketingAgent = isReceiveTicketingAgent;
    this.isReceivePromoter = isReceivePromoter;
    this.isReceiveVenue = isReceiveVenue;
  }

  public PatronProfileJson(String title, Long acctNum, String firstName, String lastName,
      List<ContactJson> contacts, Integer yearOfBirth, String nationality,
      String countryOfResidence, IdentificationJson identification,
      boolean isBillingAddressSameAsMailing, List<AddressJson> addresses) {
    this.acctNum = acctNum;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.contacts = contacts;
    this.yearOfBirth = yearOfBirth;
    this.nationality = nationality;
    this.countryOfResidence = countryOfResidence;
    this.identification = identification;
    this.isBillingAddressSameAsMailing = isBillingAddressSameAsMailing;
    this.addresses = addresses;
  }

  public PatronProfileJson(String title, String firstName, String lastName,
      List<ContactJson> contacts, Integer yearOfBirth, String nationality,
      String countryOfResidence, IdentificationJson identification,
      boolean isBillingAddressSameAsMailing, List<AddressJson> addresses) {
      this.title = title;
      this.firstName = firstName;
      this.lastName = lastName;
      this.contacts = contacts;
      this.yearOfBirth = yearOfBirth;
      this.nationality = nationality;
      this.countryOfResidence = countryOfResidence;
      this.identification = identification;
      this.isBillingAddressSameAsMailing = isBillingAddressSameAsMailing;
      this.addresses = addresses;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the acctNum
   */
  public Long getAcctNum() {
    return acctNum;
  }

  /**
   * @param acctNum the acctNum to set
   */
  public void setAcctNum(Long acctNum) {
    this.acctNum = acctNum;
  }

  public String getAccountNo() {
	return accountNo;
}

public void setAccountNo(String accountNo) {
	this.accountNo = accountNo;
}

/**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the patronType
   */
  public String getPatronType() {
    return patronType;
  }

  /**
   * @param patronType the patronType to set
   */
  public void setPatronType(String patronType) {
    this.patronType = patronType;
  }

  /**
   * @return the preferLanguage
   */
  public String getPreferLanguage() {
    return preferLanguage;
  }

  /**
   * @param preferLanguage the preferLanguage to set
   */
  public void setPreferLanguage(String preferLanguage) {
    this.preferLanguage = preferLanguage;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the contactNos
   */
  public List<ContactJson> getContacts() {
    return contacts;
  }

  /**
   * @param contactNos the contactNos to set
   */
  public void setContacts(List<ContactJson> contacts) {
    this.contacts = contacts;
  }

  /**
   * @return the yearOfBirth
   */
  public Integer getYearOfBirth() {
    return yearOfBirth;
  }

  /**
   * @param yearOfBirth the yearOfBirth to set
   */
  public void setYearOfBirth(Integer yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

  /**
   * @return the nationality
   */
  public String getNationality() {
    return nationality;
  }

  /**
   * @param nationality the nationality to set
   */
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  /**
   * @return the countryOfResidence
   */
  public String getCountryOfResidence() {
    return countryOfResidence;
  }

  /**
   * @param countryOfResidence the countryOfResidence to set
   */
  public void setCountryOfResidence(String countryOfResidence) {
    this.countryOfResidence = countryOfResidence;
  }

  /**
   * @return the identification
   */
  public IdentificationJson getIdentification() {
    return identification;
  }

  /**
   * @param identification the identification to set
   */
  public void setIdentification(IdentificationJson identification) {
    this.identification = identification;
  }

  /**
   * @return the isBillingAddressSameAsMailing
   */
  public boolean isBillingAddressSameAsMailing() {
    return isBillingAddressSameAsMailing;
  }

  /**
   * @param isBillingAddressSameAsMailing the isBillingAddressSameAsMailing to set
   */
  public void setBillingAddressSameAsMailing(boolean isBillingAddressSameAsMailing) {
    this.isBillingAddressSameAsMailing = isBillingAddressSameAsMailing;
  }

  /**
   * @return the addresses
   */
  public List<AddressJson> getAddresses() {
    return addresses;
  }

  /**
   * @param addresses the addresses to set
   */
  public void setAddresses(List<AddressJson> addresses) {
    this.addresses = addresses;
  }

  /**
   * @return the isReceiveTicketingAgent
   */
  public Boolean getIsReceiveTicketingAgent() {
    return isReceiveTicketingAgent;
  }

  /**
   * @param isReceiveTicketingAgent the isReceiveTicketingAgent to set
   */
  public void setIsReceiveTicketingAgent(Boolean isReceiveTicketingAgent) {
    this.isReceiveTicketingAgent = isReceiveTicketingAgent;
  }

  /**
   * @return the isReceivePromoter
   */
  public Boolean getIsReceivePromoter() {
    return isReceivePromoter;
  }

  /**
   * @param isReceivePromoter the isReceivePromoter to set
   */
  public void setIsReceivePromoter(Boolean isReceivePromoter) {
    this.isReceivePromoter = isReceivePromoter;
  }

  /**
   * @return the isReceiveVenue
   */
  public Boolean getIsReceiveVenue() {
    return isReceiveVenue;
  }

  /**
   * @param isReceiveVenue the isReceiveVenue to set
   */
  public void setIsReceiveVenue(Boolean isReceiveVenue) {
    this.isReceiveVenue = isReceiveVenue;
  }

  /**
   * @return the externalCustomerID
   */
  public String getExternalCustomerID() {
    return externalCustomerID;
  }

  /**
   * @param externalCustomerID the externalCustomerID to set
   */
  public void setExternalCustomerID(String externalCustomerID) {
    this.externalCustomerID = externalCustomerID;
  }

  /**
   * @return the patronAttributes
   */
  public Map<String, String> getPatronAttributes() {
    return patronAttributes;
  }

  /**
   * @param patronAttributes the patronAttributes to set
   */
  public void setPatronAttributes(Map<String, String> patronAttributes) {
    this.patronAttributes = patronAttributes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronProfileJson that = (PatronProfileJson) o;
    return new EqualsBuilder().append(email, that.getEmail()).append(acctNum, that.getAcctNum())
        .append(title, that.getTitle()).append(firstName, that.getFirstName())
        .append(lastName, that.getLastName()).append(contacts, that.getContacts())
        .append(yearOfBirth, that.getYearOfBirth()).append(nationality, that.getNationality())
        .append(countryOfResidence, that.getCountryOfResidence())
        .append(identification, that.getIdentification())
        .append(isBillingAddressSameAsMailing, that.isBillingAddressSameAsMailing())
        .append(addresses, that.getAddresses()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(email).append(acctNum).append(title).append(firstName)
        .append(lastName).append(contacts).append(yearOfBirth).append(nationality)
        .append(countryOfResidence).append(identification).append(isBillingAddressSameAsMailing)
        .append(addresses).toHashCode();
  }

}
