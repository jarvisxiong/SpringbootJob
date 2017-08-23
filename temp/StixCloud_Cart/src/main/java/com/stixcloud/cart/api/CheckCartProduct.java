package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productId",
    "productName",
    "productDate",
    "venue"
})
public class CheckCartProduct implements Serializable {

  private static final long serialVersionUID = -5414716030863849035L;
  @JsonProperty("productId")
  private Long productId;
  @JsonProperty("productName")
  private String productName;
  @JsonProperty("productDate")
  private OffsetDateTime productDate;
  @JsonProperty("venue")
  private String venue;

  /**
   * No args constructor for use in serialization
   */
  public CheckCartProduct() {
  }

  private CheckCartProduct(Builder builder) {
    productId = builder.productId;
    productName = builder.productName;
    productDate = builder.productDate;
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

    CheckCartProduct that = (CheckCartProduct) o;

    return
        Objects.equals(this.productDate, that.productDate) &&
            Objects.equals(this.productId, that.productId) &&
            Objects.equals(this.productName, that.productName) &&
            Objects.equals(this.venue, that.venue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productDate, productId, productName, venue);
  }

  public static final class Builder {
    private Long productId;
    private String productName;
    private OffsetDateTime productDate;
    private String venue;

    public Builder() {
    }

    public Builder(CheckCartProduct copy) {
      this.productId = copy.productId;
      this.productName = copy.productName;
      this.productDate = copy.productDate;
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

    public Builder venue(String venue) {
      this.venue = venue;
      return this;
    }

    public CheckCartProduct build() {
      return new CheckCartProduct(this);
    }
  }
}
