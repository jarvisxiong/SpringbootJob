package com.stixcloud.product.event;

import com.stixcloud.domain.PmtChartLive;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * Created by chongye on 27/12/2016.
 */
public interface PmtChartLiveRepository extends CrudRepository<PmtChartLive, Long> {
  @Query("SELECT chart.pricevalue "
      + "FROM PriceModelTplPrdtLive pmtpl "
      + "  JOIN PmtChartLive chart ON pmtpl.priceModelTemplateId = chart.pricemodeltemplateId "
      + "  JOIN PmtRowLive row ON chart.pmtrowId = row.pmtrowid "
      + "  JOIN PmtColumnLive column ON chart.pmtcolumnId = column.pmtcolumnid "
      + "WHERE pmtpl.productId = :productId AND row.isstandard = 'T' "
      + "AND column.pricecategoryId = :priceCategoryId")
  BigDecimal getStandardPriceClassValue(@Param("productId") Long productId,
                                        @Param("priceCategoryId") Long priceCategoryId);
}
