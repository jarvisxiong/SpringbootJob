package com.stixcloud.cart.repo;

import com.stixcloud.domain.PriceClassLive;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chongye on 13-Sep-16.
 */
public interface PriceClassLiveRepository extends CrudRepository<PriceClassLive, Long> {
  @Query("SELECT pcl "
      + "FROM PriceModelTplPrdtLive ptpl "
      + "  join PmtRowLive prl on prl.pricemodeltemplateId = ptpl.priceModelTemplateId "
      + "  join PriceClassLive pcl on pcl.priceclassid = prl.priceclassId "
      + "WHERE ptpl.productId = :productId "
      + "and pcl.priceclasscode = :priceClassCode")
  PriceClassLive getPriceClassLive(@Param("productId") Long productId,
                                   @Param("priceClassCode") String priceClassCode);
  
  @Query("SELECT pcl "
      + "FROM PriceModelTplPrdtLive ptpl "
      + "  join PmtRowLive prl on prl.pricemodeltemplateId = ptpl.priceModelTemplateId "
      + "  join PriceClassLive pcl on pcl.priceclassid = prl.priceclassId "
      + "WHERE ptpl.productId IN :productIdList "
      + "and pcl.priceclasscode IN :priceClassCodeList")
  List<PriceClassLive> getPriceClassLiveList(@Param("productIdList") Iterable<Long> productIdList,
                                   @Param("priceClassCodeList") Iterable<String> priceClassCodeList);
}
