package com.stixcloud.product.event;

import java.util.List;

import com.stixcloud.domain.ProductAvailabilityView;

public interface IProductAvailabilityRepository {
  List<ProductAvailabilityView> getProductAvailabilityList(String contentCode);
}
