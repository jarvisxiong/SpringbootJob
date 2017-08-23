package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.TreeMap;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MigsRequest implements Serializable {

  private static final long serialVersionUID = 1782322469322856113L;
  @JsonProperty("3DSecureFlag")
  private boolean _3DSecureOn;
  @JsonProperty("vpc_MerchTxnRef")
  private String vpcMerchantTxnRef;
  @JsonProperty("vpc_OrderInfo")
  private String vpcOrderInfo;
  @JsonProperty("vpc_TransNo")
  private String vpcTransNo;
  @JsonProperty("vpc_Amount")
  private String vpcAmount;
  @JsonProperty("vpc_CardNum")
  private String creditCardNumber;
  @JsonProperty("vpc_CardMonth")
  private String expiryMonth;
  @JsonProperty("vpc_CardYear")
  private String expiryYear;
  @JsonProperty("vpc_CardSecurityCode")
  private String securityCode;
  @JsonProperty("vpc_Version")
  public String vpcVersion;
  @JsonProperty("vpc_Command")
  public String vpcCommand;
  @JsonProperty("vpc_Merchant")
  public String vpcMerchant;
  @JsonProperty("vpc_AccessCode")
  public String vpcAccessCode;
  @JsonProperty("vpc_3DSECI")
  public String vpc3DSECI;
  @JsonProperty("vpc_3DSXID")
  public String vpc3DSXID;
  @JsonProperty("vpc_3DSenrolled")
  public String vpc3DSenrolled;
  @JsonProperty("vpc_3DSstatus")
  public String vpc3DSstatus;
  @JsonProperty("vpc_VerToken")
  public String vpcVerToken;
  @JsonProperty("vpc_VerType")
  public String vpcVerType;
  @JsonProperty("vpc_VerSecurityLevel")
  public String vpcVerSecurityLevel;
  @JsonProperty("vpc_VerStatus")
  public String vpcVerStatus;
  //for refund
  @JsonProperty("vpc_LoginId")
  private String loginId;
  @JsonProperty("vpc_LoginPassword")
  private String password;
  @JsonProperty("vpc_Url")
  private String vpcUrl;

  public MigsRequest() {
  }

  private MigsRequest(Builder builder) {
    this._3DSecureOn = builder._3DSecureOn;
    this.vpcMerchantTxnRef = builder.vpcMerchantTxnRef;
    this.vpcOrderInfo = builder.vpcOrderInfo;
    this.vpcTransNo = builder.vpcTransNo;
    this.vpcAmount = builder.vpcAmount;
    this.creditCardNumber = builder.creditCardNumber;
    this.expiryMonth = builder.expiryMonth;
    this.expiryYear = builder.expiryYear;
    this.securityCode = builder.securityCode;
    this.vpcVersion = builder.vpcVersion;
    this.vpcCommand = builder.vpcCommand;
    this.vpcMerchant = builder.vpcMerchant;
    this.vpcAccessCode = builder.vpcAccessCode;
    this.vpc3DSECI = builder.vpc3DSECI;
    this.vpc3DSXID = builder.vpc3DSXID;
    this.vpc3DSenrolled = builder.vpc3DSenrolled;
    this.vpc3DSstatus = builder.vpc3DSstatus;
    this.vpcVerToken = builder.vpcVerToken;
    this.vpcVerType = builder.vpcVerType;
    this.vpcVerSecurityLevel = builder.vpcVerSecurityLevel;
    this.vpcVerStatus = builder.vpcVerStatus;
    this.loginId = builder.loginId;
    this.password = builder.password;
    this.vpcUrl = builder.vpcUrl;

  }

  public boolean is_3DSecureOn() {
    return _3DSecureOn;
  }

  public String getVpcMerchantTxnRef() {
    return vpcMerchantTxnRef;
  }

  public String getVpcOrderInfo() {
    return vpcOrderInfo;
  }

  public String getVpcTransNo() {
    return vpcTransNo;
  }

  public String getVpcAmount() {
    return vpcAmount;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public String getExpiryMonth() {
    return expiryMonth;
  }

  public String getExpiryYear() {
    return expiryYear;
  }

  public String getSecurityCode() {
    return securityCode;
  }

  public String getVpcVersion() {
    return vpcVersion;
  }

  public String getVpcCommand() {
    return vpcCommand;
  }

  public String getVpcMerchant() {
    return vpcMerchant;
  }

  public String getVpcAccessCode() {
    return vpcAccessCode;
  }

  public String getVpc3DSECI() {
    return vpc3DSECI;
  }

  public String getVpc3DSXID() {
    return vpc3DSXID;
  }

  public String getVpc3DSenrolled() {
    return vpc3DSenrolled;
  }

  public String getVpc3DSstatus() {
    return vpc3DSstatus;
  }

  public String getVpcVerToken() {
    return vpcVerToken;
  }

  public String getVpcVerType() {
    return vpcVerType;
  }

  public String getVpcVerSecurityLevel() {
    return vpcVerSecurityLevel;
  }

  public String getVpcVerStatus() {
    return vpcVerStatus;
  }

  public String getLoginId() {
    return loginId;
  }

  public String getPassword() {
    return password;
  }

  public String getVpcUrl() {
    return vpcUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MigsRequest that = (MigsRequest) o;

    return Objects.equals(this._3DSecureOn, that._3DSecureOn) &&
        Objects.equals(this.vpcMerchantTxnRef, that.vpcMerchantTxnRef) &&
        Objects.equals(this.vpcOrderInfo, that.vpcOrderInfo) &&
        Objects.equals(this.vpcTransNo, that.vpcTransNo) &&
        Objects.equals(this.vpcAmount, that.vpcAmount) &&
        Objects.equals(this.creditCardNumber, that.creditCardNumber) &&
        Objects.equals(this.expiryMonth, that.expiryMonth) &&
        Objects.equals(this.expiryYear, that.expiryYear) &&
        Objects.equals(this.securityCode, that.securityCode) &&
        Objects.equals(this.vpcVersion, that.vpcVersion) &&
        Objects.equals(this.vpcCommand, that.vpcCommand) &&
        Objects.equals(this.vpcMerchant, that.vpcMerchant) &&
        Objects.equals(this.vpcAccessCode, that.vpcAccessCode) &&
        Objects.equals(this.vpc3DSECI, that.vpc3DSECI) &&
        Objects.equals(this.vpc3DSXID, that.vpc3DSXID) &&
        Objects.equals(this.vpc3DSenrolled, that.vpc3DSenrolled) &&
        Objects.equals(this.vpc3DSstatus, that.vpc3DSstatus) &&
        Objects.equals(this.vpcVerToken, that.vpcVerToken) &&
        Objects.equals(this.vpcVerType, that.vpcVerType) &&
        Objects.equals(this.vpcVerSecurityLevel, that.vpcVerSecurityLevel) &&
        Objects.equals(this.vpcVerStatus, that.vpcVerStatus) &&
        Objects.equals(this.loginId, that.loginId) &&
        Objects.equals(this.password, that.password) &&
        Objects.equals(this.vpcUrl, that.vpcUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_3DSecureOn, vpcMerchantTxnRef, vpcOrderInfo, vpcTransNo, vpcAmount,
        creditCardNumber,
        expiryMonth, expiryYear, securityCode, vpcVersion, vpcCommand,
        vpcMerchant, vpcAccessCode, vpc3DSECI, vpc3DSXID, vpc3DSenrolled,
        vpc3DSstatus, vpcVerToken, vpcVerType, vpcVerSecurityLevel, vpcVerStatus,
        loginId, password, vpcUrl);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("_3DSecureOn", _3DSecureOn)
        .append("vpcMerchantTxnRef", vpcMerchantTxnRef)
        .append("vpcOrderInfo", vpcOrderInfo)
        .append("vpcTransNo", vpcTransNo)
        .append("vpc_Amount", vpcAmount)
        .append("creditCardNumber", creditCardNumber)
        .append("expiryMonth", expiryMonth)
        .append("expiryYear", expiryYear)
        .append("securityCode", securityCode)
        .append("vpcVersion", vpcVersion)
        .append("vpcCommand", vpcCommand)
        .append("vpcMerchant", vpcMerchant)
        .append("vpcAccessCode", vpcAccessCode)
        .append("vpc3DSECI", vpc3DSECI)
        .append("vpc3DSXID", vpc3DSXID)
        .append("vpc3DSenrolled", vpc3DSenrolled)
        .append("vpc3DSstatus", vpc3DSstatus)
        .append("vpcVerToken", vpcVerToken)
        .append("vpcVerType", vpcVerType)
        .append("vpcVerSecurityLevel", vpcVerSecurityLevel)
        .append("vpcVerStatus", vpcVerStatus)
        .append("loginId", loginId)
        .append("password", password)
        .append("vpcUrl", vpcUrl)
        .toString();
  }

  public static class Builder {

    private boolean _3DSecureOn;
    private String vpcMerchantTxnRef;
    private String vpcOrderInfo;
    private String vpcTransNo;
    private String vpcAmount;
    private String creditCardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String securityCode;
    private String vpcVersion;
    private String vpcCommand;
    private String vpcMerchant;
    private String vpcAccessCode;
    private String vpc3DSECI;
    private String vpc3DSXID;
    private String vpc3DSenrolled;
    private String vpc3DSstatus;
    private String vpcVerToken;
    private String vpcVerType;
    private String vpcVerSecurityLevel;
    private String vpcVerStatus;
    //for refund
    private String loginId;
    private String password;
    private String vpcUrl;

    public Builder() {

    }

    public Builder _3DSecureOn(boolean _3DSecureOn) {
      this._3DSecureOn = _3DSecureOn;
      return this;
    }

    public Builder vpcMerchantTxnRef(String vpcMerchantTxnRef) {
      this.vpcMerchantTxnRef = vpcMerchantTxnRef;
      return this;
    }

    public Builder vpcOrderInfo(String vpcOrderInfo) {
      this.vpcOrderInfo = vpcOrderInfo;
      return this;
    }

    public Builder vpcTransNo(String vpcTransNo) {
      this.vpcTransNo = vpcTransNo;
      return this;
    }

    public Builder vpcAmount(String vpcAmount) {
      this.vpcAmount = vpcAmount;
      return this;
    }

    public Builder creditCardNumber(String creditCardNumber) {
      this.creditCardNumber = creditCardNumber;
      return this;
    }

    public Builder expiryMonth(String expiryMonth) {
      this.expiryMonth = expiryMonth;
      return this;
    }

    public Builder expiryYear(String expiryYear) {
      this.expiryYear = expiryYear;
      return this;
    }

    public Builder securityCode(String securityCode) {
      this.securityCode = securityCode;
      return this;
    }

    public Builder vpcVersion(String vpcVersion) {
      this.vpcVersion = vpcVersion;
      return this;
    }

    public Builder vpcCommand(String vpcCommand) {
      this.vpcCommand = vpcCommand;
      return this;
    }

    public Builder vpcMerchant(String vpcMerchant) {
      this.vpcMerchant = vpcMerchant;
      return this;
    }

    public Builder vpcAccessCode(String vpcAccessCode) {
      this.vpcAccessCode = vpcAccessCode;
      return this;
    }

    public Builder vpc3DSECI(String vpc3DSECI) {
      this.vpc3DSECI = vpc3DSECI;
      return this;
    }

    public Builder vpc3DSXID(String vpc3DSXID) {
      this.vpc3DSXID = vpc3DSXID;
      return this;
    }

    public Builder vpc3DSenrolled(String vpc3DSenrolled) {
      this.vpc3DSenrolled = vpc3DSenrolled;
      return this;
    }

    public Builder vpc3DSstatus(String vpc3DSstatus) {
      this.vpc3DSstatus = vpc3DSstatus;
      return this;
    }

    public Builder vpcVerToken(String vpcVerToken) {
      this.vpcVerToken = vpcVerToken;
      return this;
    }

    public Builder vpcVerType(String vpcVerType) {
      this.vpcVerType = vpcVerType;
      return this;
    }

    public Builder vpcVerSecurityLevel(String vpcVerSecurityLevel) {
      this.vpcVerSecurityLevel = vpcVerSecurityLevel;
      return this;
    }

    public Builder vpcVerStatus(String vpcVerStatus) {
      this.vpcVerStatus = vpcVerStatus;
      return this;
    }

    //for refund
    public Builder loginId(String loginId) {
      this.loginId = loginId;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder vpcUrl(String vpcUrl) {
      this.vpcUrl = vpcUrl;
      return this;
    }


    public MigsRequest build() {
      return new MigsRequest(this);
    }

  }

  public final static class PostDataBuilder {
    /**
     * Gets the post data.
     * @return the post data
     */
    public MultiValueMap<String, String> build(MigsRequest request, boolean withClearCcNum) {
      TreeMap<String, String> map = new TreeMap<>();

      map.put("vpc_Version", request.getVpcVersion());

      if (StringUtils.isNotBlank(request.getVpcCommand())) {
        map.put("vpc_Command", request.getVpcCommand());
      }

      if (StringUtils.isNotBlank(request.getVpcMerchant())) {
        map.put("vpc_Merchant", request.getVpcMerchant());
      }

      if (StringUtils.isNotBlank(request.getVpcAccessCode())) {
        map.put("vpc_AccessCode", request.getVpcAccessCode());
      }

      if (StringUtils.isNotBlank(request.getLoginId())) {
        map.put("vpc_User", request.getLoginId());
      }

      if (StringUtils.isNotBlank(request.getPassword())) {
        map.put("vpc_Password", request.getPassword());
      }

      if (StringUtils.isNotBlank(request.getVpcAmount())) {
        map.put("vpc_Amount", request.getVpcAmount());
      }

      if (StringUtils.isNotBlank(request.getVpcMerchantTxnRef())) {
        map.put("vpc_MerchTxnRef", request.getVpcMerchantTxnRef());
      }

      if (StringUtils.isNotBlank(request.getVpcOrderInfo())) {
        map.put("vpc_OrderInfo", request.getVpcOrderInfo());
      }

      if (StringUtils.isNotBlank(request.getVpcTransNo())) {
        map.put("vpc_TransNo", request.getVpcTransNo());
      }

      // Include credit card information
      if (StringUtils.isNotBlank(request.getCreditCardNumber())
          && StringUtils.isNotBlank(request.getExpiryMonth())
          && StringUtils.isNotBlank(request.getExpiryYear())
          && StringUtils.isNotBlank(request.getVpcCommand())
          && request.getVpcCommand().equals(MigsAction.PURCHASE.getAction())) {

        if (request.is_3DSecureOn()) {
          if (StringUtils.isNotBlank(request.getVpcVerType())) {
            map.put("vpc_VerType", request.getVpcVerType());
          }

          if (StringUtils.isNotBlank(request.getVpcVerToken())) {
            try {
              map.put("vpc_VerToken", URLEncoder.encode(request.getVpcVerToken(), "UTF-8"));
            } catch (UnsupportedEncodingException uee) {
              map.put("vpc_VerToken", request.getVpcVerToken());
            }
          }

          if (StringUtils.isNotBlank(request.getVpc3DSXID())) {
            try {
              map.put("vpc_3DSXID", URLEncoder.encode(request.getVpc3DSXID(), "UTF-8"));
            } catch (UnsupportedEncodingException uee) {
              map.put("vpc_3DSXID", request.getVpc3DSXID());
            }
          }

          if (StringUtils.isNotBlank(request.getVpc3DSECI())) {
            map.put("vpc_3DSECI", request.getVpc3DSECI());
          }

          if (StringUtils.isNotBlank(request.getVpc3DSenrolled())) {
            map.put("vpc_3DSenrolled", request.getVpc3DSenrolled());
          } else {
            map.put("vpc_3DSenrolled", "N");
          }

          if (StringUtils.isNotBlank(request.getVpc3DSstatus())) {
            map.put("vpc_3DSstatus", request.getVpc3DSstatus());
          }

        }
        // Migs follow credit card YYMM style
        map.put("vpc_CardExp", request.getExpiryYear() + request.getExpiryMonth());
        if (withClearCcNum) {
          map.put("vpc_CardNum", request.getCreditCardNumber());
        }
//	        else {
//	          map.put(request.creditCardDto.getMaskedCreditCardNumber());
//	        } 
      }
      if (StringUtils.isNotBlank(request.getSecurityCode())) {
        map.put("vpc_CardSecurityCode", request.getSecurityCode());
      }

      MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
      multiValueMap.setAll(map);
      return multiValueMap;
    }
  }
}
