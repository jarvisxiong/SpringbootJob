package com.sistic.be.configuration.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantContextHolder {
	
	private static final Logger logger = LoggerFactory.getLogger(TenantContextHolder.class);

	private static final ThreadLocal<Tenant> contextHolder = new ThreadLocal<>();
	private static final ThreadLocal<String> tenantCode = new ThreadLocal<>();

	public static void setTenant(String tenantStr, Tenant tenant) {
		contextHolder.set(tenant);
		tenantCode.set(tenantStr);
	}

	public static Tenant getTenant() {
		Tenant contextTenant = contextHolder.get();
		if (contextTenant == null) {
			logger.debug("There is no Tenant in contextHolder");
		}
		return contextTenant;
	}
	
	public static String getTenantCode() {
		String contextTenantCode = tenantCode.get();
		if (contextTenantCode == null) {
			logger.debug("There is no tenantCode in contextHolder");
		}
		return contextTenantCode;
	}

	public static void clearTenant() {
		contextHolder.remove();
		tenantCode.remove();
	}

}
