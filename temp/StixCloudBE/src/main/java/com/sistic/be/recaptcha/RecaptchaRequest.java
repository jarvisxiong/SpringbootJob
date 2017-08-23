package com.sistic.be.recaptcha;

import java.io.Serializable;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RecaptchaRequest implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5856293817087778207L;
	
	private String secret;
	private String response;
	
	public RecaptchaRequest() {
		super();
	}
	
	public RecaptchaRequest(String secret, String response) {
		super();
		this.secret = secret;
		this.response = response;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	public MultiValueMap<String, String> toMultiValueMap() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", secret);
		map.add("response", response);
		
		return map;
	}

	@Override
	public String toString() {
		return "RecaptchaRequest [secret=" + secret + ", response=" + response + "]";
	}
}
