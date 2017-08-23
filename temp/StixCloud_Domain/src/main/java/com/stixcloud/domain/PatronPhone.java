package com.stixcloud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * Created by chongye on 23/12/2016.
 */
@Entity
@Table(name = "PATRON_PHONE")
public class PatronPhone {
  private Long patronphoneid;
  private Boolean isprimary;
  private Long patronProfileId;
  private String phonetype;
  private Long countryId;
  private String areacode;
  private String phonenumber;

  public PatronPhone() {
  }

  public PatronPhone(Long patronphoneid, Boolean isprimary, Long patronProfileId, String phonetype,
      Long countryId, String areacode, String phonenumber) {
    this.patronphoneid = patronphoneid;
    this.isprimary = isprimary;
    this.patronProfileId = patronProfileId;
    this.phonetype = phonetype;
    this.countryId = countryId;
    this.areacode = areacode;
    this.phonenumber = phonenumber;
  }

  public PatronPhone(Boolean isprimary, Long patronProfileId, String phonetype,
      Long countryId, String areacode, String phonenumber) {
    this.isprimary = isprimary;
    this.patronProfileId = patronProfileId;
    this.phonetype = phonetype;
    this.countryId = countryId;
    this.areacode = areacode;
    this.phonenumber = phonenumber;
  }
  
  @Id
  @Column(name = "PATRONPHONEID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "patronPhoneIdSeq")
  @GenericGenerator(name = "patronPhoneIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "PATRON_PHONE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")})
  public Long getPatronphoneid() {
    return patronphoneid;
  }

  public void setPatronphoneid(Long patronphoneid) {
    this.patronphoneid = patronphoneid;
  }

  @Type(type = "true_false")
  @Column(name = "ISPRIMARY", nullable = false, length = 1)
  public Boolean getIsprimary() {
    return isprimary;
  }

  public void setIsprimary(Boolean isprimary) {
    this.isprimary = isprimary;
  }

  @Column(name = "PATRON_PROFILE_ID", nullable = false, precision = 0)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  @Column(name = "PHONETYPE", nullable = false, length = 50)
  public String getPhonetype() {
    return phonetype;
  }

  public void setPhonetype(String phonetype) {
    this.phonetype = phonetype;
  }

  @Column(name = "COUNTRY_ID", nullable = true, precision = 0)
  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  @Column(name = "AREACODE", nullable = true, length = 5)
  public String getAreacode() {
    return areacode;
  }

  public void setAreacode(String areacode) {
    this.areacode = areacode;
  }

  @Column(name = "PHONENUMBER", nullable = false, length = 20)
  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronPhone that = (PatronPhone) o;

    return new EqualsBuilder()
        .append(patronphoneid, that.patronphoneid)
        .append(isprimary, that.isprimary)
        .append(patronProfileId, that.patronProfileId)
        .append(phonetype, that.phonetype)
        .append(countryId, that.countryId)
        .append(areacode, that.areacode)
        .append(phonenumber, that.phonenumber)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(patronphoneid)
        .append(isprimary)
        .append(patronProfileId)
        .append(phonetype)
        .append(countryId)
        .append(areacode)
        .append(phonenumber)
        .toHashCode();
  }
}
