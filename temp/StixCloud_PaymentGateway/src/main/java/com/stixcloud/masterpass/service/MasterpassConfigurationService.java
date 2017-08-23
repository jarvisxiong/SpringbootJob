package com.stixcloud.masterpass.service;

import com.stixcloud.masterpass.domain.MasterpassConfiguration;
import com.stixcloud.masterpass.repo.MasterpassConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterpassConfigurationService implements IMasterpassConfigurationService {

  @Autowired
  private MasterpassConfigurationRepository repository;

  @Override
  public MasterpassConfiguration getMasterpassConfiguration(String tenantName) {
    return repository.getMasterpassConfiguration(tenantName);
  }

}
