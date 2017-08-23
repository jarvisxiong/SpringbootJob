package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "DELIVERY_METHOD_TEMPLATE_LIVE")
public class DeliveryMethodTemplateLive {
  @Id
  private Integer deliverymethodtemplateid;
  private Integer completestatus;
  private String deliverymethodtemplatename;
  private Integer globaltemplateid;
  private Timestamp createddate;
  private Integer createdBy;
  private Timestamp updateddate;
  private Integer updatedBy;

  public DeliveryMethodTemplateLive() {
  }

  @Id
  @Column(name = "DELIVERYMETHODTEMPLATEID", nullable = false, precision = 0)
  public Integer getDeliverymethodtemplateid() {
    return deliverymethodtemplateid;
  }

  public void setDeliverymethodtemplateid(Integer deliverymethodtemplateid) {
    this.deliverymethodtemplateid = deliverymethodtemplateid;
  }

  @Column(name = "COMPLETESTATUS", nullable = true, precision = 0)
  public Integer getCompletestatus() {
    return completestatus;
  }

  public void setCompletestatus(Integer completestatus) {
    this.completestatus = completestatus;
  }

  @Column(name = "DELIVERYMETHODTEMPLATENAME", nullable = false, length = 150)
  public String getDeliverymethodtemplatename() {
    return deliverymethodtemplatename;
  }

  public void setDeliverymethodtemplatename(String deliverymethodtemplatename) {
    this.deliverymethodtemplatename = deliverymethodtemplatename;
  }

  @Column(name = "GLOBALTEMPLATEID", nullable = true, precision = 0)
  public Integer getGlobaltemplateid() {
    return globaltemplateid;
  }

  public void setGlobaltemplateid(Integer globaltemplateid) {
    this.globaltemplateid = globaltemplateid;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public Timestamp getCreateddate() {
    return createddate;
  }

  public void setCreateddate(Timestamp createddate) {
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
  public Timestamp getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(Timestamp updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryMethodTemplateLive that = (DeliveryMethodTemplateLive) o;

    return Objects.equals(this.deliverymethodtemplateid, that.deliverymethodtemplateid) &&
        Objects.equals(this.completestatus, that.completestatus) &&
        Objects.equals(this.deliverymethodtemplatename, that.deliverymethodtemplatename) &&
        Objects.equals(this.globaltemplateid, that.globaltemplateid) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliverymethodtemplateid, completestatus, deliverymethodtemplatename,
        globaltemplateid, createddate, createdBy,
        updateddate, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("deliverymethodtemplateid", deliverymethodtemplateid)
        .append("completestatus", completestatus)
        .append("deliverymethodtemplatename", deliverymethodtemplatename)
        .append("globaltemplateid", globaltemplateid)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
