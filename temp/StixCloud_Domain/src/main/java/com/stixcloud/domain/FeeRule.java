package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "FEE_RULE")
public class FeeRule implements Serializable {

  private static final long serialVersionUID = 4568909975843673671L;
  private Long feeruleid;
  private String feerulename;
  private Integer feeruletype;
  private Long transactionCodeId;
  private String priceclass;
  private String pricecategory;
  private OffsetDateTime effectivedate;
  private OffsetDateTime enddate;
  private Long groupInfoId;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Integer feeRuleSequence;

  public FeeRule() {
  }

  @Id
  @Column(name = "FEERULEID", nullable = false, precision = 0)
  public Long getFeeruleid() {
    return feeruleid;
  }

  public void setFeeruleid(Long feeruleid) {
    this.feeruleid = feeruleid;
  }

  @Column(name = "FEERULENAME", nullable = false, length = 128)
  public String getFeerulename() {
    return feerulename;
  }

  public void setFeerulename(String feerulename) {
    this.feerulename = feerulename;
  }

  @Column(name = "FEERULETYPE", nullable = false, precision = 0)
  public Integer getFeeruletype() {
    return feeruletype;
  }

  public void setFeeruletype(Integer feeruletype) {
    this.feeruletype = feeruletype;
  }

  @Column(name = "TRANSACTION_CODE_ID", nullable = false, precision = 0)
  public Long getTransactionCodeId() {
    return transactionCodeId;
  }

  public void setTransactionCodeId(Long transactionCodeId) {
    this.transactionCodeId = transactionCodeId;
  }

  @Column(name = "PRICECLASS", nullable = true, length = 255)
  public String getPriceclass() {
    return priceclass;
  }

  public void setPriceclass(String priceclass) {
    this.priceclass = priceclass;
  }

  @Column(name = "PRICECATEGORY", nullable = true, length = 255)
  public String getPricecategory() {
    return pricecategory;
  }

  public void setPricecategory(String pricecategory) {
    this.pricecategory = pricecategory;
  }

  @Column(name = "EFFECTIVEDATE", nullable = true)
  public OffsetDateTime getEffectivedate() {
    return effectivedate;
  }

  public void setEffectivedate(OffsetDateTime effectivedate) {
    this.effectivedate = effectivedate;
  }

  @Column(name = "ENDDATE", nullable = true)
  public OffsetDateTime getEnddate() {
    return enddate;
  }

  public void setEnddate(OffsetDateTime enddate) {
    this.enddate = enddate;
  }

  @Column(name = "GROUP_INFO_ID", nullable = false, precision = 0)
  public Long getGroupInfoId() {
    return groupInfoId;
  }

  public void setGroupInfoId(Long groupInfoId) {
    this.groupInfoId = groupInfoId;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Transient
  public Integer getFeeRuleSequence() {
    return feeRuleSequence;
  }

  public void setFeeRuleSequence(Integer feeRuleSequence) {
    this.feeRuleSequence = feeRuleSequence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeRule that = (FeeRule) o;

    return Objects.equals(this.feeruleid, that.feeruleid) &&
        Objects.equals(this.feerulename, that.feerulename) &&
        Objects.equals(this.feeruletype, that.feeruletype) &&
        Objects.equals(this.transactionCodeId, that.transactionCodeId) &&
        Objects.equals(this.priceclass, that.priceclass) &&
        Objects.equals(this.pricecategory, that.pricecategory) &&
        Objects.equals(this.effectivedate, that.effectivedate) &&
        Objects.equals(this.enddate, that.enddate) &&
        Objects.equals(this.groupInfoId, that.groupInfoId) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(feeruleid, feerulename, feeruletype, transactionCodeId, priceclass, pricecategory,
            effectivedate, enddate, groupInfoId, createdBy, createddate,
            updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeruleid", feeruleid)
        .append("feerulename", feerulename)
        .append("feeruletype", feeruletype)
        .append("transactionCodeId", transactionCodeId)
        .append("priceclass", priceclass)
        .append("pricecategory", pricecategory)
        .append("effectivedate", effectivedate)
        .append("enddate", enddate)
        .append("groupInfoId", groupInfoId)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
