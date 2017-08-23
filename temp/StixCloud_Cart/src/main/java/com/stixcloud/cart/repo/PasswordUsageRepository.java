package com.stixcloud.cart.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PasswordUsage;

@Repository
public interface PasswordUsageRepository extends CrudRepository<PasswordUsage, Long> {

  @Modifying
  @Query("update PasswordUsage set usedtimes = usedtimes + :numberUsage where passwordRegexId = :passwordRegexId")
  void updateNumberUsage(@Param("passwordRegexId") Long passwordRegexId,
      @Param("numberUsage") Integer numberUsage);
}
