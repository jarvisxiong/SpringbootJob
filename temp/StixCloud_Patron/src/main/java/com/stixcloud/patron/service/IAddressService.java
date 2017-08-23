package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.domain.Address;

public interface IAddressService {

  Address findOne(Long id);

  Address save(Address address);

  List<Address> findByIds(List<Long> ids);

  void deleteAddresses(List<Address> addresses);
}
