package com.sistic.be.recaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
@ConfigurationProperties(prefix = "service.recaptcha")
public class RecaptchaService {

	String url = "";
	String secret = "";

	@Autowired
	protected RestTemplate restTemplate;
	
	public Boolean verifyCaptcha(String g_recaptcha_response) {
		
		MultiValueMap<String, String> request = new RecaptchaRequest(secret, g_recaptcha_response).toMultiValueMap();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity entity = new HttpEntity(request, headers);
		
		HttpEntity<JsonNode> response = restTemplate.exchange(url, HttpMethod.POST, entity, JsonNode.class);
		JsonNode apiResponse = response.getBody();
		Boolean success = apiResponse.get("success").asBoolean();
		
		return success;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}