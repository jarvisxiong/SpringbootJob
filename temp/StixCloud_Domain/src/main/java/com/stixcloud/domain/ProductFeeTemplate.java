package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
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
@Table(name = "PRODUCT_FEE_TEMPLATE")
public class ProductFeeTemplate implements Serializable {

  private static final long serialVersionUID = -676375496616994031L;
  private Long prdtfeetemplateid;
  private Long productId;
  private Long feeTemplateId;
  private Long workingId;

  public ProductFeeTemplate() {
  }

  @Id
  @Column(name = "PRDTFEETEMPLATEID", nullable = false, precision = 0)
  public Long getPrdtfeetemplateid() {
    return prdtfeetemplateid;
  }

  public void setPrdtfeetemplateid(Long prdtfeetemplateid) {
    this.prdtfeetemplateid = prdtfeetemplateid;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Column(name = "FEE_TEMPLATE_ID", nullable = false, precision = 0)
  public Long getFeeTemplateId() {
    return feeTemplateId;
  }

  public void setFeeTemplateId(Long feeTemplateId) {
    this.feeTemplateId = feeTemplateId;
  }

  @Column(name = "WORKING_ID", nullable = true, precision = 0)
  public Long getWorkingId() {
    return workingId;
  }

  public void setWorkingId(Long workingId) {
    this.workingId = workingId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductFeeTemplate that = (ProductFeeTemplate) o;

    return Objects.equals(this.prdtfeetemplateid, that.prdtfeetemplateid) &&
        Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.feeTemplateId, that.feeTemplateId) &&
        Objects.equals(this.workingId, that.workingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(prdtfeetemplateid, productId, feeTemplateId, workingId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("prdtfeetemplateid", prdtfeetemplateid)
        .append("productId", productId)
        .append("feeTemplateId", feeTemplateId)
        .append("workingId", workingId)
        .toString();
  }
}
