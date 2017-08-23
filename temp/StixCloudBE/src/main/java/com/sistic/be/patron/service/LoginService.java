package com.sistic.be.patron.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public void logout(HttpSession session) {
		session.invalidate();
		return;
	}

}
