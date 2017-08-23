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
 * Created by chongye on 07-Sep-16.
 */
@Entity
@Table(name = "PRICE_CONFIGURATION")
public class PriceConfiguration implements Serializable {

  private static final long serialVersionUID = 1687732013819543557L;
  private Long priceconfigurationid;
  private Long productid;
  private Long layoutconfigid;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  public PriceConfiguration() {
  }

  public PriceConfiguration(Long priceconfigurationid, Long productid, Long layoutconfigid,
                            Long createdBy, OffsetDateTime createddate, Long updatedBy,
                            OffsetDateTime updateddate) {
    this.priceconfigurationid = priceconfigurationid;
    this.productid = productid;
    this.layoutconfigid = layoutconfigid;
    this.createdBy = createdBy;
    this.createddate = createddate;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
  }

  @Id
  @Column(name = "PRICECONFIGURATIONID", nullable = false, precision = 0)
  public Long getPriceconfigurationid() {
    return priceconfigurationid;
  }

  public void setPriceconfigurationid(Long priceconfigurationid) {
    this.priceconfigurationid = priceconfigurationid;
  }


  @Column(name = "PRODUCTID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }


  @Column(name = "LAYOUTCONFIGID", nullable = false, precision = 0)
  public Long getLayoutconfigid() {
    return layoutconfigid;
  }

  public void setLayoutconfigid(Long layoutconfigid) {
    this.layoutconfigid = layoutconfigid;
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


  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
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

    PriceConfiguration that = (PriceConfiguration) o;

    return new EqualsBuilder()
        .append(priceconfigurationid, that.priceconfigurationid)
        .append(productid, that.productid)
        .append(layoutconfigid, that.layoutconfigid)
        .append(createdBy, that.createdBy)
        .append(createddate, that.createddate)
        .append(updatedBy, that.updatedBy)
        .append(updateddate, that.updateddate)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(priceconfigurationid)
        .append(productid)
        .append(layoutconfigid)
        .append(createdBy)
        .append(createddate)
        .append(updatedBy)
        .append(updateddate)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceconfigurationid", priceconfigurationid)
        .append("productid", productid)
        .append("layoutconfigid", layoutconfigid)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}
