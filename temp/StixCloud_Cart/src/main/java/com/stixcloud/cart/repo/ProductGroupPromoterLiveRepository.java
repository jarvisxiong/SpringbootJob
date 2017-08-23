package com.stixcloud.cart.repo;

import com.stixcloud.domain.ProductGroupPromoterLive;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductGroupPromoterLiveRepository
    extends CrudRepository<ProductGroupPromoterLive, Long> {
  @Query("SELECT p "
      + "FROM ProductGroupPromoterLive p "
      + "where p.prdtGrpId = :prdtGrpId ")
  ProductGroupPromoterLive findByProductGroup(@Param("prdtGrpId") Long prdtGrpId);

}
