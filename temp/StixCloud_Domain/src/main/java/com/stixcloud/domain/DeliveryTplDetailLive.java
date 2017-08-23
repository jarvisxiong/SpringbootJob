package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "DELIVERY_TPL_DETAIL_LIVE")
public class DeliveryTplDetailLive {
  @Id
  private Long deliverytpldetailid;
  private Boolean isapplicable;
  private Boolean isallowforga;
  private OffsetDateTime expirydate;
  private Integer expiryoption;
  private Integer expiryrulevalue;
  private Integer expiryruleunit;
  private Integer expirywindow;
  private Integer expiryhours;
  private Integer expiryminutes;
  private Long deliveryMethodTemplateId;
  private Long deliveryMethodId;

  public DeliveryTplDetailLive() {
  }

  @Id
  @Column(name = "DELIVERYTPLDETAILID", nullable = false, precision = 0)
  public Long getDeliverytpldetailid() {
    return deliverytpldetailid;
  }

  public void setDeliverytpldetailid(Long deliverytpldetailid) {
    this.deliverytpldetailid = deliverytpldetailid;
  }

  @Type(type = "true_false")
  @Column(name = "ISAPPLICABLE", nullable = false, length = 1)
  public Boolean getIsapplicable() {
    return isapplicable;
  }

  public void setIsapplicable(Boolean isapplicable) {
    this.isapplicable = isapplicable;
  }

  @Type(type = "true_false")
  @Column(name = "ISALLOWFORGA", nullable = false, length = 1)
  public Boolean getIsallowforga() {
    return isallowforga;
  }

  public void setIsallowforga(Boolean isallowforga) {
    this.isallowforga = isallowforga;
  }

  @Column(name = "EXPIRYDATE", nullable = true)
  public OffsetDateTime getExpirydate() {
    return expirydate;
  }

  public void setExpirydate(OffsetDateTime expirydate) {
    this.expirydate = expirydate;
  }

  @Column(name = "EXPIRYOPTION", nullable = false, precision = 0)
  public Integer getExpiryoption() {
    return expiryoption;
  }

  public void setExpiryoption(Integer expiryoption) {
    this.expiryoption = expiryoption;
  }

  @Column(name = "EXPIRYRULEVALUE", nullable = true, precision = 0)
  public Integer getExpiryrulevalue() {
    return expiryrulevalue;
  }

  public void setExpiryrulevalue(Integer expiryrulevalue) {
    this.expiryrulevalue = expiryrulevalue;
  }

  @Column(name = "EXPIRYRULEUNIT", nullable = true, precision = 0)
  public Integer getExpiryruleunit() {
    return expiryruleunit;
  }

  public void setExpiryruleunit(Integer expiryruleunit) {
    this.expiryruleunit = expiryruleunit;
  }

  @Column(name = "EXPIRYWINDOW", nullable = true, precision = 0)
  public Integer getExpirywindow() {
    return expirywindow;
  }

  public void setExpirywindow(Integer expirywindow) {
    this.expirywindow = expirywindow;
  }

  @Column(name = "EXPIRYHOURS", nullable = true, precision = 0)
  public Integer getExpiryhours() {
    return expiryhours;
  }

  public void setExpiryhours(Integer expiryhours) {
    this.expiryhours = expiryhours;
  }

  @Column(name = "EXPIRYMINUTES", nullable = true, precision = 0)
  public Integer getExpiryminutes() {
    return expiryminutes;
  }

  public void setExpiryminutes(Integer expiryminutes) {
    this.expiryminutes = expiryminutes;
  }

  @Column(name = "DELIVERY_METHOD_TEMPLATE_ID", nullable = false, precision = 0)
  public Long getDeliveryMethodTemplateId() {
    return deliveryMethodTemplateId;
  }

  public void setDeliveryMethodTemplateId(Long deliveryMethodTemplateId) {
    this.deliveryMethodTemplateId = deliveryMethodTemplateId;
  }

  @Column(name = "DELIVERY_METHOD_ID", nullable = false, precision = 0)
  public Long getDeliveryMethodId() {
    return deliveryMethodId;
  }

  public void setDeliveryMethodId(Long deliveryMethodId) {
    this.deliveryMethodId = deliveryMethodId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryTplDetailLive that = (DeliveryTplDetailLive) o;

    return Objects.equals(this.deliverytpldetailid, that.deliverytpldetailid) &&
        Objects.equals(this.isapplicable, that.isapplicable) &&
        Objects.equals(this.isallowforga, that.isallowforga) &&
        Objects.equals(this.expirydate, that.expirydate) &&
        Objects.equals(this.expiryoption, that.expiryoption) &&
        Objects.equals(this.expiryrulevalue, that.expiryrulevalue) &&
        Objects.equals(this.expiryruleunit, that.expiryruleunit) &&
        Objects.equals(this.expirywindow, that.expirywindow) &&
        Objects.equals(this.expiryhours, that.expiryhours) &&
        Objects.equals(this.expiryminutes, that.expiryminutes) &&
        Objects.equals(this.deliveryMethodTemplateId, that.deliveryMethodTemplateId) &&
        Objects.equals(this.deliveryMethodId, that.deliveryMethodId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliverytpldetailid, isapplicable, isallowforga, expirydate, expiryoption,
        expiryrulevalue,
        expiryruleunit, expirywindow, expiryhours, expiryminutes, deliveryMethodTemplateId,
        deliveryMethodId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("deliverytpldetailid", deliverytpldetailid)
        .append("isapplicable", isapplicable)
        .append("isallowforga", isallowforga)
        .append("expirydate", expirydate)
        .append("expiryoption", expiryoption)
        .append("expiryrulevalue", expiryrulevalue)
        .append("expiryruleunit", expiryruleunit)
        .append("expirywindow", expirywindow)
        .append("expiryhours", expiryhours)
        .append("expiryminutes", expiryminutes)
        .append("deliveryMethodTemplateId", deliveryMethodTemplateId)
        .append("deliveryMethodId", deliveryMethodId)
        .toString();
  }
}
