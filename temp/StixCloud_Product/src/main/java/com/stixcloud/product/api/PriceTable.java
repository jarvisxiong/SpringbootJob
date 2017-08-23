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
    "priceClassCode",
    "priceClassAlias",
    "priceCatList"
})
public class PriceTable implements Serializable {
  private static final long serialVersionUID = -2833781159892307330L;
  @JsonProperty("priceClassCode")
  private String priceClassCode;
  @JsonProperty("priceClassAlias")
  private String priceClassAlias;
  @JsonProperty("priceCatList")
  @Valid
  private List<PriceCatList> priceCatList = new ArrayList<PriceCatList>();

  /**
   * No args constructor for use in serialization
   */
  public PriceTable() {
  }

  /**
   *
   * @param priceCatList
   * @param priceClassCode
   * @param priceClassAlias
   */
  public PriceTable(String priceClassCode, String priceClassAlias,
                    List<PriceCatList> priceCatList) {
    this.priceClassCode = priceClassCode;
    this.priceClassAlias = priceClassAlias;
    this.priceCatList = priceCatList;
  }

  /**
   * @return The priceClassCode
   */
  @JsonProperty("priceClassCode")
  public String getPriceClassCode() {
    return priceClassCode;
  }

  /**
   * @param priceClassCode The priceClassCode
   */
  @JsonProperty("priceClassCode")
  public void setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
  }

  /**
   * @return The priceClassAlias
   */
  @JsonProperty("priceClassAlias")
  public String getPriceClassAlias() {
    return priceClassAlias;
  }

  /**
   * @param priceClassAlias The priceClassAlias
   */
  @JsonProperty("priceClassAlias")
  public void setPriceClassAlias(String priceClassAlias) {
    this.priceClassAlias = priceClassAlias;
  }

  /**
   * @return The priceCatList
   */
  @JsonProperty("priceCatList")
  public List<PriceCatList> getPriceCatList() {
    return priceCatList;
  }

  /**
   * @param priceCatList The priceCatList
   */
  @JsonProperty("priceCatList")
  public void setPriceCatList(List<PriceCatList> priceCatList) {
    this.priceCatList = priceCatList;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceClassCode", priceClassCode)
        .append("priceClassAlias", priceClassAlias)
        .append("priceCatList", priceCatList)
        .toString();
  }
}
