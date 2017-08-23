package com.stixcloud.evoucher.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVOUCHER_CREDIT_CARD_REGEX")
public class EVoucherCreditCardRegex implements Serializable {

  private static final long serialVersionUID = -5323509903979395787L;

  @Id
  private String evoucherId;
  @Id
  private String ccRegex;

  public EVoucherCreditCardRegex() {

  }

  public EVoucherCreditCardRegex(String evoucherId, String ccRegex) {
    this.evoucherId = evoucherId;
    this.ccRegex = ccRegex;
  }

  /**
   * @return The evoucherId
   */
  @Column(name = "EVOUCHER_ID", nullable = false, length = 100)
  public String getEvoucherId() {
    return evoucherId;
  }

  /**
   * @param evoucherId The evoucherId
   */
  public void setEvoucherId(String evoucherId) {
    this.evoucherId = evoucherId;
  }

  /**
   * @return The ccRegex
   */
  @Column(name = "CC_REGEX", nullable = false)
  public String getCcRegex() {
    return ccRegex;
  }

  /**
   * @param ccRegex The ccRegex
   */
  public void setCcRegex(String ccRegex) {
    this.ccRegex = ccRegex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EVoucherCreditCardRegex that = (EVoucherCreditCardRegex) o;

    return Objects.equals(this.serialVersionUID, that.serialVersionUID) &&
        Objects.equals(this.evoucherId, that.evoucherId) &&
        Objects.equals(this.ccRegex, that.ccRegex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serialVersionUID, evoucherId, ccRegex);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("evoucherId", evoucherId)
        .append("ccRegex", ccRegex)
        .toString();
  }
}
