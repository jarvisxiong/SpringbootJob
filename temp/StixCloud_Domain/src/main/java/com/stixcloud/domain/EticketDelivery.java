package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/14/2016.
 */
@Entity
@Table(name = "ETICKET_DELIVERY")
public class EticketDelivery {
  private Long eticketdeliveryid;
  private String linkid;
  private Long orderId;
  private Long transactionid;
  private Long prdtid;

  public EticketDelivery() {
  }

  public EticketDelivery(Long eticketdeliveryid, String linkid, Long orderId,
                         Long transactionid, Long prdtid) {
    this.eticketdeliveryid = eticketdeliveryid;
    this.linkid = linkid;
    this.orderId = orderId;
    this.transactionid = transactionid;
    this.prdtid = prdtid;
  }

  @Id
  @Column(name = "ETICKETDELIVERYID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "eticketDeliveryIDSeq")
  @GenericGenerator(name = "eticketDeliveryIDSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "ETICKET_DELIVERY_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getEticketdeliveryid() {
    return eticketdeliveryid;
  }

  public void setEticketdeliveryid(Long eticketdeliveryid) {
    this.eticketdeliveryid = eticketdeliveryid;
  }

  @Column(name = "LINKID", nullable = false, length = 200)
  public String getLinkid() {
    return linkid;
  }

  public void setLinkid(String linkid) {
    this.linkid = linkid;
  }

  @Column(name = "ORDER_ID", nullable = false, precision = 0)
  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  @Column(name = "TRANSACTIONID", nullable = false, precision = 0)
  public Long getTransactionid() {
    return transactionid;
  }

  public void setTransactionid(Long transactionid) {
    this.transactionid = transactionid;
  }

  @Column(name = "PRDTID", nullable = false, precision = 0)
  public Long getPrdtid() {
    return prdtid;
  }

  public void setPrdtid(Long prdtid) {
    this.prdtid = prdtid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EticketDelivery that = (EticketDelivery) o;
    return Objects.equals(eticketdeliveryid, that.eticketdeliveryid) &&
        Objects.equals(linkid, that.linkid) &&
        Objects.equals(orderId, that.orderId) &&
        Objects.equals(transactionid, that.transactionid) &&
        Objects.equals(prdtid, that.prdtid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eticketdeliveryid, linkid, orderId, transactionid, prdtid);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("eticketdeliveryid", eticketdeliveryid)
        .append("linkid", linkid)
        .append("orderId", orderId)
        .append("transactionid", transactionid)
        .append("prdtid", prdtid)
        .toString();
  }
}
