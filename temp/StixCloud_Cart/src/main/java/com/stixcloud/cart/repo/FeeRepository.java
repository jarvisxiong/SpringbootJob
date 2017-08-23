package com.stixcloud.cart.repo;

import com.stixcloud.domain.Fee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by chongye on 19/10/2016.
 */
public interface FeeRepository extends CrudRepository<Fee, Long> {
  @Query("select distinct fee "
      + "from Fee fee "
      + "join FeeClass feecl on fee.feeClassId = feecl.feeclassid "
      + "join MasterCodeTable mct on feecl.feeCategoryMctId = mct.mastercodeid "
      + "join FeeRuleFeeMapping feemap on fee.feeid = feemap.feeId "
      + "join FeeRule rule on feemap.feeRuleId = rule.feeruleid "
      + "where mct.description1 = :category "
      + "and rule.feeruleid = :feeRuleId "
      + "and feecl.chargetype = 2")
  @Cacheable("FeeByCategoryAndFeeRuleId")
  Set<Fee> getFeeByCategoryAndFeeRuleId(@Param("category") String category,
                                        @Param("feeRuleId") Long feeRuleId);
}
