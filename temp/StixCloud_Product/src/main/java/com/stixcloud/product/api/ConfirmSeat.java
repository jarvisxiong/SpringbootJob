package com.stixcloud.product.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chongye on 02-Sep-16.
 */
public class ConfirmSeat implements Serializable {

  private static final long serialVersionUID = -340772047897798261L;
  private List<Long> releasedSeatList;
  private List<Long> reservedSeatList;

  public ConfirmSeat() {
  }

  public List<Long> getReleasedSeatList() {
    return releasedSeatList;
  }

  public void setReleasedSeatList(List<Long> releasedSeatList) {
    this.releasedSeatList = releasedSeatList;
  }

  public List<Long> getReservedSeatList() {
    return reservedSeatList;
  }

  public void setReservedSeatList(List<Long> reservedSeatList) {
    this.reservedSeatList = reservedSeatList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ConfirmSeat)) {
      return false;
    }

    ConfirmSeat that = (ConfirmSeat) o;

    return new EqualsBuilder()
        .append(getReleasedSeatList(), that.getReleasedSeatList())
        .append(getReservedSeatList(), that.getReservedSeatList())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getReleasedSeatList())
        .append(getReservedSeatList())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("releasedSeatList", releasedSeatList)
        .append("reservedSeatList", reservedSeatList)
        .toString();
  }
}
