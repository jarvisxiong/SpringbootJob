package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author chongye
 */
public class CupResponse implements Serializable {

  private static final long serialVersionUID = -1580173512737048557L;
  @JsonProperty("version")
  private String version;
  @JsonProperty("charset")
  private String charset;
  @JsonProperty("signMethod")
  private String signMethod;
  @JsonProperty("signature")
  private String signature;
  @JsonProperty("transType")
  private String transType;
  @JsonProperty("respCode")
  private String respCode;
  @JsonProperty("respMsg")
  private String respMsg;
  @JsonProperty("respTime")
  private String respTime;
  @JsonProperty("merAbbr")
  private String merAbbr;
  @JsonProperty("merId")
  private String merId;
  @JsonProperty("orderAmount")
  private String orderAmount;
  @JsonProperty("orderNumber")
  private String orderNumber;
  @JsonProperty("orderCurrency")
  private String orderCurrency;
  @JsonProperty("qid")
  private String qid;
  @JsonProperty("cupReserved")
  private String cupReserved;
  @JsonProperty("reqData")
  private String reqData;
  @JsonProperty("respData")
  private String respData;
  @JsonProperty("responseValid")
  private Boolean responseValid;

  public CupResponse() {
  }

  private CupResponse(Builder builder) {
    version = builder.version;
    charset = builder.charset;
    signMethod = builder.signMethod;
    signature = builder.signature;
    transType = builder.transType;
    respCode = builder.respCode;
    respMsg = builder.respMsg;
    respTime = builder.respTime;
    merAbbr = builder.merAbbr;
    merId = builder.merId;
    orderAmount = builder.orderAmount;
    orderNumber = builder.orderNumber;
    orderCurrency = builder.orderCurrency;
    qid = builder.qid;
    cupReserved = builder.cupReserved;
    reqData = builder.reqData;
    respData = builder.respData;
    responseValid = builder.responseValid;
  }

  public String getVersion() {
    return version;
  }

  public String getCharset() {
    return charset;
  }

  public String getSignMethod() {
    return signMethod;
  }

  public String getSignature() {
    return signature;
  }

  public String getTransType() {
    return transType;
  }

  public String getRespCode() {
    return respCode;
  }

  public String getRespTime() {
    return respTime;
  }

  public String getMerAbbr() {
    return merAbbr;
  }

  public String getMerId() {
    return merId;
  }

  public String getOrderAmount() {
    return orderAmount;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public String getOrderCurrency() {
    return orderCurrency;
  }

  public String getQid() {
    return qid;
  }

  public String getCupReserved() {
    return cupReserved;
  }

  public String getRespMsg() {
    return respMsg;
  }

  public String getReqData() {
    return reqData;
  }

  public String getRespData() {
    return respData;
  }

  public Boolean getResponseValid() {
    return responseValid;
  }

  /*public int getGenericResponseCode() {
    String resultCode = this.getRespcode();
    int genericResponseCode = PaymentStatus.GENERAL_ERROR;

    // check if a single digit response code
    if (StringUtils.isNotBlank(resultCode)) {
      int resultCodeInteger = Integer.parseInt(resultCode);
      switch (resultCodeInteger) {
        case 0:
          genericResponseCode = PaymentStatus.APPROVED;
          break;
        case 1:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 2:
          genericResponseCode = PaymentStatus.INVALID_ACCOUNT_NUMBER;
          break;
        case 3:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 6:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 11:
          genericResponseCode = PaymentStatus.INSUFFICIENT_FUNDS;
          break;
        case 14:
          genericResponseCode = PaymentStatus.INVALID_EXPIRY_DATE;
          break;
        case 15:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 18:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 20:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 25:
          genericResponseCode = PaymentStatus.DECLINED;
          break;
        case 30:
          genericResponseCode = PaymentStatus.INVALID_REQUEST;
          break;
        case 32:
          genericResponseCode = PaymentStatus.GATEWAY_OFFLINE;
          break;
        case 36:
          genericResponseCode = PaymentStatus.INSUFFICIENT_FUNDS;
          break;
        case 37:
          genericResponseCode = PaymentStatus.INVALID_AMOUNT;
          break;
        case 71:
          genericResponseCode = PaymentStatus.INVALID_REQUEST;
          break;
        case 72:
          genericResponseCode = PaymentStatus.INVALID_REQUEST;
          break;
        case 73:
          genericResponseCode = PaymentStatus.GATEWAY_TIMEOUT;
          break;
        case 82:
          genericResponseCode = PaymentStatus.INVALID_REQUEST;
          break;
        case 83:
          genericResponseCode = PaymentStatus.GATEWAY_TIMEOUT;
          break;
        case 84:
          genericResponseCode = PaymentStatus.INVALID_REQUEST;
          break;
        case 94:
          genericResponseCode = PaymentStatus.INVALID_REQUEST;
          break;
        default:
          genericResponseCode = PaymentStatus.GENERAL_ERROR;
          break;
      } // end switch (input)
    }

    return genericResponseCode;
  } // end public int getGenericResponseCode()


  public String getRespMsg() {
    String resultCode = this.getRespcode();
    String responseMsg = "";

    // check if a single digit response code
    if (resultCode != null) {
      int resultCodeInteger = Integer.parseInt(resultCode);
      switch (resultCodeInteger) {
        case 0:
          responseMsg = "Payment successful";
          break;
        case 1:
          responseMsg = "Please refer to Card Issuers";
          break;
        case 2:
          responseMsg = "Invalid card number";
          break;
        case 3:
          responseMsg = "This card is not allowed for this transaction at this merchant.";
          break;
        case 6:
          responseMsg = "Expired card";
          break;
        case 11:
          responseMsg = "Insufficient funds";
          break;
        case 14:
          responseMsg = "Expired card or incorrect expiration date";
          break;
        case 15:
          responseMsg = "Incorrect pin";
          break;
        case 18:
          responseMsg = "Payment failed, please use another card for payment.";
          break;
        case 20:
          responseMsg = "Incorrect card number";
          break;
        case 25:
          responseMsg = "Unable to locate original transaction";
          break;
        case 30:
          responseMsg = "Message format error";
          break;
        case 32:
          responseMsg = "System maintenance";
          break;
        case 36:
          responseMsg = "Transaction amount limit exceeded";
          break;
        case 37:
          responseMsg = "Original transaction amount error";
          break;
        case 71:
          responseMsg = "Invalid transaction";
          break;
        case 72:
          responseMsg = "No such transaction";
          break;
        case 73:
          responseMsg = "Transaction timeout. Payment will be refunded.";
          break;
        case 80:
          responseMsg = "Internal error";
          break;
        case 82:
          responseMsg = "Failed signature verification";
          break;
        case 83:
          responseMsg = "Transaction timeout";
          break;
        case 84:
          responseMsg = "Order does not exists";
          break;
        case 94:
          responseMsg = "Duplicate transaction";
          break;
        default:
          responseMsg = this.responseHashMap.get(KEY_RESPMSG).replaceAll("\\+", " ");
          ;
          break;
      } // end switch (input)
    }

    return responseMsg;
  }*/

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("version", version)
        .append("charset", charset)
        .append("signMethod", signMethod)
        .append("signature", signature)
        .append("transType", transType)
        .append("respCode", respCode)
        .append("respMsg", respMsg)
        .append("respTime", respTime)
        .append("merAbbr", merAbbr)
        .append("merId", merId)
        .append("orderAmount", orderAmount)
        .append("orderNumber", orderNumber)
        .append("orderCurrency", orderCurrency)
        .append("qid", qid)
        .append("cupReserved", cupReserved)
        .append("reqData", reqData)
        .append("respData", respData)
        .append("responseValid", responseValid)
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

    CupResponse that = (CupResponse) o;

    return Objects.equals(this.charset, that.charset) &&
        Objects.equals(this.cupReserved, that.cupReserved) &&
        Objects.equals(this.merAbbr, that.merAbbr) &&
        Objects.equals(this.merId, that.merId) &&
        Objects.equals(this.orderAmount, that.orderAmount) &&
        Objects.equals(this.orderCurrency, that.orderCurrency) &&
        Objects.equals(this.orderNumber, that.orderNumber) &&
        Objects.equals(this.qid, that.qid) &&
        Objects.equals(this.reqData, that.reqData) &&
        Objects.equals(this.respCode, that.respCode) &&
        Objects.equals(this.respData, that.respData) &&
        Objects.equals(this.respMsg, that.respMsg) &&
        Objects.equals(this.responseValid, that.responseValid) &&
        Objects.equals(this.respTime, that.respTime) &&
        Objects.equals(this.serialVersionUID, that.serialVersionUID) &&
        Objects.equals(this.signature, that.signature) &&
        Objects.equals(this.signMethod, that.signMethod) &&
        Objects.equals(this.transType, that.transType) &&
        Objects.equals(this.version, that.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(charset, cupReserved, merAbbr, merId, orderAmount, orderCurrency,
        orderNumber, qid, reqData, respCode, respData,
        respMsg, responseValid, respTime, serialVersionUID, signature,
        signMethod, transType, version);
  }

  public static final class Builder {
    private String version;
    private String charset;
    private String signMethod;
    private String signature;
    private String transType;
    private String respCode;
    private String respMsg;
    private String respTime;
    private String merAbbr;
    private String merId;
    private String orderAmount;
    private String orderNumber;
    private String orderCurrency;
    private String qid;
    private String cupReserved;
    private String reqData;
    private String respData;
    private Boolean responseValid;

    public Builder(CupResponse copy) {
      this.version = copy.version;
      this.charset = copy.charset;
      this.signMethod = copy.signMethod;
      this.signature = copy.signature;
      this.transType = copy.transType;
      this.respCode = copy.respCode;
      this.respMsg = copy.respMsg;
      this.respTime = copy.respTime;
      this.merAbbr = copy.merAbbr;
      this.merId = copy.merId;
      this.orderAmount = copy.orderAmount;
      this.orderNumber = copy.orderNumber;
      this.orderCurrency = copy.orderCurrency;
      this.qid = copy.qid;
      this.cupReserved = copy.cupReserved;
      this.reqData = copy.reqData;
      this.respData = copy.respData;
      this.responseValid = copy.responseValid;
    }

    public Builder() {
    }

    public Builder(String reqData, String respData) {
      this.reqData = reqData;
      this.respData = respData;
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

    public Builder signature(String signature) {
      this.signature = signature;
      return this;
    }

    public Builder transType(String transType) {
      this.transType = transType;
      return this;
    }

    public Builder respCode(String respCode) {
      this.respCode = respCode;
      return this;
    }

    public Builder respMsg(String respMsg) {
      this.respMsg = respMsg;
      return this;
    }

    public Builder respTime(String respTime) {
      this.respTime = respTime;
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

    public Builder orderAmount(String orderAmount) {
      this.orderAmount = orderAmount;
      return this;
    }

    public Builder orderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
      return this;
    }

    public Builder orderCurrency(String orderCurrency) {
      this.orderCurrency = orderCurrency;
      return this;
    }

    public Builder qid(String qid) {
      this.qid = qid;
      return this;
    }

    public Builder cupReserved(String cupReserved) {
      this.cupReserved = cupReserved;
      return this;
    }

    public Builder reqData(String reqData) {
      this.reqData = reqData;
      return this;
    }

    public Builder respData(String respData) {
      this.respData = respData;
      return this;
    }

    public Builder responseValid(Boolean responseValid) {
      this.responseValid = responseValid;
      return this;
    }

    public CupResponse build() {
      return new CupResponse(this);
    }
  }
}
