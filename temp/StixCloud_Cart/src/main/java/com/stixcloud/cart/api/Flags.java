package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "receiveSistic",
    "receivePromoter",
    "receiveVenue",
    "doNotSignUp"
})
public class Flags {

  @JsonProperty("receiveSistic")
  private Boolean receiveSistic;
  @JsonProperty("receivePromoter")
  private Boolean receivePromoter;
  @JsonProperty("receiveVenue")
  private Boolean receiveVenue;
  @JsonProperty("doNotSignUp")
  private Boolean doNotSignUp;

  public Flags() {
  }

  private Flags(Builder builder) {
    setReceiveSistic(builder.receiveSistic);
    setReceivePromoter(builder.receivePromoter);
    setReceiveVenue(builder.receiveVenue);
    setDoNotSignUp(builder.doNotSignUp);
  }

  /**
   * @return The receiveSistic
   */
  @JsonProperty("receiveSistic")
  public Boolean getReceiveSistic() {
    return receiveSistic;
  }

  /**
   * @param receiveSistic The receiveSistic
   */
  @JsonProperty("receiveSistic")
  public void setReceiveSistic(Boolean receiveSistic) {
    this.receiveSistic = receiveSistic;
  }

  /**
   * @return The receivePromoter
   */
  @JsonProperty("receivePromoter")
  public Boolean getReceivePromoter() {
    return receivePromoter;
  }

  /**
   * @param receivePromoter The receivePromoter
   */
  @JsonProperty("receivePromoter")
  public void setReceivePromoter(Boolean receivePromoter) {
    this.receivePromoter = receivePromoter;
  }

  /**
   * @return The receiveVenue
   */
  @JsonProperty("receiveVenue")
  public Boolean getReceiveVenue() {
    return receiveVenue;
  }

  /**
   * @param receiveVenue The receiveVenue
   */
  @JsonProperty("receiveVenue")
  public void setReceiveVenue(Boolean receiveVenue) {
    this.receiveVenue = receiveVenue;
  }

  /**
   * @return The doNotSignUp
   */
  @JsonProperty("doNotSignUp")
  public Boolean getDoNotSignUp() {
    return doNotSignUp;
  }

  /**
   * @param doNotSignUp The doNotSignUp
   */
  @JsonProperty("doNotSignUp")
  public void setDoNotSignUp(Boolean doNotSignUp) {
    this.doNotSignUp = doNotSignUp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Flags that = (Flags) o;

    return Objects.equals(this.receiveSistic, that.receiveSistic) &&
        Objects.equals(this.receivePromoter, that.receivePromoter) &&
        Objects.equals(this.receiveVenue, that.receiveVenue) &&
        Objects.equals(this.doNotSignUp, that.doNotSignUp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(receiveSistic, receivePromoter, receiveVenue, doNotSignUp);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("receiveSistic", receiveSistic)
        .append("receivePromoter", receivePromoter)
        .append("receiveVenue", receiveVenue)
        .append("doNotSignUp", doNotSignUp)
        .toString();
  }

  public static final class Builder {
    private Boolean receiveSistic;
    private Boolean receivePromoter;
    private Boolean receiveVenue;
    private Boolean doNotSignUp;

    public Builder() {
    }

    public Builder(Flags copy) {
      this.receiveSistic = copy.receiveSistic;
      this.receivePromoter = copy.receivePromoter;
      this.receiveVenue = copy.receiveVenue;
      this.doNotSignUp = copy.doNotSignUp;
    }

    public Builder receiveSistic(Boolean receiveSistic) {
      this.receiveSistic = receiveSistic;
      return this;
    }

    public Builder receivePromoter(Boolean receivePromoter) {
      this.receivePromoter = receivePromoter;
      return this;
    }

    public Builder receiveVenue(Boolean receiveVenue) {
      this.receiveVenue = receiveVenue;
      return this;
    }

    public Builder doNotSignUp(Boolean doNotSignUp) {
      this.doNotSignUp = doNotSignUp;
      return this;
    }

    public Flags build() {
      return new Flags(this);
    }
  }
}