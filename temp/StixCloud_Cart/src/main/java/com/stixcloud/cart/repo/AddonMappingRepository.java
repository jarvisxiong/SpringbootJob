package com.stixcloud.cart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stixcloud.domain.AddonMapping;

public interface AddonMappingRepository extends CrudRepository<AddonMapping, Long> {
  @Query("select distinct am.addonProductID from AddonMapping am "
      + "where am.addonProductID in :productIds "
      + "and am.is_applicable=1"
      + "and am.addonType in :addonType")
  List<Long> getDonationProducts(@Param("productIds") Iterable<Long> productIds, @Param("addonType")String[] addonType);
 
}
