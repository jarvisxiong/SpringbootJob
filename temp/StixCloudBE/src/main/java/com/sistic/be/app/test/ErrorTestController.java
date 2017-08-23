package com.sistic.be.app.test;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sistic.be.app.test.exception.CustomTestException;
import com.sistic.be.cart.api.model.JsonResponse;

@RestController
@RequestMapping(value = "/{tenant}")
public class ErrorTestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/test/error/create")
	@ResponseBody
	public JsonResponse patronProductSelection (HttpServletResponse response, Locale locale,
			@RequestBody(required=false) JsonResponse json) throws CustomTestException {
		
		if (json == null) {
			json = new JsonResponse(400, null, "HttpStatus and StatusMessage should be set for creating test exception");
		}

		Integer httpStatus = json.getHttpStatus();
		String statusMessage = json.getStatusMessage();
		
		throw new CustomTestException(statusMessage, httpStatus);
		
	}
}
