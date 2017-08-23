package com.stixcloud.product.event;

import com.stixcloud.domain.SeatmapOverviewProducts;

import java.util.List;

/**
 * Created by chongye on 23-Aug-16.
 */
public interface ISeatmapOverviewProductsRepository {
  List<SeatmapOverviewProducts> getSeatmapOverview(Long productId);

  List<SeatmapOverviewProducts> getSeatmapOverview(List<Long> productIds);
}
