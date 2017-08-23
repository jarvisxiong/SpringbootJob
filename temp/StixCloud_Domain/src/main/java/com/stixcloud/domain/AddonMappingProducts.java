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
 * Created by chetan on 05/12/2016.
 */
@Entity
@Table(name = "ADDON_MAPPING_PRODUCT")
public class AddonMappingProducts implements Serializable {

  private static final long serialVersionUID = -1071097669667425092L;

  Long addOnMappingPrdID;
  Long addonMappingID;
  Long productID;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  @Id
  @Column(name = "ADDONMAPPINGPRDSID", nullable = false, precision = 0)
  public Long getAddOnMappingPrdID() {
    return addOnMappingPrdID;
  }

  public void setAddOnMappingPrdID(Long addOnMappingPrdID) {
    this.addOnMappingPrdID = addOnMappingPrdID;
  }

  @Column(name = "ADDON_MAPPING_ID", nullable = false, precision = 0)
  public Long getAddonMappingID() {
    return addonMappingID;
  }

  public void setAddonMappingID(Long addonMappingID) {
    this.addonMappingID = addonMappingID;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductID() {
    return productID;
  }

  public void setProductID(Long productID) {
    this.productID = productID;
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

  @Column(name = "UPDATEDDATE", nullable = false)
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
    AddonMappingProducts that = (AddonMappingProducts) o;
    return Objects.equals(this.addonMappingID, that.addonMappingID) &&
        Objects.equals(this.addOnMappingPrdID, that.addOnMappingPrdID) &&
        Objects.equals(this.productID, that.productID) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addonMappingID, addOnMappingPrdID, productID, createdBy,
        createddate, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("addOnMappingPrdID", addOnMappingPrdID)
        .append("addonMappingID", addonMappingID)
        .append("productID", productID)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }

}
