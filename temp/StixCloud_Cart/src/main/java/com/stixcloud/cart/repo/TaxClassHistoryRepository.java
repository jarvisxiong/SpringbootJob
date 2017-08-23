package com.stixcloud.cart.repo;

import com.stixcloud.domain.TaxClassHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Created by chongye on 6/1/2017.
 */
public interface TaxClassHistoryRepository extends CrudRepository<TaxClassHistory, Long> {
  @Query("SELECT hist.value "
      + "FROM ProductLive pl "
      + "JOIN ProductGroupLive pgl ON pl.productGroupId = pgl.productgroupid "
      + "JOIN TaxClass tax ON pgl.taxPercentId = tax.taxclassid "
      + "JOIN TaxClassHistory hist ON tax.taxclassid = hist.taxClassId "
      + "WHERE :purchaseDate BETWEEN hist.effectivedate AND hist.expirydate "
      + "AND pl.productid = :productId")
  BigDecimal getApplicableTaxPercent(
      @Param("productId") Long productId, @Param("purchaseDate") OffsetDateTime purchaseDate);
}
