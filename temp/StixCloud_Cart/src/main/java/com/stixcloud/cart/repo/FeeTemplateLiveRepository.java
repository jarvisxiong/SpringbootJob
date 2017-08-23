package com.stixcloud.cart.repo;

import com.stixcloud.domain.FeeTemplateLive;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chongye on 27/10/2016.
 */
public interface FeeTemplateLiveRepository extends CrudRepository<FeeTemplateLive, Long> {
  @Query("select tpl from FeeTemplateLive tpl "
      + "join ProductFeeTemplate pft on tpl.feetemplateid = pft.feeTemplateId "
      + "where pft.productId = :productId")
  @Cacheable("FeeTemplateLiveByProductId")
  FeeTemplateLive getFeeTemplateLiveByProductId(@Param("productId") Long productId);
}
