package com.stixcloud.cart.builder;

import com.stixcloud.domain.EticketDelivery;

/**
 * Created by chongye on 27/12/2016.
 */
public final class EticketDeliveryBuilder {
  private Long eticketdeliveryid;
  private String linkid;
  private Long orderId;
  private Long transactionid;
  private Long prdtid;

  private EticketDeliveryBuilder() {
  }

  public static EticketDeliveryBuilder anEticketDelivery() {
    return new EticketDeliveryBuilder();
  }

  public EticketDeliveryBuilder eticketdeliveryid(Long eticketdeliveryid) {
    this.eticketdeliveryid = eticketdeliveryid;
    return this;
  }

  public EticketDeliveryBuilder linkid(String linkid) {
    this.linkid = linkid;
    return this;
  }

  public EticketDeliveryBuilder orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public EticketDeliveryBuilder transactionid(Long transactionid) {
    this.transactionid = transactionid;
    return this;
  }

  public EticketDeliveryBuilder prdtid(Long prdtid) {
    this.prdtid = prdtid;
    return this;
  }

  public EticketDelivery build() {
    EticketDelivery eticketDelivery = new EticketDelivery();
    eticketDelivery.setEticketdeliveryid(eticketdeliveryid);
    eticketDelivery.setLinkid(linkid);
    eticketDelivery.setOrderId(orderId);
    eticketDelivery.setTransactionid(transactionid);
    eticketDelivery.setPrdtid(prdtid);
    return eticketDelivery;
  }
}
