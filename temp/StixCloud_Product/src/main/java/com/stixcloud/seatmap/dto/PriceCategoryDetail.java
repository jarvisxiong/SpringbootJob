package com.stixcloud.seatmap.dto;

import com.stixcloud.seatmap.constants.SalesConstants;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public class PriceCategoryDetail implements Serializable {

  private static final long serialVersionUID = -3382680473596468188L;
  private Integer categoryNumber;
  private String categoryName;
  private String catAlias;
  private int color;
  private Long priceCategoryId;

  public PriceCategoryDetail() {
  }

  /**
   * Instantiates a new price category details.
   * @param categoryNumber the category number
   * @param categoryName the category name
   * @param color the color
   * @param priceCategoryId the price category id
   */
  public PriceCategoryDetail(Integer categoryNumber, String categoryName, Integer color,
                             Long priceCategoryId) {
    this.categoryNumber = categoryNumber;
    this.categoryName = categoryName;
    this.color = color;
    this.priceCategoryId = priceCategoryId;
    catAlias = SalesConstants.CATEGORY_PREFIX + categoryNumber;
  }


  /**
   * Gets the category number.
   * @return the category number
   */
  public Integer getCategoryNumber() {
    return categoryNumber;
  }

  /**
   * Sets the category number.
   * @param categoryNumber the new category number
   */
  public void setCategoryNumber(Integer categoryNumber) {
    this.categoryNumber = categoryNumber;
  }

  /**
   * Gets the category name.
   * @return the category name
   */
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * Sets the category name.
   * @param categoryName the new category name
   */
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Gets the color.
   * @return the color
   */
  public Integer getColor() {
    return color;
  }

  /**
   * Sets the color.
   * @param color the new color
   */
  public void setColor(Integer color) {
    this.color = color;
  }

  /**
   * Gets the cat alias.
   * @return the cat alias
   */
  public String getCatAlias() {
    return catAlias;
  }

  /**
   * Sets the cat alias.
   * @param catAlias the new cat alias
   */
  public void setCatAlias(String catAlias) {
    this.catAlias = catAlias;
  }

  /**
   * Gets the price category id.
   * @return the price category id
   */
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  /**
   * Sets the price category id.
   * @param priceCategoryId the new price category id
   */
  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof PriceCategoryDetail)) {
      return false;
    }

    PriceCategoryDetail that = (PriceCategoryDetail) o;

    return new EqualsBuilder()
        .append(getColor(), that.getColor())
        .append(getCategoryNumber(), that.getCategoryNumber())
        .append(getCategoryName(), that.getCategoryName())
        .append(getPriceCategoryId(), that.getPriceCategoryId())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getCategoryNumber())
        .append(getCategoryName())
        .append(getColor())
        .append(getPriceCategoryId())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("categoryNumber", categoryNumber)
        .append("categoryName", categoryName)
        .append("color", color)
        .append("priceCategoryId", priceCategoryId)
        .toString();
  }
}
