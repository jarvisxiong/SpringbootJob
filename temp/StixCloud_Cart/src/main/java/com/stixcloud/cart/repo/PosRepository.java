package com.stixcloud.cart.repo;

import com.stixcloud.domain.Pos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by chongye on 21/12/2016.
 */
public interface PosRepository extends CrudRepository<Pos, Long> {
  @Query("SELECT pos "
      + "FROM UserPos up "
      + "JOIN Pos pos ON up.posId = pos.posid "
      + "where up.profileInfoId = :profileInfoId "
      + "and up.userInfoId = :userInfoId")
  Pos getPosByProfileAndUserInfoId(@Param("profileInfoId") Long profileInfoId,
                                   @Param("userInfoId") Long userInfoId);
}
