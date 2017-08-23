package com.stixcloud.cart.repo;

import com.stixcloud.domain.FeeChargeRule;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chongye on 19/10/2016.
 */
public interface FeeChargeRuleRepository extends CrudRepository<FeeChargeRule, Long> {
  @Query("SELECT rule FROM FeeChargeRule rule "
      + "JOIN FeeAttributes attr ON rule.feeAttributeId = attr.feeattributeid "
      + "where rule.ruletype = :ruleType and attr.chargetype = :chargeType "
      + "and attr.feeattributeid = :feeAttributeId")
  @Cacheable("FeeChargeRuleByChargeTypeAndRuleType")
  List<FeeChargeRule> getFeeChargeRule(@Param("feeAttributeId") Long feeAttributeId,
                                       @Param("chargeType") Integer chargeType,
                                       @Param("ruleType") Integer ruleType);
}
