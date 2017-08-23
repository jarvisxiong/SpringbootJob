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
@Table(name = "FEE")
public class Fee implements Serializable {

  private static final long serialVersionUID = -3221951172802790151L;
  private Long feeid;
  private String feename;
  private Long feeClassId;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  public Fee() {
  }

  @Id
  @Column(name = "FEEID", nullable = false, precision = 0)
  public Long getFeeid() {
    return feeid;
  }

  public void setFeeid(Long feeid) {
    this.feeid = feeid;
  }

  @Column(name = "FEENAME", nullable = false, length = 255)
  public String getFeename() {
    return feename;
  }

  public void setFeename(String feename) {
    this.feename = feename;
  }

  @Column(name = "FEE_CLASS_ID", nullable = false, precision = 0)
  public Long getFeeClassId() {
    return feeClassId;
  }

  public void setFeeClassId(Long feeClassId) {
    this.feeClassId = feeClassId;
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

    Fee that = (Fee) o;

    return Objects.equals(this.feeid, that.feeid) &&
        Objects.equals(this.feename, that.feename) &&
        Objects.equals(this.feeClassId, that.feeClassId) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feeid, feename, feeClassId, createdBy, createddate, updatedBy,
        updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeid", feeid)
        .append("feename", feename)
        .append("feeClassId", feeClassId)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
