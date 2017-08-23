package com.stixcloud.product.event;

import com.stixcloud.domain.RetrievePriceTypeView;

import java.util.List;
import java.util.Set;

public interface IPriceClassRepository {

  public List<RetrievePriceTypeView> getPriceClassList(Long productId, Long priceCatId,
      Set<Long> priceClassIds, Long userInfoId, Long profileInfoId,
      String isPackageClass, boolean exclusive);

}
