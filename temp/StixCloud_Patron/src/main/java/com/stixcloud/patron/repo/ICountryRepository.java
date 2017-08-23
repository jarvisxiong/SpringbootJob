package com.stixcloud.patron.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.Country;

@Repository
public interface ICountryRepository extends CrudRepository<Country, Long> {

  @Query("select c from Country c where c.countryCode = :countryCode")
  Country findByCountryCode(@Param("countryCode") String countryCode);
}
