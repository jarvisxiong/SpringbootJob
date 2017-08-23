package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 23/12/2016.
 */
@Entity
@Table(name = "ORDER_DELIVERY_INFO")
public class OrderDeliveryInfo {
  private Long orderdeliveryinfoid;
  private Long deliveryMethod;
  private Integer addressType;
  private String addrline1;
  private String addrline2;
  private String addrline3;
  private String postalcode;
  private String city;
  private String state;
  private String country;
  private String email;
  private String mobilenum;

  @Id
  @Column(name = "ORDERDELIVERYINFOID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "orderDeliveryInfoIdSeq")
  @GenericGenerator(name = "orderDeliveryInfoIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "ORDER_DELIVERY_INFO_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getOrderdeliveryinfoid() {
    return orderdeliveryinfoid;
  }

  public void setOrderdeliveryinfoid(Long orderdeliveryinfoid) {
    this.orderdeliveryinfoid = orderdeliveryinfoid;
  }

  @Column(name = "DELIVERY_METHOD", nullable = false, precision = 0)
  public Long getDeliveryMethod() {
    return deliveryMethod;
  }

  public void setDeliveryMethod(Long deliveryMethod) {
    this.deliveryMethod = deliveryMethod;
  }

  @Column(name = "ADDRESS_TYPE", nullable = true, precision = 0)
  public Integer getAddressType() {
    return addressType;
  }

  public void setAddressType(Integer addressType) {
    this.addressType = addressType;
  }

  @Column(name = "ADDRLINE1", nullable = true, length = 255)
  public String getAddrline1() {
    return addrline1;
  }

  public void setAddrline1(String addrline1) {
    this.addrline1 = addrline1;
  }

  @Column(name = "ADDRLINE2", nullable = true, length = 255)
  public String getAddrline2() {
    return addrline2;
  }

  public void setAddrline2(String addrline2) {
    this.addrline2 = addrline2;
  }

  @Column(name = "ADDRLINE3", nullable = true, length = 255)
  public String getAddrline3() {
    return addrline3;
  }

  public void setAddrline3(String addrline3) {
    this.addrline3 = addrline3;
  }

  @Column(name = "POSTALCODE", nullable = true, length = 15)
  public String getPostalcode() {
    return postalcode;
  }

  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }

  @Column(name = "CITY", nullable = true, length = 50)
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

  @Column(name = "COUNTRY", nullable = true, length = 50)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Column(name = "EMAIL", nullable = true, length = 255)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "MOBILENUM", nullable = true, length = 50)
  public String getMobilenum() {
    return mobilenum;
  }

  public void setMobilenum(String mobilenum) {
    this.mobilenum = mobilenum;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    OrderDeliveryInfo that = (OrderDeliveryInfo) o;

    return new EqualsBuilder()
        .append(orderdeliveryinfoid, that.orderdeliveryinfoid)
        .append(deliveryMethod, that.deliveryMethod)
        .append(addressType, that.addressType)
        .append(addrline1, that.addrline1)
        .append(addrline2, that.addrline2)
        .append(addrline3, that.addrline3)
        .append(postalcode, that.postalcode)
        .append(city, that.city)
        .append(state, that.state)
        .append(country, that.country)
        .append(email, that.email)
        .append(mobilenum, that.mobilenum)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(orderdeliveryinfoid)
        .append(deliveryMethod)
        .append(addressType)
        .append(addrline1)
        .append(addrline2)
        .append(addrline3)
        .append(postalcode)
        .append(city)
        .append(state)
        .append(country)
        .append(email)
        .append(mobilenum)
        .toHashCode();
  }
}
