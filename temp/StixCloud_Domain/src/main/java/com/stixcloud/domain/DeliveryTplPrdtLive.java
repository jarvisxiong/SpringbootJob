package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "DELIVERY_TPL_PRDT_LIVE")
public class DeliveryTplPrdtLive {
  @Id
  private Long deliverytplprdtid;
  private Long deliveryMethodTemplateId;
  private Long productId;

  public DeliveryTplPrdtLive() {
  }

  @Id
  @Column(name = "DELIVERYTPLPRDTID", nullable = false, precision = 0)
  public Long getDeliverytplprdtid() {
    return deliverytplprdtid;
  }

  public void setDeliverytplprdtid(Long deliverytplprdtid) {
    this.deliverytplprdtid = deliverytplprdtid;
  }

  @Column(name = "DELIVERY_METHOD_TEMPLATE_ID", nullable = false, precision = 0)
  public Long getDeliveryMethodTemplateId() {
    return deliveryMethodTemplateId;
  }

  public void setDeliveryMethodTemplateId(Long deliveryMethodTemplateId) {
    this.deliveryMethodTemplateId = deliveryMethodTemplateId;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryTplPrdtLive that = (DeliveryTplPrdtLive) o;

    return Objects.equals(this.deliverytplprdtid, that.deliverytplprdtid) &&
        Objects.equals(this.deliveryMethodTemplateId, that.deliveryMethodTemplateId) &&
        Objects.equals(this.productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliverytplprdtid, deliveryMethodTemplateId, productId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("deliverytplprdtid", deliverytplprdtid)
        .append("deliveryMethodTemplateId", deliveryMethodTemplateId)
        .append("productId", productId)
        .toString();
  }
}
