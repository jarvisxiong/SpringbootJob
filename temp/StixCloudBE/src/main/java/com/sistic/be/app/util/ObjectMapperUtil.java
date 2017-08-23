package com.sistic.be.app.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.cart.api.model.JsonResponse;

public class ObjectMapperUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ObjectMapperUtil.class);
	
	private static ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
	
	public static JsonResponse getJsonResponse(String jsonResponseBody) {
		JsonResponse jsonResponse;
		try {
			jsonResponse = mapper.readValue(jsonResponseBody, JsonResponse.class);
			return jsonResponse;
		} catch (IOException e) {
			logger.error("Could not convert jsonResponseBody to JsonResponse type");
			return null;
		}
	}

}
