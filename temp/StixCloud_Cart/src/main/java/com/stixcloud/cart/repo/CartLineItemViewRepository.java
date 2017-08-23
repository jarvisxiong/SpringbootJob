package com.stixcloud.cart.repo;

import com.stixcloud.domain.CartLineItemsView;
import com.stixcloud.domain.CartLineItemsViewKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by sengkai on 10/19/2016.
 */
@Repository
public interface CartLineItemViewRepository
    extends ReadOnlyRepository<CartLineItemsView, CartLineItemsViewKey> {

  @Query("select cliv from CartLineItemsView cliv where cliv.key.productid = :productId "
      + " and cliv.key.pricecatid = :priceCatId and cliv.key.seatsectionid = :seatSectionId "
      + " and cliv.seatsectiontype = :seatSectionType and cliv.key.priceclasscode = :priceClassCode "
      + " and cliv.key.salesseatinventoryid = :salesSeatInventoryId and cliv.key.profileInfoId = :profileInfoId ")
  CartLineItemsView findCartLineItem(@Param("productId") Long productId,
                                     @Param("priceCatId") Long priceCatId,
                                     @Param("seatSectionId") Long seatSectionId,
                                     @Param("seatSectionType") String seatSectionType,
                                     @Param("priceClassCode") String priceClassCode,
                                     @Param("salesSeatInventoryId") Long salesSeatInventoryId,
                                     @Param("profileInfoId") Long profileInfoId);
}
