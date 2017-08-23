package com.sistic.be.configuration.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.app.util.WebUrlUtil;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.session.OnlineUserSession;

@Component
public class LoggingFilters implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		if (session != null) {
			MDC.put("sessionID", session.getId());
			MDC.put("ipAddress", WebUrlUtil.getClientIP());
			
			// get OnlineUserSession
			OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
			
			if (userSession != null && userSession.getPatronLogin() != null) {
				PatronLogin patronLogin = userSession.getPatronLogin();
				String email = patronLogin.getEmail();
				Long patronId = patronLogin.getPatronId();
				String cartGuid = userSession.getCartGuid();
				
				if (email != null && !email.isEmpty()) {
					MDC.put("email", email);
				}
				if (patronId != null) {
					MDC.put("patronId", patronId.toString());
				}
				if (cartGuid != null && !cartGuid.isEmpty()) {
					MDC.put("cartGuid", cartGuid);
				}
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}