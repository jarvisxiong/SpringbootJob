package com.sistic.be.app.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sistic.be.configuration.multitenant.TenantContextHolder;

public class WebUrlUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WebUrlUtil.class);

	public static String getHost(){
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String forwardedHost = request.getHeader("X-Forwarded-Host");
		String host;
		if (forwardedHost != null && !forwardedHost.isEmpty()) {
			host = forwardedHost;
		} else {
			host = request.getServerName();
			String port = String.valueOf(request.getServerPort());
			if (port != null && !port.isEmpty()) {
				host = host + ":" + port;
			}
		}
		return host;
	}

	/**
	 * Returns the URL up to the context path
	 * @param request
	 * @return
	 */
	public static String getURLWithContextPath() {
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

//		String forwardedFor = request.getHeader("X-Forwarded-For");
//		String forwardedPort = request.getHeader("X-Forwarded-Port");
		String forwardedProto = request.getHeader("X-Forwarded-Proto");
		String forwardedHost = request.getHeader("X-Forwarded-Host");
		
		String scheme;
		String host;
		String port;
		
		if (forwardedProto != null && !forwardedProto.isEmpty()) {
			scheme = forwardedProto;
		} else {
			scheme = request.getScheme();
		}
		
		if (forwardedHost != null && !forwardedHost.isEmpty()) {
			host = forwardedHost;
		} else {
			port = String.valueOf(request.getServerPort());
			host = request.getServerName();
			if (port != null && !port.isEmpty()) {
				host = host + ":" + port;
			}
		}
		
		return scheme + "://" + host + request.getContextPath();
	}

	/**
	 * Returns the URL up to the port
	 * @param request
	 * @return
	 */
	public static String getURLWithPort() {
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

//		String forwardedFor = request.getHeader("X-Forwarded-For");
//		String forwardedPort = request.getHeader("X-Forwarded-Port");
		String forwardedProto = request.getHeader("X-Forwarded-Proto");
		String forwardedHost = request.getHeader("X-Forwarded-Host");
		
		String scheme;
		String host;
		String port;
		
		if (forwardedProto != null && !forwardedProto.isEmpty()) {
			scheme = forwardedProto;
		} else {
			scheme = request.getScheme();
		}
		
		if (forwardedHost != null && !forwardedHost.isEmpty()) {
			host = forwardedHost;
		} else {
			port = String.valueOf(request.getServerPort());
			host = request.getServerName();
			if (port != null && !port.isEmpty()) {
				host = host + ":" + port;
			}
		}
		
		return scheme + "://" + host;
	}
	
	public static String getURLWithContextPathWithTenantCode() {
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

//		String forwardedFor = request.getHeader("X-Forwarded-For");
//		String forwardedPort = request.getHeader("X-Forwarded-Port");
		String forwardedProto = request.getHeader("X-Forwarded-Proto");
		String forwardedHost = request.getHeader("X-Forwarded-Host");
		
		String scheme;
		String host;
		String port;
		
		if (forwardedProto != null && !forwardedProto.isEmpty()) {
			scheme = forwardedProto;
		} else {
			scheme = request.getScheme();
		}
		
		if (forwardedHost != null && !forwardedHost.isEmpty()) {
			host = forwardedHost;
		} else {
			port = String.valueOf(request.getServerPort());
			host = request.getServerName();
			if (port != null && !port.isEmpty()) {
				host = host + ":" + port;
			}
		}
		
		String urlTenantCode = TenantContextHolder.getTenantCode();
		return scheme + "://" + host + request.getContextPath() + "/" + urlTenantCode;
		
	}
	
	
	public static String getClientIP() {
		
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		String userIp = "";
		
		String xForwardedFor = request.getHeader("X-Forwarded-For");
		String proxyClientIp = request.getHeader("Proxy-Client-IP");
		String wlProxyClientIp = request.getHeader("WL-Proxy-Client-IP");
		String remoteAddr = request.getRemoteAddr();
		
		try {
			if (xForwardedFor != null && !"unknown".equalsIgnoreCase(xForwardedFor) && !xForwardedFor.isEmpty()) {
				userIp = xForwardedFor;
			} else if (proxyClientIp != null && !"unknown".equalsIgnoreCase(proxyClientIp) && !proxyClientIp.isEmpty()) {
				userIp = proxyClientIp;
			} else if (wlProxyClientIp != null && !"unknown".equalsIgnoreCase(wlProxyClientIp) && !wlProxyClientIp.isEmpty()) {
				userIp = wlProxyClientIp;
			} else {
				userIp = remoteAddr;
			}
		} catch (Exception e) {
			logger.error("Cannot get client ip: ", e);
		}
		
		return userIp;
		
	}

}
