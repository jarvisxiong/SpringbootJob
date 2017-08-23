package com.stixcloud.common.filter;


import org.slf4j.MDC;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Log4JFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

    try {
      HttpServletRequest req = (HttpServletRequest) request;
      MDC.put("sessionId", req.getSession().getId());

      String ipAddress = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
      if (ipAddress == null) {
        ipAddress = request.getRemoteAddr();
      }
      MDC.put("ipAddress", ipAddress);
      chain.doFilter(request, response);
    } finally {
      MDC.remove("sessionId");
      MDC.remove("sessionId");
    }
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }
}