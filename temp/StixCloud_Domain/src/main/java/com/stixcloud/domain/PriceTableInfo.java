package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 30-Aug-16.
 */
@Entity
@Table(name = "PRICE_TABLE_INFO")
@Embeddable
public class PriceTableInfo implements Serializable {
  private static final long serialVersionUID = -2461461936833879128L;
  @Id
  private Long productid;
  @Id
  private Long profileInfoId;
  @Id
  private String priceclasscode;
  @Id
  private String priceclassname;
  @Id
  private String pricecategoryname;
  @Id
  private Integer pricecategorynumber;
  @Id
  private BigDecimal pricevalue;
  @Id
  private Long priceClassId;
  @Id
  private Long priceCategoryId;
  @Id
  private Integer priceStatus;
  private Integer ordering;
  private String ispackageclass;

  public PriceTableInfo() {
  }

  public PriceTableInfo(Long productid, Long profileInfoId, String priceclasscode,
                        String priceclassname, String pricecategoryname,
                        Integer pricecategorynumber, BigDecimal pricevalue, Long priceClassId,
                        Long priceCategoryId, Integer priceStatus, Integer ordering, Long holdCodeId,
                        Integer seatSalesStatus) {
    this.productid = productid;
    this.profileInfoId = profileInfoId;
    this.priceclasscode = priceclasscode;
    this.priceclassname = priceclassname;
    this.pricecategoryname = pricecategoryname;
    this.pricecategorynumber = pricecategorynumber;
    this.pricevalue = pricevalue;
    this.priceClassId = priceClassId;
    this.priceCategoryId = priceCategoryId;
    this.priceStatus = priceStatus;
    this.ordering = ordering;
  }

  public PriceTableInfo(Long productid, Long profileInfoId, String priceclasscode,
                        String priceclassname, String pricecategoryname,
                        Integer pricecategorynumber, BigDecimal pricevalue, Integer priceStatus) {
    this.productid = productid;
    this.profileInfoId = profileInfoId;
    this.priceclasscode = priceclasscode;
    this.priceclassname = priceclassname;
    this.pricecategoryname = pricecategoryname;
    this.pricecategorynumber = pricecategorynumber;
    this.pricevalue = pricevalue;
    this.priceStatus = priceStatus;
  }

  @Column(name = "ISPACKAGECLASS", nullable = false, length = 1)
  public String getIspackageclass() {
    return ispackageclass;
  }

  public void setIspackageclass(String ispackageclass) {
    this.ispackageclass = ispackageclass;
  }

  @Column(name = "PRODUCTID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }


  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }


  @Column(name = "PRICECLASSCODE", nullable = false, length = 10)
  public String getPriceclasscode() {
    return priceclasscode;
  }

  public void setPriceclasscode(String priceclasscode) {
    this.priceclasscode = priceclasscode;
  }


  @Column(name = "PRICECLASSNAME", nullable = false, length = 100)
  public String getPriceclassname() {
    return priceclassname;
  }

  public void setPriceclassname(String priceclassname) {
    this.priceclassname = priceclassname;
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


  @Column(name = "PRICEVALUE", nullable = false, precision = 20, scale = 5)
  public BigDecimal getPricevalue() {
    return pricevalue;
  }

  public void setPricevalue(BigDecimal pricevalue) {
    this.pricevalue = pricevalue;
  }


  @Column(name = "PRICE_STATUS", nullable = true, precision = 0)
  public Integer getPriceStatus() {
    return priceStatus;
  }

  public void setPriceStatus(Integer quotaStatus) {
    this.priceStatus = quotaStatus;
  }
  
  @Column(name = "PRICE_CLASS_ID", nullable = false, precision = 0)
  public Long getPriceClassId() {
    return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  @Column(name = "PRICE_CATEGORY_ID", nullable = false, precision = 0)
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  @Column(name = "ORDERING", nullable = false, precision = 0)
  public Integer getOrdering() {
    return ordering;
  }

  public void setOrdering(Integer ordering) {
    this.ordering = ordering;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PriceTableInfo that = (PriceTableInfo) o;

    return new EqualsBuilder()
        .append(productid, that.productid)
        .append(profileInfoId, that.profileInfoId)
        .append(priceclasscode, that.priceclasscode)
        .append(priceclassname, that.priceclassname)
        .append(pricecategoryname, that.pricecategoryname)
        .append(pricecategorynumber, that.pricecategorynumber)
        .append(pricevalue, that.pricevalue)
        .append(priceClassId, that.priceClassId)
        .append(priceCategoryId, that.priceCategoryId)
        .append(priceStatus, that.priceStatus)
        .append(ordering, that.ordering)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(productid)
        .append(profileInfoId)
        .append(priceclasscode)
        .append(priceclassname)
        .append(pricecategoryname)
        .append(pricecategorynumber)
        .append(pricevalue)
        .append(priceClassId)
        .append(priceCategoryId)
        .append(priceStatus)
        .append(ordering)
        .toHashCode();
  }
}
