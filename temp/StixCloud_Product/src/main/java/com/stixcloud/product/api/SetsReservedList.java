package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "setsReserved"
})
public class SetsReservedList {

  @JsonProperty("setsReserved")
  private List<Seat> setsReserved = new ArrayList<Seat>();

  /**
   * No args constructor for use in serialization
   */
  public SetsReservedList() {
  }

  /**
   *
   * @param setsReserved
   */
  public SetsReservedList(List<Seat> setsReserved) {
    this.setsReserved = setsReserved;
  }

  /**
   * @return The setsReserved
   */
  @JsonProperty("setsReserved")
  public List<Seat> getSetsReserved() {
    return setsReserved;
  }

  /**
   * @param setsReserved The setsReserved
   */
  @JsonProperty("setsReserved")
  public void setSetsReserved(List<Seat> setsReserved) {
    this.setsReserved = setsReserved;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SetsReservedList that = (SetsReservedList) o;

    return new EqualsBuilder()
        .append(setsReserved, that.setsReserved)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(setsReserved)
        .toHashCode();
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("setsReserved", setsReserved)
        .toString();
  }
}
