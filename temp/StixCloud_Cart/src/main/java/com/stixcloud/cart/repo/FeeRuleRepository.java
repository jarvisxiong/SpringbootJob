package com.stixcloud.cart.repo;

import com.stixcloud.domain.FeeRule;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chongye on 26/10/2016.
 */
public interface FeeRuleRepository extends CrudRepository<FeeRule, Long> {
  @Query("SELECT DISTINCT "
      + "conf.feerulesequence, "
      + "rule "
      + "FROM FeeRuleTable tbl "
      + "JOIN FeeRuleTableConfiguration conf ON tbl.feeruletableid = conf.feeRuleTableId "
      + "JOIN FeeRule rule ON conf.feeRuleId = rule.feeruleid "
      + "JOIN TransactionCode code ON rule.transactionCodeId = code.transactioncodeid "
      + "JOIN MasterCodeTable mct ON code.txnCodeMctId = mct.mastercodeid "
      + "JOIN GroupInfo gi ON rule.groupInfoId = gi.groupinfoid "
      + "JOIN UserGroup ug ON gi.groupinfoid = ug.groupInfoId "
      + "WHERE code.txnreason = :txnDesc AND mct.description1 = :txnCode "
      + "   AND tbl.feeruletableid = :feeRuleTableId AND ug.profileInfoId = :profileInfoId "
      + "ORDER BY conf.feerulesequence DESC")
  @Cacheable("FeeRuleByFeeRuleTableId")
  List<Object[]> findMatchingFeeRule(@Param("txnCode") String txnCode,
                                     @Param("txnDesc") String txnDesc,
                                     @Param("profileInfoId") Long profileInfoId,
                                     @Param("feeRuleTableId") Long feeRuleTableId);
}
