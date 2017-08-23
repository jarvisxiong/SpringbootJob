package com.stixcloud.common.config.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@RefreshScope
public class MultiTenantFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private MultiTenantProperties multiTenantProperties;

  public MultiTenantFilter() {
  }

  public MultiTenantFilter(MultiTenantProperties multiTenantProperties) {
    this.multiTenantProperties = multiTenantProperties;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    logger.debug("contextPath = " + contextPath);
    logger.debug("requestURI = " + requestURI);
    List<String>
        pathArr =
        new ArrayList<>(Arrays.asList(
            requestURI.replaceFirst(contextPath, "").replaceFirst("/api/v0", "").split("/")));
    pathArr.removeIf(String::isEmpty);

    try {
      String tenantStr = pathArr.get(0);

      if (multiTenantProperties.getTenants().containsKey(tenantStr)) {
        MultiTenantProperties.Tenant tenant = multiTenantProperties.getTenants().get(tenantStr);
        request.setAttribute("PROFILE_INFO_ID", tenant.getProfileInfoId());
        request.setAttribute("USER_INFO_ID", tenant.getUserInfoId());
        TenantContextHolder.setTenant(tenant);
        logger.debug("Tenant set to : " + TenantContextHolder.getTenant());
      }
    } catch (IllegalArgumentException e) {
      logger.debug(e.getMessage(), e);
    }
    chain.doFilter(req, res);
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }
}