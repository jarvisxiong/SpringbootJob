package com.stixcloud.cart.repo;

import com.stixcloud.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 12/1/2016.
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

  @Query("SELECT addr "
      + "FROM PatronProfile pp "
      + "JOIN PatronAdvanceProfile  pap ON pp.patronprofileid = pap.patronProfileId "
      + "JOIN PatronAddress pa ON pp.patronprofileid = pa.patronProfileId "
      + "JOIN Address addr ON pa.addressId = addr.addressid "
      + "WHERE pp.patronprofileid = :patronprofileid ")
  List<Address> findPatronAddresses(@Param("patronprofileid") Long patronprofileid);
}
