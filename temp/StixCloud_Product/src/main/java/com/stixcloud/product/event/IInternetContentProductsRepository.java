package com.stixcloud.product.event;

import com.stixcloud.domain.InternetContentProducts;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by chongye on 23-Aug-16.
 */
public interface IInternetContentProductsRepository {
  List<InternetContentProducts> getProductsByInternetContentCode(String code, LocalDate startDate, LocalDate endDate, Collection<Long> productIds);
  List<InternetContentProducts> getProductsByInternetContentCode(String code, LocalDate startDate, LocalDate endDate);
}
