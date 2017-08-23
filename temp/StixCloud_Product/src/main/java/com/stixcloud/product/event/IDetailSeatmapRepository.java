package com.stixcloud.product.event;

import com.stixcloud.domain.DetailSeatmapProducts;

import java.util.List;
import java.util.Set;

public interface IDetailSeatmapRepository {

  List<DetailSeatmapProducts> getDetailSeatmap(long productId, long priceCatId, Set<Long> seatIds,
      boolean exclusive);

}
