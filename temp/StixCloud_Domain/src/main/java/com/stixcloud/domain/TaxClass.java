package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 6/1/2017.
 */
@Entity
@Table(name = "TAX_CLASS")
public class TaxClass {
  private Long taxclassid;
  private String taxclassname;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  @Id
  @Column(name = "TAXCLASSID", nullable = false, precision = 0)
  public Long getTaxclassid() {
    return taxclassid;
  }

  public void setTaxclassid(Long taxclassid) {
    this.taxclassid = taxclassid;
  }

  @Column(name = "TAXCLASSNAME", nullable = false, length = 255)
  public String getTaxclassname() {
    return taxclassname;
  }

  public void setTaxclassname(String taxclassname) {
    this.taxclassname = taxclassname;
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

    TaxClass that = (TaxClass) o;

    return Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.taxclassid, that.taxclassid) &&
        Objects.equals(this.taxclassname, that.taxclassname) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdBy, createddate, taxclassid, taxclassname, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("taxclassid", taxclassid)
        .append("taxclassname", taxclassname)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
