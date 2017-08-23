package com.sistic.be.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ValidationUtilService {
	
	private Pattern emailPattern;
	private Matcher matcher;
	
	String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	public ValidationUtilService() {
		emailPattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	
	public boolean getEmailValid(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

}
