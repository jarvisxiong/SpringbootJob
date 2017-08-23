package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
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
@Table(name = "FEE_TEMPLATE_LIVE")
public class FeeTemplateLive implements Serializable {

  private static final long serialVersionUID = -7924882840211390680L;
  private Long feetemplateid;
  private String feetemplatename;
  private Boolean isstandardruleapbl;
  private Long eventSpecificRuleId;
  private Long txnFeeListId;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Integer completestatus;

  public FeeTemplateLive() {
  }

  @Id
  @Column(name = "FEETEMPLATEID", nullable = false, precision = 0)
  public Long getFeetemplateid() {
    return feetemplateid;
  }

  public void setFeetemplateid(Long feetemplateid) {
    this.feetemplateid = feetemplateid;
  }

  @Column(name = "FEETEMPLATENAME", nullable = true, length = 50)
  public String getFeetemplatename() {
    return feetemplatename;
  }

  public void setFeetemplatename(String feetemplatename) {
    this.feetemplatename = feetemplatename;
  }

  @Type(type = "true_false")
  @Column(name = "ISSTANDARDRULEAPBL", nullable = false, length = 1)
  public Boolean getIsstandardruleapbl() {
    return isstandardruleapbl;
  }

  public void setIsstandardruleapbl(Boolean isstandardruleapbl) {
    this.isstandardruleapbl = isstandardruleapbl;
  }

  @Column(name = "EVENT_SPECIFIC_RULE_ID", nullable = true, precision = 0)
  public Long getEventSpecificRuleId() {
    return eventSpecificRuleId;
  }

  public void setEventSpecificRuleId(Long eventSpecificRuleId) {
    this.eventSpecificRuleId = eventSpecificRuleId;
  }

  @Column(name = "TXN_FEE_LIST_ID", nullable = true, precision = 0)
  public Long getTxnFeeListId() {
    return txnFeeListId;
  }

  public void setTxnFeeListId(Long txnFeeListId) {
    this.txnFeeListId = txnFeeListId;
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

  @Column(name = "COMPLETESTATUS", nullable = true, precision = 0)
  public Integer getCompletestatus() {
    return completestatus;
  }

  public void setCompletestatus(Integer completestatus) {
    this.completestatus = completestatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeTemplateLive that = (FeeTemplateLive) o;

    return Objects.equals(this.feetemplateid, that.feetemplateid) &&
        Objects.equals(this.feetemplatename, that.feetemplatename) &&
        Objects.equals(this.isstandardruleapbl, that.isstandardruleapbl) &&
        Objects.equals(this.eventSpecificRuleId, that.eventSpecificRuleId) &&
        Objects.equals(this.txnFeeListId, that.txnFeeListId) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.completestatus, that.completestatus);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(feetemplateid, feetemplatename, isstandardruleapbl, eventSpecificRuleId, txnFeeListId,
            createdBy,
            createddate, updatedBy, updateddate, completestatus);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feetemplateid", feetemplateid)
        .append("feetemplatename", feetemplatename)
        .append("isstandardruleapbl", isstandardruleapbl)
        .append("eventSpecificRuleId", eventSpecificRuleId)
        .append("txnFeeListId", txnFeeListId)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("completestatus", completestatus)
        .toString();
  }
}
