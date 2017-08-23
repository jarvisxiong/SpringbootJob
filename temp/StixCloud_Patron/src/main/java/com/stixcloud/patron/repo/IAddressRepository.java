package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.Address;


@Repository
public interface IAddressRepository extends CrudRepository<Address, Long> {

  @Query("select a from Address a where addressId in :ids")
  List<Address> findByIds(@Param("ids") List<Long> ids);
}
