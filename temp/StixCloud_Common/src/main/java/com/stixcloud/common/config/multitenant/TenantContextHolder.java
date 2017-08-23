package com.stixcloud.common.config.multitenant;

import org.springframework.util.Assert;

public class TenantContextHolder {

  private static final ThreadLocal<MultiTenantProperties.Tenant>
      contextHolder =
      new ThreadLocal<>();

  public static void setTenant(MultiTenantProperties.Tenant tenant) {
    Assert.notNull(tenant, "tenant cannot be null");
    contextHolder.set(tenant);
  }

  public static MultiTenantProperties.Tenant getTenant() {
    return contextHolder.get();
  }

  public static void clearTenant() {
    contextHolder.remove();
  }
}