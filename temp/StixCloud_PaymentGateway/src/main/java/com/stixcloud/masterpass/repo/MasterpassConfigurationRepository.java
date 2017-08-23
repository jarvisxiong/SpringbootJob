package com.stixcloud.masterpass.repo;

import com.stixcloud.masterpass.domain.MasterpassConfiguration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MasterpassConfigurationRepository
    extends CrudRepository<MasterpassConfiguration, Long> {

  @Query("select m from MasterpassConfiguration m where m.tenantName = :tenantName")
  public MasterpassConfiguration getMasterpassConfiguration(@Param("tenantName") String tenantName);
}
