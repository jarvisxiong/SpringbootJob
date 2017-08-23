package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productId",
    "productName",
    "productDate",
    "level",
    "section",
    "row",
    "seatNo",
    "productType",
    "venue"
})
public class Product implements Serializable {

  private static final long serialVersionUID = -5414716030863849035L;
  @JsonProperty("productId")
  private Long productId;
  @JsonProperty("productName")
  private String productName;
  @JsonProperty("productDate")
  private OffsetDateTime productDate;
  @JsonProperty("level")
  private String level;
  @JsonProperty("section")
  private String section;
  @JsonProperty("row")
  private String row;
  @JsonProperty("seatNo")
  private List<Integer> seatNo;
  @JsonProperty("productType")
  private String productType;
  @JsonProperty("venue")
  private String venue;

  /**
   * No args constructor for use in serialization
   */
  public Product() {
  }

  private Product(Builder builder) {
    productId = builder.productId;
    productName = builder.productName;
    productDate = builder.productDate;
    level = builder.level;
    section = builder.section;
    row = builder.row;
    seatNo = builder.seatNo;
    productType = builder.productType;
    venue = builder.venue;
  }

  /**
   * @return The productName
   */
  @JsonProperty("productName")
  public String getProductName() {
    return productName;
  }

  /**
   * @return The productDate
   */
  @JsonProperty("productDate")
  public OffsetDateTime getProductDate() {
    return productDate;
  }

  /**
   * @return The level
   */
  @JsonProperty("level")
  public String getLevel() {
    return level;
  }


  /**
   * @return The section
   */
  @JsonProperty("section")
  public String getSection() {
    return section;
  }

  /**
   * @return The row
   */
  @JsonProperty("row")
  public String getRow() {
    return row;
  }

  /**
   * @return The seatNo
   */
  @JsonProperty("seatNo")
  public List<Integer> getSeatNo() {
    return seatNo;
  }

  /**
   * @return The productType
   */
  @JsonProperty("productType")
  public String getProductType() {
    return productType;
  }

  /**
   * @return The venue
   */
  @JsonProperty("venue")
  public String getVenue() {
    return venue;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("productName", productName)
        .append("productDate", productDate)
        .append("level", level)
        .append("section", section)
        .append("row", row)
        .append("seatNo", seatNo)
        .append("productType", productType)
        .append("venue", venue)
        .toString();
  }

  @JsonProperty("productId")
  public Long getProductId() {
    return productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product that = (Product) o;

    return Objects.equals(this.level, that.level) &&
        Objects.equals(this.productDate, that.productDate) &&
        Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.productName, that.productName) &&
        Objects.equals(this.productType, that.productType) &&
        Objects.equals(this.row, that.row) &&
        Objects.equals(this.seatNo, that.seatNo) &&
        Objects.equals(this.section, that.section) &&
        Objects.equals(this.venue, that.venue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, productDate, productId, productName, productType, row,
        seatNo, section, venue);
  }

  public static final class Builder {
    private Long productId;
    private String productName;
    private OffsetDateTime productDate;
    private String level;
    private String section;
    private String row;
    private List<Integer> seatNo;
    private String productType;
    private String venue;

    public Builder() {
    }

    public Builder(Product copy) {
      this.productId = copy.productId;
      this.productName = copy.productName;
      this.productDate = copy.productDate;
      this.level = copy.level;
      this.section = copy.section;
      this.row = copy.row;
      this.seatNo = copy.seatNo;
      this.productType = copy.productType;
      this.venue = copy.venue;
    }

    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder productName(String productName) {
      this.productName = productName;
      return this;
    }

    public Builder productDate(OffsetDateTime productDate) {
      this.productDate = productDate;
      return this;
    }

    public Builder level(String level) {
      this.level = level;
      return this;
    }

    public Builder section(String section) {
      this.section = section;
      return this;
    }

    public Builder row(String row) {
      this.row = row;
      return this;
    }

    public Builder seatNo(List<Integer> seatNo) {
      this.seatNo = seatNo;
      return this;
    }

    public Builder productType(String productType) {
      this.productType = productType;
      return this;
    }

    public Builder venue(String venue) {
      this.venue = venue;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }
}
