package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "deliveryMethodCode",
    "paymentMethodCode",
    "sameAsMailingAddrFlag",
    "purchaseTp",
    "address",
    "patronSolicitation",
    "evoucherIdList",
    "creditCardNo",
    "creditCardMonth",
    "creditCardYear",
    "creditCardCSC"
})
public class PreCommitRequest {

  @NotBlank
  @JsonProperty("deliveryMethodCode")
  private String deliveryMethodCode;

  @JsonProperty("paymentMethodCode")
  private String paymentMethodCode;

  @JsonProperty("sameAsMailingAddrFlag")
  private Boolean sameAsMailingAddrFlag;

  @NotNull
  @JsonProperty("purchaseTp")
  private Boolean purchaseTp;

  @JsonProperty("address")
  @Valid
  private List<AddressJson> address = new ArrayList<AddressJson>();

  @JsonProperty("patronSolicitation")
  @Valid
  private PatronSolicitation patronSolicitation;

  @JsonProperty("evoucherIdList")
  private List<String> evoucherIdList = new ArrayList<String>();

  @JsonProperty("creditCardNo")
  private String creditCardNo;

  @JsonProperty("creditCardMonth")
  private String creditCardMonth;

  @JsonProperty("creditCardYear")
  private String creditCardYear;

  @JsonProperty("creditCardCSC")
  private Long creditCardCSC;

  /**
   * No args constructor for use in serialization
   */
  public PreCommitRequest() {
  }

  private PreCommitRequest(Builder builder) {
    deliveryMethodCode = builder.deliveryMethodCode;
    paymentMethodCode = builder.paymentMethodCode;
    sameAsMailingAddrFlag = builder.sameAsMailingAddrFlag;
    purchaseTp = builder.purchaseTp;
    address = builder.address;
    patronSolicitation = builder.patronSolicitation;
    evoucherIdList = builder.evoucherIdList;
    creditCardNo = builder.creditCardNo;
    creditCardMonth = builder.creditCardMonth;
    creditCardYear = builder.creditCardYear;
    creditCardCSC = builder.creditCardCSC;
  }

  /**
   * @return The deliveryMethodCode
   */
  @JsonProperty("deliveryMethodCode")
  public String getDeliveryMethodCode() {
    return deliveryMethodCode;
  }

  /**
   * @return The paymentMethodCode
   */
  @JsonProperty("paymentMethodCode")
  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }


  /**
   * @return The sameAsMailingAddrFlag
   */
  @JsonProperty("sameAsMailingAddrFlag")
  public Boolean getSameAsMailingAddrFlag() {
    return sameAsMailingAddrFlag;
  }

  /**
   * @return The purchaseTp
   */
  @JsonProperty("purchaseTp")
  public Boolean getPurchaseTp() {
    return purchaseTp;
  }

  /**
   * @return The addressJson
   */
  @JsonProperty("address")
  public List<AddressJson> getAddress() {
    return address;
  }

  /**
   * @return The patronSolicitation
   */
  @JsonProperty("patronSolicitation")
  public PatronSolicitation getPatronSolicitation() {
    return patronSolicitation;
  }

  /**
   * @return The evoucherIdList
   */
  @JsonProperty("evoucherIdList")
  public List<String> getEvoucherIdList() {
    return evoucherIdList;
  }

  /**
   * @return The creditCardNo
   */
  @JsonProperty("creditCardNo")
  public String getCreditCardNo() {
    return creditCardNo;
  }

  @JsonProperty("creditCardMonth")
  public String getCreditCardMonth() {
    return creditCardMonth;
  }

  @JsonProperty("creditCardYear")
  public String getCreditCardYear() {
    return creditCardYear;
  }

  @JsonProperty("creditCardCSC")
  public Long getCreditCardCSC() {
    return creditCardCSC;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PreCommitRequest that = (PreCommitRequest) o;

    return Objects.equals(this.address, that.address) &&
        Objects.equals(this.creditCardCSC, that.creditCardCSC) &&
        Objects.equals(this.creditCardMonth, that.creditCardMonth) &&
        Objects.equals(this.creditCardNo, that.creditCardNo) &&
        Objects.equals(this.creditCardYear, that.creditCardYear) &&
        Objects.equals(this.deliveryMethodCode, that.deliveryMethodCode) &&
        Objects.equals(this.evoucherIdList, that.evoucherIdList) &&
        Objects.equals(this.patronSolicitation, that.patronSolicitation) &&
        Objects.equals(this.paymentMethodCode, that.paymentMethodCode) &&
        Objects.equals(this.purchaseTp, that.purchaseTp) &&
        Objects.equals(this.sameAsMailingAddrFlag, that.sameAsMailingAddrFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, creditCardCSC, creditCardMonth, creditCardNo, creditCardYear,
        deliveryMethodCode,
        evoucherIdList, patronSolicitation, paymentMethodCode, purchaseTp, sameAsMailingAddrFlag);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this,
        ToStringStyle.JSON_STYLE)
        .append("deliveryMethodCode", deliveryMethodCode)
        .append("paymentMethodCode", paymentMethodCode)
        .append("sameAsMailingAddrFlag", sameAsMailingAddrFlag)
        .append("purchaseTp", purchaseTp)
        .append("address", address)
        .append("patronSolicitation", patronSolicitation)
        .append("evoucherIdList", evoucherIdList)
        .append("creditCardNo", creditCardNo)
        .append("creditCardMonth", creditCardMonth)
        .append("creditCardYear", creditCardYear)
        .append("creditCardCSC", creditCardCSC)
        .toString();
  }

  public static final class Builder {
    private String deliveryMethodCode;
    private String paymentMethodCode;
    private Boolean sameAsMailingAddrFlag;
    private Boolean purchaseTp;
    private List<AddressJson> address;
    private PatronSolicitation patronSolicitation;
    private List<String> evoucherIdList;
    private String creditCardNo;
    private String creditCardMonth;
    private String creditCardYear;
    private Long creditCardCSC;

    public Builder() {
    }

    public Builder(PreCommitRequest copy) {
      this.deliveryMethodCode = copy.deliveryMethodCode;
      this.paymentMethodCode = copy.paymentMethodCode;
      this.sameAsMailingAddrFlag = copy.sameAsMailingAddrFlag;
      this.purchaseTp = copy.purchaseTp;
      this.address = copy.address;
      this.patronSolicitation = copy.patronSolicitation;
      this.evoucherIdList = copy.evoucherIdList;
      this.creditCardNo = copy.creditCardNo;
      this.creditCardMonth = copy.creditCardMonth;
      this.creditCardCSC = copy.creditCardCSC;
    }

    public Builder deliveryMethodCode(String deliveryMethodCode) {
      this.deliveryMethodCode = deliveryMethodCode;
      return this;
    }

    public Builder paymentMethodCode(String paymentMethodCode) {
      this.paymentMethodCode = paymentMethodCode;
      return this;
    }

    public Builder sameAsMailingAddrFlag(Boolean sameAsMailingAddrFlag) {
      this.sameAsMailingAddrFlag = sameAsMailingAddrFlag;
      return this;
    }

    public Builder purchaseTp(Boolean purchaseTp) {
      this.purchaseTp = purchaseTp;
      return this;
    }

    public Builder address(List<AddressJson> address) {
      this.address = address;
      return this;
    }

    public Builder patronSolicitation(PatronSolicitation patronSolicitation) {
      this.patronSolicitation = patronSolicitation;
      return this;
    }

    public Builder evoucherIdList(List<String> evoucherIdList) {
      this.evoucherIdList = evoucherIdList;
      return this;
    }

    public Builder creditCardNo(String creditCardNo) {
      this.creditCardNo = creditCardNo;
      return this;
    }

    public Builder creditCardMonth(String val) {
      creditCardMonth = val;
      return this;
    }

    public Builder creditCardYear(String val) {
      creditCardYear = val;
      return this;
    }

    public Builder creditCardCSC(Long creditCardCSC) {
      this.creditCardCSC = creditCardCSC;
      return this;
    }

    public PreCommitRequest build() {
      return new PreCommitRequest(this);
    }
  }
}