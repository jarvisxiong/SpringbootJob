package com.stixcloud.product.event;


import com.stixcloud.domain.AddonMapping;

import java.util.List;

public interface AddOnProductRepository {
  List<AddonMapping> findAddOnProductsByEventProductIds(List<Long> eventProductIds);

List<AddonMapping> findAddOnProductsByOrganizationId(List<Long> orgIds);

List<Long> findOrganizationIdForProducts(List<Long> productIds);
}
