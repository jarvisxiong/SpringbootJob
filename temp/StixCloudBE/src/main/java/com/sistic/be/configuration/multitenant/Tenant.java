package com.sistic.be.configuration.multitenant;

public class Tenant {
	
	private String apiCode;
	private String templateCode;
	private Long cartExpiration;
	private Long organizationId;
	private String preferLanguage;
	private Boolean mobileResponsive;
	private String defaultRedirect;
	private String defaultCallingCode;
	private String defaultCountryCode;
	private String defaultIdType;
	private TenantComponents components;
	
	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public Long getCartExpiration() {
		return cartExpiration;
	}
	public void setCartExpiration(Long cartExpiration) {
		this.cartExpiration = cartExpiration;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getPreferLanguage() {
		return preferLanguage;
	}
	public void setPreferLanguage(String preferLanguage) {
		this.preferLanguage = preferLanguage;
	}
	public Boolean getMobileResponsive() {
		return mobileResponsive;
	}
	public void setMobileResponsive(Boolean mobileResponsive) {
		this.mobileResponsive = mobileResponsive;
	}
	public String getDefaultRedirect() {
		return defaultRedirect;
	}
	public void setDefaultRedirect(String defaultRedirect) {
		this.defaultRedirect = defaultRedirect;
	}
	public String getDefaultCallingCode() {
		return defaultCallingCode;
	}
	public void setDefaultCallingCode(String defaultCallingCode) {
		this.defaultCallingCode = defaultCallingCode;
	}
	public String getDefaultCountryCode() {
		return defaultCountryCode;
	}
	public void setDefaultCountryCode(String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}
	public String getDefaultIdType() {
		return defaultIdType;
	}
	public void setDefaultIdType(String defaultIdType) {
		this.defaultIdType = defaultIdType;
	}
	public TenantComponents getComponents() {
		return components;
	}
	public void setComponents(TenantComponents components) {
		this.components = components;
	}

}
