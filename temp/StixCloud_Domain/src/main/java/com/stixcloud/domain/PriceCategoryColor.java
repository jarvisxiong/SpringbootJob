package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 06-Sep-16.
 */
@Entity
@Table(name = "PRICE_CATEGORY_COLOR")
public class PriceCategoryColor implements Serializable {

  private static final long serialVersionUID = 4185467845731175287L;
  private Long pricecategorycolorid;
  private Integer pricecategorynumber;
  private Integer color;

  public PriceCategoryColor() {
  }

  public PriceCategoryColor(Long pricecategorycolorid, Integer pricecategorynumber, Integer color) {
    this.pricecategorycolorid = pricecategorycolorid;
    this.pricecategorynumber = pricecategorynumber;
    this.color = color;
  }

  @Id
  @Column(name = "PRICECATEGORYCOLORID", nullable = false, precision = 0)
  public Long getPricecategorycolorid() {
    return pricecategorycolorid;
  }

  public void setPricecategorycolorid(Long pricecategorycolorid) {
    this.pricecategorycolorid = pricecategorycolorid;
  }

  @Column(name = "PRICECATEGORYNUMBER", nullable = false, precision = 0)
  public Integer getPricecategorynumber() {
    return pricecategorynumber;
  }

  public void setPricecategorynumber(Integer pricecategorynumber) {
    this.pricecategorynumber = pricecategorynumber;
  }

  @Column(name = "COLOR", nullable = false, precision = 0)
  public Integer getColor() {
    return color;
  }

  public void setColor(Integer color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PriceCategoryColor that = (PriceCategoryColor) o;

    return new EqualsBuilder()
        .append(pricecategorycolorid, that.pricecategorycolorid)
        .append(pricecategorynumber, that.pricecategorynumber)
        .append(color, that.color)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(pricecategorycolorid)
        .append(pricecategorynumber)
        .append(color)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("pricecategorycolorid", pricecategorycolorid)
        .append("pricecategorynumber", pricecategorynumber)
        .append("color", color)
        .toString();
  }
}
