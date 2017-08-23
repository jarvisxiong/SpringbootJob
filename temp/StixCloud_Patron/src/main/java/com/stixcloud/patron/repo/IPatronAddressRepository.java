package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronAddress;


@Repository
public interface IPatronAddressRepository extends CrudRepository<PatronAddress, Long> {

  @Query("select p from PatronAddress p where p.patronProfileId = :patronProfileId")
  List<PatronAddress> findByPatronProfileId(@Param("patronProfileId") Long patronProfileId);
  
  @Query("select p from PatronAddress p where p.addressId in :addressIds")
  List<PatronAddress> findPatronAddress(@Param("addressIds") List<Long> addressIds);
  
}
