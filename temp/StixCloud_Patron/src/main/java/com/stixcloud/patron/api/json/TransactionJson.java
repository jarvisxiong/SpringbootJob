package com.stixcloud.patron.api.json;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import javax.money.MonetaryAmount;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"transactionRefNumber", "type", "dateOfPurchase", "purchasedWith", "lineItems",
    "totalAmount"})
public class TransactionJson implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -2100616186004718366L;

  @JsonProperty("transactionRefNumber")
  private String referenceId;
  @JsonProperty("type")
  private String type;
  @JsonProperty("dateOfPurchase")
  private OffsetDateTime dateOfPurchase;
  @JsonProperty("purchasedWith")
  private String purchasedWith;
  @JsonProperty("lineItems")
  private List<LineItemJson> lineItems;
  @JsonProperty("totalAmount")
  private MonetaryAmount totalAmount;

  public TransactionJson() {
  }

  public TransactionJson(String referenceId, String type, OffsetDateTime dateOfPurchase,
      String purchasedWith, List<LineItemJson> lineItems, MonetaryAmount totalAmount) {
    super();
    this.referenceId = referenceId;
    this.type = type;
    this.dateOfPurchase = dateOfPurchase;
    this.purchasedWith = purchasedWith;
    this.lineItems = lineItems;
    this.totalAmount = totalAmount;
  }

  /**
   * @return the referenceId
   */
  public String getReferenceId() {
    return referenceId;
  }

  /**
   * @param referenceId the referenceId to set
   */
  public void setReferenceId(String referenceId) {
    this.referenceId = referenceId;
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
   * @return the dateOfPurchase
   */
  public OffsetDateTime getDateOfPurchase() {
    return dateOfPurchase;
  }

  /**
   * @param dateOfPurchase the dateOfPurchase to set
   */
  public void setDateOfPurchase(OffsetDateTime dateOfPurchase) {
    this.dateOfPurchase = dateOfPurchase;
  }

  /**
   * @return the purchasedWith
   */
  public String getPurchasedWith() {
    return purchasedWith;
  }

  /**
   * @param purchasedWith the purchasedWith to set
   */
  public void setPurchasedWith(String purchasedWith) {
    this.purchasedWith = purchasedWith;
  }

  /**
   * @return the lineItems
   */
  public List<LineItemJson> getLineItems() {
    return lineItems;
  }

  /**
   * @param lineItems the lineItems to set
   */
  public void setLineItems(List<LineItemJson> lineItems) {
    this.lineItems = lineItems;
  }

  /**
   * @return the totalAmount
   */
  public MonetaryAmount getTotalAmount() {
    return totalAmount;
  }

  /**
   * @param totalAmount the totalAmount to set
   */
  public void setTotalAmount(MonetaryAmount totalAmount) {
    this.totalAmount = totalAmount;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TransactionJson that = (TransactionJson) o;
    return new EqualsBuilder().append(referenceId, that.getReferenceId())
        .append(dateOfPurchase, that.getDateOfPurchase())
        .append(lineItems, that.getLineItems())
        .append(purchasedWith, that.getPurchasedWith())
        .append(totalAmount, that.getTotalAmount())
        .append(type, that.getType())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(referenceId).append(dateOfPurchase).append(lineItems)
        .append(purchasedWith).append(totalAmount).append(type).toHashCode();
  }
}
