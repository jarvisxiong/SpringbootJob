package com.sistic.be.configuration.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sistic.be.configuration.multitenant.MultiTenantProperties;
import com.sistic.be.configuration.multitenant.Tenant;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

@Component
public class MultiTenantFilter implements Filter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MultiTenantProperties multiTenantProperties;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stubHttpServletRequest request = (HttpServletRequest) req;
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		String requestURI = request.getRequestURI();
	    String contextPath = request.getContextPath();
	    //TODO: change back to debug level
	    logger.debug("requestURI = " + requestURI + ", contextPath = " + contextPath);
	    String[] pathArr = requestURI.replaceFirst(contextPath, "").replaceFirst("/", "").split("/");
	    
	    String tenantStr = pathArr[0];
	    Tenant tenant = multiTenantProperties.getMultitenant().get(tenantStr);
		TenantContextHolder.setTenant(tenantStr, tenant);
		
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
