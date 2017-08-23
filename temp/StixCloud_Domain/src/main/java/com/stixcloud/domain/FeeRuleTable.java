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

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "FEE_RULE_TABLE")
public class FeeRuleTable implements Serializable {

  private static final long serialVersionUID = 2741773848181825043L;
  private Long feeruletableid;
  private String feeruletablename;
  private Integer feeruletablecategory;
  private Integer feeruletabletype;
  private Integer status;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  public FeeRuleTable() {
  }

  @Id
  @Column(name = "FEERULETABLEID", nullable = false, precision = 0)
  public Long getFeeruletableid() {
    return feeruletableid;
  }

  public void setFeeruletableid(Long feeruletableid) {
    this.feeruletableid = feeruletableid;
  }

  @Column(name = "FEERULETABLENAME", nullable = false, length = 50)
  public String getFeeruletablename() {
    return feeruletablename;
  }

  public void setFeeruletablename(String feeruletablename) {
    this.feeruletablename = feeruletablename;
  }

  @Column(name = "FEERULETABLECATEGORY", nullable = true, precision = 0)
  public Integer getFeeruletablecategory() {
    return feeruletablecategory;
  }

  public void setFeeruletablecategory(Integer feeruletablecategory) {
    this.feeruletablecategory = feeruletablecategory;
  }

  @Column(name = "FEERULETABLETYPE", nullable = true, precision = 0)
  public Integer getFeeruletabletype() {
    return feeruletabletype;
  }

  public void setFeeruletabletype(Integer feeruletabletype) {
    this.feeruletabletype = feeruletabletype;
  }

  @Column(name = "STATUS", nullable = true, precision = 0)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeRuleTable that = (FeeRuleTable) o;

    return Objects.equals(this.feeruletableid, that.feeruletableid) &&
        Objects.equals(this.feeruletablename, that.feeruletablename) &&
        Objects.equals(this.feeruletablecategory, that.feeruletablecategory) &&
        Objects.equals(this.feeruletabletype, that.feeruletabletype) &&
        Objects.equals(this.status, that.status) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(feeruletableid, feeruletablename, feeruletablecategory, feeruletabletype, status,
            createdBy,
            createddate, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeruletableid", feeruletableid)
        .append("feeruletablename", feeruletablename)
        .append("feeruletablecategory", feeruletablecategory)
        .append("feeruletabletype", feeruletabletype)
        .append("status", status)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
