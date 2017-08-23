package com.stixcloud.cart.repo;

import com.stixcloud.domain.FeeRuleTable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chongye on 20/10/2016.
 */
public interface FeeRuleTableRepository extends CrudRepository<FeeRuleTable, Long> {
  @Query("select tbl from FeeRuleTable tbl where tbl.feeruletabletype = :tbltype "
      + "and tbl.feeruletablecategory = :tblcategory")
  @Cacheable("FeeRuleTableStandard")
  FeeRuleTable getStandardFeeRuleTable(@Param("tbltype") Integer type,
                                       @Param("tblcategory") Integer category);


  @Query("select tbl "
      + "from FeeRuleTable tbl "
      + "join FeeTemplateLive tpl on tpl.eventSpecificRuleId = tbl.feeruletableid "
      + "join ProductFeeTemplate prod on prod.feeTemplateId = tpl.feetemplateid "
      + "where prod.productId = :productId")
  @Cacheable("FeeRuleTableByProductId")
  FeeRuleTable getFeeRuleTable(@Param("productId") Long productId);
}
