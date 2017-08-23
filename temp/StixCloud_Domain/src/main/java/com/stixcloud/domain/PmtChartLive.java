package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 27/12/2016.
 */
@Entity
@Table(name = "PMT_CHART_LIVE")
public class PmtChartLive {
  private Long pmtchartid;
  private Boolean isapplicable;
  private Integer quantitystatus;
  private Integer quantity;
  private Integer maximumquantity;
  private Integer quota;
  private BigDecimal pricevalue;
  private Long pmtrowId;
  private Long pmtcolumnId;
  private Long pricemodeltemplateId;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  @Id
  @Column(name = "PMTCHARTID", nullable = false, precision = 0)
  public Long getPmtchartid() {
    return pmtchartid;
  }

  public void setPmtchartid(Long pmtchartid) {
    this.pmtchartid = pmtchartid;
  }

  @Type(type = "true_false")
  @Column(name = "ISAPPLICABLE", nullable = false, length = 1)
  public Boolean getIsapplicable() {
    return isapplicable;
  }

  public void setIsapplicable(Boolean isapplicable) {
    this.isapplicable = isapplicable;
  }

  @Column(name = "QUANTITYSTATUS", nullable = false, precision = 0)
  public Integer getQuantitystatus() {
    return quantitystatus;
  }

  public void setQuantitystatus(Integer quantitystatus) {
    this.quantitystatus = quantitystatus;
  }

  @Column(name = "QUANTITY", nullable = false, precision = 0)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Column(name = "MAXIMUMQUANTITY", nullable = false, precision = 0)
  public Integer getMaximumquantity() {
    return maximumquantity;
  }

  public void setMaximumquantity(Integer maximumquantity) {
    this.maximumquantity = maximumquantity;
  }

  @Column(name = "QUOTA", nullable = true, precision = 0)
  public Integer getQuota() {
    return quota;
  }

  public void setQuota(Integer quota) {
    this.quota = quota;
  }

  @Column(name = "PRICEVALUE", nullable = false, precision = 5)
  public BigDecimal getPricevalue() {
    return pricevalue;
  }

  public void setPricevalue(BigDecimal pricevalue) {
    this.pricevalue = pricevalue;
  }

  @Column(name = "PMTROW_ID", nullable = false, precision = 0)
  public Long getPmtrowId() {
    return pmtrowId;
  }

  public void setPmtrowId(Long pmtrowId) {
    this.pmtrowId = pmtrowId;
  }

  @Column(name = "PMTCOLUMN_ID", nullable = false, precision = 0)
  public Long getPmtcolumnId() {
    return pmtcolumnId;
  }

  public void setPmtcolumnId(Long pmtcolumnId) {
    this.pmtcolumnId = pmtcolumnId;
  }

  @Column(name = "PRICEMODELTEMPLATE_ID", nullable = false, precision = 0)
  public Long getPricemodeltemplateId() {
    return pricemodeltemplateId;
  }

  public void setPricemodeltemplateId(Long pricemodeltemplateId) {
    this.pricemodeltemplateId = pricemodeltemplateId;
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

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
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

    PmtChartLive that = (PmtChartLive) o;

    return new EqualsBuilder()
        .append(pmtchartid, that.pmtchartid)
        .append(isapplicable, that.isapplicable)
        .append(quantitystatus, that.quantitystatus)
        .append(quantity, that.quantity)
        .append(maximumquantity, that.maximumquantity)
        .append(quota, that.quota)
        .append(pricevalue, that.pricevalue)
        .append(pmtrowId, that.pmtrowId)
        .append(pmtcolumnId, that.pmtcolumnId)
        .append(pricemodeltemplateId, that.pricemodeltemplateId)
        .append(createddate, that.createddate)
        .append(createdBy, that.createdBy)
        .append(updateddate, that.updateddate)
        .append(updatedBy, that.updatedBy)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(pmtchartid)
        .append(isapplicable)
        .append(quantitystatus)
        .append(quantity)
        .append(maximumquantity)
        .append(quota)
        .append(pricevalue)
        .append(pmtrowId)
        .append(pmtcolumnId)
        .append(pricemodeltemplateId)
        .append(createddate)
        .append(createdBy)
        .append(updateddate)
        .append(updatedBy)
        .toHashCode();
  }
}
