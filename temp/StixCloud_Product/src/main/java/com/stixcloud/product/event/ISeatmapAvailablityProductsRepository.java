package com.stixcloud.product.event;

import com.stixcloud.domain.SeatmapAvailablityProducts;

import java.util.List;
import java.util.Set;

/**
 * Created by chongye on 23-Aug-16.
 */
public interface ISeatmapAvailablityProductsRepository {
  public List<SeatmapAvailablityProducts> getSeatmapAvailability(Long productId, Set<Long> priceClassIds, Set<Long> holdCodeIds, boolean exclusive);
}
