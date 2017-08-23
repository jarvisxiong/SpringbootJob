package com.stixcloud.cart.repo;


import com.stixcloud.domain.SalesSeatInventory;

import java.util.List;

/**
 * Created by chongye on 23-Aug-16.
 */
public interface SalesSeatInventoryRepositoryCustom {
  List<SalesSeatInventory> findAvailableSeatsForGA(Long productId, Long priceCatId,
                                                   Long seatSectionId, int quantity);
}
