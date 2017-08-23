package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/1/2016.
 */
@Entity
@Table(name = "PATRON_PROFILE")
public class PatronProfile {
  private Long patronprofileid;
  private String identityno;
  private Long accno;
  private Integer status;
  private String firstname;
  private String lastname;
  private String emailaddress;
  private Boolean isreceiveticketingagent;
  private Boolean isreceivepromoter;
  private Boolean isreceivevenue;
  private Boolean isbillingaddresssameasmailing;
  private Boolean isdonotsignup;
  private String organizationname;
  private String companyid;
  private String companyname;
  private String employeeid;
  private String guestof;
  private String externalid;
  private String acctremark;
  private Long accTypeMctId;
  private Long genderMctId;
  private Long titleMctId;
  private String nationality;
  private String countryofresidence;
  private OffsetDateTime createddate;
  private Integer createdBy;
  private OffsetDateTime updateddate;
  private Integer updatedBy;
  private Long identityNoTypeMctId;
  private String preferLanguage;
  private String extCustId;

  public PatronProfile() {}

  /**
   * 
   * @param patronprofileid
   * @param identityno
   * @param accno
   * @param status
   * @param firstname
   * @param lastname
   * @param emailaddress
   * @param isreceiveticketingagent
   * @param isreceivepromoter
   * @param isreceivevenue
   * @param isbillingaddresssameasmailing
   * @param isdonotsignup
   * @param organizationname
   * @param companyid
   * @param companyname
   * @param employeeid
   * @param guestof
   * @param externalid
   * @param acctremark
   * @param accTypeMctId
   * @param genderMctId
   * @param titleMctId
   * @param nationality
   * @param countryofresidence
   * @param createddate
   * @param createdBy
   * @param updateddate
   * @param updatedBy
   * @param identityNoTypeMctId
   * @param preferLanguage
   * @param externalCustomerId
   */
  public PatronProfile(Long patronprofileid, String identityno, Long accno, Integer status,
      String firstname, String lastname, String emailaddress, Boolean isreceiveticketingagent,
      Boolean isreceivepromoter, Boolean isreceivevenue, Boolean isbillingaddresssameasmailing,
      Boolean isdonotsignup, String organizationname, String companyid, String companyname,
      String employeeid, String guestof, String externalid, String acctremark, Long accTypeMctId,
      Long genderMctId, Long titleMctId, String nationality, String countryofresidence,
      OffsetDateTime createddate, Integer createdBy, OffsetDateTime updateddate, Integer updatedBy,
      Long identityNoTypeMctId, String preferLanguage, String externalCustomerId) {
    this.patronprofileid = patronprofileid;
    this.identityno = identityno;
    this.accno = accno;
    this.status = status;
    this.firstname = firstname;
    this.lastname = lastname;
    this.emailaddress = emailaddress;
    this.isreceiveticketingagent = isreceiveticketingagent;
    this.isreceivepromoter = isreceivepromoter;
    this.isreceivevenue = isreceivevenue;
    this.isbillingaddresssameasmailing = isbillingaddresssameasmailing;
    this.isdonotsignup = isdonotsignup;
    this.organizationname = organizationname;
    this.companyid = companyid;
    this.companyname = companyname;
    this.employeeid = employeeid;
    this.guestof = guestof;
    this.externalid = externalid;
    this.acctremark = acctremark;
    this.accTypeMctId = accTypeMctId;
    this.genderMctId = genderMctId;
    this.titleMctId = titleMctId;
    this.nationality = nationality;
    this.countryofresidence = countryofresidence;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
    this.identityNoTypeMctId = identityNoTypeMctId;
    this.preferLanguage = preferLanguage;
    this.extCustId = externalCustomerId;
  }

  /**
   * 
   * @param identityno
   * @param accno
   * @param status
   * @param firstname
   * @param lastname
   * @param emailaddress
   * @param isreceiveticketingagent
   * @param isreceivepromoter
   * @param isreceivevenue
   * @param isbillingaddresssameasmailing
   * @param isdonotsignup
   * @param organizationname
   * @param companyid
   * @param companyname
   * @param employeeid
   * @param guestof
   * @param externalid
   * @param acctremark
   * @param accTypeMctId
   * @param genderMctId
   * @param titleMctId
   * @param nationality
   * @param countryofresidence
   * @param createddate
   * @param createdBy
   * @param updateddate
   * @param updatedBy
   * @param identityNoTypeMctId
   * @param preferLanguage
   * @param externalCustomerId
   */
  public PatronProfile(String identityno, Long accno, Integer status, String firstname,
      String lastname, String emailaddress, Boolean isreceiveticketingagent,
      Boolean isreceivepromoter, Boolean isreceivevenue, Boolean isbillingaddresssameasmailing,
      Boolean isdonotsignup, String organizationname, String companyid, String companyname,
      String employeeid, String guestof, String externalid, String acctremark, Long accTypeMctId,
      Long genderMctId, Long titleMctId, String nationality, String countryofresidence,
      OffsetDateTime createddate, Integer createdBy, OffsetDateTime updateddate, Integer updatedBy,
      Long identityNoTypeMctId, String preferLanguage, String externalCustomerId) {
    this.identityno = identityno;
    this.accno = accno;
    this.status = status;
    this.firstname = firstname;
    this.lastname = lastname;
    this.emailaddress = emailaddress;
    this.isreceiveticketingagent = isreceiveticketingagent;
    this.isreceivepromoter = isreceivepromoter;
    this.isreceivevenue = isreceivevenue;
    this.isbillingaddresssameasmailing = isbillingaddresssameasmailing;
    this.isdonotsignup = isdonotsignup;
    this.organizationname = organizationname;
    this.companyid = companyid;
    this.companyname = companyname;
    this.employeeid = employeeid;
    this.guestof = guestof;
    this.externalid = externalid;
    this.acctremark = acctremark;
    this.accTypeMctId = accTypeMctId;
    this.genderMctId = genderMctId;
    this.titleMctId = titleMctId;
    this.nationality = nationality;
    this.countryofresidence = countryofresidence;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
    this.identityNoTypeMctId = identityNoTypeMctId;
    this.preferLanguage = preferLanguage;
    this.extCustId = externalCustomerId;
  }

  @Id
  @Column(name = "PATRONPROFILEID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATRON_PROFILE_ID_SEQ")
  @GenericGenerator(name = "PATRON_PROFILE_ID_SEQ",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "PATRON_PROFILE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")})
  public Long getPatronprofileid() {
    return patronprofileid;
  }

  public void setPatronprofileid(Long patronprofileid) {
    this.patronprofileid = patronprofileid;
  }

  @Column(name = "IDENTITYNO", nullable = true, length = 30)
  public String getIdentityno() {
    return identityno;
  }

  public void setIdentityno(String identityno) {
    this.identityno = identityno;
  }

  @Column(name = "ACCNO", nullable = false, precision = 0)
  public Long getAccno() {
    return accno;
  }

  public void setAccno(Long accno) {
    this.accno = accno;
  }

  @Column(name = "STATUS", nullable = false, precision = 1)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Column(name = "FIRSTNAME", nullable = false, length = 100)
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  @Column(name = "LASTNAME", nullable = false, length = 100)
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Column(name = "EMAILADDRESS", nullable = true, length = 75)
  public String getEmailaddress() {
    return emailaddress;
  }

  public void setEmailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
  }

  @Column(name = "ISRECEIVETICKETINGAGENT", nullable = false, length = 1)
  @Type(type = "true_false")
  public Boolean getIsreceiveticketingagent() {
    return isreceiveticketingagent;
  }

  public void setIsreceiveticketingagent(Boolean isreceiveticketingagent) {
    this.isreceiveticketingagent = isreceiveticketingagent;
  }

  @Column(name = "ISRECEIVEPROMOTER", nullable = false, length = 1)
  @Type(type = "true_false")
  public Boolean getIsreceivepromoter() {
    return isreceivepromoter;
  }

  public void setIsreceivepromoter(Boolean isreceivepromoter) {
    this.isreceivepromoter = isreceivepromoter;
  }

  @Column(name = "ISRECEIVEVENUE", nullable = false, length = 1)
  @Type(type = "true_false")
  public Boolean getIsreceivevenue() {
    return isreceivevenue;
  }

  public void setIsreceivevenue(Boolean isreceivevenue) {
    this.isreceivevenue = isreceivevenue;
  }

  @Column(name = "ISBILLINGADDRESSSAMEASMAILING", nullable = true, length = 1)
  @Type(type = "true_false")
  public Boolean getIsbillingaddresssameasmailing() {
    return isbillingaddresssameasmailing;
  }

  public void setIsbillingaddresssameasmailing(Boolean isbillingaddresssameasmailing) {
    this.isbillingaddresssameasmailing = isbillingaddresssameasmailing;
  }

  @Column(name = "ISDONOTSIGNUP", nullable = true, length = 1)
  @Type(type = "true_false")
  public Boolean getIsdonotsignup() {
    return isdonotsignup;
  }

  public void setIsdonotsignup(Boolean isdonotsignup) {
    this.isdonotsignup = isdonotsignup;
  }

  @Column(name = "ORGANIZATIONNAME", nullable = true, length = 150)
  public String getOrganizationname() {
    return organizationname;
  }

  public void setOrganizationname(String organizationname) {
    this.organizationname = organizationname;
  }

  @Column(name = "COMPANYID", nullable = true, length = 255)
  public String getCompanyid() {
    return companyid;
  }

  public void setCompanyid(String companyid) {
    this.companyid = companyid;
  }

  @Column(name = "COMPANYNAME", nullable = true, length = 255)
  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }

  @Column(name = "EMPLOYEEID", nullable = true, length = 150)
  public String getEmployeeid() {
    return employeeid;
  }

  public void setEmployeeid(String employeeid) {
    this.employeeid = employeeid;
  }

  @Column(name = "GUESTOF", nullable = true, length = 255)
  public String getGuestof() {
    return guestof;
  }

  public void setGuestof(String guestof) {
    this.guestof = guestof;
  }

  @Column(name = "EXTERNALID", nullable = true, length = 255)
  public String getExternalid() {
    return externalid;
  }

  public void setExternalid(String externalid) {
    this.externalid = externalid;
  }

  @Column(name = "ACCTREMARK", nullable = true, length = 1000)
  public String getAcctremark() {
    return acctremark;
  }

  public void setAcctremark(String acctremark) {
    this.acctremark = acctremark;
  }

  @Column(name = "ACC_TYPE_MCT_ID", nullable = false, precision = 0)
  public Long getAccTypeMctId() {
    return accTypeMctId;
  }

  public void setAccTypeMctId(Long accTypeMctId) {
    this.accTypeMctId = accTypeMctId;
  }

  @Column(name = "GENDER_MCT_ID", nullable = true, precision = 0)
  public Long getGenderMctId() {
    return genderMctId;
  }

  public void setGenderMctId(Long genderMctId) {
    this.genderMctId = genderMctId;
  }

  @Column(name = "TITLE_MCT_ID", nullable = true, precision = 0)
  public Long getTitleMctId() {
    return titleMctId;
  }

  public void setTitleMctId(Long titleMctId) {
    this.titleMctId = titleMctId;
  }

  @Column(name = "NATIONALITY", nullable = false, length = 2)
  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Column(name = "COUNTRYOFRESIDENCE", nullable = false, length = 2)
  public String getCountryofresidence() {
    return countryofresidence;
  }

  public void setCountryofresidence(String countryofresidence) {
    this.countryofresidence = countryofresidence;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "IDENTITY_NO_TYPE_MCT_ID", nullable = false, precision = 0)
  public Long getIdentityNoTypeMctId() {
    return identityNoTypeMctId;
  }

  public void setIdentityNoTypeMctId(Long identityNoTypeMctId) {
    this.identityNoTypeMctId = identityNoTypeMctId;
  }

  @Column(name = "PREFER_LANGUAGE", nullable = true, length = 255)
  public String getPreferLanguage() {
	return preferLanguage;
  }

  public void setPreferLanguage(String preferLanguage) {
	this.preferLanguage = preferLanguage;
  }

  @Column(name = "EXT_CUST_ID", nullable = true, length = 100)
  public String getExtCustId() {
	  return extCustId;
  }

  public void setExtCustId(String extCustId) {
	  this.extCustId = extCustId;
  }

@Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatronProfile that = (PatronProfile) o;
    return Objects.equals(patronprofileid, that.patronprofileid)
        && Objects.equals(identityno, that.identityno) && Objects.equals(accno, that.accno)
        && Objects.equals(status, that.status) && Objects.equals(firstname, that.firstname)
        && Objects.equals(lastname, that.lastname)
        && Objects.equals(emailaddress, that.emailaddress)
        && Objects.equals(isreceiveticketingagent, that.isreceiveticketingagent)
        && Objects.equals(isreceivepromoter, that.isreceivepromoter)
        && Objects.equals(isreceivevenue, that.isreceivevenue)
        && Objects.equals(isbillingaddresssameasmailing, that.isbillingaddresssameasmailing)
        && Objects.equals(isdonotsignup, that.isdonotsignup)
        && Objects.equals(organizationname, that.organizationname)
        && Objects.equals(companyid, that.companyid)
        && Objects.equals(companyname, that.companyname)
        && Objects.equals(employeeid, that.employeeid) && Objects.equals(guestof, that.guestof)
        && Objects.equals(externalid, that.externalid)
        && Objects.equals(acctremark, that.acctremark)
        && Objects.equals(accTypeMctId, that.accTypeMctId)
        && Objects.equals(genderMctId, that.genderMctId)
        && Objects.equals(titleMctId, that.titleMctId)
        && Objects.equals(nationality, that.nationality)
        && Objects.equals(countryofresidence, that.countryofresidence)
        && Objects.equals(identityNoTypeMctId, that.identityNoTypeMctId)
        && Objects.equals(preferLanguage, that.preferLanguage)
//        && Objects.equals(externalCustomerId, that.externalCustomerId)
        ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(patronprofileid, identityno, accno, status, firstname, lastname,
        emailaddress, isreceiveticketingagent, isreceivepromoter, isreceivevenue,
        isbillingaddresssameasmailing, isdonotsignup, organizationname, companyid, companyname,
        employeeid, guestof, externalid, acctremark, accTypeMctId, genderMctId, titleMctId,
        nationality, countryofresidence, createddate, createdBy, updateddate, updatedBy,
        identityNoTypeMctId,preferLanguage
        //,externalCustomerId
        );
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("patronprofileid", patronprofileid).append("identityno", identityno)
        .append("accno", accno).append("status", status).append("firstname", firstname)
        .append("lastname", lastname).append("emailaddress", emailaddress)
        .append("isreceiveticketingagent", isreceiveticketingagent)
        .append("isreceivepromoter", isreceivepromoter).append("isreceivevenue", isreceivevenue)
        .append("isbillingaddresssameasmailing", isbillingaddresssameasmailing)
        .append("isdonotsignup", isdonotsignup).append("organizationname", organizationname)
        .append("companyid", companyid).append("companyname", companyname)
        .append("employeeid", employeeid).append("guestof", guestof)
        .append("externalid", externalid).append("acctremark", acctremark)
        .append("accTypeMctId", accTypeMctId).append("genderMctId", genderMctId)
        .append("titleMctId", titleMctId).append("nationality", nationality)
        .append("countryofresidence", countryofresidence).append("createddate", createddate)
        .append("createdBy", createdBy).append("updateddate", updateddate)
        .append("updatedBy", updatedBy).append("identityNoTypeMctId", identityNoTypeMctId)
        .append("preferLanguage", preferLanguage)
        //.append("externalCustomerId", externalCustomerId)
        .toString();
  }
}
