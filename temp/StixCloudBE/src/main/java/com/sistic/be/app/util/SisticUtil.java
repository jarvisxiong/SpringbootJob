package com.sistic.be.app.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sistic.be.exception.InvalidSessionException;
import com.sistic.be.patron.session.OnlineUserSession;

public final class SisticUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SisticUtil.class);
	
	@Deprecated
	public static OnlineUserSession getOnlineUserSession(HttpSession session, String name) {
		Object sessionObject = session.getAttribute(name);
		if (sessionObject != null && (sessionObject instanceof OnlineUserSession)) {
			logger.debug("Getting existing OnlineUserSession for sessionId: " + session.getId());
			return (OnlineUserSession) sessionObject;
		}
		logger.info("New online user was created for sessionId: " + session.getId());
		return new OnlineUserSession();
	}
	
	/**
	 * 
	 * @param session
	 * @param name
	 * @param userSession
	 * @throws InvalidSessionException
	 */
	@Deprecated
	public static void setOnlineUserSession(HttpSession session, String name, OnlineUserSession userSession) throws InvalidSessionException {
		try {
			session.setAttribute(name, userSession);
		} catch (IllegalStateException e) {
			logger.error("Failed to set session attribute for name: " + name + " and OnlineUserSession: " + userSession.toString());
			throw new InvalidSessionException(e);
		}
	}
	
	@Deprecated
	public static String getURLWithContextPath(HttpServletRequest request) {
	   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
}
