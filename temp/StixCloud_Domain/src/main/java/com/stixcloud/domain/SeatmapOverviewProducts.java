package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 22-Aug-16.
 */
@Entity
@Table(name = "SEATMAP_OVERVIEW_PRODUCTS")
public class SeatmapOverviewProducts implements Serializable {

  @Id
  private Long productid;
  @Id
  private String seatlevelalias;
  @Id
  private String seatsectionslias;
  @Id
  private Long seatsectionid;
  @Id
  private String seatsectiontype;
  @Id
  private String seatentrance;
  @Id
  private Long pricecatid;
  @Id
  private String pricecatalias;
  @Id
  private Integer pricecatnum;
  @Id
  private BigDecimal pricevalue;
  @Id
  private Long priceclassid;
  @Id
  private String priceclasscode;
  @Id
  private String priceclassname;
  @Id
  private Integer ranking;
  @Id
  private String isstandard;
  @Id
  private Long profileInfoId;
  @Id
  private Integer hideStandardPrice;
  @Id
  private Integer interactive;
  @Id
  private String salesMode;
  @Id
  private String coordinates;
  @Id
  private String imageurl;
  @Id
  private String redeem;
  @Id
  private Long seatmapMappingId;
  @Id
  private Long noSeatmapAttr;

  public SeatmapOverviewProducts() {
  }

  public SeatmapOverviewProducts(Long productid, String seatlevelalias, String seatsectionslias,
                                 Long seatsectionid, String seatsectiontype, String seatentrance,
                                 Long pricecatid, String pricecatalias, Integer pricecatnum,
                                 BigDecimal pricevalue, Long priceclassid, String priceclasscode,
                                 String priceclassname, Integer ranking, String isstandard,
                                 Long profileInfoId, Integer hideStandardPrice, Integer interactive,
                                 String salesMode, String coordinates, String imageurl,
                                 String redeem, Long seatmapMappingId, Long noSeatmapAttr) {
    this.productid = productid;
    this.seatlevelalias = seatlevelalias;
    this.seatsectionslias = seatsectionslias;
    this.seatsectionid = seatsectionid;
    this.seatsectiontype = seatsectiontype;
    this.seatentrance = seatentrance;
    this.pricecatid = pricecatid;
    this.pricecatalias = pricecatalias;
    this.pricecatnum = pricecatnum;
    this.pricevalue = pricevalue;
    this.priceclassid = priceclassid;
    this.priceclasscode = priceclasscode;
    this.priceclassname = priceclassname;
    this.ranking = ranking;
    this.isstandard = isstandard;
    this.profileInfoId = profileInfoId;
    this.hideStandardPrice = hideStandardPrice;
    this.interactive = interactive;
    this.salesMode = salesMode;
    this.coordinates = coordinates;
    this.imageurl = imageurl;
    this.redeem = redeem;
    this.seatmapMappingId = seatmapMappingId;
    this.noSeatmapAttr = noSeatmapAttr;
  }

  @Column(name = "PRODUCTID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }

  @Column(name = "SEATLEVELALIAS", nullable = true, length = 50)
  public String getSeatlevelalias() {
    return seatlevelalias;
  }

  public void setSeatlevelalias(String seatlevelalias) {
    this.seatlevelalias = seatlevelalias;
  }

  @Column(name = "SEATSECTIONSLIAS", nullable = true, length = 50)
  public String getSeatsectionslias() {
    return seatsectionslias;
  }

  public void setSeatsectionslias(String seatsectionslias) {
    this.seatsectionslias = seatsectionslias;
  }

  @Column(name = "SEATSECTIONID", nullable = false, precision = 0)
  public Long getSeatsectionid() {
    return seatsectionid;
  }

  public void setSeatsectionid(Long seatsectionid) {
    this.seatsectionid = seatsectionid;
  }

  @Column(name = "SEATSECTIONTYPE", nullable = true, length = 2)
  public String getSeatsectiontype() {
    return seatsectiontype;
  }

  public void setSeatsectiontype(String seatsectiontype) {
    this.seatsectiontype = seatsectiontype;
  }

  @Column(name = "SEATENTRANCE", nullable = true, length = 50)
  public String getSeatentrance() {
    return seatentrance;
  }

  public void setSeatentrance(String seatentrance) {
    this.seatentrance = seatentrance;
  }

  @Column(name = "PRICECATID", nullable = false, length = 150, precision = 0)
  public Long getPricecatid() {
    return pricecatid;
  }

  public void setPricecatid(Long pricecatid) {
    this.pricecatid = pricecatid;
  }

  @Column(name = "PRICECLASSID", nullable = false, length = 10)
  public Long getPriceclassid() {
    return priceclassid;
  }

  public void setPriceclassid(Long priceclassid) {
    this.priceclassid = priceclassid;
  }

  @Column(name = "PRICECATALIAS", nullable = false, length = 150)
  public String getPricecatalias() {
    return pricecatalias;
  }

  public void setPricecatalias(String pricecatalias) {
    this.pricecatalias = pricecatalias;
  }

  @Column(name = "PRICECATNUM", nullable = false, precision = 0)
  public Integer getPricecatnum() {
    return pricecatnum;
  }

  public void setPricecatnum(Integer pricecatnum) {
    this.pricecatnum = pricecatnum;
  }

  @Column(name = "PRICEVALUE", nullable = false, precision = 5, scale = 5)
  public BigDecimal getPricevalue() {
    return pricevalue;
  }

  public void setPricevalue(BigDecimal pricevalue) {
    this.pricevalue = pricevalue;
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

  @Column(name = "RANKING", nullable = false, precision = 0)
  public Integer getRanking() {
    return ranking;
  }

  public void setRanking(Integer ranking) {
    this.ranking = ranking;
  }

  @Column(name = "ISSTANDARD", nullable = false, length = 1)
  public String getIsstandard() {
    return isstandard;
  }

  public void setIsstandard(String isstandard) {
    this.isstandard = isstandard;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Column(name = "HIDE_STANDARD_PRICE", nullable = true, precision = 0)
  public Integer getHideStandardPrice() {
    return hideStandardPrice;
  }

  public void setHideStandardPrice(Integer hideStandardPrice) {
    this.hideStandardPrice = hideStandardPrice;
  }

  @Column(name = "INTERACTIVE", nullable = true, precision = 0)
  public Integer getInteractive() {
    return interactive;
  }

  public void setInteractive(Integer interactive) {
    this.interactive = interactive;
  }

  @Column(name = "SALES_MODE", nullable = true, length = 2)
  public String getSalesMode() {
    return salesMode;
  }

  public void setSalesMode(String salesMode) {
    this.salesMode = salesMode;
  }

  @Column(name = "COORDINATES", nullable = true, length = 500)
  public String getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  @Column(name = "IMAGEURL", nullable = true, length = 255)
  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  @Column(name = "REDEEM", nullable = true, length = 1)
  public String getRedeem() {
    return redeem;
  }

  public void setRedeem(String redeem) {
    this.redeem = redeem;
  }

  @Column(name = "SEATMAP_MAPPING_ID", nullable = true, length = 10, precision = 0)
  public Long getSeatmapMappingId() {
    return seatmapMappingId;
  }

  public void setSeatmapMappingId(Long seatMappingId) {
    this.seatmapMappingId = seatMappingId;
  }

  @Column(name = "NO_SEATMAP_ATTR", nullable = true, precision = 0)
  public Long getNoSeatmapAttr() {
    return noSeatmapAttr;
  }

  public void setNoSeatmapAttr(Long noSeatmapAttr) {
    this.noSeatmapAttr = noSeatmapAttr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SeatmapOverviewProducts products = (SeatmapOverviewProducts) o;

    return new EqualsBuilder()
        .append(productid, products.productid)
        .append(seatlevelalias, products.seatlevelalias)
        .append(seatsectionslias, products.seatsectionslias)
        .append(seatsectionid, products.seatsectionid)
        .append(seatsectiontype, products.seatsectiontype)
        .append(seatentrance, products.seatentrance)
        .append(pricecatid, products.pricecatid)
        .append(pricecatalias, products.pricecatalias)
        .append(pricecatnum, products.pricecatnum)
        .append(pricevalue, products.pricevalue)
        .append(priceclassid, products.priceclassid)
        .append(priceclasscode, products.priceclasscode)
        .append(priceclassname, products.priceclassname)
        .append(ranking, products.ranking)
        .append(isstandard, products.isstandard)
        .append(profileInfoId, products.profileInfoId)
        .append(hideStandardPrice, products.hideStandardPrice)
        .append(interactive, products.interactive)
        .append(salesMode, products.salesMode)
        //.append(coordinates, products.coordinates)
        .append(imageurl, products.imageurl)
        .append(redeem, products.redeem)
        .append(seatmapMappingId, products.seatmapMappingId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(productid)
        .append(seatlevelalias)
        .append(seatsectionslias)
        .append(seatsectionid)
        .append(seatsectiontype)
        .append(seatentrance)
        .append(pricecatid)
        .append(pricecatalias)
        .append(pricecatnum)
        .append(pricevalue)
        .append(priceclassid)
        .append(priceclasscode)
        .append(priceclassname)
        .append(ranking)
        .append(isstandard)
        .append(profileInfoId)
        .append(hideStandardPrice)
        .append(interactive)
        .append(salesMode)
        //.append(coordinates)
        .append(imageurl)
        .append(redeem)
        .append(seatmapMappingId)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productid", productid)
        .append("seatlevelalias", seatlevelalias)
        .append("seatsectionslias", seatsectionslias)
        .append("seatsectionid", seatsectionid)
        .append("seatsectiontype", seatsectiontype)
        .append("seatentrance", seatentrance)
        .append("pricecatid", pricecatid)
        .append("pricecatalias", pricecatalias)
        .append("pricecatnum", pricecatnum)
        .append("pricevalue", pricevalue)
        .append("priceclassid", priceclassid)
        .append("priceclasscode", priceclasscode)
        .append("priceclassname", priceclassname)
        .append("ranking", ranking)
        .append("isstandard", isstandard)
        .append("profileInfoId", profileInfoId)
        .append("hideStandardPrice", hideStandardPrice)
        .append("interactive", interactive)
        .append("salesMode", salesMode)
        .append("coordinates", coordinates)
        .append("imageurl", imageurl)
        .append("redeem", redeem)
        .append("seatmapMappingId", seatmapMappingId)
        .toString();
  }
}
