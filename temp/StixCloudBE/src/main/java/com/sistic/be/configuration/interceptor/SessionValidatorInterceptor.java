package com.sistic.be.configuration.interceptor;

import java.time.DateTimeException;
import java.time.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.configuration.cart.CartExpirationConfig;
import com.sistic.be.configuration.multitenant.Tenant;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.patron.session.OnlineUserSession;

/**
 * Note: It’s recommended to extend the HandlerInterceptorAdapter for the convenient default implementations.
 * @author jianhong
 *
 */

@Component
public class SessionValidatorInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	CartExpirationConfig cartExpirationConfig;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * before the actual handler will be executed
	 * this will run for the number of threads there are, so may make multiple calls
	 * this must return true in order to proceed with the controller
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		invalidatedCartExpiredSession(session);
		return true;
	}
	
	/**
	 * @param session
	 * @return true if session invalidated, else return false
	 */
	public boolean invalidatedCartExpiredSession(HttpSession session) {
		
		Tenant contextTenant = TenantContextHolder.getTenant();
		
		if (contextTenant != null) {
			long ttl = contextTenant.getCartExpiration();
			
			OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
			if (userSession == null) {
				return false;
			}
			
			try {
				OffsetDateTime cartTimer = userSession.getCartTimer();
				if (cartTimer != null) {
//					Long ttlRemaining = cartTimer.plusSeconds(ttl).toEpochSecond() - OffsetDateTime.now().toEpochSecond();
					
					if (OffsetDateTime.now().isAfter(cartTimer.plusSeconds(ttl))) {
						session.invalidate();
						logger.info("Invalidated session");
						return true;
					}
				}
				return false;
			} catch (NullPointerException | DateTimeException e) {
				logger.error("Error in invalidatedCartExpiredSession", e);
			}
		}
		
		return false;
	}
	
//	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		logger.info("INSIDE AFTERCONCURRENT");
//	}
//	
//	public void postHandle(
//			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
//			throws Exception {
//		logger.info("INSIDE POST HANDLE");
//	}
//	
//	@Override
//	public void afterCompletion(
//			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		logger.info("INSIDE AFTER COMPLETION");
//	}
	
}
