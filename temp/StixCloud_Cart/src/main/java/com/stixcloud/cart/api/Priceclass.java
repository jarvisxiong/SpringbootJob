package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "priceClassCode",
    "priceClassName"
})
public class Priceclass implements Serializable {

  private static final long serialVersionUID = 1901090605488095696L;
  @JsonProperty("priceClassCode")
  private String priceClassCode;
  @JsonProperty("priceClassName")
  private String priceClassName;

  /**
   * No args constructor for use in serialization
   */
  public Priceclass() {
  }

  /**
   *
   * @param priceClassName
   */
  public Priceclass(String priceClassName) {
    this.priceClassName = priceClassName;
  }

  private Priceclass(Builder builder) {
    setPriceClassCode(builder.priceClassCode);
    setPriceClassName(builder.priceClassName);
  }

  /**
   * @return The priceClassName
   */
  @JsonProperty("priceClassName")
  public String getPriceClassName() {
    return priceClassName;
  }

  /**
   * @param priceClassName The priceClassName
   */
  @JsonProperty("priceClassName")
  public void setPriceClassName(String priceClassName) {
    this.priceClassName = priceClassName;
  }

  @JsonProperty("priceClassCode")
  public String getPriceClassCode() {
    return priceClassCode;
  }

  @JsonProperty("priceClassCode")
  public void setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Priceclass that = (Priceclass) o;

    return Objects.equals(this.serialVersionUID, that.serialVersionUID) &&
        Objects.equals(this.priceClassCode, that.priceClassCode) &&
        Objects.equals(this.priceClassName, that.priceClassName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serialVersionUID, priceClassCode, priceClassName);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceClassCode", priceClassCode)
        .append("priceClassName", priceClassName)
        .toString();
  }

  public static final class Builder {
    private String priceClassCode;
    private String priceClassName;

    public Builder() {
    }

    public Builder(Priceclass copy) {
      this.priceClassCode = copy.priceClassCode;
      this.priceClassName = copy.priceClassName;
    }

    public Builder priceClassCode(String priceClassCode) {
      this.priceClassCode = priceClassCode;
      return this;
    }

    public Builder priceClassName(String priceClassName) {
      this.priceClassName = priceClassName;
      return this;
    }

    public Priceclass build() {
      return new Priceclass(this);
    }
  }
}
