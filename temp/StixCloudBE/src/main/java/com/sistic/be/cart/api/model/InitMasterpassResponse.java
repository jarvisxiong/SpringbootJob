package com.sistic.be.cart.api.model;

import java.io.Serializable;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
  "requestToken",
  "merchantCheckoutId"
})
public class InitMasterpassResponse implements Serializable{

  private static final long serialVersionUID = -4248561508916805892L;
  
  @JsonProperty("requestToken")
  private String requestToken;
  @JsonProperty("merchantCheckoutId")
  private String merchantCheckoutId;
  
  public InitMasterpassResponse() {
    
  }
  
  public InitMasterpassResponse(String requestToken, String merchantCheckoutId) {
    this.requestToken = requestToken;
    this.merchantCheckoutId = merchantCheckoutId;
  }

  @JsonProperty("requestToken")
  public String getRequestToken() {
    return requestToken;
  }

  public void setRequestToken(String requestToken) {
    this.requestToken = requestToken;
  }

  @JsonProperty("merchantCheckoutId")
  public String getMerchantCheckoutId() {
    return merchantCheckoutId;
  }

  public void setMerchantCheckoutId(String merchantCheckoutId) {
    this.merchantCheckoutId = merchantCheckoutId;
  }
}
