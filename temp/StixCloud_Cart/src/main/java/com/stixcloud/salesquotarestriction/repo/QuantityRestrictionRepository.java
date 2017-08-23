package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.domain.QuantityRestriction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

/**
 * Created by mango on 15/3/17.
 */
@Repository
public interface QuantityRestrictionRepository extends PagingAndSortingRepository<QuantityRestriction, Long>, QuantityRestrictionRepositoryCustom {

    @Query(value = " SELECT qr, qrd, qrr, qrri \n" +
            " FROM #{#entityName} qr \n" +
            " JOIN FETCH QuantityRestrictionDetail qrd ON qrd.quantityRestrictionID=qr.ID \n" +
            " JOIN FETCH QuantityRestrictionRule qrr ON qrr.quantityRestrictionID=qr.ID \n" +
            " JOIN FETCH QuantityRestrictionRuleItem qrri ON qrri.quantityRestrictionRuleID=qrr.ID \n" +
            " WHERE qrd.active=true \n" +
            " AND ((:asatdatetime IS NULL \n" +
            " AND qrd.effectiveStart <= CURRENT_TIMESTAMP AND (qrd.effectiveEnd IS NULL OR qrd.effectiveEnd > CURRENT_TIMESTAMP ) \n" +
            " AND qrr.effectiveStart <= CURRENT_TIMESTAMP AND (qrr.effectiveEnd IS NULL OR qrr.effectiveEnd > CURRENT_TIMESTAMP )) \n" +
            " OR (:asatdatetime IS NOT NULL \n" +
            " AND qrd.effectiveStart <= :asatdatetime AND (qrd.effectiveEnd IS NULL OR qrd.effectiveEnd > :asatdatetime) \n" +
            " AND qrr.effectiveStart <= :asatdatetime AND (qrr.effectiveEnd IS NULL OR qrr.effectiveEnd > :asatdatetime))) \n" +
            " ORDER BY qr.ID, qrr.ID, qrri.ID")
    Stream<Object[]> _findApplicableRestriction(@Param("asatdatetime") OffsetDateTime asAtDateTime);
}
