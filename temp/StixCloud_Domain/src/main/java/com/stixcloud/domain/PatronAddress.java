package com.stixcloud.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Created by sengkai on 12/1/2016.
 */
@Entity
@Table(name = "PATRON_ADDRESS")
public class PatronAddress {
  private Long patronaddressid;
  private Long addressId;
  private Long patronProfileId;
  private String isprimary;

  public PatronAddress() {
  }

  public PatronAddress(Long patronaddressid, Long addressId, Long patronProfileId,
                       String isprimary) {
    this.patronaddressid = patronaddressid;
    this.addressId = addressId;
    this.patronProfileId = patronProfileId;
    this.isprimary = isprimary;
  }
  
  public PatronAddress(Long addressId, Long patronProfileId,
      String isprimary) {
    this.addressId = addressId;
    this.patronProfileId = patronProfileId;
    this.isprimary = isprimary;
  }

  @Id
  @Column(name = "PATRONADDRESSID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "patronAddressIdSeq")
  @GenericGenerator(name = "patronAddressIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
  parameters = {
      @Parameter(name = "sequence_name", value = "PATRON_ADDRESS_ID_SEQ"),
      @Parameter(name = "optimizer", value = "none")
  })
  public Long getPatronaddressid() {
    return patronaddressid;
  }

  public void setPatronaddressid(Long patronaddressid) {
    this.patronaddressid = patronaddressid;
  }

  @Column(name = "ADDRESS_ID", nullable = false, precision = 0)
  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  @Column(name = "PATRON_PROFILE_ID", nullable = false, precision = 0)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  @Column(name = "ISPRIMARY", nullable = false, length = 1)
  public String getIsprimary() {
    return isprimary;
  }

  public void setIsprimary(String isprimary) {
    this.isprimary = isprimary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatronAddress that = (PatronAddress) o;
    return Objects.equals(patronaddressid, that.patronaddressid) &&
        Objects.equals(addressId, that.addressId) &&
        Objects.equals(patronProfileId, that.patronProfileId) &&
        Objects.equals(isprimary, that.isprimary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(patronaddressid, addressId, patronProfileId, isprimary);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("patronaddressid", patronaddressid)
        .append("addressId", addressId)
        .append("patronProfileId", patronProfileId)
        .append("isprimary", isprimary)
        .toString();
  }
}
