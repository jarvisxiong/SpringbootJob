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
@Table(name = "PRICE_MODEL_TPL_PRDT_LIVE")
public class PriceModelTplPrdtLive implements Serializable {

  private static final long serialVersionUID = -8171985000627023712L;
  private Long pricemodeltplprdtid;
  private Long priceModelTemplateId;
  private Long productId;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  public PriceModelTplPrdtLive() {
  }

  public PriceModelTplPrdtLive(Long pricemodeltplprdtid, Long priceModelTemplateId, Long productId,
                               OffsetDateTime createddate, Long createdBy,
                               OffsetDateTime updateddate, Long updatedBy) {
    this.pricemodeltplprdtid = pricemodeltplprdtid;
    this.priceModelTemplateId = priceModelTemplateId;
    this.productId = productId;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PRICEMODELTPLPRDTID", nullable = false, precision = 0)
  public Long getPricemodeltplprdtid() {
    return pricemodeltplprdtid;
  }

  public void setPricemodeltplprdtid(Long pricemodeltplprdtid) {
    this.pricemodeltplprdtid = pricemodeltplprdtid;
  }


  @Column(name = "PRICE_MODEL_TEMPLATE_ID", nullable = false, precision = 0)
  public Long getPriceModelTemplateId() {
    return priceModelTemplateId;
  }

  public void setPriceModelTemplateId(Long priceModelTemplateId) {
    this.priceModelTemplateId = priceModelTemplateId;
  }


  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
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

    PriceModelTplPrdtLive that = (PriceModelTplPrdtLive) o;

    return new EqualsBuilder()
        .append(pricemodeltplprdtid, that.pricemodeltplprdtid)
        .append(priceModelTemplateId, that.priceModelTemplateId)
        .append(productId, that.productId)
        .append(createddate, that.createddate)
        .append(createdBy, that.createdBy)
        .append(updateddate, that.updateddate)
        .append(updatedBy, that.updatedBy)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(pricemodeltplprdtid)
        .append(priceModelTemplateId)
        .append(productId)
        .append(createddate)
        .append(createdBy)
        .append(updateddate)
        .append(updatedBy)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("pricemodeltplprdtid", pricemodeltplprdtid)
        .append("priceModelTemplateId", priceModelTemplateId)
        .append("productId", productId)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
