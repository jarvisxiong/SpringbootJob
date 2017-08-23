package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author chongye
 */
public class CupRequest implements Serializable {

  private static final long serialVersionUID = 9207019942520367655L;
  @JsonProperty("version")
  private String version = "1.0.0";
  @JsonProperty("charset")
  private String charset = "UTF-8";
  @JsonProperty("signMethod")
  private String signMethod = "SHA";
  @JsonProperty("postUrl")
  private String postUrl;
  @JsonProperty("signature")
  private String signature;
  @JsonProperty("transType")
  private String transType;
  @JsonProperty("merAbbr")
  private String merAbbr;
  @JsonProperty("merId")
  private String merId;
  @JsonProperty("backEndUrl")
  private String backEndUrl;
  @JsonProperty("frontEndUrl")
  private String frontEndUrl;
  @JsonProperty("orderTime")
  private String orderTime;
  @JsonProperty("orderNumber")
  private String orderNumber;
  @JsonProperty("commodityUrl")
  private String commodityUrl;
  @JsonProperty("orderAmount")
  private String orderAmount;
  @JsonProperty("customerName")
  private String customerName;
  @JsonProperty("defaultPayType")
  private String defaultPayType;//optional
  @JsonProperty("defaultBankNumber")
  private String defaultBankNumber;//optional
  @JsonProperty("transTimeout")
  private String transTimeout;
  @JsonProperty("customerIp")
  private String customerIp;
  @JsonProperty("origQid")
  private String origQid;//tenable
  @JsonProperty("merReserved")
  private String merReserved;//optional
  @JsonProperty("language")
  private String language = "0";//0 for english, 1 for chinese
  @JsonProperty("cardHolderName")
  private String cardHolderName;
  @JsonProperty("creditCardNumber")
  private String creditCardNumber;

  public CupRequest() {
  }

  private CupRequest(Builder builder) {
    version = builder.version;
    charset = builder.charset;
    signMethod = builder.signMethod;
    postUrl = builder.postUrl;
    signature = builder.signature;
    transType = builder.transType;
    merAbbr = builder.merAbbr;
    merId = builder.merId;
    backEndUrl = builder.backEndUrl;
    frontEndUrl = builder.frontEndUrl;
    orderTime = builder.orderTime;
    orderNumber = builder.orderNumber;
    commodityUrl = builder.commodityUrl;
    orderAmount = builder.orderAmount;
    customerName = builder.customerName;
    defaultPayType = builder.defaultPayType;
    defaultBankNumber = builder.defaultBankNumber;
    transTimeout = builder.transTimeout;
    customerIp = builder.customerIp;
    origQid = builder.origQid;
    merReserved = builder.merReserved;
    language = builder.language;
    cardHolderName = builder.cardHolderName;
    creditCardNumber = builder.creditCardNumber;
  }

  public String getCardHolderName() {
    return cardHolderName;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public String getBackEndUrl() {
    return backEndUrl;
  }

  public String getCharset() {
    return charset;
  }

  public String getCommodityUrl() {
    return commodityUrl;
  }

  public String getCustomerIp() {
    return customerIp;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getDefaultBankNumber() {
    return defaultBankNumber;
  }

  public String getDefaultPayType() {
    return defaultPayType;
  }

  public String getFrontEndUrl() {
    return frontEndUrl;
  }

  public String getMerAbbr() {
    return merAbbr;
  }

  public String getMerId() {
    return merId;
  }

  public String getMerReserved() {
    return merReserved;
  }

  public String getOrderAmount() {
    return orderAmount;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public String getOrderTime() {
    return orderTime;
  }

  public String getOrigQid() {
    return origQid;
  }

  public String getSignature() {
    return signature;
  }

  public String getSignMethod() {
    return signMethod;
  }

  public String getTransTimeout() {
    return transTimeout;
  }

  public String getTransType() {
    return transType;
  }

  public String getVersion() {
    return version;
  }

  public String getPostUrl() {
    return postUrl;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("version", version)
        .append("charset", charset)
        .append("signMethod", signMethod)
        .append("postUrl", postUrl)
        .append("signature", signature)
        .append("transType", transType)
        .append("merAbbr", merAbbr)
        .append("merId", merId)
        .append("backEndUrl", backEndUrl)
        .append("frontEndUrl", frontEndUrl)
        .append("orderTime", orderTime)
        .append("orderNumber", orderNumber)
        .append("commodityUrl", commodityUrl)
        .append("orderAmount", orderAmount)
        .append("customerName", customerName)
        .append("defaultPayType", defaultPayType)
        .append("defaultBankNumber", defaultBankNumber)
        .append("transTimeout", transTimeout)
        .append("customerIp", customerIp)
        .append("origQid", origQid)
        .append("merReserved", merReserved)
        .append("language", language)
        .append("cardHolderName", cardHolderName)
        .append("creditCardNumber", creditCardNumber)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CupRequest that = (CupRequest) o;

    return Objects.equals(this.backEndUrl, that.backEndUrl) &&
        Objects.equals(this.cardHolderName, that.cardHolderName) &&
        Objects.equals(this.charset, that.charset) &&
        Objects.equals(this.commodityUrl, that.commodityUrl) &&
        Objects.equals(this.creditCardNumber, that.creditCardNumber) &&
        Objects.equals(this.customerIp, that.customerIp) &&
        Objects.equals(this.customerName, that.customerName) &&
        Objects.equals(this.defaultBankNumber, that.defaultBankNumber) &&
        Objects.equals(this.defaultPayType, that.defaultPayType) &&
        Objects.equals(this.frontEndUrl, that.frontEndUrl) &&
        Objects.equals(this.language, that.language) &&
        Objects.equals(this.merAbbr, that.merAbbr) &&
        Objects.equals(this.merId, that.merId) &&
        Objects.equals(this.merReserved, that.merReserved) &&
        Objects.equals(this.orderAmount, that.orderAmount) &&
        Objects.equals(this.orderNumber, that.orderNumber) &&
        Objects.equals(this.orderTime, that.orderTime) &&
        Objects.equals(this.origQid, that.origQid) &&
        Objects.equals(this.postUrl, that.postUrl) &&
        Objects.equals(this.serialVersionUID, that.serialVersionUID) &&
        Objects.equals(this.signature, that.signature) &&
        Objects.equals(this.signMethod, that.signMethod) &&
        Objects.equals(this.transTimeout, that.transTimeout) &&
        Objects.equals(this.transType, that.transType) &&
        Objects.equals(this.version, that.version);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(backEndUrl, cardHolderName, charset, commodityUrl, creditCardNumber, customerIp,
            customerName, defaultBankNumber, defaultPayType, frontEndUrl, language,
            merAbbr, merId, merReserved, orderAmount, orderNumber,
            orderTime, origQid, postUrl, serialVersionUID, signature,
            signMethod, transTimeout, transType, version);
  }

  public static final class Builder {
    private String version;
    private String charset;
    private String signMethod;
    private String postUrl;
    private String signature;
    private String transType;
    private String merAbbr;
    private String merId;
    private String backEndUrl;
    private String frontEndUrl;
    private String orderTime;
    private String orderNumber;
    private String commodityUrl;
    private String orderAmount;
    private String customerName;
    private String defaultPayType;
    private String defaultBankNumber;
    private String transTimeout;
    private String customerIp;
    private String origQid;
    private String merReserved;
    private String language;
    private String cardHolderName;
    private String creditCardNumber;

    public Builder() {
    }

    public Builder(CupRequest copy) {
      this.version = copy.version;
      this.charset = copy.charset;
      this.signMethod = copy.signMethod;
      this.postUrl = copy.postUrl;
      this.signature = copy.signature;
      this.transType = copy.transType;
      this.merAbbr = copy.merAbbr;
      this.merId = copy.merId;
      this.backEndUrl = copy.backEndUrl;
      this.frontEndUrl = copy.frontEndUrl;
      this.orderTime = copy.orderTime;
      this.orderNumber = copy.orderNumber;
      this.commodityUrl = copy.commodityUrl;
      this.orderAmount = copy.orderAmount;
      this.customerName = copy.customerName;
      this.defaultPayType = copy.defaultPayType;
      this.defaultBankNumber = copy.defaultBankNumber;
      this.transTimeout = copy.transTimeout;
      this.customerIp = copy.customerIp;
      this.origQid = copy.origQid;
      this.merReserved = copy.merReserved;
      this.language = copy.language;
      this.cardHolderName = copy.cardHolderName;
      this.creditCardNumber = copy.creditCardNumber;
    }

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public Builder charset(String charset) {
      this.charset = charset;
      return this;
    }

    public Builder signMethod(String signMethod) {
      this.signMethod = signMethod;
      return this;
    }

    public Builder postUrl(String postUrl) {
      this.postUrl = postUrl;
      return this;
    }

    public Builder signature(String signature) {
      this.signature = signature;
      return this;
    }

    public Builder transType(String transType) {
      this.transType = transType;
      return this;
    }

    public Builder merAbbr(String merAbbr) {
      this.merAbbr = merAbbr;
      return this;
    }

    public Builder merId(String merId) {
      this.merId = merId;
      return this;
    }

    public Builder backEndUrl(String backEndUrl) {
      this.backEndUrl = backEndUrl;
      return this;
    }

    public Builder frontEndUrl(String frontEndUrl) {
      this.frontEndUrl = frontEndUrl;
      return this;
    }

    public Builder orderTime(String orderTime) {
      this.orderTime = orderTime;
      return this;
    }

    public Builder orderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
      return this;
    }

    public Builder commodityUrl(String commodityUrl) {
      this.commodityUrl = commodityUrl;
      return this;
    }

    public Builder orderAmount(String orderAmount) {
      this.orderAmount = orderAmount;
      return this;
    }

    public Builder customerName(String customerName) {
      this.customerName = customerName;
      return this;
    }

    public Builder defaultPayType(String defaultPayType) {
      this.defaultPayType = defaultPayType;
      return this;
    }

    public Builder defaultBankNumber(String defaultBankNumber) {
      this.defaultBankNumber = defaultBankNumber;
      return this;
    }

    public Builder transTimeout(String transTimeout) {
      this.transTimeout = transTimeout;
      return this;
    }

    public Builder customerIp(String customerIp) {
      this.customerIp = customerIp;
      return this;
    }

    public Builder origQid(String origQid) {
      this.origQid = origQid;
      return this;
    }

    public Builder merReserved(String merReserved) {
      this.merReserved = merReserved;
      return this;
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder cardHolderName(String cardHolderName) {
      this.cardHolderName = cardHolderName;
      return this;
    }

    public Builder creditCardNumber(String creditCardNumber) {
      this.creditCardNumber = creditCardNumber;
      return this;
    }

    public CupRequest build() {
      return new CupRequest(this);
    }
  }

  public final static class PostDataBuilder {
    private CupRequest cupRequest;
    private boolean includeSignature;

    public PostDataBuilder(CupRequest cupRequest, boolean includeSignature) {
      this.cupRequest = cupRequest;
      this.includeSignature = includeSignature;
    }

    public MultiValueMap<String, String> buildQuery() {
      TreeMap<String, String> map = new TreeMap<>();

      map.put("charset", cupRequest.getCharset());

      if (StringUtils.isNotBlank(cupRequest.getMerId())) {
        map.put("merId", cupRequest.getMerId());
      }

      if (StringUtils.isNotBlank(cupRequest.getMerReserved())) {
        map.put("merReserved", cupRequest.getMerReserved());
      } else {
        map.put("merReserved", "");
      }

      if (StringUtils.isNotBlank(cupRequest.getOrderNumber())) {
        map.put("orderNumber", cupRequest.getOrderNumber());
      } else {
        map.put("orderNumber", "");
      }

      if (StringUtils.isNotBlank(cupRequest.getOrderTime())) {
        map.put("orderTime", cupRequest.getOrderTime());
      } else {
        map.put("orderTime", "");
      }

      if (includeSignature && StringUtils.isNotBlank(cupRequest.getSignature())) {
        map.put("signature", cupRequest.getSignature());
      }

      if (includeSignature && StringUtils.isNotBlank(cupRequest.getSignMethod())) {
        map.put("signMethod", cupRequest.getSignMethod());
      }

      if (StringUtils.isNotBlank(cupRequest.getTransType())) {
        map.put("transType", cupRequest.getTransType());
      }

      if (StringUtils.isNotBlank(cupRequest.getVersion())) {
        map.put("version", cupRequest.getVersion());
      }

      MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
      multiValueMap.setAll(map);
      return multiValueMap;
    }

    public MultiValueMap<String, String> buildRequest() {
      TreeMap<String, String> map = new TreeMap<>();
      if (StringUtils.isNotBlank(cupRequest.getBackEndUrl())) {
        map.put("backEndUrl", cupRequest.getBackEndUrl());
      }

      map.put("charset", cupRequest.getCharset());

      if (cupRequest.getTransType().equals(CupConstants.ACTION_PURCHASE)) {
        if (StringUtils.isNotBlank(cupRequest.getCreditCardNumber())) {
          map.put("cardNumber", cupRequest.getCreditCardNumber());
        }
      }

      if (StringUtils.isNotBlank(cupRequest.getCommodityUrl())) {
        map.put("commodityUrl", cupRequest.getCommodityUrl());
      }

      if (StringUtils.isNotBlank(cupRequest.getCustomerIp())) {
        map.put("customerIp", cupRequest.getCustomerIp());
      }

      if (StringUtils.isNotBlank(cupRequest.getCustomerName())) {
        map.put("customerName", cupRequest.getCustomerName());
      }

      if (StringUtils.isNotBlank(cupRequest.getDefaultBankNumber())) {
        map.put("defaultBankNumber", cupRequest.getDefaultBankNumber());
      }

      if (StringUtils.isNotBlank(cupRequest.getDefaultPayType())) {
        map.put("defaultPayType", cupRequest.getDefaultPayType());
      }

      if (StringUtils.isNotBlank(cupRequest.getFrontEndUrl())) {
        map.put("frontEndUrl", cupRequest.getFrontEndUrl());
      }

      if (StringUtils.isNotBlank(cupRequest.getMerAbbr())) {
        map.put("merAbbr", cupRequest.getMerAbbr());
      }

      if (StringUtils.isNotBlank(cupRequest.getMerId())) {
        map.put("merId", cupRequest.getMerId());
      }

      if (StringUtils.isNotBlank(cupRequest.getMerReserved())) {
        map.put("merReserved", cupRequest.getMerReserved());
      }

      if (StringUtils.isNotBlank(cupRequest.getOrderAmount())) {
        map.put("orderAmount", cupRequest.getOrderAmount());
      }

      if (StringUtils.isNotBlank(cupRequest.getOrderNumber())) {
        map.put("orderNumber", cupRequest.getOrderNumber());
      }

      if (StringUtils.isNotBlank(cupRequest.getOrderTime())) {
        map.put("orderTime", cupRequest.getOrderTime());
      }

      if (StringUtils.isNotBlank(cupRequest.getOrigQid())) {
        map.put("origQid", cupRequest.getOrigQid());
      }

      if (includeSignature && StringUtils.isNotBlank(cupRequest.getSignature())) {
        map.put("signature", cupRequest.getSignature());
      }

      if (includeSignature && StringUtils.isNotBlank(cupRequest.getSignMethod())) {
        map.put("signMethod", cupRequest.getSignMethod());
      }

      if (StringUtils.isNotBlank(cupRequest.getTransTimeout())) {
        map.put("transTimeout", cupRequest.getTransTimeout());
      }

      if (StringUtils.isNotBlank(cupRequest.getTransType())) {
        map.put("transType", cupRequest.getTransType());
      }

      if (StringUtils.isNotBlank(cupRequest.getVersion())) {
        map.put("version", cupRequest.getVersion());
      }

      MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
      multiValueMap.setAll(map);
      return multiValueMap;
    }

    public String buildCUPSignature(String password) {
      String reqStr = this.buildQuery().toSingleValueMap().entrySet().stream()
          .map(entry -> entry.getKey() + "=" + entry.getValue())
          .reduce((a, b) -> String.join("&", a, b)).get();
      password = DigestUtils.sha256Hex(password);
      String paramsWithoutSignature = reqStr.concat("&" + password);

      return DigestUtils.sha256Hex(paramsWithoutSignature);
    }
  }
}
