package com.stixcloud.product.api;

import com.stixcloud.domain.DetailSeatmapProducts;

import java.util.List;

public interface IDetailSeatmap {

  DetailSeatmap getDetailSeatmap(List<DetailSeatmapProducts> detailSeatmapProductsList,
      long productId, long priceCatId, long seatSectionId, int quantity, boolean isHold);

}
