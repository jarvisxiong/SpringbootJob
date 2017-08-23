package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "cartGuid",
    "productId",
    "priceClassMap",
    "priceCatId",
    "seatSectionId",
    "inventoryList",
    "mode",
    "icc",
    "maxQuantity"
})
public class AddToCartRequest implements Serializable {
  private static final long serialVersionUID = -8582988637590221235L;
  @JsonProperty("cartGuid")
  private String cartGuid;
  @JsonProperty("productId")
  private Long productId;
  @JsonProperty("priceClassMap")
  private String priceClassMap;
  @JsonProperty("priceCatId")
  private Long priceCatId;
  @JsonProperty("seatSectionId")
  private Long seatSectionId;
  @JsonProperty("inventoryList")
  private Set<Long> inventoryList;
  @JsonProperty("mode")
  private String mode;
  @JsonProperty("icc")
  private String icc;
  @JsonProperty("maxQuantity")
  private Integer maxQuantity;

  public AddToCartRequest() {
  }

  private AddToCartRequest(Builder builder) {
    cartGuid = builder.cartGuid;
    productId = builder.productId;
    priceClassMap = builder.priceClassMap;
    priceCatId = builder.priceCatId;
    seatSectionId = builder.seatSectionId;
    inventoryList = builder.inventoryList;
    mode = builder.mode;
    icc=builder.icc;
    maxQuantity=builder.maxQuantity;
  }

  @JsonProperty("cartGuid")
  public String getCartGuid() {
    return cartGuid;
  }

  @JsonProperty("productId")
  public Long getProductId() {
    return productId;
  }

  @JsonProperty("priceClassMap")
  public String getPriceClassMap() {
    return priceClassMap;
  }

  @JsonProperty("priceCatId")
  public Long getPriceCatId() {
    return priceCatId;
  }

  @JsonProperty("seatSectionId")
  public Long getSeatSectionId() {
    return seatSectionId;
  }

  @JsonProperty("inventoryList")
  public Set<Long> getInventoryList() {
    return inventoryList;
  }

  @JsonProperty("mode")
  public String getMode() {
    return mode;
  }
  
  @JsonProperty("icc")
  public String getIcc() {
    return icc;
  }
  
  @JsonProperty("icc")
  public Integer getMaxQuantity() {
    return maxQuantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AddToCartRequest that = (AddToCartRequest) o;

    return Objects.equals(this.cartGuid, that.cartGuid) &&
        Objects.equals(this.inventoryList, that.inventoryList) &&
        Objects.equals(this.mode, that.mode) &&
        Objects.equals(this.priceCatId, that.priceCatId) &&
        Objects.equals(this.priceClassMap, that.priceClassMap) &&
        Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.seatSectionId, that.seatSectionId) &&
        Objects.equals(this.icc, that.icc) &&
        Objects.equals(this.maxQuantity, that.maxQuantity) &&
        Objects.equals(this.serialVersionUID, that.serialVersionUID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartGuid, inventoryList, mode, priceCatId, priceClassMap, productId,
        seatSectionId, icc,maxQuantity,serialVersionUID);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("cartGuid", cartGuid)
        .append("productId", productId)
        .append("priceClassMap", priceClassMap)
        .append("priceCatId", priceCatId)
        .append("seatSectionId", seatSectionId)
        .append("inventoryList", inventoryList)
        .append("mode", mode)
        .append("icc",icc)
        .append("maxQuantity",maxQuantity)
        .toString();
  }

  public static final class Builder {
    private String cartGuid;
    private Long productId;
    private String priceClassMap;
    private Long priceCatId;
    private Long seatSectionId;
    private Set<Long> inventoryList;
    private String mode;
    private String icc;
    private Integer maxQuantity;

    public Builder() {
    }

    public Builder(AddToCartRequest copy) {
      this.cartGuid = copy.cartGuid;
      this.productId = copy.productId;
      this.priceClassMap = copy.priceClassMap;
      this.priceCatId = copy.priceCatId;
      this.seatSectionId = copy.seatSectionId;
      this.inventoryList = copy.inventoryList;
      this.mode = copy.mode;
      this.icc=copy.icc;
      this.maxQuantity=copy.maxQuantity;
    }

    public Builder cartGuid(String cartGuid) {
      this.cartGuid = cartGuid;
      return this;
    }

    public Builder productId(Long productId) {
      this.productId = productId;
      return this;
    }

    public Builder priceClassMap(String priceClassMap) {
      this.priceClassMap = priceClassMap;
      return this;
    }

    public Builder priceCatId(Long priceCatId) {
      this.priceCatId = priceCatId;
      return this;
    }

    public Builder seatSectionId(Long seatSectionId) {
      this.seatSectionId = seatSectionId;
      return this;
    }

    public Builder inventoryList(Set<Long> inventoryList) {
      this.inventoryList = inventoryList;
      return this;
    }

    public Builder mode(String mode) {
      this.mode = mode;
      return this;
    }
    
    public Builder icc(String icc) {
        this.icc = icc;
        return this;
      }
    
    public Builder maxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
        return this;
      }

    public AddToCartRequest build() {
      return new AddToCartRequest(this);
    }
  }
}
