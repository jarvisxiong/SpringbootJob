package com.stixcloud.patron.api.json;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"type", "product", "description", "mailingAddress", "quantity", "unitPrice",
    "bookingFee", "subTotal"})
public class LineItemJson implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 221241738500725550L;
  @JsonProperty("type")
  private String type;
  @JsonProperty("product")
  private ProductJson product;
  @JsonProperty("name")
  private String name;
  @JsonProperty("code")
  private String code;
  @JsonProperty("description")
  private String description;
  @JsonProperty("mailingAddress")
  private MailingAddressJson mailingAddress;
  @JsonProperty("quantity")
  private Long quantity;
  @JsonProperty("unitPrice")
  private MonetaryAmount unitPrice;
  @JsonProperty("bookingFee")
  private MonetaryAmount bookingFee;
  @JsonProperty("subTotal")
  private MonetaryAmount subTotal;

  public LineItemJson() {
    super();
  }

  public LineItemJson(String type, ProductJson product, String name, String code,
      String description, MailingAddressJson mailingAddress, Long quantity,
      MonetaryAmount unitPrice, MonetaryAmount bookingFee, MonetaryAmount subTotal) {
    super();
    this.type = type;
    this.product = product;
    this.name = name;
    this.code = code;
    this.description = description;
    this.mailingAddress = mailingAddress;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.bookingFee = bookingFee;
    this.subTotal = subTotal;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the product
   */
  public ProductJson getProduct() {
    return product;
  }

  /**
   * @param product the product to set
   */
  public void setProduct(ProductJson product) {
    this.product = product;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the mailingAddress
   */
  public MailingAddressJson getMailingAddress() {
    return mailingAddress;
  }

  /**
   * @param mailingAddress the mailingAddress to set
   */
  public void setMailingAddress(MailingAddressJson mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  /**
   * @return the quantity
   */
  public Long getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  /**
   * @return the unitPrice
   */
  public MonetaryAmount getUnitPrice() {
    return unitPrice;
  }

  /**
   * @param unitPrice the unitPrice to set
   */
  public void setUnitPrice(MonetaryAmount unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * @return the bookingFee
   */
  public MonetaryAmount getBookingFee() {
    return bookingFee;
  }

  /**
   * @param bookingFee the bookingFee to set
   */
  public void setBookingFee(MonetaryAmount bookingFee) {
    this.bookingFee = bookingFee;
  }

  /**
   * @return the subTotal
   */
  public MonetaryAmount getSubTotal() {
    return subTotal;
  }

  /**
   * @param subTotal the subTotal to set
   */
  public void setSubTotal(MonetaryAmount subTotal) {
    this.subTotal = subTotal;
  }


}
