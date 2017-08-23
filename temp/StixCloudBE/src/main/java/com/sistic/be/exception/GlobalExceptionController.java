package com.sistic.be.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.sdk.core.exceptions.SDKErrorResponseException;
import com.mastercard.sdk.core.models.Error;
import com.mastercard.sdk.core.models.Errors;
import com.sistic.be.app.test.exception.CustomTestException;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.StatusResponse;
import com.sistic.be.configuration.multitenant.Tenant;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.patron.api.auth.service.AuthenticationException;


/**
 * This is the Global exception handler which handles exceptions unhandled by Controller ExceptionHandler
 * @author jianhong
 *
 */
@ControllerAdvice
public class GlobalExceptionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ObjectMapper mapper;
	

	@ExceptionHandler(InvalidSessionException.class)
	public ResponseEntity<StatusResponse> invalidSessionError (InvalidSessionException e) {
		e.printStackTrace();
		return new ResponseEntity<StatusResponse>(new StatusResponse("Session is invalid or has been invalidated"), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ErrorJsonResponseException.class)
	public ResponseEntity<JsonResponse> errorJsonReponseError (ErrorJsonResponseException e) {
		return new ResponseEntity<JsonResponse>(e.getJsonErrorResponse(), e.getHttpStatus());
	}
	
    @ExceptionHandler(InvalidPatronSelectionException.class)
    public ResponseEntity<JsonResponse> invalidPatronSelectionException(InvalidPatronSelectionException e) {
        return new ResponseEntity<JsonResponse>(new JsonResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(InvalidPatronLoginException.class)
	public ModelAndView invalidPatronLoginError (InvalidPatronLoginException e) throws UnsupportedEncodingException {
		// TODO: review
		String param = "";
		String gotoUrl = e.getGotoUrl().toString();
		if (!gotoUrl.isEmpty()) {
			String urlEncoded = URLEncoder.encode(gotoUrl, "UTF-8");
			param = "?gotoUrl="+urlEncoded;
		}
		return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/loginredirect"+param);
	}

	@ExceptionHandler(RequireLoginException.class)
	public ResponseEntity<JsonResponse> requireLoginException (RequireLoginException e) {
		return new ResponseEntity<JsonResponse>(new JsonResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<StatusResponse> invalidStateError (IllegalStateException e) {
		return new ResponseEntity<StatusResponse>(new StatusResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<StatusResponse> internalServerError (InternalServerErrorException e) {
		return new ResponseEntity<StatusResponse>(new StatusResponse("Server is experiencing internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<JsonNode> restClientError (HttpClientErrorException e) throws JsonParseException, JsonMappingException, IOException {
		logger.error("Encountered HttpClientErrorException: " + e.getResponseBodyAsString());
		JsonNode errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonNode.class);
		return new ResponseEntity<JsonNode>(errorResponse, e.getStatusCode());
	}
	
	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<JsonNode> restServerError (HttpServerErrorException e) throws JsonParseException, JsonMappingException, IOException {
		logger.error("Encountered HttpServerErrorException: " + e.getResponseBodyAsString());
		JsonNode errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonNode.class);
		return new ResponseEntity<JsonNode>(errorResponse, e.getStatusCode());
	}
	
	@ExceptionHandler(HttpStatusCodeException.class)
	public ResponseEntity<JsonNode> restStatusError (HttpStatusCodeException e) throws JsonParseException, JsonMappingException, IOException {
		logger.error("Encountered HttpStatusCodeException: " + e.getResponseBodyAsString());
		JsonNode errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonNode.class);
		return new ResponseEntity<JsonNode>(errorResponse, e.getStatusCode());
	}

	@ExceptionHandler(ShoppingCartModelException.class)
	public ResponseEntity<StatusResponse> shoppingCartModelError (ShoppingCartModelException e) {
		e.printStackTrace();
		return new ResponseEntity<StatusResponse>(new StatusResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<StatusResponse> fileNotFoundError (FileNotFoundException e) {
		e.printStackTrace();
		return new ResponseEntity<StatusResponse>(new StatusResponse(e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<JsonResponse> authenticationError (AuthenticationException e) {
		return new ResponseEntity<JsonResponse>(new JsonResponse(e.getHttpCode(), null, e.getOAuth2ErrorCode()), HttpStatus.valueOf(e.getHttpCode()));
	}

	@ExceptionHandler(SDKErrorResponseException.class)
	public ResponseEntity<StatusResponse> masterpassError(SDKErrorResponseException e) {
		e.printStackTrace();
		String errorMessage = "";

		Errors errors = (Errors) e.getErrorResponse();

		List<Error> errorList = errors.getError();

		for (Error error : errorList) {
			// developer logic to get the error details from Error object.
			errorMessage = "Source:" + error.getSource() + "\n" + "ReasonCode:" + error.getReasonCode()
			+ "\n" + "Description:" + error.getDescription() + "\n" + "Recoverable:"
			+ error.getRecoverable();
		}
		return new ResponseEntity<StatusResponse>(new StatusResponse(errorMessage),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomTestException.class)
	public ResponseEntity<JsonResponse> runtimeExceptionError (CustomTestException e) {

		Integer httpStatus = e.getHttpStatus();
		Integer status = e.getStatus();
		String statusMessage = e.getMessage();

		JsonResponse jsonResponse = new JsonResponse(httpStatus, status, statusMessage);

		try {
			return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.valueOf(httpStatus));
		}

		catch (IllegalArgumentException illegalEx) {
			logger.error("CustomTestException invalid httpStatus: " + illegalEx.getMessage());

			jsonResponse.setStatusMessage("Invalid httpStatus was used to create CustomTestException");
			jsonResponse.setHttpStatus(400);

			return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView requestHandlingNoHandlerFound(NoHandlerFoundException e) {
		Tenant contextTenant = TenantContextHolder.getTenant();
		if (contextTenant != null) {
			String contextTenantCode = TenantContextHolder.getTenantCode();
			logger.error("contextTenantCode: " + contextTenantCode);
			String templateCode = contextTenant.getTemplateCode();
			logger.error("templateCode: " + templateCode);
			if (templateCode != null && !templateCode.isEmpty()) {
				ModelAndView modelAndView = new ModelAndView(templateCode+"/view/error");
				// Need to add in the tenant related modelattributes, not accessible through controlleradvice
				modelAndView.addObject("tenantContext", TenantContextHolder.getTenantCode());
				modelAndView.addObject("tenantTemplateCode", TenantContextHolder.getTenant().getTemplateCode());
				modelAndView.addObject("error", "The page you are looking for does not exist.");
				return modelAndView;
			}
			
		}
		// Return here means could not get templateCode from TenantContextHolder.getTenant()
		return new ModelAndView("error");	// The generic non whitelabel error page
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StatusResponse> runtimeExceptionError (RuntimeException e) {
		e.printStackTrace();
		return new ResponseEntity<StatusResponse>(new StatusResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
