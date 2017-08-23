package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.domain.QuantityRestriction;
import com.stixcloud.domain.QuantityRestrictionDetail;
import com.stixcloud.domain.QuantityRestrictionRule;
import com.stixcloud.domain.QuantityRestrictionRuleItem;

import java.time.OffsetDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mango on 16/3/17.
 */
public interface QuantityRestrictionRepositoryCustom {

    Map<Long, Map<Long, Map<Long, EnumMap<QuantityRestrictionRuleItem.ItemType, List<Long>>>>> findIDOfApplicableRestriction(OffsetDateTime asAtDateTime);

    Map<QuantityRestriction, Map<QuantityRestrictionDetail, Map<QuantityRestrictionRule, EnumMap<QuantityRestrictionRuleItem.ItemType, List<QuantityRestrictionRuleItem>>>>> findApplicableRestriction(OffsetDateTime asAtDateTime);

}
