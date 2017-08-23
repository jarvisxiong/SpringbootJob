package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 22/12/2016.
 */
@Entity
@Table(name = "PMT_ROW_LIVE")
public class PmtRowLive {
  private Long pmtrowid;
  private String ticketprintalias;
  private String isdisplayforsales;
  private String isstandard;
  private Integer ranking;
  private Integer quota;
  private Long priceclassId;
  private Long pricemodeltemplateId;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  @Id
  @Column(name = "PMTROWID", nullable = false, precision = 0)
  public Long getPmtrowid() {
    return pmtrowid;
  }

  public void setPmtrowid(Long pmtrowid) {
    this.pmtrowid = pmtrowid;
  }

  @Column(name = "TICKETPRINTALIAS", nullable = true, length = 100)
  public String getTicketprintalias() {
    return ticketprintalias;
  }

  public void setTicketprintalias(String ticketprintalias) {
    this.ticketprintalias = ticketprintalias;
  }

  @Column(name = "ISDISPLAYFORSALES", nullable = false, length = 1)
  public String getIsdisplayforsales() {
    return isdisplayforsales;
  }

  public void setIsdisplayforsales(String isdisplayforsales) {
    this.isdisplayforsales = isdisplayforsales;
  }

  @Column(name = "ISSTANDARD", nullable = false, length = 1)
  public String getIsstandard() {
    return isstandard;
  }

  public void setIsstandard(String isstandard) {
    this.isstandard = isstandard;
  }

  @Column(name = "RANKING", nullable = false, precision = 0)
  public Integer getRanking() {
    return ranking;
  }

  public void setRanking(Integer ranking) {
    this.ranking = ranking;
  }

  @Column(name = "QUOTA", nullable = true, precision = 0)
  public Integer getQuota() {
    return quota;
  }

  public void setQuota(Integer quota) {
    this.quota = quota;
  }

  @Column(name = "PRICECLASS_ID", nullable = false, precision = 0)
  public Long getPriceclassId() {
    return priceclassId;
  }

  public void setPriceclassId(Long priceclassId) {
    this.priceclassId = priceclassId;
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

    PmtRowLive that = (PmtRowLive) o;

    return new EqualsBuilder()
        .append(pmtrowid, that.pmtrowid)
        .append(ticketprintalias, that.ticketprintalias)
        .append(isdisplayforsales, that.isdisplayforsales)
        .append(isstandard, that.isstandard)
        .append(ranking, that.ranking)
        .append(quota, that.quota)
        .append(priceclassId, that.priceclassId)
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
        .append(pmtrowid)
        .append(ticketprintalias)
        .append(isdisplayforsales)
        .append(isstandard)
        .append(ranking)
        .append(quota)
        .append(priceclassId)
        .append(pricemodeltemplateId)
        .append(createddate)
        .append(createdBy)
        .append(updateddate)
        .append(updatedBy)
        .toHashCode();
  }
}
