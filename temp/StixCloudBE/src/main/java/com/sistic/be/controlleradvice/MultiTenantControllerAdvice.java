package com.sistic.be.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sistic.be.configuration.multitenant.Tenant;
import com.sistic.be.configuration.multitenant.TenantComponents;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

@ControllerAdvice
public class MultiTenantControllerAdvice {
	
	@ModelAttribute("tenantContext")
	public String getTenantContext()
	{		
		return TenantContextHolder.getTenantCode();
	}
	
	@ModelAttribute("tenantTemplateCode")
	public String getTenantTemplateCode()
	{
		if (TenantContextHolder.getTenant() != null) {
			return TenantContextHolder.getTenant().getTemplateCode();
		}
		return null;
	}
	
	@ModelAttribute("tenantComponents")
	public TenantComponents getTenantComponents()
	{
		if (TenantContextHolder.getTenant() != null) {
			return TenantContextHolder.getTenant().getComponents();
		}
		return null;
	}
	
	@ModelAttribute("tenantObject")
	public Tenant getTenantObject()
	{
		return TenantContextHolder.getTenant();
	}

}
