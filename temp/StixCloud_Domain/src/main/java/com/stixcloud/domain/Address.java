package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@Table(name = "ADDRESS")
public class Address {
  private Long addressid;
  private String addresstype;
  private String lineone;
  private String linetwo;
  private String linethree;
  private String city;
  private String state;
  private String country;
  private String postcode;
  private Integer createdBy;
  private OffsetDateTime createddate;
  private Integer updatedBy;
  private OffsetDateTime updateddate;

  public Address() {
  }

  public Address(Long addressid, String addresstype, String lineone, String linetwo,
                 String linethree, String city, String state, String country,
                 String postcode, Integer createdBy, OffsetDateTime createddate,
                 Integer updatedBy, OffsetDateTime updateddate) {
    this.addressid = addressid;
    this.addresstype = addresstype;
    this.lineone = lineone;
    this.linetwo = linetwo;
    this.linethree = linethree;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postcode = postcode;
    this.createdBy = createdBy;
    this.createddate = createddate;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
  }

  public Address(String addresstype, String lineone, String linetwo, String linethree, String city,
      String state, String country, String postcode, Integer createdBy, OffsetDateTime createddate,
      Integer updatedBy, OffsetDateTime updateddate) {
    this.addresstype = addresstype;
    this.lineone = lineone;
    this.linetwo = linetwo;
    this.linethree = linethree;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postcode = postcode;
    this.createdBy = createdBy;
    this.createddate = createddate;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
  }
  
  public Address(String lineone, String linetwo, String linethree, String city,
                 String state, String country, String postcode) {
    this.lineone = lineone;
    this.linetwo = linetwo;
    this.linethree = linethree;
    this.city = city;
    this.state = state;
    this.country = country;
    this.postcode = postcode;
  }

  @Id
  @Column(name = "ADDRESSID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "addressIdSeq")
  @GenericGenerator(name = "addressIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "ADDRESS_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")})
  public Long getAddressid() {
    return addressid;
  }

  public void setAddressid(Long addressid) {
    this.addressid = addressid;
  }

  @Column(name = "ADDRESSTYPE", nullable = false, length = 50)
  public String getAddresstype() {
    return addresstype;
  }

  public void setAddresstype(String addresstype) {
    this.addresstype = addresstype;
  }

  @Column(name = "LINEONE", nullable = true, length = 255)
  public String getLineone() {
    return lineone;
  }

  public void setLineone(String lineone) {
    this.lineone = lineone;
  }

  @Column(name = "LINETWO", nullable = true, length = 255)
  public String getLinetwo() {
    return linetwo;
  }

  public void setLinetwo(String linetwo) {
    this.linetwo = linetwo;
  }

  @Column(name = "LINETHREE", nullable = true, length = 285)
  public String getLinethree() {
    return linethree;
  }

  public void setLinethree(String linethree) {
    this.linethree = linethree;
  }

  @Column(name = "CITY", nullable = true, length = 255)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(name = "STATE", nullable = true, length = 50)
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Column(name = "COUNTRY", nullable = true, length = 2)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column(name = "POSTCODE", nullable = true, length = 25)
  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  @Column(name = "CREATED_BY", nullable = true, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = true)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(addressid, address.addressid) &&
        Objects.equals(addresstype, address.addresstype) &&
        Objects.equals(lineone, address.lineone) &&
        Objects.equals(linetwo, address.linetwo) &&
        Objects.equals(linethree, address.linethree) &&
        Objects.equals(city, address.city) &&
        Objects.equals(state, address.state) &&
        Objects.equals(country, address.country) &&
        Objects.equals(postcode, address.postcode) &&
        Objects.equals(createdBy, address.createdBy) &&
        Objects.equals(createddate, address.createddate) &&
        Objects.equals(updatedBy, address.updatedBy) &&
        Objects.equals(updateddate, address.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(addressid, addresstype, lineone, linetwo, linethree, city, state, country, postcode,
            createdBy, createddate, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("addressid", addressid)
        .append("addresstype", addresstype)
        .append("lineone", lineone)
        .append("linetwo", linetwo)
        .append("linethree", linethree)
        .append("city", city)
        .append("state", state)
        .append("country", country)
        .append("postcode", postcode)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
