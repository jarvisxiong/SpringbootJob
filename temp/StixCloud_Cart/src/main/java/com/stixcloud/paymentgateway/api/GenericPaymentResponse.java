package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by chetan on 28/11/2016.
 */

@JsonPropertyOrder({
    "vpc_OrderInfo",
    "vpc_TxnResponseCode",
    "vpc_AcqResponseCode",
    "vpc_TransactionNo",
    "vpc_ShopTransactionNo",
    "vpc_Amount",
    "vpc_AuthorisedAmount",
    "vpc_RefundedAmount",
    "vpc_CapturedAmount",
    "vpc_Message",
    "vpc_MerchTxnRef",
    "vpc_AuthorizeId",
    "vpc_BatchNo",
    "vpc_CSCResultCode",
    "vpc_AcqCSCRespCode"
})
public class GenericPaymentResponse implements Serializable {
  private static final long serialVersionUID = -8835895139467487146L;
  @JsonProperty("vpc_OrderInfo")
  private String vpcOrderInfo;
  @JsonProperty("vpc_TxnResponseCode")
  private String vpcTxnResponseCode;
  @JsonProperty("vpc_AcqResponseCode")
  private String vpcAcqResponseCode;
  @JsonProperty("vpc_TransactionNo")
  private String vpcTransactionNo;
  @JsonProperty("vpc_ShopTransactionNo")
  private String vpcShopTransactionNo;
  @JsonProperty("vpc_Amount")
  private String vpcAmount;
  @JsonProperty("vpc_AuthorisedAmount")
  private String vpcAuthorisedAmount;
  @JsonProperty("vpc_RefundedAmount")
  private String vpcRefundedAmount;
  @JsonProperty("vpc_CapturedAmount")
  private String vpcCapturedAmount;
  @JsonProperty("vpc_Message")
  private String vpcMessage;
  @JsonProperty("vpc_MerchTxnRef")
  private String vpcMerchTxnRef;
  @JsonProperty("vpc_AuthorizeId")
  private String vpcAuthorizeId;
  @JsonProperty("vpc_BatchNo")
  private String vpcBatchNo;
  @JsonProperty("vpc_CSCResultCode")
  private String vpcCSCResultCode;
  @JsonProperty("vpc_AcqCSCRespCode")
  private String vpcAcqCSCRespCode;

  public GenericPaymentResponse(Builder builder) {
    this.vpcOrderInfo = builder.vpcOrderInfo;
    this.vpcTxnResponseCode = builder.vpcTxnResponseCode;
    this.vpcAcqResponseCode = builder.vpcAcqResponseCode;
    this.vpcTransactionNo = builder.vpcTransactionNo;
    this.vpcShopTransactionNo = builder.vpcShopTransactionNo;
    this.vpcAmount = builder.vpcAmount;
    this.vpcAuthorisedAmount = builder.vpcAuthorisedAmount;
    this.vpcRefundedAmount = builder.vpcRefundedAmount;
    this.vpcCapturedAmount = builder.vpcCapturedAmount;
    this.vpcMessage = builder.vpcMessage;
    this.vpcMerchTxnRef = builder.vpcMerchTxnRef;
    this.vpcAuthorizeId = builder.vpcAuthorizeId;
    this.vpcBatchNo = builder.vpcBatchNo;
    this.vpcCSCResultCode = builder.vpcCSCResultCode;
    this.vpcAcqCSCRespCode = builder.vpcAcqCSCRespCode;
  }

  public String getVpcOrderInfo() {
    return vpcOrderInfo;
  }

  public String getVpcTxnResponseCode() {
    return vpcTxnResponseCode;
  }

  public String getVpcAcqResponseCode() {
    return vpcAcqResponseCode;
  }

  public String getVpcTransactionNo() {
    return vpcTransactionNo;
  }

  public String getVpcShopTransactionNo() {
    return vpcShopTransactionNo;
  }

  public String getVpcAmount() {
    return vpcAmount;
  }

  public String getVpcAuthorisedAmount() {
    return vpcAuthorisedAmount;
  }

  public String getVpcRefundedAmount() {
    return vpcRefundedAmount;
  }

  public String getVpcCapturedAmount() {
    return vpcCapturedAmount;
  }

  public String getVpcMessage() {
    return vpcMessage;
  }

  public String getVpcMerchTxnRef() {
    return vpcMerchTxnRef;
  }

  public String getVpcAuthorizeId() {
    return vpcAuthorizeId;
  }

  public String getVpcBatchNo() {
    return vpcBatchNo;
  }

  public String getVpcCSCResultCode() {
    return vpcCSCResultCode;
  }

  public String getVpcAcqCSCRespCode() {
    return vpcAcqCSCRespCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GenericPaymentResponse that = (GenericPaymentResponse) o;

    return Objects.equals(this.vpcOrderInfo, that.vpcOrderInfo) &&
        Objects.equals(this.vpcTxnResponseCode, that.vpcTxnResponseCode) &&
        Objects.equals(this.vpcAcqResponseCode, that.vpcAcqResponseCode) &&
        Objects.equals(this.vpcTransactionNo, that.vpcTransactionNo) &&
        Objects.equals(this.vpcShopTransactionNo, that.vpcShopTransactionNo) &&
        Objects.equals(this.vpcAmount, that.vpcAmount) &&
        Objects.equals(this.vpcAuthorisedAmount, that.vpcAuthorisedAmount) &&
        Objects.equals(this.vpcRefundedAmount, that.vpcRefundedAmount) &&
        Objects.equals(this.vpcCapturedAmount, that.vpcCapturedAmount) &&
        Objects.equals(this.vpcMessage, that.vpcMessage) &&
        Objects.equals(this.vpcMerchTxnRef, that.vpcMerchTxnRef) &&
        Objects.equals(this.vpcAuthorizeId, that.vpcAuthorizeId) &&
        Objects.equals(this.vpcBatchNo, that.vpcBatchNo) &&
        Objects.equals(this.vpcCSCResultCode, that.vpcCSCResultCode) &&
        Objects.equals(this.vpcAcqCSCRespCode, that.vpcAcqCSCRespCode);


  }

  @Override
  public int hashCode() {
    return Objects.hash(vpcOrderInfo, vpcTxnResponseCode, vpcAcqResponseCode, vpcTransactionNo,
        vpcShopTransactionNo,
        vpcAmount,
        vpcAuthorisedAmount, vpcRefundedAmount, vpcCapturedAmount, vpcMessage, vpcMerchTxnRef,
        vpcAuthorizeId, vpcBatchNo, vpcCSCResultCode, vpcAcqCSCRespCode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("vpc_OrderInfo", vpcOrderInfo)
        .append("vpc_TxnResponseCode", vpcTxnResponseCode)
        .append("vpc_AcqResponseCode", vpcAcqResponseCode)
        .append("vpc_TransactionNo", vpcTransactionNo)
        .append("vpc_ShopTransactionNo", vpcShopTransactionNo)
        .append("vpc_Amount", vpcAmount)
        .append("vpc_AuthorisedAmount", vpcAuthorisedAmount)
        .append("vpc_RefundedAmount", vpcRefundedAmount)
        .append("vpc_CapturedAmount", vpcCapturedAmount)
        .append("vpc_Message", vpcMessage)
        .append("vpc_MerchTxnRef", vpcMerchTxnRef)
        .append("vpc_AuthorizeId", vpcAuthorizeId)
        .append("vpc_BatchNo", vpcBatchNo)
        .append("vpc_CSCResultCode", vpcCSCResultCode)
        .append("vpc_AcqCSCRespCode", vpcAcqCSCRespCode)
        .toString();
  }


  public static final class Builder {
    private String vpcOrderInfo;
    private String vpcTxnResponseCode;
    private String vpcAcqResponseCode;
    private String vpcTransactionNo;
    private String vpcShopTransactionNo;
    private String vpcAmount;
    private String vpcAuthorisedAmount;
    private String vpcRefundedAmount;
    private String vpcCapturedAmount;
    private String vpcMessage;
    private String vpcMerchTxnRef;
    private String vpcAuthorizeId;
    private String vpcBatchNo;
    private String vpcCSCResultCode;
    private String vpcAcqCSCRespCode;

    public Builder() {
    }

    public Builder(String response) {
      StringTokenizer keyValuePairToken = new StringTokenizer(response.trim(), "&");
      String keyValuePair = null;
      StringTokenizer keyValueDataToken = null;
      String key = null;
      String value = null;
      while (keyValuePairToken.hasMoreTokens()) {
        keyValuePair = keyValuePairToken.nextToken();
        keyValueDataToken = new StringTokenizer(keyValuePair, "=");
        while (keyValueDataToken.hasMoreTokens()) {
          key = keyValueDataToken.nextToken();
          if (keyValueDataToken.hasMoreTokens()) {
            value = keyValueDataToken.nextToken();
            if (StringUtils.isNotBlank(key) && "vpc_OrderInfo".equals(key)) {
              this.vpcOrderInfo = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_TxnResponseCode".equals(key)) {
              this.vpcTxnResponseCode = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_AcqResponseCode".equals(key)) {
              this.vpcAcqResponseCode = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_TransactionNo".equals(key)) {
              this.vpcTransactionNo = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_ShopTransactionNo".equals(key)) {
              this.vpcShopTransactionNo = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_Amount".equals(key)) {
              this.vpcAmount = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_AuthorisedAmount".equals(key)) {
              this.vpcAuthorisedAmount = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_RefundedAmount".equals(key)) {
              this.vpcRefundedAmount = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_CapturedAmount".equals(key)) {
              this.vpcCapturedAmount = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_Message".equals(key)) {
              this.vpcMessage = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_BatchNo".equals(key)) {
              this.vpcAcqResponseCode = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_CSCResultCode".equals(key)) {
              this.vpcCSCResultCode = value;
            } else if (StringUtils.isNotBlank(key) && "vpc_AcqCSCRespCode".equals(key)) {
              this.vpcAcqCSCRespCode = value;
            }
          }
        } // end while
      } // while loop
    }

    public Builder vpcOrderInfo() {
      return this;
    }

    public Builder vpcTxnResponseCode() {
      return this;
    }

    public Builder vpcAcqResponseCode() {
      return this;
    }

    public Builder vpcTransactionNo() {
      return this;
    }

    public Builder vpcShopTransactionNo() {
      return this;
    }

    public Builder vpcAmount() {
      return this;
    }

    public Builder vpcAuthorisedAmount() {
      return this;
    }

    public Builder vpcRefundedAmount() {
      return this;
    }

    public Builder vpcCapturedAmount() {
      return this;
    }

    public Builder vpcMessage() {
      return this;
    }

    public Builder vpcMerchTxnRef() {
      return this;
    }

    public Builder vpcAuthorizeId() {
      return this;
    }

    public Builder vpcBatchNo() {
      return this;
    }

    public Builder vpcCSCResultCode() {
      return this;
    }

    public Builder vpcAcqCSCRespCode() {
      return this;
    }

    public GenericPaymentResponse build() {
      return new GenericPaymentResponse(this);
    }

  }

}
