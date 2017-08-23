package com.stixcloud.cart.repo;

import com.stixcloud.domain.PriceCategoryLive;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chongye on 20/10/2016.
 */
public interface PriceCategoryLiveRepository extends CrudRepository<PriceCategoryLive, Long> {
  @Override
    // @Cacheable("PriceCategoryLive")
  PriceCategoryLive findOne(Long aLong);
}
