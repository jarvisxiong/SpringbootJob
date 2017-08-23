package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.domain.SharedQuotaDetailLive;
import com.stixcloud.domain.SharedQuotaLive;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mango on 19/3/2017.
 */
public class SharedQuotaLiveRepositoryImpl implements SharedQuotaLiveRepositoryCustom {

    @Autowired
    private SharedQuotaLiveRepository sharedQuotaLiveRepository;

    @Override
    public Map<Long, List<Long>> findIDOfApplicableQuota(List<Long> productGroupIDList, List<Long> productIDList) {
        return sharedQuotaLiveRepository._findApplicableQuota(productGroupIDList, productIDList).stream().collect(
                Collectors.groupingBy(
                        e -> ((SharedQuotaLive) e[0]).getID()
                        , Collectors.mapping(
                                e -> ((SharedQuotaDetailLive) e[1]).getID()
                                , Collectors.toList()
                        )
                )
        );
    }

    @Override
    public Map<SharedQuotaLive, List<SharedQuotaDetailLive>> findApplicableQuota(List<Long> productGroupIDList, List<Long> productIDList) {
        if (productIDList.isEmpty() || productGroupIDList.isEmpty())
            return Collections.emptyMap();
        return sharedQuotaLiveRepository._findApplicableQuota(productGroupIDList, productIDList).stream().collect(
                Collectors.groupingBy(
                        e -> (SharedQuotaLive) e[0]
                        , Collectors.mapping(
                                e -> (SharedQuotaDetailLive) e[1]
                                , Collectors.toList()
                        )
                )
        );
    }
}
