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
@Table(name = "PMT_COLUMN_LIVE")
public class PmtColumnLive implements Serializable {

  private static final long serialVersionUID = 2542992345084204125L;
  private Long pmtcolumnid;
  private Integer orderno;
  private Long pricecategoryId;
  private Long pricemodeltemplateId;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  public PmtColumnLive() {
  }

  public PmtColumnLive(Long pmtcolumnid, Integer orderno, Long pricecategoryId,
                       Long pricemodeltemplateId, OffsetDateTime createddate, Long createdBy,
                       OffsetDateTime updateddate, Long updatedBy) {
    this.pmtcolumnid = pmtcolumnid;
    this.orderno = orderno;
    this.pricecategoryId = pricecategoryId;
    this.pricemodeltemplateId = pricemodeltemplateId;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PMTCOLUMNID", nullable = false, precision = 0)
  public Long getPmtcolumnid() {
    return pmtcolumnid;
  }

  public void setPmtcolumnid(Long pmtcolumnid) {
    this.pmtcolumnid = pmtcolumnid;
  }

  @Column(name = "ORDERNO", nullable = false, precision = 0)
  public Integer getOrderno() {
    return orderno;
  }

  public void setOrderno(Integer orderno) {
    this.orderno = orderno;
  }

  @Column(name = "PRICECATEGORY_ID", nullable = false, precision = 0)
  public Long getPricecategoryId() {
    return pricecategoryId;
  }

  public void setPricecategoryId(Long pricecategoryId) {
    this.pricecategoryId = pricecategoryId;
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

    PmtColumnLive that = (PmtColumnLive) o;

    return new EqualsBuilder()
        .append(pmtcolumnid, that.pmtcolumnid)
        .append(orderno, that.orderno)
        .append(pricecategoryId, that.pricecategoryId)
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
        .append(pmtcolumnid)
        .append(orderno)
        .append(pricecategoryId)
        .append(pricemodeltemplateId)
        .append(createddate)
        .append(createdBy)
        .append(updateddate)
        .append(updatedBy)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("pmtcolumnid", pmtcolumnid)
        .append("orderno", orderno)
        .append("pricecategoryId", pricecategoryId)
        .append("pricemodeltemplateId", pricemodeltemplateId)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
