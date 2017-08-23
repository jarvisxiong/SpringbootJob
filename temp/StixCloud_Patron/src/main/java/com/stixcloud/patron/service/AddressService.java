package com.stixcloud.patron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.domain.Address;
import com.stixcloud.patron.repo.IAddressRepository;

@Service
public class AddressService implements IAddressService {

  @Autowired
  private IAddressRepository repo;

  @Override
  public Address findOne(Long id) {
    return repo.findOne(id);
  }

  @Override
  public Address save(Address address) {
    return repo.save(address);
  }

  @Override
  public List<Address> findByIds(List<Long> ids) {
    return repo.findByIds(ids);
  }

  @Override
  public void deleteAddresses(List<Address> addresses) {
    repo.delete(addresses);
  }

}
