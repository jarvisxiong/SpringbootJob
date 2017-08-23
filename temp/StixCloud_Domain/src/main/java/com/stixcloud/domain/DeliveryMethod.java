package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Objects;
import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "DELIVERY_METHOD")
public class DeliveryMethod implements Serializable {
  @Id
  private Long deliverymethodid;
  private Boolean addressrequired;
  private String deliverymethodname;
  private String deliverytype;
  private String description;
  private Boolean emailrequired;
  private Boolean mobilenorequired;
  private Long ordermethod;
  private String deliverymethodcode;
  private Integer tickettype;
  @Transient
  private MonetaryAmount charge;

  public DeliveryMethod() {
  }

  @Id
  @Column(name = "DELIVERYMETHODID", nullable = false, precision = 0)
  public Long getDeliverymethodid() {
    return deliverymethodid;
  }

  public void setDeliverymethodid(Long deliverymethodid) {
    this.deliverymethodid = deliverymethodid;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "ADDRESSREQUIRED", nullable = true, precision = 0)
  public Boolean getAddressrequired() {
    return addressrequired;
  }

  public void setAddressrequired(Boolean addressrequired) {
    this.addressrequired = addressrequired;
  }

  @Column(name = "DELIVERYMETHODNAME", nullable = false, length = 100)
  public String getDeliverymethodname() {
    return deliverymethodname;
  }

  public void setDeliverymethodname(String deliverymethodname) {
    this.deliverymethodname = deliverymethodname;
  }

  @Column(name = "DELIVERYTYPE", nullable = false, length = 50)
  public String getDeliverytype() {
    return deliverytype;
  }

  public void setDeliverytype(String deliverytype) {
    this.deliverytype = deliverytype;
  }

  @Column(name = "DESCRIPTION", nullable = true, length = 128)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "EMAILREQUIRED", nullable = true, precision = 0)
  public Boolean getEmailrequired() {
    return emailrequired;
  }

  public void setEmailrequired(Boolean emailrequired) {
    this.emailrequired = emailrequired;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "MOBILENOREQUIRED", nullable = true, precision = 0)
  public Boolean getMobilenorequired() {
    return mobilenorequired;
  }

  public void setMobilenorequired(Boolean mobilenorequired) {
    this.mobilenorequired = mobilenorequired;
  }

  @Column(name = "ORDERMETHOD", nullable = true, precision = 0)
  public Long getOrdermethod() {
    return ordermethod;
  }

  public void setOrdermethod(Long ordermethod) {
    this.ordermethod = ordermethod;
  }

  @Column(name = "DELIVERYMETHODCODE", nullable = false, length = 100)
  public String getDeliverymethodcode() {
    return deliverymethodcode;
  }

  public void setDeliverymethodcode(String deliverymethodcode) {
    this.deliverymethodcode = deliverymethodcode;
  }

  @Column(name = "TICKETTYPE", nullable = false, precision = 0)
  public Integer getTickettype() {
    return tickettype;
  }

  public void setTickettype(Integer tickettype) {
    this.tickettype = tickettype;
  }

  @Transient
  public MonetaryAmount getCharge() {
    return charge;
  }

  public void setCharge(MonetaryAmount charge) {
    this.charge = charge;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryMethod that = (DeliveryMethod) o;

    return Objects.equals(this.deliverymethodid, that.deliverymethodid) &&
        Objects.equals(this.addressrequired, that.addressrequired) &&
        Objects.equals(this.deliverymethodname, that.deliverymethodname) &&
        Objects.equals(this.deliverytype, that.deliverytype) &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.emailrequired, that.emailrequired) &&
        Objects.equals(this.mobilenorequired, that.mobilenorequired) &&
        Objects.equals(this.ordermethod, that.ordermethod) &&
        Objects.equals(this.deliverymethodcode, that.deliverymethodcode) &&
        Objects.equals(this.tickettype, that.tickettype);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(deliverymethodid, addressrequired, deliverymethodname, deliverytype, description,
            emailrequired,
            mobilenorequired, ordermethod, deliverymethodcode, tickettype);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("deliverymethodid", deliverymethodid)
        .append("addressrequired", addressrequired)
        .append("deliverymethodname", deliverymethodname)
        .append("deliverytype", deliverytype)
        .append("description", description)
        .append("emailrequired", emailrequired)
        .append("mobilenorequired", mobilenorequired)
        .append("ordermethod", ordermethod)
        .append("deliverymethodcode", deliverymethodcode)
        .append("tickettype", tickettype)
        .toString();
  }
}
