package com.sistic.be.app;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.configuration.multitenant.MultiTenantProperties;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.patron.api.auth.service.AuthorisationService;
import com.sistic.be.patron.session.OnlineUserSession;
import com.stixcloud.policyagent.repo.SessionTokenRepositoryImpl;

@ComponentScan(basePackageClasses = SessionTokenRepositoryImpl.class)
@Controller
@RequestMapping(value = "/{tenant}")
public class AppController extends HttpServlet {


	private static final long serialVersionUID = -2958512202991346986L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MultiTenantProperties multiTenantProperties;
	
	@Autowired
	SessionTokenRepositoryImpl sessionToken;

	@Autowired
	private AuthorisationService authService;


	@RequestMapping("/booking/{content}")
	public String bookingcontent(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		/**
		 * Add condition for if already logged in password grant type add to model "token"
		 */
		/**
		 * Create a new onlineUserSession if not exist for use in booking flow
		 */
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession == null) {
			userSession = new OnlineUserSession();
			SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
		}
		
	    response.addCookie(authService.createTokenCookie());

	    String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		return templateCode + "/view/index";
	}
	
	/*
	 * This method use to retrieve membership sales product in Booking Flow 
	 */
	@RequestMapping("/membership/{content}")
	public String membershipcontent(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		/**
		 * Create a new onlineUserSession if not exist for use in booking flow
		 */
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession == null) {
			userSession = new OnlineUserSession();
			SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
		}
		
	    response.addCookie(authService.createTokenCookie());
	
	    String templateCode = TenantContextHolder.getTenant().getTemplateCode();
	    
	    return templateCode + "/view/membership/index";
	}

	@RequestMapping("/loginredirect")
	public String loginRedirect(Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		/**
		 * Add condition for if already logged in password grant type add to model "token"
		 */
		
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
				
		if (userSession != null && userSession.getPatronLogin() != null) {
			// already login, get the gotoUrl from the login redirect request
			String gotoUrl = request.getParameter("gotoUrl");
			gotoUrl = (gotoUrl == null)? "/" + TenantContextHolder.getTenantCode() + "/patron/management" :  gotoUrl;
			String href = "redirect:"+gotoUrl;
			return href;
		}
		
		response.addCookie(authService.createTokenCookie());
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		return templateCode + "/view/login-redirect";
	}
}
