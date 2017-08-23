package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by chongye on 3/10/2016.
 */
@Entity
@Table(name = "VALIDATE_CART_VIEW")
public class ValidateCartView {
  @EmbeddedId
  private ValidateCartViewKey key;
  private String priceclasscode;
  private Integer quantitystatus;
  private Integer quantity;
  private Integer maximumquantity;
  private Integer chartquota;
  private Integer rowquota;
  private Integer priceclassquota;
  private Integer priceClassUnavailableSeats;
  private Integer priceCatUnavailableSeats;
  private String ispackageclass;

  public ValidateCartViewKey getKey() {
    return key;
  }

  public void setKey(ValidateCartViewKey validateCartViewKey) {
    this.key = validateCartViewKey;
  }


@Column(name = "ISPACKAGECLASS", nullable = false, length = 1)
public String getIspackageclass() {
	return ispackageclass;
}

public void setIspackageclass(String ispackageclass) {
	this.ispackageclass = ispackageclass;
}

@Column(name = "PRICECLASSCODE", nullable = false, length = 10)
  public String getPriceclasscode() {
    return priceclasscode;
  }

  public void setPriceclasscode(String priceclasscode) {
    this.priceclasscode = priceclasscode;
  }

  @Column(name = "QUANTITYSTATUS", nullable = false, precision = 0)
  public Integer getQuantitystatus() {
    return quantitystatus;
  }

  public void setQuantitystatus(Integer quantitystatus) {
    this.quantitystatus = quantitystatus;
  }

  @Column(name = "QUANTITY", nullable = false, precision = 0)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Column(name = "MAXIMUMQUANTITY", nullable = false, precision = 0)
  public Integer getMaximumquantity() {
    return maximumquantity;
  }

  public void setMaximumquantity(Integer maximumquantity) {
    this.maximumquantity = maximumquantity;
  }

  @Column(name = "CHARTQUOTA", nullable = true, precision = 0)
  public Integer getChartquota() {
    return chartquota;
  }

  public void setChartquota(Integer chartquota) {
    this.chartquota = chartquota;
  }

  @Column(name = "ROWQUOTA", nullable = true, precision = 0)
  public Integer getRowquota() {
    return rowquota;
  }

  public void setRowquota(Integer rowquota) {
    this.rowquota = rowquota;
  }

  @Column(name = "PRICECLASSQUOTA", nullable = true, precision = 0)
  public Integer getPriceclassquota() {
    return priceclassquota;
  }

  public void setPriceclassquota(Integer priceclassquota) {
    this.priceclassquota = priceclassquota;
  }

  @Column(name = "PRICE_CLASS_UNAVAILABLE_SEATS", nullable = true, precision = 0)
  public Integer getPriceClassUnavailableSeats() {
    return priceClassUnavailableSeats;
  }

  public void setPriceClassUnavailableSeats(Integer priceClassUnavailableSeats) {
    this.priceClassUnavailableSeats = priceClassUnavailableSeats;
  }

  @Column(name = "PRICE_CAT_UNAVAILABLE_SEATS", nullable = true, precision = 0)
  public Integer getPriceCatUnavailableSeats() {
    return priceCatUnavailableSeats;
  }

  public void setPriceCatUnavailableSeats(Integer priceCatUnavailableSeats) {
    this.priceCatUnavailableSeats = priceCatUnavailableSeats;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ValidateCartView that = (ValidateCartView) o;

    return Objects.equals(this.key, that.key) &&
        Objects.equals(this.priceclasscode, that.priceclasscode) &&
        Objects.equals(this.quantitystatus, that.quantitystatus) &&
        Objects.equals(this.quantity, that.quantity) &&
        Objects.equals(this.maximumquantity, that.maximumquantity) &&
        Objects.equals(this.chartquota, that.chartquota) &&
        Objects.equals(this.rowquota, that.rowquota) &&
        Objects.equals(this.priceclassquota, that.priceclassquota) &&
        Objects.equals(this.priceClassUnavailableSeats, that.priceClassUnavailableSeats) &&
        Objects.equals(this.priceCatUnavailableSeats, that.priceCatUnavailableSeats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, priceclasscode, quantitystatus, quantity, maximumquantity, chartquota,
        rowquota, priceclassquota, priceClassUnavailableSeats, priceCatUnavailableSeats);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("validateCartViewKey", key)
        .append("priceclasscode", priceclasscode)
        .append("quantitystatus", quantitystatus)
        .append("quantity", quantity)
        .append("maximumquantity", maximumquantity)
        .append("chartquota", chartquota)
        .append("rowquota", rowquota)
        .append("priceclassquota", priceclassquota)
        .append("priceClassUnavailableSeats", priceClassUnavailableSeats)
        .append("priceCatUnavailableSeats", priceCatUnavailableSeats)
        .toString();
  }
}
