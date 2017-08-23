package com.sistic.be.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sistic.be.patron.api.auth.service.AuthorisationService;

@Service
public class AuthUtilService {

	private static final int RETRY = 1;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthorisationService authService;

	@Autowired
	protected RestTemplate restTemplate;

	protected HttpHeaders createAuthorizationHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + authService.getAccessToken());
		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

		return headers;
	}

	/*
	 * JsonNode apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() +
	 * "/cart/confirmseats", HttpMethod.GET, JsonNode.class, body,
	 * uriVariables);
	 */
	public <T> T exchange(String url, HttpMethod method, Class<T> responseType, Object requestBody,
			Object... uriVariables) {
		int count = 0;
		HttpEntity<T> response = null;
		while (count <= RETRY) {
			HttpHeaders headers = createAuthorizationHeader();
			HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers);

			try {
				logger.info("Requesting " + url);
				response = restTemplate.exchange(url, method, entity, responseType, uriVariables);
				logger.info("Responded " + url);
				count = RETRY + 1;
				return response.getBody();
			} catch (HttpClientErrorException ex) {
				// call api via rest template
				if (HttpStatus.UNAUTHORIZED.value() == ex.getRawStatusCode()) {
					logger.error("Error 401, retrying...");	// Ok, can log this
					count++;
				} else {
					throw ex;
				}
			}
		}
		return null;
	}

}
