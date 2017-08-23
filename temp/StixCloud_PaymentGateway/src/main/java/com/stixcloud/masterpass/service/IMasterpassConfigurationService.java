package com.stixcloud.masterpass.service;

import com.stixcloud.masterpass.domain.MasterpassConfiguration;

public interface IMasterpassConfigurationService {

  MasterpassConfiguration getMasterpassConfiguration(String tenantName);
}
