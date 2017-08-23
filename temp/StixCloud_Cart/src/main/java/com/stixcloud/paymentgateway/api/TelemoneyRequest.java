package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.cart.SecurityUtils;
import org.apache.commons.lang.StringUtils;
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
@JsonPropertyOrder({
    "mid",
    "ref",
    "cur",
    "paytype",
    "amt",
    "transtype",
    "subTransType",
    "recurrentid",
    "subsequentmid",
    "refundauthcode",
    "originalref",
    "ccnum",
    "ccdate",
    "cccvv",
    "3ds", //Indicate whether 3Ds is on or off with Y or N
    "3dsstatus",
    "eci",
    "cavv",
    "xid",
    "postURL"
})
public class TelemoneyRequest implements Serializable {

  private static final long serialVersionUID = 4418701188029676040L;
  @JsonProperty("mid")
  public String mid;
  @JsonProperty("ref")
  public String ref;
  @JsonProperty("cur")
  public String cur;
  @JsonProperty("paytype")
  public String paytype;
  @JsonProperty("amt")
  public String amt;
  @JsonProperty("transtype")
  public String transtype;
  @JsonProperty("subTransType")
  public String subTransType;
  @JsonProperty("recurrentid")
  public String recurrentid;
  @JsonProperty("subsequentmid")
  public String subsequentmid;
  @JsonProperty("refundauthcode")
  public String refundauthcode;
  @JsonProperty("originalref")
  public String originalref;

  @JsonProperty("ccnum")
  public String ccnum;
  @JsonProperty("ccdate")
  public String ccdate;
  @JsonProperty("cccvv")
  public String cccvv;
  @JsonProperty("3ds")
  public String _3ds;
  @JsonProperty("3dsstatus")
  public String _3dsstatus;
  @JsonProperty("eci")
  public String eci;
  @JsonProperty("cavv")
  public String cavv;
  @JsonProperty("xid")
  public String xid;

  @JsonProperty("postURL")
  public String postURL;

  public TelemoneyRequest() {
  }

  private TelemoneyRequest(Builder builder) {
    setMid(builder.mid);
    setRef(builder.ref);
    setCur(builder.cur);
    setPaytype(builder.paytype);
    setAmt(builder.amt);
    setTranstype(builder.transtype);
    setSubTransType(builder.subTransType);
    setRecurrentid(builder.recurrentid);
    setSubsequentmid(builder.subsequentmid);
    setRefundauthcode(builder.refundauthcode);
    setOriginalref(builder.originalref);
    setCcnum(builder.ccnum);
    setCcdate(builder.ccdate);
    setCccvv(builder.cccvv);
    set_3ds(builder._3ds);
    set_3dsstatus(builder._3dsstatus);
    setEci(builder.eci);
    setCavv(builder.cavv);
    setXid(builder.xid);
    setPostURL(builder.postURL);
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public String getCur() {
    return cur;
  }

  public void setCur(String cur) {
    this.cur = cur;
  }

  public String getPaytype() {
    return paytype;
  }

  public void setPaytype(String paytype) {
    this.paytype = paytype;
  }

  public String getAmt() {
    return amt;
  }

  public void setAmt(String amt) {
    this.amt = amt;
  }

  public String getTranstype() {
    return transtype;
  }

  public void setTranstype(String transtype) {
    this.transtype = transtype;
  }

  public String getSubTransType() {
    return subTransType;
  }

  public void setSubTransType(String subTransType) {
    this.subTransType = subTransType;
  }

  public String getRecurrentid() {
    return recurrentid;
  }

  public void setRecurrentid(String recurrentid) {
    this.recurrentid = recurrentid;
  }

  public String getSubsequentmid() {
    return subsequentmid;
  }

  public void setSubsequentmid(String subsequentmid) {
    this.subsequentmid = subsequentmid;
  }

  public String getRefundauthcode() {
    return refundauthcode;
  }

  public void setRefundauthcode(String refundauthcode) {
    this.refundauthcode = refundauthcode;
  }

  public String getOriginalref() {
    return originalref;
  }

  public void setOriginalref(String originalref) {
    this.originalref = originalref;
  }

  public String getCcnum() {
    return ccnum;
  }

  public void setCcnum(String ccnum) {
    this.ccnum = ccnum;
  }

  public String getCcdate() {
    return ccdate;
  }

  public void setCcdate(String ccdate) {
    this.ccdate = ccdate;
  }

  public String getCccvv() {
    return cccvv;
  }

  public void setCccvv(String cccvv) {
    this.cccvv = cccvv;
  }

  public String get_3ds() {
    return _3ds;
  }

  public void set_3ds(String _3ds) {
    this._3ds = _3ds;
  }

  public String get_3dsstatus() {
    return _3dsstatus;
  }

  public void set_3dsstatus(String _3dsstatus) {
    this._3dsstatus = _3dsstatus;
  }

  public String getEci() {
    return eci;
  }

  public void setEci(String eci) {
    this.eci = eci;
  }

  public String getCavv() {
    return cavv;
  }

  public void setCavv(String cavv) {
    this.cavv = cavv;
  }

  public String getXid() {
    return xid;
  }

  public void setXid(String xid) {
    this.xid = xid;
  }

  public String getPostURL() {
    return postURL;
  }

  public void setPostURL(String postURL) {
    this.postURL = postURL;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TelemoneyRequest that = (TelemoneyRequest) o;
    return Objects.equals(mid, that.mid) &&
        Objects.equals(ref, that.ref) &&
        Objects.equals(cur, that.cur) &&
        Objects.equals(paytype, that.paytype) &&
        Objects.equals(amt, that.amt) &&
        Objects.equals(transtype, that.transtype) &&
        Objects.equals(subTransType, that.subTransType) &&
        Objects.equals(recurrentid, that.recurrentid) &&
        Objects.equals(subsequentmid, that.subsequentmid) &&
        Objects.equals(refundauthcode, that.refundauthcode) &&
        Objects.equals(originalref, that.originalref) &&
        Objects.equals(ccnum, that.ccnum) &&
        Objects.equals(ccdate, that.ccdate) &&
        Objects.equals(cccvv, that.cccvv) &&
        Objects.equals(_3ds, that._3ds) &&
        Objects.equals(_3dsstatus, that._3dsstatus) &&
        Objects.equals(eci, that.eci) &&
        Objects.equals(cavv, that.cavv) &&
        Objects.equals(xid, that.xid) &&
        Objects.equals(postURL, that.postURL);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(mid, ref, cur, paytype, amt, transtype, subTransType, recurrentid, subsequentmid,
            refundauthcode, originalref, ccnum, ccdate, cccvv,
            _3ds, _3dsstatus, eci, cavv, xid, postURL);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("mid", mid)
        .append("ref", ref)
        .append("cur", cur)
        .append("paytype", paytype)
        .append("amt", amt)
        .append("transtype", transtype)
        .append("subTransType", subTransType)
        .append("recurrentid", recurrentid)
        .append("subsequentmid", subsequentmid)
        .append("refundauthcode", refundauthcode)
        .append("originalref", originalref)
        .append("ccnum", ccnum)
        .append("ccdate", ccdate)
        .append("cccvv", cccvv)
        .append("_3ds", _3ds)
        .append("_3dsstatus", _3dsstatus)
        .append("eci", eci)
        .append("cavv", cavv)
        .append("xid", xid)
        .append("postURL", postURL)
        .toString();
  }


  public static final class Builder {
    private String mid;
    private String ref;
    private String cur;
    private String paytype;
    private String amt;
    private String transtype;
    private String subTransType;
    private String recurrentid;
    private String subsequentmid;
    private String refundauthcode;
    private String originalref;
    private String ccnum;
    private String ccdate;
    private String cccvv;
    private String _3ds;
    private String _3dsstatus;
    private String eci;
    private String cavv;
    private String xid;
    private String postURL;

    public Builder() {
    }

    public Builder(TelemoneyRequest copy) {
      this.mid = copy.mid;
      this.ref = copy.ref;
      this.cur = copy.cur;
      this.paytype = copy.paytype;
      this.amt = copy.amt;
      this.transtype = copy.transtype;
      this.subTransType = copy.subTransType;
      this.recurrentid = copy.recurrentid;
      this.subsequentmid = copy.subsequentmid;
      this.refundauthcode = copy.refundauthcode;
      this.originalref = copy.originalref;
      this.ccnum = copy.ccnum;
      this.ccdate = copy.ccdate;
      this.cccvv = copy.cccvv;
      this._3ds = copy._3ds;
      this._3dsstatus = copy._3dsstatus;
      this.eci = copy.eci;
      this.cavv = copy.cavv;
      this.xid = copy.xid;
      this.postURL = copy.postURL;
    }

    public Builder mid(String mid) {
      this.mid = mid;
      return this;
    }

    public Builder ref(String ref) {
      this.ref = ref;
      return this;
    }

    public Builder cur(String cur) {
      this.cur = cur;
      return this;
    }

    public Builder paytype(String paytype) {
      this.paytype = paytype;
      return this;
    }

    public Builder amt(String amt) {
      this.amt = amt;
      return this;
    }

    public Builder transtype(String transtype) {
      this.transtype = transtype;
      return this;
    }

    public Builder subTransType(String subTransType) {
      this.subTransType = subTransType;
      return this;
    }

    public Builder recurrentid(String recurrentid) {
      this.recurrentid = recurrentid;
      return this;
    }

    public Builder subsequentmid(String subsequentmid) {
      this.subsequentmid = subsequentmid;
      return this;
    }

    public Builder refundauthcode(String refundauthcode) {
      this.refundauthcode = refundauthcode;
      return this;
    }

    public Builder originalref(String originalref) {
      this.originalref = originalref;
      return this;
    }

    public Builder ccnum(String ccnum) {
      this.ccnum = ccnum;
      return this;
    }

    public Builder ccdate(String ccdate) {
      this.ccdate = ccdate;
      return this;
    }

    public Builder cccvv(String cccvv) {
      this.cccvv = cccvv;
      return this;
    }

    public Builder _3ds(String _3ds) {
      this._3ds = _3ds;
      return this;
    }

    public Builder _3dsstatus(String _3dsstatus) {
      this._3dsstatus = _3dsstatus;
      return this;
    }

    public Builder eci(String eci) {
      this.eci = eci;
      return this;
    }

    public Builder cavv(String cavv) {
      this.cavv = cavv;
      return this;
    }

    public Builder xid(String xid) {
      this.xid = xid;
      return this;
    }

    public Builder postURL(String postURL) {
      this.postURL = postURL;
      return this;
    }

    public TelemoneyRequest build() {
      return new TelemoneyRequest(this);
    }

    public final static class PostDataBuilder {
      private TelemoneyRequest telemoneyRequest;
      private boolean withClearCcNum;

      public PostDataBuilder(TelemoneyRequest telemoneyRequest, boolean withClearCcNum) {
        this.telemoneyRequest = telemoneyRequest;
        this.withClearCcNum = withClearCcNum;
      }

      public MultiValueMap<String, String> buildQuery() {
        TreeMap<String, String> map = new TreeMap<>();

        if (StringUtils.isNotBlank(telemoneyRequest.getMid())) {
          map.put("mid", telemoneyRequest.getMid());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getRef())) {
          map.put("ref", telemoneyRequest.getRef());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getCur())) {
          map.put("cur", telemoneyRequest.getCur());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getPaytype())) {
          map.put("paytype", telemoneyRequest.getPaytype());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getAmt())) {
          map.put("amt", telemoneyRequest.getAmt());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getTranstype())) {
          map.put("transtype", telemoneyRequest.getTranstype());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getSubTransType())) {
          map.put("subtranstype", telemoneyRequest.getSubTransType());
        }

        //Start of Credit Card
        if (StringUtils.isNotBlank(telemoneyRequest.getCcnum())
            && StringUtils.isNotBlank(telemoneyRequest.getCcdate())
            && StringUtils.isNotBlank(telemoneyRequest.getCccvv())) {
          if (withClearCcNum) {
            map.put("ccnum", telemoneyRequest.getCcnum());
          } else {
            map.put("ccnum", SecurityUtils.maskPartial(telemoneyRequest.getCcnum()));
          }

          map.put("ccdate", telemoneyRequest.getCcdate());
          map.put("cccvv", telemoneyRequest.getCccvv());

          //Check if 3Ds is On or Off. Y = Yes, N = No
          if (telemoneyRequest.get_3ds().equalsIgnoreCase("Y")) {
            map.put("3ds", telemoneyRequest.get_3ds());
            if (StringUtils.isNotBlank(telemoneyRequest.get_3dsstatus())) {
              map.put("3dsstatus", telemoneyRequest.get_3ds());
            }

            if (StringUtils.isNotBlank(telemoneyRequest.getEci())) {
              map.put("eci", telemoneyRequest.getEci());
            }

            if (StringUtils.isNotBlank(telemoneyRequest.getCavv())) {
              map.put("cavv", telemoneyRequest.getCavv());
            }

            if (StringUtils.isNotBlank(telemoneyRequest.getXid())) {
              try {
                map.put("xid", URLEncoder.encode(telemoneyRequest.getXid(), "UTF-8"));
              } catch (UnsupportedEncodingException uee) {
                map.put("xid", telemoneyRequest.getXid());
              }
            }
          } else {
            map.put("3ds", telemoneyRequest.get_3ds());
          }
        }//End of Credit Card

        if (StringUtils.isNotBlank(telemoneyRequest.getRecurrentid())) {
          map.put("recurrentid", telemoneyRequest.getRecurrentid());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getSubsequentmid())) {
          map.put("subsequentmid", telemoneyRequest.getSubsequentmid());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getRefundauthcode())) {
          map.put("refundauthcode", telemoneyRequest.getRefundauthcode());
        }

        if (StringUtils.isNotBlank(telemoneyRequest.getOriginalref())) {
          map.put("originalref", telemoneyRequest.getOriginalref());
        }

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(map);
        return multiValueMap;
      }
    }
  }
}