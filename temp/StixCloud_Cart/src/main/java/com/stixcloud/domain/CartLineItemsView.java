package com.stixcloud.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by sengkai on 10/26/2016.
 */
@Entity
@Table(name = "CART_LINE_ITEMS_VIEW")
public class CartLineItemsView implements Serializable {

  private static final long serialVersionUID = 907619909731597731L;
  @EmbeddedId
  private CartLineItemsViewKey key;
  private String productname;
  private OffsetDateTime startdate;
  private String seatlevelalias;
  private String rowalias;
  private Integer seatno;
  private String venuealias;
  private String pricecatalias;
  private String priceclassname;
  private BigDecimal pricevalue;
  private String seatsectiontype;
  private String seatsectionalias;
  @Transient
  private String cartItemId;
  @Transient
  private String icc;
  private String ispackageclass;
  

  public CartLineItemsView() {
  }

  public CartLineItemsView(CartLineItemsViewKey key, String productname,
                           OffsetDateTime startdate, String seatlevelalias, String rowalias,
                           Integer seatno, String venuealias, String pricecatalias,
                           String priceclassname, BigDecimal pricevalue, String seatsectiontype,
                           String seatsectionalias, String ispackageclass) {
    this.key = key;
    this.productname = productname;
    this.startdate = startdate;
    this.seatlevelalias = seatlevelalias;
    this.rowalias = rowalias;
    this.seatno = seatno;
    this.venuealias = venuealias;
    this.pricecatalias = pricecatalias;
    this.priceclassname = priceclassname;
    this.pricevalue = pricevalue;
    this.seatsectiontype = seatsectiontype;
    this.seatsectionalias = seatsectionalias;
    this.ispackageclass=ispackageclass;
  }

  public CartLineItemsViewKey getKey() {
    return key;
  }

  public void setKey(CartLineItemsViewKey key) {
    this.key = key;
  }

  
  @Column(name = "ISPACKAGECLASS", nullable = true, length = 1)
  public String getIspackageclass() {
	return ispackageclass;
  }
	
  public void setIspackageclass(String ispackageclass) {
		this.ispackageclass = ispackageclass;
  }

@Column(name = "PRODUCTNAME", nullable = false, length = 255)
  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  @Column(name = "STARTDATE", nullable = false)
  public OffsetDateTime getStartdate() {
    return startdate;
  }

  public void setStartdate(OffsetDateTime startdate) {
    this.startdate = startdate;
  }

  @Column(name = "SEATLEVELALIAS", nullable = true, length = 50)
  public String getSeatlevelalias() {
    return seatlevelalias;
  }

  public void setSeatlevelalias(String seatlevelalias) {
    this.seatlevelalias = seatlevelalias;
  }

  @Column(name = "ROWALIAS", nullable = true, length = 50)
  public String getRowalias() {
    return rowalias;
  }

  public void setRowalias(String rowalias) {
    this.rowalias = rowalias;
  }

  @Column(name = "SEATNO", nullable = true, precision = 0)
  public Integer getSeatno() {
    return seatno;
  }

  public void setSeatno(Integer seatno) {
    this.seatno = seatno;
  }

  @Column(name = "VENUEALIAS", nullable = true, length = 255)
  public String getVenuealias() {
    return venuealias;
  }

  public void setVenuealias(String venuealias) {
    this.venuealias = venuealias;
  }

  @Column(name = "PRICECATALIAS", nullable = false, length = 150)
  public String getPricecatalias() {
    return pricecatalias;
  }

  public void setPricecatalias(String pricecatalias) {
    this.pricecatalias = pricecatalias;
  }

  @Column(name = "PRICECLASSNAME", nullable = false, length = 100)
  public String getPriceclassname() {
    return priceclassname;
  }

  public void setPriceclassname(String priceclassname) {
    this.priceclassname = priceclassname;
  }

  @Column(name = "PRICEVALUE", nullable = false, precision = 5)
  public BigDecimal getPricevalue() {
    return pricevalue;
  }

  public void setPricevalue(BigDecimal pricevalue) {
    this.pricevalue = pricevalue;
  }

  @Column(name = "SEATSECTIONTYPE", nullable = true, length = 2)
  public String getSeatsectiontype() {
    return seatsectiontype;
  }

  public void setSeatsectiontype(String seatsectiontype) {
    this.seatsectiontype = seatsectiontype;
  }

  @Column(name = "SEATSECTIONALIAS", nullable = true, length = 50)
  public String getSeatsectionalias() {
    return seatsectionalias;
  }

  public void setSeatsectionalias(String seatsectionalias) {
    this.seatsectionalias = seatsectionalias;
  }

  public String getCartItemId() {
    return cartItemId;
  }

  public void setCartItemId(String cartItemId) {
    this.cartItemId = cartItemId;
  }
  
  public String getIcc() {
    return icc;
  }
	
  public void setIcc(String icc) {
	this.icc = icc;
  }

  @Transient
  public CartLineItemsViewTuple<Long, String, Long, Long> getTuple() {
    return new CartLineItemsViewTuple<>(this.getKey().getProductid(),
        this.getKey().getPriceclasscode(), this.getKey().getPricecatid(),
        this.getKey().getSeatsectionid());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartLineItemsView that = (CartLineItemsView) o;
    return Objects.equals(key, that.key) &&
        Objects.equals(productname, that.productname) &&
        Objects.equals(startdate, that.startdate) &&
        Objects.equals(seatlevelalias, that.seatlevelalias) &&
        Objects.equals(rowalias, that.rowalias) &&
        Objects.equals(seatno, that.seatno) &&
        Objects.equals(venuealias, that.venuealias) &&
        Objects.equals(pricecatalias, that.pricecatalias) &&
        Objects.equals(priceclassname, that.priceclassname) &&
        Objects.equals(pricevalue, that.pricevalue) &&
        Objects.equals(seatsectiontype, that.seatsectiontype) &&
        Objects.equals(seatsectionalias, that.seatsectionalias);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(key, productname, startdate, seatlevelalias, rowalias,
            seatno, venuealias, pricecatalias, priceclassname,
            pricevalue, seatsectiontype, seatsectionalias);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("key", key)
        .append("cartItemId", cartItemId)
        .append("icc", icc)
        .append("productName", productname)
        .append("startDate", startdate)
        .append("seatLevelAlias", seatlevelalias)
        .append("rowAlias", rowalias)
        .append("seatNo", seatno)
        .append("venueAlias", venuealias)
        .append("priceCatAlias", pricecatalias)
        .append("priceClassName", priceclassname)
        .append("priceValue", pricevalue)
        .append("seatsectiontype", seatsectiontype)
        .append("seatsectionalias", seatsectionalias)
        .toString();
  }
  
}
