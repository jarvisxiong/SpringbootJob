package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.domain.SharedQuotaDetailLive;
import com.stixcloud.domain.SharedQuotaLive;

import java.util.List;
import java.util.Map;

/**
 * Created by mango on 19/3/2017.
 */
public interface SharedQuotaLiveRepositoryCustom {

    Map<Long, List<Long>> findIDOfApplicableQuota(List<Long> productGroupIDList, List<Long> productIDList);

    Map<SharedQuotaLive, List<SharedQuotaDetailLive>> findApplicableQuota(List<Long> productGroupIDList, List<Long> productIDList);

}
