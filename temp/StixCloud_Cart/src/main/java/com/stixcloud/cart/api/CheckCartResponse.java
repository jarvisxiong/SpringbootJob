package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lineItemList"
})
public class CheckCartResponse implements Serializable {

  private static final long serialVersionUID = -387995020966294429L;
  @JsonProperty("lineItemList")
  private List<CheckCartItem> lineItemList;

  public CheckCartResponse() {
  }

  private CheckCartResponse(List<CheckCartItem> lineItemList
  ) {
    this.lineItemList = lineItemList;
  }

  private CheckCartResponse(Builder builder) {
    lineItemList = builder.lineItemList;
  }

  @JsonProperty("lineItemList")
  public List<CheckCartItem> getLineItemList() {
    return lineItemList;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheckCartResponse that = (CheckCartResponse) o;
    return Objects.equals(lineItemList, that.lineItemList);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(lineItemList);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("lineItemList", lineItemList)
        .toString();
  }

  public static class Builder {
    private List<CheckCartItem> lineItemList;

    public Builder() {
    }

    public Builder cartLineItems(List<CheckCartItem> lineItemList) {
      this.lineItemList = lineItemList;
      return this;
    }


    public CheckCartResponse build() {
      return new CheckCartResponse(lineItemList);
    }

    public Builder lineItemList(List<CheckCartItem> lineItemList) {
      this.lineItemList = lineItemList;
      return this;
    }

  }
}
