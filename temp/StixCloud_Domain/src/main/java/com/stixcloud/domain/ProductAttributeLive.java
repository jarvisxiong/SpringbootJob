package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_ATTRIBUTE_LIVE")
public class ProductAttributeLive implements Serializable {

  private static final long serialVersionUID = -2282548427523590025L;

  private Long productAttributeID;

  private Long workingID;

  private Long prdtAttrMctID;

  private Long productid;


  @Id
  @Column(name = "PRODUCTATTRIBUTEID", nullable = false, precision = 0)
  public Long getProductAttributeID() {
    return productAttributeID;
  }

  @Column(name = "WORKING_ID", precision = 10, scale = 0)
  public Long getWorkingID() {
    return workingID;
  }

  @Column(name = "PRDT_ATTR_MCT_ID", nullable = false)
  public Long getPrdtAttrMctID() {
    return prdtAttrMctID;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductAttributeID(Long productAttributeID) {
    this.productAttributeID = productAttributeID;
  }

  public void setWorkingID(Long workingID) {
    this.workingID = workingID;
  }

  public void setPrdtAttrMctID(Long prdtAttrMctID) {
    this.prdtAttrMctID = prdtAttrMctID;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductAttributeLive that = (ProductAttributeLive) o;

    return Objects.equals(this.productid, that.productid) &&
        Objects.equals(this.productAttributeID, that.productAttributeID) &&
        Objects.equals(this.prdtAttrMctID, that.prdtAttrMctID) &&
        Objects.equals(this.workingID, that.workingID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productid, productAttributeID, prdtAttrMctID, workingID);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productid", productid)
        .append("productAttributeID", productAttributeID)
        .append("prdtAttrMctID", prdtAttrMctID)
        .append("workingID", workingID)
        .toString();
  }

}
