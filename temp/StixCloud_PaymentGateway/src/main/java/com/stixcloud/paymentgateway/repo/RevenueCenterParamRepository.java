package com.stixcloud.paymentgateway.repo;

import com.stixcloud.domain.RevenueCenterParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chongye on 3/1/2017.
 */
public interface RevenueCenterParamRepository extends CrudRepository<RevenueCenterParam, Long> {
  @Query("SELECT rcp1.revenueCenterId  "
      + "  FROM RevenueCenterParam rcp1  "
      + "  JOIN Pos pos ON rcp1.configurationvalue like CONCAT('%',pos.posid,'%') "
      + "  JOIN UserPos userpos ON pos.posid = userpos.posId  "
      + "  WHERE rcp1.configurationId = 16  "
      + "        AND userpos.profileInfoId = :profileInfoId")
  List<Long> findRevenueCenterIdByProfileInfoId(@Param("profileInfoId") Long profileInfoId);
}
