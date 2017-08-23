package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.domain.QuantityRestriction;
import com.stixcloud.domain.QuantityRestrictionDetail;
import com.stixcloud.domain.QuantityRestrictionRule;
import com.stixcloud.domain.QuantityRestrictionRuleItem;
import com.stixcloud.salesquotarestriction.domain.converters.RootExpressionToSQLStringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mango on 16/3/17.
 */
public class QuantityRestrictionRepositoryImpl implements QuantityRestrictionRepositoryCustom {

    @Autowired
    private       QuantityRestrictionRepository      quantityRestrictionRepository;
    private final EntityManager                      entityManager;
    private final RootExpressionToSQLStringConverter rootExpressionToSQLStringConverter;

    @Autowired
    public QuantityRestrictionRepositoryImpl(EntityManager entityManager, RootExpressionToSQLStringConverter rootExpressionToSQLStringConverter) {
        this.entityManager = entityManager;
        this.rootExpressionToSQLStringConverter = rootExpressionToSQLStringConverter;
    }

    public Map<Long, Map<Long, Map<Long, EnumMap<QuantityRestrictionRuleItem.ItemType, List<Long>>>>> findIDOfApplicableRestriction(OffsetDateTime asAtDateTime) {
        return quantityRestrictionRepository._findApplicableRestriction(asAtDateTime).collect(
                Collectors.groupingBy(
                        e -> ((QuantityRestriction) e[0]).getID()
                        , Collectors.groupingBy(
                                e -> ((QuantityRestrictionDetail) e[1]).getID()
                                , Collectors.groupingBy(
                                        e -> ((QuantityRestrictionRule) e[2]).getID()
                                        , Collectors.groupingBy(
                                                e -> ((QuantityRestrictionRuleItem) e[3]).getType()
                                                , () -> new EnumMap<>(QuantityRestrictionRuleItem.ItemType.class)
                                                , Collectors.mapping(
                                                        e -> ((QuantityRestrictionRuleItem) e[3]).getID()
                                                        , Collectors.toList()
                                                )
                                        )
                                )
                        )
                ));
    }

    public Map<QuantityRestriction, Map<QuantityRestrictionDetail, Map<QuantityRestrictionRule, EnumMap<QuantityRestrictionRuleItem.ItemType, List<QuantityRestrictionRuleItem>>>>> findApplicableRestriction(OffsetDateTime asAtDateTime) {
        return quantityRestrictionRepository._findApplicableRestriction(asAtDateTime).collect(
                Collectors.groupingBy(
                        e -> (QuantityRestriction) e[0]
                        , Collectors.groupingBy(
                                e -> (QuantityRestrictionDetail) e[1]
                                , Collectors.groupingBy(
                                        e -> (QuantityRestrictionRule) e[2]
                                        , Collectors.groupingBy(
                                                e -> ((QuantityRestrictionRuleItem) e[3]).getType()
                                                , () -> new EnumMap<>(QuantityRestrictionRuleItem.ItemType.class)
                                                , Collectors.mapping(
                                                        e -> (QuantityRestrictionRuleItem) e[3]
                                                        , Collectors.toList()
                                                )
                                        )
                                )
                        )
                ));
    }


}
