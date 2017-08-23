package com.stixcloud.seatmap.repo;

import com.stixcloud.seatmap.dto.PriceCategoryDetail;

import java.util.List;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface PriceCategoryDetailRepository {
  List<PriceCategoryDetail> findPriceCategoryDetailByProductId(Long productId);
}
