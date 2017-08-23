package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "productID",
    "productDescription",
    "priceTableList"
})
public class AddOnPriceTable implements Serializable {
  private static final long serialVersionUID = -6213162995228575603L;
  
  @JsonProperty("addonType")
  private String addonType;

  @JsonProperty("productID")
  private Long productID;
  @JsonProperty("productDescription")
  private String productDescription;
  @JsonProperty("productMessage")
  private String productMessage;
  
  @JsonProperty("lightboxContent")
  private String lightboxContent;
  
  @JsonProperty("priceTableList")
  @Valid
  private List<PriceTable> priceTableList = new ArrayList<PriceTable>();

  /**
   * No args constructor for use in serialization
   */
  public AddOnPriceTable() {
  }

  /**
   * @return The productID
   */
  @JsonProperty("productID")

  public Long getProductID() {
    return productID;
  }

  @JsonProperty("productID")
  public void setProductID(Long productID) {
    this.productID = productID;
  }

  /**
   * @return The productDescription
   */
  @JsonProperty("productDescription")
  public String getProductDescription() {
    return productDescription;
  }

  @JsonProperty("productDescription")
  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }
  
  @JsonProperty("productMessage")
  public String getProductMessage() {
    return productMessage;
  }
  
  @JsonProperty("productMessage")
  public void setProductMessage(String productMessage) {
    this.productMessage = productMessage;
  }
  
  @JsonProperty("lightboxContent")
  public String getLightboxContent() {
    return lightboxContent;
  }

  @JsonProperty("lightBoxContent")
  public void setLightboxContent(String lightboxContent) {
    this.lightboxContent = lightboxContent;
  }
  
  /**
   * @return The priceTableList
   */
  @JsonProperty("priceTableList")
  public List<PriceTable> getPriceTableList() {
    return priceTableList;
  }

  @JsonProperty("priceTableList")
  public void setPriceTableList(List<PriceTable> priceTableList) {
    this.priceTableList = priceTableList;
  }
  
  @JsonProperty("addonType")
  public String getAddonType() {
    return addonType;
  }

  @JsonProperty("addonType")
  public void setAddonType(String addonType) {
    this.addonType = addonType;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productID", productID)
        .append("productDescription", productDescription)
        .append("priceTableList", priceTableList)
        .toString();
  }
}
