package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.salesquotarestriction.domain.ItemQuantityTuple;
import com.stixcloud.salesquotarestriction.domain.Quota;

import java.util.stream.Stream;

/**
 * Created by mango on 28/3/17.
 */

public interface QuotaRepositoryCustom {

    Stream<ItemQuantityTuple> findQuantityByPatronPrimaryContactNumber(Quota... quotas);

    Stream<ItemQuantityTuple> findQuantityByPatronEmailAddress(Quota... quotas);

    Stream<ItemQuantityTuple> findQuantity(Quota... quotas);

}
