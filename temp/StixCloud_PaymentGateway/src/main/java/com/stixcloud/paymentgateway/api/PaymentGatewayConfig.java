package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "enableCSC",
    "clientServer",
    "merchantId",
    "secureType",
    "secureCode",
    "accessCode",
    "returnUrl",
    "redirectUrl",
    "processingUrl",
    "payType",
    "timeout",
    "currency",
    "hashkey",
    "signatureVersion",
    "signatureOn",
    "secureMethod",
    "merchantName",
    "frontendUrl",
    "isSinopayOn",
    "statusUrl",
    "revenueCenterId"
})
public class PaymentGatewayConfig implements Serializable {

  private static final long serialVersionUID = -7800695728928636798L;
  @JsonProperty("enableCSC")
  private Boolean enableCSC;
  @JsonProperty("clientServer")
  private String clientServer;
  @JsonProperty("merchantId")
  private String merchantId;
  @JsonProperty("secureType")
  private String secureType;
  @JsonProperty("secureCode")
  private String secureCode;
  @JsonProperty("accessCode")
  private String accessCode;
  @JsonProperty("returnUrl")
  private String returnUrl;
  @JsonProperty("redirectUrl")
  private String redirectUrl;
  @JsonProperty("processingUrl")
  private String processingUrl;
  @JsonProperty("payType")
  private Integer payType;
  @JsonProperty("timeout")
  private Integer timeout;
  @JsonProperty("currency")
  private String currency;
  @JsonProperty("hashkey")
  private String hashkey;
  @JsonProperty("signatureVersion")
  private Integer signatureVersion;
  @JsonProperty("signatureOn")
  private Boolean signatureOn;
  @JsonProperty("secureMethod")
  private String secureMethod;
  @JsonProperty("merchantName")
  private String merchantName;
  @JsonProperty("frontendUrl")
  private String frontendUrl;
  @JsonProperty("isSinopayOn")
  private Boolean isSinopayOn;
  @JsonProperty("statusUrl")
  private String statusUrl;
  @JsonProperty("revenueCenterId")
  private String revenueCenterId;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<>();

  /**
   * No args constructor for use in serialization
   */
  public PaymentGatewayConfig() {
  }

  private PaymentGatewayConfig(Builder builder) {
    setEnableCSC(builder.enableCSC);
    setClientServer(builder.clientServer);
    setMerchantId(builder.merchantId);
    setSecureType(builder.secureType);
    setSecureCode(builder.secureCode);
    setAccessCode(builder.accessCode);
    setReturnUrl(builder.returnUrl);
    setRedirectUrl(builder.redirectUrl);
    setProcessingUrl(builder.processingUrl);
    setPayType(builder.payType);
    setTimeout(builder.timeout);
    setCurrency(builder.currency);
    setHashkey(builder.hashkey);
    setSignatureVersion(builder.signatureVersion);
    setSignatureOn(builder.signatureOn);
    setSecureMethod(builder.secureMethod);
    setMerchantName(builder.merchantName);
    setFrontendUrl(builder.frontendUrl);
    isSinopayOn = builder.isSinopayOn;
    setStatusUrl(builder.statusUrl);
    setRevenueCenterId(builder.revenueCenterId);
    additionalProperties = builder.additionalProperties;
  }

  public Boolean getEnableCSC() {
    return enableCSC;
  }

  public void setEnableCSC(Boolean enableCSC) {
    this.enableCSC = enableCSC;
  }

  public String getClientServer() {
    return clientServer;
  }

  public void setClientServer(String clientServer) {
    this.clientServer = clientServer;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getSecureType() {
    return secureType;
  }

  public void setSecureType(String secureType) {
    this.secureType = secureType;
  }

  public String getSecureCode() {
    return secureCode;
  }

  public void setSecureCode(String secureCode) {
    this.secureCode = secureCode;
  }

  public String getAccessCode() {
    return accessCode;
  }

  public void setAccessCode(String accessCode) {
    this.accessCode = accessCode;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  public String getProcessingUrl() {
    return processingUrl;
  }

  public void setProcessingUrl(String processingUrl) {
    this.processingUrl = processingUrl;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Integer getTimeout() {
    return timeout;
  }

  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getHashkey() {
    return hashkey;
  }

  public void setHashkey(String hashkey) {
    this.hashkey = hashkey;
  }

  public Integer getSignatureVersion() {
    return signatureVersion;
  }

  public void setSignatureVersion(Integer signatureVersion) {
    this.signatureVersion = signatureVersion;
  }

  public Boolean getSignatureOn() {
    return signatureOn;
  }

  public void setSignatureOn(Boolean signatureOn) {
    this.signatureOn = signatureOn;
  }

  public String getSecureMethod() {
    return secureMethod;
  }

  public void setSecureMethod(String secureMethod) {
    this.secureMethod = secureMethod;
  }

  public String getMerchantName() {
    return merchantName;
  }

  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }

  public String getFrontendUrl() {
    return frontendUrl;
  }

  public void setFrontendUrl(String frontendUrl) {
    this.frontendUrl = frontendUrl;
  }

  public Boolean getSinopayOn() {
    return isSinopayOn;
  }

  public void setSinopayOn(Boolean sinopayOn) {
    isSinopayOn = sinopayOn;
  }

  public String getStatusUrl() {

    return statusUrl;
  }

  public void setStatusUrl(String statusUrl) {
    this.statusUrl = statusUrl;
  }

  public String getRevenueCenterId() {
    return this.revenueCenterId;
  }

  public void setRevenueCenterId(String revenueCenterId) {
    this.revenueCenterId = revenueCenterId;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("enableCSC", enableCSC)
        .append("clientServer", clientServer)
        .append("merchantId", merchantId)
        .append("secureType", secureType)
        .append("secureCode", secureCode)
        .append("accessCode", accessCode)
        .append("returnUrl", returnUrl)
        .append("redirectUrl", redirectUrl)
        .append("processingUrl", processingUrl)
        .append("payType", payType)
        .append("timeout", timeout)
        .append("currency", currency)
        .append("hashkey", hashkey)
        .append("signatureVersion", signatureVersion)
        .append("signatureOn", signatureOn)
        .append("secureMethod", secureMethod)
        .append("merchantName", merchantName)
        .append("frontendUrl", frontendUrl)
        .append("isSinopayOn", isSinopayOn)
        .append("statusUrl", statusUrl)
        .append("revenueCenterId", revenueCenterId)
        .append("additionalProperties", additionalProperties)
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

    PaymentGatewayConfig that = (PaymentGatewayConfig) o;

    return Objects.equals(this.accessCode, that.accessCode) &&
        Objects.equals(this.additionalProperties, that.additionalProperties) &&
        Objects.equals(this.clientServer, that.clientServer) &&
        Objects.equals(this.currency, that.currency) &&
        Objects.equals(this.enableCSC, that.enableCSC) &&
        Objects.equals(this.frontendUrl, that.frontendUrl) &&
        Objects.equals(this.hashkey, that.hashkey) &&
        Objects.equals(this.isSinopayOn, that.isSinopayOn) &&
        Objects.equals(this.merchantId, that.merchantId) &&
        Objects.equals(this.merchantName, that.merchantName) &&
        Objects.equals(this.payType, that.payType) &&
        Objects.equals(this.processingUrl, that.processingUrl) &&
        Objects.equals(this.redirectUrl, that.redirectUrl) &&
        Objects.equals(this.returnUrl, that.returnUrl) &&
        Objects.equals(this.revenueCenterId, that.revenueCenterId) &&
        Objects.equals(this.secureCode, that.secureCode) &&
        Objects.equals(this.secureMethod, that.secureMethod) &&
        Objects.equals(this.secureType, that.secureType) &&
        Objects.equals(this.serialVersionUID, that.serialVersionUID) &&
        Objects.equals(this.signatureOn, that.signatureOn) &&
        Objects.equals(this.signatureVersion, that.signatureVersion) &&
        Objects.equals(this.statusUrl, that.statusUrl) &&
        Objects.equals(this.timeout, that.timeout);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(accessCode, additionalProperties, clientServer, currency, enableCSC, frontendUrl,
            hashkey, isSinopayOn, merchantId, merchantName, payType,
            processingUrl, redirectUrl, returnUrl, revenueCenterId, secureCode,
            secureMethod, secureType, serialVersionUID, signatureOn, signatureVersion,
            statusUrl, timeout);
  }

  public static final class Builder {
    private Boolean enableCSC;
    private String clientServer;
    private String merchantId;
    private String secureType;
    private String secureCode;
    private String accessCode;
    private String returnUrl;
    private String redirectUrl;
    private String processingUrl;
    private Integer payType;
    private Integer timeout;
    private String currency;
    private String hashkey;
    private Integer signatureVersion;
    private Boolean signatureOn;
    private String secureMethod;
    private String merchantName;
    private String frontendUrl;
    private Boolean isSinopayOn;
    private String statusUrl;
    private String revenueCenterId;
    private Map<String, Object> additionalProperties;

    public Builder() {
    }

    public Builder(PaymentGatewayConfig copy) {
      this.enableCSC = copy.enableCSC;
      this.clientServer = copy.clientServer;
      this.merchantId = copy.merchantId;
      this.secureType = copy.secureType;
      this.secureCode = copy.secureCode;
      this.accessCode = copy.accessCode;
      this.returnUrl = copy.returnUrl;
      this.redirectUrl = copy.redirectUrl;
      this.processingUrl = copy.processingUrl;
      this.payType = copy.payType;
      this.timeout = copy.timeout;
      this.currency = copy.currency;
      this.hashkey = copy.hashkey;
      this.signatureVersion = copy.signatureVersion;
      this.signatureOn = copy.signatureOn;
      this.secureMethod = copy.secureMethod;
      this.merchantName = copy.merchantName;
      this.frontendUrl = copy.frontendUrl;
      this.isSinopayOn = copy.isSinopayOn;
      this.statusUrl = copy.statusUrl;
      this.revenueCenterId = copy.revenueCenterId;
      this.additionalProperties = copy.additionalProperties;
    }

    public Builder enableCSC(Boolean enableCSC) {
      this.enableCSC = enableCSC;
      return this;
    }

    public Builder clientServer(String clientServer) {
      this.clientServer = clientServer;
      return this;
    }

    public Builder merchantId(String merchantId) {
      this.merchantId = merchantId;
      return this;
    }

    public Builder secureType(String secureType) {
      this.secureType = secureType;
      return this;
    }

    public Builder secureCode(String secureCode) {
      this.secureCode = secureCode;
      return this;
    }

    public Builder accessCode(String accessCode) {
      this.accessCode = accessCode;
      return this;
    }

    public Builder returnUrl(String returnUrl) {
      this.returnUrl = returnUrl;
      return this;
    }

    public Builder redirectUrl(String redirectUrl) {
      this.redirectUrl = redirectUrl;
      return this;
    }

    public Builder processingUrl(String processingUrl) {
      this.processingUrl = processingUrl;
      return this;
    }

    public Builder payType(Integer payType) {
      this.payType = payType;
      return this;
    }

    public Builder timeout(Integer timeout) {
      this.timeout = timeout;
      return this;
    }

    public Builder currency(String currency) {
      this.currency = currency;
      return this;
    }

    public Builder hashkey(String hashkey) {
      this.hashkey = hashkey;
      return this;
    }

    public Builder signatureVersion(Integer signatureVersion) {
      this.signatureVersion = signatureVersion;
      return this;
    }

    public Builder signatureOn(Boolean signatureOn) {
      this.signatureOn = signatureOn;
      return this;
    }

    public Builder secureMethod(String secureMethod) {
      this.secureMethod = secureMethod;
      return this;
    }

    public Builder merchantName(String merchantName) {
      this.merchantName = merchantName;
      return this;
    }

    public Builder frontendUrl(String frontendUrl) {
      this.frontendUrl = frontendUrl;
      return this;
    }

    public Builder isSinopayOn(Boolean isSinopayOn) {
      this.isSinopayOn = isSinopayOn;
      return this;
    }

    public Builder statusUrl(String statusUrl) {
      this.statusUrl = statusUrl;
      return this;
    }

    public Builder revenueCenterId(String revenueCenterId) {
      this.revenueCenterId = revenueCenterId;
      return this;
    }

    public Builder additionalProperties(Map<String, Object> additionalProperties) {
      this.additionalProperties = additionalProperties;
      return this;
    }

    public PaymentGatewayConfig build() {
      return new PaymentGatewayConfig(this);
    }
  }
}