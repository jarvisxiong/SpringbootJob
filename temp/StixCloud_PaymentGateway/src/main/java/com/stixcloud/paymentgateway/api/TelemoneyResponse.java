package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "TM_MCode",
    "TM_RefNo",
    "TM_TrnType",
    "TM_SubTrnType",
    "TM_Status",
    "TM_Error",
    "TM_Currency",
    "TM_DebitAmt",
    "TM_PaymentType",
    "TM_BankRespCode",
    "TM_ApprovalCode",
    "TM_ErrorMsg",
    "TM_UserField1",
    "TM_UserField2",
    "TM_UserField3",
    "TM_UserField4",
    "TM_UserField5",
    "TM_Original_RefNo",
    "TM_CCLast4Digit",
    "TM_RecurrentId",
    "TM_CCNum",
    "TM_ExpiryDate",
    "TM_IPP_FirstPayment",
    "TM_IPP_LastPayment",
    "TM_IPP_MonthlyPayment",
    "TM_IPP_TransTenure",
    "TM_IPP_TotalInterest",
    "TM_IPP_DownPayment",
    "TM_IPP_MonthlyInterest",
    "TM_Signature",
    "TM_OriginalPayType",
    "reqData",
    "respData"
})
public class TelemoneyResponse implements Serializable {

  private static final long serialVersionUID = 6999213834063926701L;
  @JsonProperty("TM_MCode")
  public String tMMCode;
  @JsonProperty("TM_RefNo")
  public String tMRefNo;
  @JsonProperty("TM_TrnType")
  public String tMTrnType;
  @JsonProperty("TM_SubTrnType")
  public String tMSubTrnType;
  @JsonProperty("TM_Status")
  public String tMStatus;
  @JsonProperty("TM_Error")
  public String tMError;
  @JsonProperty("TM_Currency")
  public String tMCurrency;
  @JsonProperty("TM_DebitAmt")
  public String tMDebitAmt;
  @JsonProperty("TM_PaymentType")
  public String tMPaymentType;
  @JsonProperty("TM_BankRespCode")
  public String tMBankRespCode;
  @JsonProperty("TM_ApprovalCode")
  public String tMApprovalCode;
  @JsonProperty("TM_ErrorMsg")
  public String tMErrorMsg;
  @JsonProperty("TM_UserField1")
  public String tMUserField1;
  @JsonProperty("TM_UserField2")
  public String tMUserField2;
  @JsonProperty("TM_UserField3")
  public String tMUserField3;
  @JsonProperty("TM_UserField4")
  public String tMUserField4;
  @JsonProperty("TM_UserField5")
  public String tMUserField5;
  @JsonProperty("TM_Original_RefNo")
  public String tMOriginalRefNo;
  @JsonProperty("TM_CCLast4Digit")
  public String tMCCLast4Digit;
  @JsonProperty("TM_RecurrentId")
  public String tMRecurrentId;
  @JsonProperty("TM_CCNum")
  public String tMCCNum;
  @JsonProperty("TM_ExpiryDate")
  public String tMExpiryDate;
  @JsonProperty("TM_IPP_FirstPayment")
  public String tMIPPFirstPayment;
  @JsonProperty("TM_IPP_LastPayment")
  public String tMIPPLastPayment;
  @JsonProperty("TM_IPP_MonthlyPayment")
  public String tMIPPMonthlyPayment;
  @JsonProperty("TM_IPP_TransTenure")
  public String tMIPPTransTenure;
  @JsonProperty("TM_IPP_TotalInterest")
  public String tMIPPTotalInterest;
  @JsonProperty("TM_IPP_DownPayment")
  public String tMIPPDownPayment;
  @JsonProperty("TM_IPP_MonthlyInterest")
  public String tMIPPMonthlyInterest;
  @JsonProperty("TM_Signature")
  public String tMSignature;
  @JsonProperty("TM_OriginalPayType")
  public String tMOriginalPayType;
  @JsonProperty("reqData")
  public String reqData;
  @JsonProperty("respData")
  public String respData;

  public TelemoneyResponse() {
  }

  private TelemoneyResponse(Builder builder) {
    settMMCode(builder.tMMCode);
    settMRefNo(builder.tMRefNo);
    settMTrnType(builder.tMTrnType);
    settMSubTrnType(builder.tMSubTrnType);
    settMStatus(builder.tMStatus);
    settMError(builder.tMError);
    settMCurrency(builder.tMCurrency);
    settMDebitAmt(builder.tMDebitAmt);
    settMPaymentType(builder.tMPaymentType);
    settMBankRespCode(builder.tMBankRespCode);
    settMApprovalCode(builder.tMApprovalCode);
    settMErrorMsg(builder.tMErrorMsg);
    settMUserField1(builder.tMUserField1);
    settMUserField2(builder.tMUserField2);
    settMUserField3(builder.tMUserField3);
    settMUserField4(builder.tMUserField4);
    settMUserField5(builder.tMUserField5);
    settMOriginalRefNo(builder.tMOriginalRefNo);
    settMCCLast4Digit(builder.tMCCLast4Digit);
    settMRecurrentId(builder.tMRecurrentId);
    settMCCNum(builder.tMCCNum);
    settMExpiryDate(builder.tMExpiryDate);
    settMIPPFirstPayment(builder.tMIPPFirstPayment);
    settMIPPLastPayment(builder.tMIPPLastPayment);
    settMIPPMonthlyPayment(builder.tMIPPMonthlyPayment);
    settMIPPTransTenure(builder.tMIPPTransTenure);
    settMIPPTotalInterest(builder.tMIPPTotalInterest);
    settMIPPDownPayment(builder.tMIPPDownPayment);
    settMIPPMonthlyInterest(builder.tMIPPMonthlyInterest);
    settMSignature(builder.tMSignature);
    settMOriginalPayType(builder.tMOriginalPayType);
    setReqData(builder.reqData);
    setRespData(builder.respData);
  }

  public String gettMMCode() {
    return tMMCode;
  }

  public void settMMCode(String tMMCode) {
    this.tMMCode = tMMCode;
  }

  public String gettMRefNo() {
    return tMRefNo;
  }

  public void settMRefNo(String tMRefNo) {
    this.tMRefNo = tMRefNo;
  }

  public String gettMTrnType() {
    return tMTrnType;
  }

  public void settMTrnType(String tMTrnType) {
    this.tMTrnType = tMTrnType;
  }

  public String gettMSubTrnType() {
    return tMSubTrnType;
  }

  public void settMSubTrnType(String tMSubTrnType) {
    this.tMSubTrnType = tMSubTrnType;
  }

  public String gettMStatus() {
    return tMStatus;
  }

  public void settMStatus(String tMStatus) {
    this.tMStatus = tMStatus;
  }

  public String gettMError() {
    return tMError;
  }

  public void settMError(String tMError) {
    this.tMError = tMError;
  }

  public String gettMCurrency() {
    return tMCurrency;
  }

  public void settMCurrency(String tMCurrency) {
    this.tMCurrency = tMCurrency;
  }

  public String gettMDebitAmt() {
    return tMDebitAmt;
  }

  public void settMDebitAmt(String tMDebitAmt) {
    this.tMDebitAmt = tMDebitAmt;
  }

  public String gettMPaymentType() {
    return tMPaymentType;
  }

  public void settMPaymentType(String tMPaymentType) {
    this.tMPaymentType = tMPaymentType;
  }

  public String gettMBankRespCode() {
    return tMBankRespCode;
  }

  public void settMBankRespCode(String tMBankRespCode) {
    this.tMBankRespCode = tMBankRespCode;
  }

  public String gettMApprovalCode() {
    return tMApprovalCode;
  }

  public void settMApprovalCode(String tMApprovalCode) {
    this.tMApprovalCode = tMApprovalCode;
  }

  public String gettMErrorMsg() {
    return tMErrorMsg;
  }

  public void settMErrorMsg(String tMErrorMsg) {
    this.tMErrorMsg = tMErrorMsg;
  }

  public String gettMUserField1() {
    return tMUserField1;
  }

  public void settMUserField1(String tMUserField1) {
    this.tMUserField1 = tMUserField1;
  }

  public String gettMUserField2() {
    return tMUserField2;
  }

  public void settMUserField2(String tMUserField2) {
    this.tMUserField2 = tMUserField2;
  }

  public String gettMUserField3() {
    return tMUserField3;
  }

  public void settMUserField3(String tMUserField3) {
    this.tMUserField3 = tMUserField3;
  }

  public String gettMUserField4() {
    return tMUserField4;
  }

  public void settMUserField4(String tMUserField4) {
    this.tMUserField4 = tMUserField4;
  }

  public String gettMUserField5() {
    return tMUserField5;
  }

  public void settMUserField5(String tMUserField5) {
    this.tMUserField5 = tMUserField5;
  }

  public String gettMOriginalRefNo() {
    return tMOriginalRefNo;
  }

  public void settMOriginalRefNo(String tMOriginalRefNo) {
    this.tMOriginalRefNo = tMOriginalRefNo;
  }

  public String gettMCCLast4Digit() {
    return tMCCLast4Digit;
  }

  public void settMCCLast4Digit(String tMCCLast4Digit) {
    this.tMCCLast4Digit = tMCCLast4Digit;
  }

  public String gettMRecurrentId() {
    return tMRecurrentId;
  }

  public void settMRecurrentId(String tMRecurrentId) {
    this.tMRecurrentId = tMRecurrentId;
  }

  public String gettMCCNum() {
    return tMCCNum;
  }

  public void settMCCNum(String tMCCNum) {
    this.tMCCNum = tMCCNum;
  }

  public String gettMExpiryDate() {
    return tMExpiryDate;
  }

  public void settMExpiryDate(String tMExpiryDate) {
    this.tMExpiryDate = tMExpiryDate;
  }

  public String gettMIPPFirstPayment() {
    return tMIPPFirstPayment;
  }

  public void settMIPPFirstPayment(String tMIPPFirstPayment) {
    this.tMIPPFirstPayment = tMIPPFirstPayment;
  }

  public String gettMIPPLastPayment() {
    return tMIPPLastPayment;
  }

  public void settMIPPLastPayment(String tMIPPLastPayment) {
    this.tMIPPLastPayment = tMIPPLastPayment;
  }

  public String gettMIPPMonthlyPayment() {
    return tMIPPMonthlyPayment;
  }

  public void settMIPPMonthlyPayment(String tMIPPMonthlyPayment) {
    this.tMIPPMonthlyPayment = tMIPPMonthlyPayment;
  }

  public String gettMIPPTransTenure() {
    return tMIPPTransTenure;
  }

  public void settMIPPTransTenure(String tMIPPTransTenure) {
    this.tMIPPTransTenure = tMIPPTransTenure;
  }

  public String gettMIPPTotalInterest() {
    return tMIPPTotalInterest;
  }

  public void settMIPPTotalInterest(String tMIPPTotalInterest) {
    this.tMIPPTotalInterest = tMIPPTotalInterest;
  }

  public String gettMIPPDownPayment() {
    return tMIPPDownPayment;
  }

  public void settMIPPDownPayment(String tMIPPDownPayment) {
    this.tMIPPDownPayment = tMIPPDownPayment;
  }

  public String gettMIPPMonthlyInterest() {
    return tMIPPMonthlyInterest;
  }

  public void settMIPPMonthlyInterest(String tMIPPMonthlyInterest) {
    this.tMIPPMonthlyInterest = tMIPPMonthlyInterest;
  }

  public String gettMSignature() {
    return tMSignature;
  }

  public void settMSignature(String tMSignature) {
    this.tMSignature = tMSignature;
  }

  public String gettMOriginalPayType() {
    return tMOriginalPayType;
  }

  public void settMOriginalPayType(String tMOriginalPayType) {
    this.tMOriginalPayType = tMOriginalPayType;
  }

  public String getReqData() {
    return reqData;
  }

  public void setReqData(String reqData) {
    this.reqData = reqData;
  }

  public String getRespData() {
    return respData;
  }

  public void setRespData(String respData) {
    this.respData = respData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TelemoneyResponse that = (TelemoneyResponse) o;
    return Objects.equals(tMMCode, that.tMMCode) &&
        Objects.equals(tMRefNo, that.tMRefNo) &&
        Objects.equals(tMTrnType, that.tMTrnType) &&
        Objects.equals(tMSubTrnType, that.tMSubTrnType) &&
        Objects.equals(tMStatus, that.tMStatus) &&
        Objects.equals(tMError, that.tMError) &&
        Objects.equals(tMCurrency, that.tMCurrency) &&
        Objects.equals(tMDebitAmt, that.tMDebitAmt) &&
        Objects.equals(tMPaymentType, that.tMPaymentType) &&
        Objects.equals(tMBankRespCode, that.tMBankRespCode) &&
        Objects.equals(tMApprovalCode, that.tMApprovalCode) &&
        Objects.equals(tMErrorMsg, that.tMErrorMsg) &&
        Objects.equals(tMUserField1, that.tMUserField1) &&
        Objects.equals(tMUserField2, that.tMUserField2) &&
        Objects.equals(tMUserField3, that.tMUserField3) &&
        Objects.equals(tMUserField4, that.tMUserField4) &&
        Objects.equals(tMUserField5, that.tMUserField5) &&
        Objects.equals(tMOriginalRefNo, that.tMOriginalRefNo) &&
        Objects.equals(tMCCLast4Digit, that.tMCCLast4Digit) &&
        Objects.equals(tMRecurrentId, that.tMRecurrentId) &&
        Objects.equals(tMCCNum, that.tMCCNum) &&
        Objects.equals(tMExpiryDate, that.tMExpiryDate) &&
        Objects.equals(tMIPPFirstPayment, that.tMIPPFirstPayment) &&
        Objects.equals(tMIPPLastPayment, that.tMIPPLastPayment) &&
        Objects.equals(tMIPPMonthlyPayment, that.tMIPPMonthlyPayment) &&
        Objects.equals(tMIPPTransTenure, that.tMIPPTransTenure) &&
        Objects.equals(tMIPPTotalInterest, that.tMIPPTotalInterest) &&
        Objects.equals(tMIPPDownPayment, that.tMIPPDownPayment) &&
        Objects.equals(tMIPPMonthlyInterest, that.tMIPPMonthlyInterest) &&
        Objects.equals(tMSignature, that.tMSignature) &&
        Objects.equals(tMOriginalPayType, that.tMOriginalPayType) &&
        Objects.equals(reqData, that.reqData) &&
        Objects.equals(respData, that.respData);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(tMMCode, tMRefNo, tMTrnType, tMSubTrnType, tMStatus, tMError, tMCurrency, tMDebitAmt,
            tMPaymentType, tMBankRespCode, tMApprovalCode, tMErrorMsg, tMUserField1, tMUserField2,
            tMUserField3, tMUserField4, tMUserField5, tMOriginalRefNo, tMCCLast4Digit,
            tMRecurrentId,
            tMCCNum, tMExpiryDate, tMIPPFirstPayment, tMIPPLastPayment, tMIPPMonthlyPayment,
            tMIPPTransTenure, tMIPPTotalInterest, tMIPPDownPayment, tMIPPMonthlyInterest,
            tMSignature,
            tMOriginalPayType, reqData, respData);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("tMMCode", tMMCode)
        .append("tMRefNo", tMRefNo)
        .append("tMTrnType", tMTrnType)
        .append("tMSubTrnType", tMSubTrnType)
        .append("tMStatus", tMStatus)
        .append("tMError", tMError)
        .append("tMCurrency", tMCurrency)
        .append("tMDebitAmt", tMDebitAmt)
        .append("tMPaymentType", tMPaymentType)
        .append("tMBankRespCode", tMBankRespCode)
        .append("tMApprovalCode", tMApprovalCode)
        .append("tMErrorMsg", tMErrorMsg)
        .append("tMUserField1", tMUserField1)
        .append("tMUserField2", tMUserField2)
        .append("tMUserField3", tMUserField3)
        .append("tMUserField4", tMUserField4)
        .append("tMUserField5", tMUserField5)
        .append("tMOriginalRefNo", tMOriginalRefNo)
        .append("tMCCLast4Digit", tMCCLast4Digit)
        .append("tMRecurrentId", tMRecurrentId)
        .append("tMCCNum", tMCCNum)
        .append("tMExpiryDate", tMExpiryDate)
        .append("tMIPPFirstPayment", tMIPPFirstPayment)
        .append("tMIPPLastPayment", tMIPPLastPayment)
        .append("tMIPPMonthlyPayment", tMIPPMonthlyPayment)
        .append("tMIPPTransTenure", tMIPPTransTenure)
        .append("tMIPPTotalInterest", tMIPPTotalInterest)
        .append("tMIPPDownPayment", tMIPPDownPayment)
        .append("tMIPPMonthlyInterest", tMIPPMonthlyInterest)
        .append("tMSignature", tMSignature)
        .append("tMOriginalPayType", tMOriginalPayType)
        .append("reqData", reqData)
        .append("respData", respData)
        .toString();
  }


  public static final class Builder {
    private String tMMCode;
    private String tMRefNo;
    private String tMTrnType;
    private String tMSubTrnType;
    private String tMStatus;
    private String tMError;
    private String tMCurrency;
    private String tMDebitAmt;
    private String tMPaymentType;
    private String tMBankRespCode;
    private String tMApprovalCode;
    private String tMErrorMsg;
    private String tMUserField1;
    private String tMUserField2;
    private String tMUserField3;
    private String tMUserField4;
    private String tMUserField5;
    private String tMOriginalRefNo;
    private String tMCCLast4Digit;
    private String tMRecurrentId;
    private String tMCCNum;
    private String tMExpiryDate;
    private String tMIPPFirstPayment;
    private String tMIPPLastPayment;
    private String tMIPPMonthlyPayment;
    private String tMIPPTransTenure;
    private String tMIPPTotalInterest;
    private String tMIPPDownPayment;
    private String tMIPPMonthlyInterest;
    private String tMOriginalPayType;
    private String tMSignature;
    private String reqData;
    private String respData;

    public Builder() {
    }

    public Builder(TelemoneyResponse copy) {
      this.tMMCode = copy.tMMCode;
      this.tMRefNo = copy.tMRefNo;
      this.tMTrnType = copy.tMTrnType;
      this.tMSubTrnType = copy.tMSubTrnType;
      this.tMStatus = copy.tMStatus;
      this.tMError = copy.tMError;
      this.tMCurrency = copy.tMCurrency;
      this.tMDebitAmt = copy.tMDebitAmt;
      this.tMPaymentType = copy.tMPaymentType;
      this.tMBankRespCode = copy.tMBankRespCode;
      this.tMApprovalCode = copy.tMApprovalCode;
      this.tMErrorMsg = copy.tMErrorMsg;
      this.tMUserField1 = copy.tMUserField1;
      this.tMUserField2 = copy.tMUserField2;
      this.tMUserField3 = copy.tMUserField3;
      this.tMUserField4 = copy.tMUserField4;
      this.tMUserField5 = copy.tMUserField5;
      this.tMOriginalRefNo = copy.tMOriginalRefNo;
      this.tMCCLast4Digit = copy.tMCCLast4Digit;
      this.tMRecurrentId = copy.tMRecurrentId;
      this.tMCCNum = copy.tMCCNum;
      this.tMExpiryDate = copy.tMExpiryDate;
      this.tMIPPFirstPayment = copy.tMIPPFirstPayment;
      this.tMIPPLastPayment = copy.tMIPPLastPayment;
      this.tMIPPMonthlyPayment = copy.tMIPPMonthlyPayment;
      this.tMIPPTransTenure = copy.tMIPPTransTenure;
      this.tMIPPTotalInterest = copy.tMIPPTotalInterest;
      this.tMIPPDownPayment = copy.tMIPPDownPayment;
      this.tMIPPMonthlyInterest = copy.tMIPPMonthlyInterest;
      this.tMSignature = copy.tMSignature;
      this.tMOriginalPayType = copy.tMOriginalPayType;
      this.reqData = copy.reqData;
      this.respData = copy.respData;
    }

    public Builder tMMCode(String tMMCode) {
      this.tMMCode = tMMCode;
      return this;
    }

    public Builder tMRefNo(String tMRefNo) {
      this.tMRefNo = tMRefNo;
      return this;
    }

    public Builder tMTrnType(String tMTrnType) {
      this.tMTrnType = tMTrnType;
      return this;
    }

    public Builder tMSubTrnType(String tMSubTrnType) {
      this.tMSubTrnType = tMSubTrnType;
      return this;
    }

    public Builder tMStatus(String tMStatus) {
      this.tMStatus = tMStatus;
      return this;
    }

    public Builder tMError(String tMError) {
      this.tMError = tMError;
      return this;
    }

    public Builder tMCurrency(String tMCurrency) {
      this.tMCurrency = tMCurrency;
      return this;
    }

    public Builder tMDebitAmt(String tMDebitAmt) {
      this.tMDebitAmt = tMDebitAmt;
      return this;
    }

    public Builder tMPaymentType(String tMPaymentType) {
      this.tMPaymentType = tMPaymentType;
      return this;
    }

    public Builder tMBankRespCode(String tMBankRespCode) {
      this.tMBankRespCode = tMBankRespCode;
      return this;
    }

    public Builder tMApprovalCode(String tMApprovalCode) {
      this.tMApprovalCode = tMApprovalCode;
      return this;
    }

    public Builder tMErrorMsg(String tMErrorMsg) {
      this.tMErrorMsg = tMErrorMsg;
      return this;
    }

    public Builder tMUserField1(String tMUserField1) {
      this.tMUserField1 = tMUserField1;
      return this;
    }

    public Builder tMUserField2(String tMUserField2) {
      this.tMUserField2 = tMUserField2;
      return this;
    }

    public Builder tMUserField3(String tMUserField3) {
      this.tMUserField3 = tMUserField3;
      return this;
    }

    public Builder tMUserField4(String tMUserField4) {
      this.tMUserField4 = tMUserField4;
      return this;
    }

    public Builder tMUserField5(String tMUserField5) {
      this.tMUserField5 = tMUserField5;
      return this;
    }

    public Builder tMOriginalRefNo(String tMOriginalRefNo) {
      this.tMOriginalRefNo = tMOriginalRefNo;
      return this;
    }

    public Builder tMCCLast4Digit(String tMCCLast4Digit) {
      this.tMCCLast4Digit = tMCCLast4Digit;
      return this;
    }

    public Builder tMRecurrentId(String tMRecurrentId) {
      this.tMRecurrentId = tMRecurrentId;
      return this;
    }

    public Builder tMCCNum(String tMCCNum) {
      this.tMCCNum = tMCCNum;
      return this;
    }

    public Builder tMExpiryDate(String tMExpiryDate) {
      this.tMExpiryDate = tMExpiryDate;
      return this;
    }

    public Builder tMIPPFirstPayment(String tMIPPFirstPayment) {
      this.tMIPPFirstPayment = tMIPPFirstPayment;
      return this;
    }

    public Builder tMIPPLastPayment(String tMIPPLastPayment) {
      this.tMIPPLastPayment = tMIPPLastPayment;
      return this;
    }

    public Builder tMIPPMonthlyPayment(String tMIPPMonthlyPayment) {
      this.tMIPPMonthlyPayment = tMIPPMonthlyPayment;
      return this;
    }

    public Builder tMIPPTransTenure(String tMIPPTransTenure) {
      this.tMIPPTransTenure = tMIPPTransTenure;
      return this;
    }

    public Builder tMIPPTotalInterest(String tMIPPTotalInterest) {
      this.tMIPPTotalInterest = tMIPPTotalInterest;
      return this;
    }

    public Builder tMIPPDownPayment(String tMIPPDownPayment) {
      this.tMIPPDownPayment = tMIPPDownPayment;
      return this;
    }

    public Builder tMIPPMonthlyInterest(String tMIPPMonthlyInterest) {
      this.tMIPPMonthlyInterest = tMIPPMonthlyInterest;
      return this;
    }

    public Builder tMSignature(String tMSignature) {
      this.tMSignature = tMSignature;
      return this;
    }

    public Builder tMOriginalPayType(String tMOriginalPayType) {
      this.tMOriginalPayType = tMOriginalPayType;
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

    public TelemoneyResponse build() {
      return new TelemoneyResponse(this);
    }
  }
}