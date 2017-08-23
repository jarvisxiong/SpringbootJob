package com.stixcloud.patron.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "PATRON_PROFILE_VIEW")
public class PatronProfileView implements Serializable {

  private static final long serialVersionUID = 5476752729647646057L;

  private String title;
  @Id
  private Long acctNum;
  @Id
  private String firstName;
  @Id
  private String lastName;
  @Id
  private String emailAddress;
  @Id
  private Integer yearOfDob;
  @Id
  private String phoneType;
  @Id
  private String phoneNumber;
  @Id
  private Integer countryCallingCode;
  @Id
  private String countryCode;
  @Id
  private String nationality;
  @Id
  private String countryOfResidence;
  @Id
  private String identityName;
  @Id
  private String identityNo;
  @Id
  private String addressType;
  @Id
  private String blockNo;
  @Id
  private String unitNo;
  @Id
  private String buildingName;
  @Id
  private String postCode;
  @Id
  private String country;
  @Id
  private String isBillingSameAsMailing;
  @Id
  private Long patronProfileId;

  public PatronProfileView() {

  }

  public PatronProfileView(String title, String firstName, String lastName, String emailAddress,
      Integer yearOfDob, String phoneType, String phoneNumber, Integer countryCallingCode,
      String countryCode, String nationality, String countryOfResidence, String identityName,
      String identityNo, String addressType, String blockNo, String unitNo, String buildingName,
      String postCode, String country, String isBillingSameAsMailing, Long acctNum,
      Long patronProfileId) {
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.yearOfDob = yearOfDob;
    this.phoneType = phoneType;
    this.phoneNumber = phoneNumber;
    this.countryCallingCode = countryCallingCode;
    this.countryCode = countryCode;
    this.nationality = nationality;
    this.countryOfResidence = countryOfResidence;
    this.identityName = identityName;
    this.identityNo = identityNo;
    this.addressType = addressType;
    this.blockNo = blockNo;
    this.unitNo = unitNo;
    this.buildingName = buildingName;
    this.postCode = postCode;
    this.country = country;
    this.isBillingSameAsMailing = isBillingSameAsMailing;
    this.acctNum = acctNum;
    this.patronProfileId = patronProfileId;
  }

  /**
   * @return the title
   */
  @Column(name = "TITLE", nullable = true, length = 255)
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
   * @return the acctNum
   */
  @Column(name = "ACCT_NUM", nullable = false, length = 10)
  public Long getAcctNum() {
    return acctNum;
  }

  /**
   * @param acctNum the acctNum to set
   */
  public void setAcctNum(Long acctNum) {
    this.acctNum = acctNum;
  }

  /**
   * @return the firstName
   */
  @Column(name = "FIRST_NAME", nullable = false, length = 100)
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
  @Column(name = "LAST_NAME", nullable = false, length = 100)
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
   * @return the emailAddress
   */
  @Column(name = "EMAIL_ADDRESS", nullable = false, length = 75)
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * @param emailAddress the emailAddress to set
   */
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * @return the yearOfDob
   */
  @Column(name = "YEAR_OF_DOB", nullable = true, length = 4)
  public Integer getYearOfDob() {
    return yearOfDob;
  }

  /**
   * @param yearOfDob the yearOfDob to set
   */
  public void setYearOfDob(Integer yearOfDob) {
    this.yearOfDob = yearOfDob;
  }

  /**
   * @return the phoneType
   */
  @Column(name = "PHONE_TYPE", nullable = false, length = 50)
  public String getPhoneType() {
    return phoneType;
  }

  /**
   * @param phoneType the phoneType to set
   */
  public void setPhoneType(String phoneType) {
    this.phoneType = phoneType;
  }

  /**
   * @return the phoneNumber
   */
  @Column(name = "PHONE_NUMBER", nullable = false, length = 20)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @param phoneNumber the phoneNumber to set
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * @return the countryCallingCode
   */
  @Column(name = "COUNTRY_CALLING_CODE", nullable = false, length = 4)
  public Integer getCountryCallingCode() {
    return countryCallingCode;
  }

  /**
   * @param countryCallingCode the countryCallingCode to set
   */
  public void setCountryCallingCode(Integer countryCallingCode) {
    this.countryCallingCode = countryCallingCode;
  }

  /**
   * @return the countryCode
   */
  @Column(name = "COUNTRY_CODE", nullable = false, length = 2)
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * @param countryCode the countryCode to set
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * @return the nationality
   */
  @Column(name = "NATIONALITY", nullable = false, length = 2)
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
  @Column(name = "COUNTRY_OF_RESIDENCE", nullable = false, length = 2)
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
   * @return the identityName
   */
  @Column(name = "IDENTITY_NAME", nullable = true, length = 255)
  public String getIdentityName() {
    return identityName;
  }

  /**
   * @param identityName the identityName to set
   */
  public void setIdentityName(String identityName) {
    this.identityName = identityName;
  }

  /**
   * @return the identityNo
   */
  @Column(name = "IDENTITY_NO", nullable = true, length = 30)
  public String getIdentityNo() {
    return identityNo;
  }

  /**
   * @param identityNo the identityNo to set
   */
  public void setIdentityNo(String identityNo) {
    this.identityNo = identityNo;
  }

  /**
   * @return the addressType
   */
  @Column(name = "ADDRESS_TYPE", nullable = false, length = 50)
  public String getAddressType() {
    return addressType;
  }

  /**
   * @param addressType the addressType to set
   */
  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  /**
   * @return the blockNo
   */
  @Column(name = "BLOCK_NO", nullable = true, length = 255)
  public String getBlockNo() {
    return blockNo;
  }

  /**
   * @param blockNo the blockNo to set
   */
  public void setBlockNo(String blockNo) {
    this.blockNo = blockNo;
  }

  /**
   * @return the unitNo
   */
  @Column(name = "UNIT_NO", nullable = true, length = 255)
  public String getUnitNo() {
    return unitNo;
  }

  /**
   * @param unitNo the unitNo to set
   */
  public void setUnitNo(String unitNo) {
    this.unitNo = unitNo;
  }

  /**
   * @return the buildingName
   */
  @Column(name = "BUILDING_NAME", nullable = true, length = 285)
  public String getBuildingName() {
    return buildingName;
  }

  /**
   * @param buildingName the buildingName to set
   */
  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  /**
   * @return the postCode
   */
  @Column(name = "POST_CODE", nullable = true, length = 25)
  public String getPostCode() {
    return postCode;
  }

  /**
   * @param postCode the postCode to set
   */
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  /**
   * @return the country
   */
  @Column(name = "COUNTRY", nullable = true, length = 2)
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return the isBillingSameAsMailing
   */
  @Column(name = "IS_BILLING_SAME_AS_MAILING", nullable = true, length = 1)
  public String getIsBillingSameAsMailing() {
    return isBillingSameAsMailing;
  }

  /**
   * @param isBillingSameAsMailing the isBillingSameAsMailing to set
   */
  public void setIsBillingSameAsMailing(String isBillingSameAsMailing) {
    this.isBillingSameAsMailing = isBillingSameAsMailing;
  }

  /**
   * @return the patronProfileId
   */
  @Column(name = "PATRON_PROFILE_ID", nullable = false, length = 10)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  /**
   * @param patronProfileId the patronProfileId to set
   */
  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronProfileView that = (PatronProfileView) o;
    return new EqualsBuilder().append(title, that.getTitle()).append(firstName, that.getFirstName())
        .append(lastName, that.getLastName()).append(emailAddress, that.getEmailAddress())
        .append(yearOfDob, that.getYearOfDob()).append(phoneType, that.getPhoneType())
        .append(phoneNumber, that.getPhoneNumber()).append(nationality, that.getNationality())
        .append(countryCode, that.getCountryCode())
        .append(countryCallingCode, that.getCountryCallingCode())
        .append(countryOfResidence, that.getCountryOfResidence())
        .append(identityName, that.getIdentityName()).append(identityNo, that.getIdentityNo())
        .append(addressType, that.getAddressType()).append(blockNo, that.getBlockNo())
        .append(unitNo, that.getUnitNo()).append(buildingName, that.getBuildingName())
        .append(postCode, that.getPostCode()).append(country, that.getCountry())
        .append(isBillingSameAsMailing, that.getIsBillingSameAsMailing())
        .append(acctNum, that.getAcctNum()).append(patronProfileId, that.getPatronProfileId())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(title).append(firstName).append(lastName)
        .append(emailAddress).append(title).append(yearOfDob).append(phoneType).append(phoneNumber)
        .append(nationality).append(countryCode).append(countryCallingCode)
        .append(countryOfResidence).append(identityName).append(identityNo).append(addressType)
        .append(blockNo).append(unitNo).append(buildingName).append(postCode).append(country)
        .append(isBillingSameAsMailing).append(acctNum).append(patronProfileId).toHashCode();
  }

}
