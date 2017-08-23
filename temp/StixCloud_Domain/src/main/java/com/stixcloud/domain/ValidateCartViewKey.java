package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by chongye on 3/10/2016.
 */
@Embeddable
public class ValidateCartViewKey implements Serializable {
  private Long productId;
  private Long pricecategoryid;
  private Long priceclassid;

  public ValidateCartViewKey() {
  }

  public ValidateCartViewKey(Long productId, Long pricecategoryid, Long priceclassid) {
    this.productId = productId;
    this.pricecategoryid = pricecategoryid;
    this.priceclassid = priceclassid;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Column(name = "PRICECATEGORYID", nullable = false, precision = 0)
  public Long getPricecategoryid() {
    return pricecategoryid;
  }

  public void setPricecategoryid(Long pricecategoryId) {
    this.pricecategoryid = pricecategoryId;
  }

  @Column(name = "PRICECLASSID", nullable = false, precision = 0)
  public Long getPriceclassid() {
    return priceclassid;
  }

  public void setPriceclassid(Long priceclassid) {
    this.priceclassid = priceclassid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ValidateCartViewKey that = (ValidateCartViewKey) o;

    return Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.pricecategoryid, that.pricecategoryid) &&
        Objects.equals(this.priceclassid, that.priceclassid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, pricecategoryid, priceclassid);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("pricecategoryId", pricecategoryid)
        .append("priceclassid", priceclassid)
        .toString();
  }
}