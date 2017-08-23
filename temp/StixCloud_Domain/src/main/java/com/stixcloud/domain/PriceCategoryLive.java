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
 * Created by chongye on 06-Sep-16.
 */
@Entity
@Table(name = "PRICE_CATEGORY_LIVE")
public class PriceCategoryLive implements Serializable {

  private static final long serialVersionUID = -7032103826559173626L;
  private Long pricecategoryid;
  private String pricecategoryname;
  private Integer pricecategorynumber;
  private Long productgroupId;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  public PriceCategoryLive() {
  }

  public PriceCategoryLive(Long pricecategoryid, String pricecategoryname,
                           Integer pricecategorynumber, Long productgroupId,
                           OffsetDateTime createddate, Long createdBy, OffsetDateTime updateddate,
                           Long updatedBy) {
    this.pricecategoryid = pricecategoryid;
    this.pricecategoryname = pricecategoryname;
    this.pricecategorynumber = pricecategorynumber;
    this.productgroupId = productgroupId;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PRICECATEGORYID", nullable = false, precision = 0)
  public Long getPricecategoryid() {
    return pricecategoryid;
  }

  public void setPricecategoryid(Long pricecategoryid) {
    this.pricecategoryid = pricecategoryid;
  }


  @Column(name = "PRICECATEGORYNAME", nullable = false, length = 150)
  public String getPricecategoryname() {
    return pricecategoryname;
  }

  public void setPricecategoryname(String pricecategoryname) {
    this.pricecategoryname = pricecategoryname;
  }


  @Column(name = "PRICECATEGORYNUMBER", nullable = false, precision = 0)
  public Integer getPricecategorynumber() {
    return pricecategorynumber;
  }

  public void setPricecategorynumber(Integer pricecategorynumber) {
    this.pricecategorynumber = pricecategorynumber;
  }


  @Column(name = "PRODUCTGROUP_ID", nullable = false, precision = 0)
  public Long getProductgroupId() {
    return productgroupId;
  }

  public void setProductgroupId(Long productgroupId) {
    this.productgroupId = productgroupId;
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

    PriceCategoryLive that = (PriceCategoryLive) o;

    return Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.pricecategoryid, that.pricecategoryid) &&
        Objects.equals(this.pricecategoryname, that.pricecategoryname) &&
        Objects.equals(this.pricecategorynumber, that.pricecategorynumber) &&
        Objects.equals(this.productgroupId, that.productgroupId) &&
        Objects.equals(this.serialVersionUID, that.serialVersionUID) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(createdBy, createddate, pricecategoryid, pricecategoryname, pricecategorynumber,
            productgroupId,
            serialVersionUID, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("pricecategoryid", pricecategoryid)
        .append("pricecategoryname", pricecategoryname)
        .append("pricecategorynumber", pricecategorynumber)
        .append("productgroupId", productgroupId)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
