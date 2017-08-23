package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 06-Sep-16.
 */
@Entity
@Table(name = "PRICE_MODEL_TEMPLATE_LIVE")
public class PriceModelTemplateLive implements Serializable {

  private static final long serialVersionUID = 8973588073345267283L;
  private Long pricemodeltemplateid;
  private Integer completestatus;
  private String pricemodeltemplatename;
  private Long sellabilitytemplateid;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  public PriceModelTemplateLive() {
  }

  public PriceModelTemplateLive(Long pricemodeltemplateid, Integer completestatus,
                                String pricemodeltemplatename, Long sellabilitytemplateid,
                                OffsetDateTime createddate, Long createdBy,
                                OffsetDateTime updateddate, Long updatedBy) {
    this.pricemodeltemplateid = pricemodeltemplateid;
    this.completestatus = completestatus;
    this.pricemodeltemplatename = pricemodeltemplatename;
    this.sellabilitytemplateid = sellabilitytemplateid;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PRICEMODELTEMPLATEID", nullable = false, precision = 0)
  public Long getPricemodeltemplateid() {
    return pricemodeltemplateid;
  }

  public void setPricemodeltemplateid(Long pricemodeltemplateid) {
    this.pricemodeltemplateid = pricemodeltemplateid;
  }


  @Column(name = "COMPLETESTATUS", nullable = true, precision = 0)
  public Integer getCompletestatus() {
    return completestatus;
  }

  public void setCompletestatus(Integer completestatus) {
    this.completestatus = completestatus;
  }


  @Column(name = "PRICEMODELTEMPLATENAME", nullable = false, length = 150)
  public String getPricemodeltemplatename() {
    return pricemodeltemplatename;
  }

  public void setPricemodeltemplatename(String pricemodeltemplatename) {
    this.pricemodeltemplatename = pricemodeltemplatename;
  }


  @Column(name = "SELLABILITYTEMPLATEID", nullable = true, precision = 0)
  public Long getSellabilitytemplateid() {
    return sellabilitytemplateid;
  }

  public void setSellabilitytemplateid(Long sellabilitytemplateid) {
    this.sellabilitytemplateid = sellabilitytemplateid;
  }


  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }


  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }


  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }


  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
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

    PriceModelTemplateLive that = (PriceModelTemplateLive) o;

    return new EqualsBuilder()
        .append(pricemodeltemplateid, that.pricemodeltemplateid)
        .append(completestatus, that.completestatus)
        .append(pricemodeltemplatename, that.pricemodeltemplatename)
        .append(sellabilitytemplateid, that.sellabilitytemplateid)
        .append(createddate, that.createddate)
        .append(createdBy, that.createdBy)
        .append(updateddate, that.updateddate)
        .append(updatedBy, that.updatedBy)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(pricemodeltemplateid)
        .append(completestatus)
        .append(pricemodeltemplatename)
        .append(sellabilitytemplateid)
        .append(createddate)
        .append(createdBy)
        .append(updateddate)
        .append(updatedBy)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("pricemodeltemplateid", pricemodeltemplateid)
        .append("completestatus", completestatus)
        .append("pricemodeltemplatename", pricemodeltemplatename)
        .append("sellabilitytemplateid", sellabilitytemplateid)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
