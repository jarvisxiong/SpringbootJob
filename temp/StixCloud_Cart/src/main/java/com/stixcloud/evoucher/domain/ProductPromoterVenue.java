package com.stixcloud.evoucher.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dbthan on 19/10/2016.
 */

@Entity
@Table(name = "PRODUCT_PROMOTER_VENUE")
public class ProductPromoterVenue implements Serializable {

  private static final long serialVersionUID = 1128483576096256577L;
  @Id
  private Long productId;
  @Id
  private Long promoterId;
  @Id
  private Long venueId;

  public ProductPromoterVenue() {

  }

  public ProductPromoterVenue(Long productId, Long promoterId, Long venueId) {
    this.productId = productId;
    this.promoterId = promoterId;
    this.venueId = venueId;
  }

  /**
   * @return the productId
   */
  @Column(name = "PRODUCT_ID", nullable = false, length = 10)
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * @return the promoterId
   */
  @Column(name = "PROMOTER_ID", nullable = false, length = 10)
  public Long getPromoterId() {
    return promoterId;
  }

  /**
   * @param promoterId the promoterId to set
   */
  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }

  /**
   * @return the venueId
   */
  @Column(name = "VENUE_ID", nullable = false, length = 10)
  public Long getVenueId() {
    return venueId;
  }

  /**
   * @param venueId the venueId to set
   */
  public void setVenueId(Long venueId) {
    this.venueId = venueId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductPromoterVenue that = (ProductPromoterVenue) o;
    return new EqualsBuilder().append(productId, that.getProductId())
        .append(promoterId, that.getPromoterId()).append(venueId, that.getVenueId()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(productId).append(promoterId).append(venueId)
        .toHashCode();
  }
}
