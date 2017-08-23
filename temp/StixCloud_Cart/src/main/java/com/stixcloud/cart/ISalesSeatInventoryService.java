package com.stixcloud.cart;

import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.SalesSeatInventory;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by chongye on 7/10/2016.
 */
public interface ISalesSeatInventoryService {
  void releaseSeatsForGA(List<CartItem> cartItems);

  List<SalesSeatInventory> findSeatsAndReserveForGA(
      Long productId, Long priceCatId, Long seatSectionId, int quantity) throws CartException;

  List<SalesSeatInventory> updateSeatsReserveExpiry(List<Long> seatInvIds);

  List<SalesSeatInventory> releaseSeats(List<Long> seatInvIds);

  List<SalesSeatInventory> updateSeatsToSold(List<Long> seatInvIds);

  OffsetDateTime getEarliestReservedTime(List<Long> seatInvIds);

  OffsetDateTime getEarliestReservedStartTime(List<Long> seatInvIds);

  List<SalesSeatInventory> updateSeatsReserveExpiry(List<Long> seatInvIds, Long timeToLive);
}
