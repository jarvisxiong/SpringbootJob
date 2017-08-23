package com.sistic.be.app.metrics.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.exception.InvalidPatronSelectionException;
import com.sistic.be.patron.session.OnlineUserSession;

@RestController
public class MonitoringController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private RedisTemplate<String, String> template;
	@Autowired private ObjectMapper mapper;	// used to enable mapping back
	@Autowired private SessionRepository sessionRepo;
	@Autowired private RedisOperationsSessionRepository redisSessionRepo;
	@Autowired private MessageSource messageSource;
	
	@RequestMapping("/uat/viewsession")
	@ResponseBody
	public OnlineUserSession viewSession(HttpSession session) {
		Object sessionAttrib = session.getAttribute("onlineUserSessionInfo");
		if (sessionAttrib != null && (sessionAttrib instanceof OnlineUserSession)) {
			return (OnlineUserSession) sessionAttrib;
		}
		else {
			throw new InvalidPatronSelectionException("User session was not created first. Please reload booking step.");
		}
	}
	
	/**
	 * Test function to view all session
	 * @return
	 */
	@RequestMapping("/uat/viewallsession")
	@ResponseBody
	public Map<String, OnlineUserSession> viewAllSession() {
		Set<String> keys = template.keys("*spring:session:sessions:*");
		Map<String, OnlineUserSession> patrons = new HashMap<String, OnlineUserSession>();	
		for (String key: keys) {		
			String sessionId = key.substring(key.lastIndexOf(":") + 1);
			Object obj = redisSessionRepo.getSession(sessionId);
			ExpiringSession session = null;
			if (obj != null) {
				session = (ExpiringSession) obj;
			}
			if (session != null) {
				Object patronObj = session.getAttribute("onlineUserSessionInfo");
				if (patronObj != null && (patronObj instanceof OnlineUserSession)) {
					patrons.put(sessionId, (OnlineUserSession) patronObj);
				}
			}
		}		
		return patrons;
	}
	
	@RequestMapping("/uat/sessionid")
	@ResponseBody
	public String getSessionId(HttpSession session) {
		logger.debug("session id: " + session.getId());
		return (String) session.getId();
	}
	
	@RequestMapping("/uat/sessionexpiry")
	@ResponseBody
	public String sessionExpiry(HttpSession session) {
		long timer = System.currentTimeMillis() - session.getLastAccessedTime();	//always returns 0 because accessing the session here
		logger.debug("session id: " + timer);
		String message = "session id: " + session.getId() + " expires in " + timer;
		return message;
	}
	
/*	@RequestMapping("/uat/flushdb")
	@ResponseBody
	public List<String> flushDb() {
		Set<String> keys = template.keys("*spring:session:sessions:*");
		List<String> flushCommands = new ArrayList<String>();
		for (String key: keys) {
			String sessionId = key.substring(key.lastIndexOf(":") + 1);
			redisSessionRepo.delete(sessionId);
			flushCommands.add(sessionId + " was deleted.");
		}
		flushCommands.add("The redis database has been flushed.");
		return flushCommands;
	}*/
	
	@RequestMapping("/uat/setinactive")
	@ResponseBody
	public String setInactive(HttpSession session) {
		session.setMaxInactiveInterval(10);
		return "max inactive set to 10 seconds";
	}
	
	@RequestMapping("/test/generate/uuid")
	public String generateUuid(HttpServletResponse response, HttpSession session) throws Exception {
		
		 String uuid = UUID.randomUUID().toString();
		 
		 return uuid;
	}
	
	/**
	 * Test endpoint for exploring what Locale.getISOCountries is able to provide
	 * @param session
	 * @param locale
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping("/test/countrylist")
	@ResponseBody
	public String showCountryList(HttpSession session, Locale locale) throws JsonProcessingException {
		
		String[] locales = Locale.getISOCountries();
		String json = mapper.writeValueAsString(locales);
		logger.info(json);
		
//		List<String> localeList = new ArrayList<String>();

		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			
			logger.info("language: " + obj.getLanguage() + "\n"
							+ "country: " + obj.getCountry() + "\n"
							+ "variant: " + obj.getVariant() + "\n"
							+ "display country: " + obj.getDisplayCountry() + "\n"
							+ "display language: " + obj.getDisplayLanguage() + "\n"
							+ "display name: " + obj.getDisplayName() + "\n"
							+ "display script: " + obj.getDisplayScript() + "\n"
							+ "display variant: " + obj.getDisplayVariant() + "\n"
							+ "ISO3Country: " + obj.getISO3Country() + "\n"
							+ "ISO3Language: " + obj.getISO3Language() + "\n"
							+ "get script: " + obj.getScript() + "\n"
							+ "toLanguageTag: " + obj.toLanguageTag());
			
			break;	// just for one sample
		}
		
		return json;
	}

}
