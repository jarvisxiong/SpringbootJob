package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Cacheable
@Entity
@Table(name = "FEE_CLASS")
public class FeeClass implements Serializable {

  private static final long serialVersionUID = -5241266666486412L;
  private Long feeclassid;
  private String feeclassname;
  private Long feeCategoryMctId;
  private Integer chargetype;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  public FeeClass() {
  }

  @Id
  @Column(name = "FEECLASSID", nullable = false, precision = 0)
  public Long getFeeclassid() {
    return feeclassid;
  }

  public void setFeeclassid(Long feeclassid) {
    this.feeclassid = feeclassid;
  }

  @Column(name = "FEECLASSNAME", nullable = false, length = 128)
  public String getFeeclassname() {
    return feeclassname;
  }

  public void setFeeclassname(String feeclassname) {
    this.feeclassname = feeclassname;
  }

  @Column(name = "FEE_CATEGORY_MCT_ID", nullable = false, precision = 0)
  public Long getFeeCategoryMctId() {
    return feeCategoryMctId;
  }

  public void setFeeCategoryMctId(Long feeCategoryMctId) {
    this.feeCategoryMctId = feeCategoryMctId;
  }

  @Column(name = "CHARGETYPE", nullable = false, precision = 0)
  public Integer getChargetype() {
    return chargetype;
  }

  public void setChargetype(Integer chargetype) {
    this.chargetype = chargetype;
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
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeclassid", feeclassid)
        .append("feeclassname", feeclassname)
        .append("feeCategoryMctId", feeCategoryMctId)
        .append("chargetype", chargetype)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeClass that = (FeeClass) o;

    return Objects.equals(this.feeclassid, that.feeclassid) &&
        Objects.equals(this.feeclassname, that.feeclassname) &&
        Objects.equals(this.feeCategoryMctId, that.feeCategoryMctId) &&
        Objects.equals(this.chargetype, that.chargetype) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(feeclassid, feeclassname, feeCategoryMctId, chargetype, createdBy, createddate,
            updatedBy, updateddate);
  }
}
