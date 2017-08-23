package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by sengkai on 10/26/2016.
 */
@Embeddable
public class CartLineItemsViewKey implements Serializable {

  private static final long serialVersionUID = 1958467489358176794L;
  private Long productid;
  private Long salesseatinventoryid;
  private String priceclasscode;
  private Long pricecatid;
  private Long seatsectionid;
  private Long profileInfoId;

  public CartLineItemsViewKey() {
  }

  public CartLineItemsViewKey(Long productid, Long salesseatinventoryid,
                              String priceclasscode, Long pricecatid,
                              Long seatsectionid, Long profileInfoId) {
    this.productid = productid;
    this.salesseatinventoryid = salesseatinventoryid;
    this.priceclasscode = priceclasscode;
    this.pricecatid = pricecatid;
    this.seatsectionid = seatsectionid;
    this.profileInfoId = profileInfoId;
  }

  @Column(name = "PRODUCTID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }

  @Column(name = "SALESSEATINVENTORYID", nullable = false, precision = 0)
  public Long getSalesseatinventoryid() {
    return salesseatinventoryid;
  }

  public void setSalesseatinventoryid(Long salesseatinventoryid) {
    this.salesseatinventoryid = salesseatinventoryid;
  }

  @Column(name = "PRICECLASSCODE", nullable = false, length = 10)
  public String getPriceclasscode() {
    return priceclasscode;
  }

  public void setPriceclasscode(String priceclasscode) {
    this.priceclasscode = priceclasscode;
  }

  @Column(name = "PRICECATID", nullable = false, precision = 0)
  public Long getPricecatid() {
    return pricecatid;
  }

  public void setPricecatid(Long pricecatid) {
    this.pricecatid = pricecatid;
  }

  @Column(name = "SEATSECTIONID", nullable = false, precision = 0)
  public Long getSeatsectionid() {
    return seatsectionid;
  }

  public void setSeatsectionid(Long seatsectionid) {
    this.seatsectionid = seatsectionid;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartLineItemsViewKey that = (CartLineItemsViewKey) o;
    return Objects.equals(productid, that.productid) &&
        Objects.equals(salesseatinventoryid, that.salesseatinventoryid) &&
        Objects.equals(priceclasscode, that.priceclasscode) &&
        Objects.equals(pricecatid, that.pricecatid) &&
        Objects.equals(seatsectionid, that.seatsectionid) &&
        Objects.equals(profileInfoId, that.profileInfoId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(productid, salesseatinventoryid, priceclasscode, pricecatid,
            seatsectionid, profileInfoId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productid)
        .append("salesSeatInventoryId", salesseatinventoryid)
        .append("priceClassCode", priceclasscode)
        .append("priceCatId", pricecatid)
        .append("seatSectionId", seatsectionid)
        .append("profileInfoId", profileInfoId)
        .toString();
  }
}
