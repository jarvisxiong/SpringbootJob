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
 * Created by chongye on 28/11/2016.
 */
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard {
  private Long creditcardid;
  private String creditcardname;
  private Integer status;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  @Id
  @Column(name = "CREDITCARDID", nullable = false, precision = 0)
  public Long getCreditcardid() {
    return creditcardid;
  }

  public void setCreditcardid(Long creditcardid) {
    this.creditcardid = creditcardid;
  }

  @Column(name = "CREDITCARDNAME", nullable = false, length = 128)
  public String getCreditcardname() {
    return creditcardname;
  }

  public void setCreditcardname(String creditcardname) {
    this.creditcardname = creditcardname;
  }

  @Column(name = "STATUS", nullable = false, precision = 0)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

    CreditCard that = (CreditCard) o;

    return Objects.equals(this.creditcardid, that.creditcardid) &&
        Objects.equals(this.creditcardname, that.creditcardname) &&
        Objects.equals(this.status, that.status) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creditcardid, creditcardname, status, createddate, createdBy, updateddate,
        updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("creditcardid", creditcardid)
        .append("creditcardname", creditcardname)
        .append("status", status)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
