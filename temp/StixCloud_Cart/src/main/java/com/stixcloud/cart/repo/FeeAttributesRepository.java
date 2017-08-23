package com.stixcloud.cart.repo;

import com.stixcloud.domain.FeeAttributes;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chongye on 24/10/2016.
 */
public interface FeeAttributesRepository extends CrudRepository<FeeAttributes, Long> {
  @Query("SELECT DISTINCT attr "
      + "FROM FeeAttributes attr "
      + "  join MasterCodeTable mct on attr.levyByMctId = mct.mastercodeid "
      + "  JOIN FeeVersionHistory ver ON attr.feeVersionHistoryId = ver.feeversionhistoryid "
      + "  JOIN Fee fee ON ver.feeId = fee.feeid "
      + "  JOIN FeeClass feecl ON fee.feeClassId = feecl.feeclassid "
      + "WHERE feecl.chargetype = :feeClassChargeType AND fee.feeid = :feeid "
      + "      AND mct.description1 in :levyBys "
      + "      and current_date BETWEEN ver.effectivestartdate AND ver.effectiveenddate")
  @Cacheable("FeeAttributesByFeeIdAndLevyBy")
  List<FeeAttributes> getFeeAttributesByFeeIdAndLevyBy(
      @Param("feeid") Long feeid,
      @Param("feeClassChargeType") Integer feeClassChargeType,
      @Param("levyBys") List<String> levyBys);
}
