package com.sistic.be.patron.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.patron.api.service.ApiPatronService;
import com.sistic.be.patron.controller.token.PatronToken;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.service.LoginService;
import com.sistic.be.patron.session.OnlineUserSession;
import com.stixcloud.policyagent.repo.SessionTokenRepositoryImpl;

@Controller
@ComponentScan(basePackageClasses = SessionTokenRepositoryImpl.class)
@RequestMapping(value = "/{tenant}")
public class LoginController {
	
	private Logger logger = LogManager.getLogger(LoginController.class);
	
    @Autowired
	SessionTokenRepositoryImpl sessionToken;
    @Autowired
    LoginService loginService;
	
    @Autowired
    private ApiPatronService patronService;
    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public PatronToken patronLogin(HttpServletResponse response, HttpSession session, HttpServletRequest request,
    		@RequestBody JsonNode json) throws JSONException {

		String userName = json.get("username").asText();
		String password = json.get("password").asText();
        
        PatronLogin patronLogin = patronService.login(userName, password);
    	logger.info(patronLogin);
    	
        PatronToken patronToken = new PatronToken(
        								patronLogin.getFirstName(),
									    patronLogin.getLastName(),
									    patronLogin.getPatronName(),
									    patronLogin.getRefreshToken());
        
        OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
        if (userSession == null) {
        	userSession = new OnlineUserSession();
        }
        userSession.setPatronLogin(patronLogin);
        SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
		
        return patronToken;	// return to AJAX call made by reactJS
    }
	
	@RequestMapping(value = "/logout")
    public String patronLogout(HttpServletResponse response, HttpSession session, HttpServletRequest request) {
        
        loginService.logout(session);
		
        String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
        return templateCode + "/view/logout";
    }

}
