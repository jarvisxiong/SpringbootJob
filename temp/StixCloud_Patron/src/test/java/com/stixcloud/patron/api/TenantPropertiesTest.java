package com.stixcloud.patron.api;

import com.stixcloud.common.config.multitenant.MultiTenantProperties;
import com.stixcloud.common.config.multitenant.TenantContextHolder;

/**
 * Created by sengkai on 1/13/2017.
 */
public class TenantPropertiesTest {

  public static void setUp() throws Exception {
    MultiTenantProperties.Tenant tenant = new MultiTenantProperties.Tenant();
    tenant.setName("SISTIC");
    tenant.setProfileInfoId(11L);
    tenant.setUserInfoId(59L);

    TenantContextHolder.setTenant(tenant);
  }
}
