package com.sistic.be.configuration.multitenant;

import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "")
public class MultiTenantProperties {

	private SortedMap<String, Tenant> multitenant = new TreeMap<String, Tenant>();

	public SortedMap<String, Tenant> getMultitenant() {
		return multitenant;
	}

	public void setMultitenant(SortedMap<String, Tenant> multitenant) {
		this.multitenant = multitenant;
	}

	public Tenant getTenant(String tenant) {
		return multitenant.get(tenant);
	}
}
