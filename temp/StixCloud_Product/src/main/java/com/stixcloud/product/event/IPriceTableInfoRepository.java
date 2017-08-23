package com.stixcloud.product.event;

import com.stixcloud.domain.PriceTableInfo;

import java.util.List;
import java.util.Set;

/**
 * Created by chongye on 23-Aug-16.
 */
public interface IPriceTableInfoRepository {
  List<PriceTableInfo> getPriceInfoTable(Long productId, String isPackageClass,
      Set<Long> priceClassIds, Set<Long> priceCatIds, boolean exclusive);

  List<PriceTableInfo> getPriceInfoTableProducts(List<Long> products, String promoCode,
      String isPackageClass);
}
