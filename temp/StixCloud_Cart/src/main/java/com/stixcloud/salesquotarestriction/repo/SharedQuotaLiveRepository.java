package com.stixcloud.salesquotarestriction.repo;

import com.stixcloud.domain.SharedQuotaLive;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mango on 19/3/2017.
 */
@Repository
public interface SharedQuotaLiveRepository extends PagingAndSortingRepository<SharedQuotaLive, Long>, SharedQuotaLiveRepositoryCustom {

    @Query(value = " SELECT sql, sqdl \n" +
            " FROM #{#entityName} sql \n" +
            " JOIN FETCH SharedQuotaDetailLive sqdl ON sql.ID=sqdl.sharedQuotaLiveID \n" +
            " JOIN FETCH SharedQuotaDetailLive sqdl2 ON sqdl2.sharedQuotaLiveID=sqdl.sharedQuotaLiveID \n" +
            " WHERE sql.productGroupID IN :productGroupIDs \n" +
            " AND sqdl2.productID IN :productIDs \n")
    List<Object[]> _findApplicableQuota(@Param("productGroupIDs") List<Long> productGroupIDList, @Param("productIDs") List<Long> productIDList);

}
