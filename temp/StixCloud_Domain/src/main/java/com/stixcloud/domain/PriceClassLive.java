package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 13-Sep-16.
 */
@Entity
@Table(name = "PRICE_CLASS_LIVE")
public class PriceClassLive implements Serializable {

  private static final long serialVersionUID = 7533931231146102406L;
  private Long priceclassid;
  private String priceclassname;
  private String priceclasscode;
  private String priceclassdescription;
  private Boolean iscomplimentaryticket;
  private Boolean ispackageclass;
  private Integer discountrule;
  private Integer pricevaluerule;
  private Integer spanquota;
  private Integer buyticketsquantity;
  private Integer getticketquantity;
  private Long offvalue;
  private Long pvpriceclassId;
  private Long discountpriceclassId;
  private Long typeofdiscountMctId;
  private Long promotionpasswordId;
  private Long creditcardrangeId;
  private Long productgroupId;
  private String validityPeriod;
  private String channels;
  private OffsetDateTime createddate;
  private Long createdBy;
  private OffsetDateTime updateddate;
  private Long updatedBy;
  private Integer ordering;

  public PriceClassLive() {
  }

  public PriceClassLive(Long priceclassid, String priceclassname, String priceclasscode,
                        String priceclassdescription, Boolean iscomplimentaryticket,
                        Boolean ispackageclass, Integer discountrule,
                        Integer pricevaluerule, Integer spanquota,
                        Integer buyticketsquantity, Integer getticketquantity,
                        Long offvalue, Long pvpriceclassId, Long discountpriceclassId,
                        Long typeofdiscountMctId, Long promotionpasswordId,
                        Long creditcardrangeId, Long productgroupId, String validityPeriod,
                        String channels, OffsetDateTime createddate, Long createdBy,
                        OffsetDateTime updateddate, Long updatedBy, Integer ordering) {
    this.priceclassid = priceclassid;
    this.priceclassname = priceclassname;
    this.priceclasscode = priceclasscode;
    this.priceclassdescription = priceclassdescription;
    this.iscomplimentaryticket = iscomplimentaryticket;
    this.ispackageclass = ispackageclass;
    this.discountrule = discountrule;
    this.pricevaluerule = pricevaluerule;
    this.spanquota = spanquota;
    this.buyticketsquantity = buyticketsquantity;
    this.getticketquantity = getticketquantity;
    this.offvalue = offvalue;
    this.pvpriceclassId = pvpriceclassId;
    this.discountpriceclassId = discountpriceclassId;
    this.typeofdiscountMctId = typeofdiscountMctId;
    this.promotionpasswordId = promotionpasswordId;
    this.creditcardrangeId = creditcardrangeId;
    this.productgroupId = productgroupId;
    this.validityPeriod = validityPeriod;
    this.channels = channels;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
    this.ordering = ordering;
  }

  @Id
  @Column(name = "PRICECLASSID", nullable = false, precision = 0)
  public Long getPriceclassid() {
    return priceclassid;
  }

  public void setPriceclassid(Long priceclassid) {
    this.priceclassid = priceclassid;
  }


  @Column(name = "PRICECLASSNAME", nullable = false, length = 100)
  public String getPriceclassname() {
    return priceclassname;
  }

  public void setPriceclassname(String priceclassname) {
    this.priceclassname = priceclassname;
  }


  @Column(name = "PRICECLASSCODE", nullable = false, length = 10)
  public String getPriceclasscode() {
    return priceclasscode;
  }

  public void setPriceclasscode(String priceclasscode) {
    this.priceclasscode = priceclasscode;
  }


  @Column(name = "PRICECLASSDESCRIPTION", nullable = true, length = 255)
  public String getPriceclassdescription() {
    return priceclassdescription;
  }

  public void setPriceclassdescription(String priceclassdescription) {
    this.priceclassdescription = priceclassdescription;
  }


  @Column(name = "ISCOMPLIMENTARYTICKET", nullable = false, length = 1)
  @Type(type = "true_false")
  public Boolean getIscomplimentaryticket() {
    return iscomplimentaryticket;
  }

  public void setIscomplimentaryticket(Boolean iscomplimentaryticket) {
    this.iscomplimentaryticket = iscomplimentaryticket;
  }


  @Column(name = "ISPACKAGECLASS", nullable = false, length = 1)
  @Type(type = "true_false")
  public Boolean getIspackageclass() {
    return ispackageclass;
  }

  public void setIspackageclass(Boolean ispackageclass) {
    this.ispackageclass = ispackageclass;
  }


  @Column(name = "DISCOUNTRULE", nullable = false, precision = 0)
  public Integer getDiscountrule() {
    return discountrule;
  }

  public void setDiscountrule(Integer discountrule) {
    this.discountrule = discountrule;
  }


  @Column(name = "PRICEVALUERULE", nullable = false, precision = 0)
  public Integer getPricevaluerule() {
    return pricevaluerule;
  }

  public void setPricevaluerule(Integer pricevaluerule) {
    this.pricevaluerule = pricevaluerule;
  }


  @Column(name = "SPANQUOTA", nullable = true, precision = 0)
  public Integer getSpanquota() {
    return spanquota;
  }

  public void setSpanquota(Integer spanquota) {
    this.spanquota = spanquota;
  }


  @Column(name = "BUYTICKETSQUANTITY", nullable = true, precision = 0)
  public Integer getBuyticketsquantity() {
    return buyticketsquantity;
  }

  public void setBuyticketsquantity(Integer buyticketsquantity) {
    this.buyticketsquantity = buyticketsquantity;
  }


  @Column(name = "GETTICKETQUANTITY", nullable = true, precision = 0)
  public Integer getGetticketquantity() {
    return getticketquantity;
  }

  public void setGetticketquantity(Integer getticketquantity) {
    this.getticketquantity = getticketquantity;
  }


  @Column(name = "OFFVALUE", nullable = true, precision = 5)
  public Long getOffvalue() {
    return offvalue;
  }

  public void setOffvalue(Long offvalue) {
    this.offvalue = offvalue;
  }


  @Column(name = "PVPRICECLASS_ID", nullable = true, precision = 0)
  public Long getPvpriceclassId() {
    return pvpriceclassId;
  }

  public void setPvpriceclassId(Long pvpriceclassId) {
    this.pvpriceclassId = pvpriceclassId;
  }


  @Column(name = "DISCOUNTPRICECLASS_ID", nullable = true, precision = 0)
  public Long getDiscountpriceclassId() {
    return discountpriceclassId;
  }

  public void setDiscountpriceclassId(Long discountpriceclassId) {
    this.discountpriceclassId = discountpriceclassId;
  }


  @Column(name = "TYPEOFDISCOUNT_MCT_ID", nullable = true, precision = 0)
  public Long getTypeofdiscountMctId() {
    return typeofdiscountMctId;
  }

  public void setTypeofdiscountMctId(Long typeofdiscountMctId) {
    this.typeofdiscountMctId = typeofdiscountMctId;
  }


  @Column(name = "PROMOTIONPASSWORD_ID", nullable = true, precision = 0)
  public Long getPromotionpasswordId() {
    return promotionpasswordId;
  }

  public void setPromotionpasswordId(Long promotionpasswordId) {
    this.promotionpasswordId = promotionpasswordId;
  }


  @Column(name = "CREDITCARDRANGE_ID", nullable = true, precision = 0)
  public Long getCreditcardrangeId() {
    return creditcardrangeId;
  }

  public void setCreditcardrangeId(Long creditcardrangeId) {
    this.creditcardrangeId = creditcardrangeId;
  }


  @Column(name = "PRODUCTGROUP_ID", nullable = false, precision = 0)
  public Long getProductgroupId() {
    return productgroupId;
  }

  public void setProductgroupId(Long productgroupId) {
    this.productgroupId = productgroupId;
  }


  @Column(name = "VALIDITY_PERIOD", nullable = true, length = 255)
  public String getValidityPeriod() {
    return validityPeriod;
  }

  public void setValidityPeriod(String validityPeriod) {
    this.validityPeriod = validityPeriod;
  }


  @Column(name = "CHANNELS", nullable = true, length = 255)
  public String getChannels() {
    return channels;
  }

  public void setChannels(String channels) {
    this.channels = channels;
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

  @Column(name = "ORDERING", nullable = true, precision = 0)
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

    PriceClassLive that = (PriceClassLive) o;

    return Objects.equals(this.buyticketsquantity, that.buyticketsquantity) &&
        Objects.equals(this.channels, that.channels) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.creditcardrangeId, that.creditcardrangeId) &&
        Objects.equals(this.discountpriceclassId, that.discountpriceclassId) &&
        Objects.equals(this.discountrule, that.discountrule) &&
        Objects.equals(this.getticketquantity, that.getticketquantity) &&
        Objects.equals(this.iscomplimentaryticket, that.iscomplimentaryticket) &&
        Objects.equals(this.ispackageclass, that.ispackageclass) &&
        Objects.equals(this.offvalue, that.offvalue) &&
        Objects.equals(this.priceclasscode, that.priceclasscode) &&
        Objects.equals(this.priceclassdescription, that.priceclassdescription) &&
        Objects.equals(this.priceclassid, that.priceclassid) &&
        Objects.equals(this.priceclassname, that.priceclassname) &&
        Objects.equals(this.pricevaluerule, that.pricevaluerule) &&
        Objects.equals(this.productgroupId, that.productgroupId) &&
        Objects.equals(this.promotionpasswordId, that.promotionpasswordId) &&
        Objects.equals(this.pvpriceclassId, that.pvpriceclassId) &&
        Objects.equals(this.spanquota, that.spanquota) &&
        Objects.equals(this.typeofdiscountMctId, that.typeofdiscountMctId) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.validityPeriod, that.validityPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyticketsquantity, channels, createdBy, createddate, creditcardrangeId,
        discountpriceclassId,
        discountrule, getticketquantity, iscomplimentaryticket, ispackageclass, offvalue,
        priceclasscode, priceclassdescription, priceclassid, priceclassname, pricevaluerule,
        productgroupId, promotionpasswordId, pvpriceclassId, spanquota, typeofdiscountMctId,
        updatedBy, updateddate, validityPeriod);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceclassid", priceclassid)
        .append("priceclassname", priceclassname)
        .append("priceclasscode", priceclasscode)
        .append("priceclassdescription", priceclassdescription)
        .append("iscomplimentaryticket", iscomplimentaryticket)
        .append("ispackageclass", ispackageclass)
        .append("discountrule", discountrule)
        .append("pricevaluerule", pricevaluerule)
        .append("spanquota", spanquota)
        .append("buyticketsquantity", buyticketsquantity)
        .append("getticketquantity", getticketquantity)
        .append("offvalue", offvalue)
        .append("pvpriceclassId", pvpriceclassId)
        .append("discountpriceclassId", discountpriceclassId)
        .append("typeofdiscountMctId", typeofdiscountMctId)
        .append("promotionpasswordId", promotionpasswordId)
        .append("creditcardrangeId", creditcardrangeId)
        .append("productgroupId", productgroupId)
        .append("validityPeriod", validityPeriod)
        .append("channels", channels)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}
