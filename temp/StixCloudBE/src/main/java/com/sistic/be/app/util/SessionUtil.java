package com.sistic.be.app.util;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sistic.be.exception.InvalidSessionException;
import com.sistic.be.patron.session.OnlineUserSession;

public class SessionUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);
	
	public static final String KEY_ONLINE_USER_SESSION = "onlineUserSessionInfo";
	
	/**
	 * The new getOnlineUserSession util
	 * Returns null if there is no OnlineUserSession in the session object
	 * @param session
	 * @param name
	 * @return
	 */
	public static OnlineUserSession getOnlineUserSession(HttpSession session, String name) {
		Object sessionObject = session.getAttribute(name);
		if (sessionObject != null && (sessionObject instanceof OnlineUserSession)) {
			return (OnlineUserSession) sessionObject;
		}
		return null;
	}
	
	/**
	 * 
	 * @param session
	 * @param name
	 * @param userSession
	 * @throws InvalidSessionException
	 */
	public static void setOnlineUserSession(HttpSession session, String name, OnlineUserSession userSession) throws InvalidSessionException {
		try {
			session.setAttribute(name, userSession);
		} catch (IllegalStateException e) {
			logger.error("Failed to set session attribute for name: " + name + " and OnlineUserSession: " + userSession.toString());
			throw new InvalidSessionException(e);
		}
	}
	
	
	public static void setOnlineUserSession(HttpSession session, OnlineUserSession userSession) throws InvalidSessionException {
      try {
          session.setAttribute(KEY_ONLINE_USER_SESSION, userSession);
      } catch (IllegalStateException e) {
          logger.error("Failed to set session attribute for name: " + KEY_ONLINE_USER_SESSION + " and OnlineUserSession: " + userSession.toString());
          throw new InvalidSessionException(e);
      }
    }
	
	public static OnlineUserSession getOnlineUserSession(HttpSession session) {
      Object sessionObject = session.getAttribute(KEY_ONLINE_USER_SESSION);
      if (sessionObject != null && (sessionObject instanceof OnlineUserSession)) {
          return (OnlineUserSession) sessionObject;
      }
      return null;
    }
	
	public static OnlineUserSession createOnlineUser() {
		return new OnlineUserSession();
	}

}
