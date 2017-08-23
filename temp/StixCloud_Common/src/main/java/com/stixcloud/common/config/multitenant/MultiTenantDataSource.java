package com.stixcloud.common.config.multitenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiTenantDataSource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
	    if (TenantContextHolder.getTenant() != null) {
		      return TenantContextHolder.getTenant().getName(); //caller requires enum instead of String
			/*
			 * String tenantName=TenantContextHolder.getTenant().getName();
			 * for(Tenant t:Tenant.values()){ if
			 * (tenantName.equals(t.toString())) {
			 * logger.debug("TenantName: "+tenantName); return t.toString(); } }
			 */
		}
		return null;
	}
}