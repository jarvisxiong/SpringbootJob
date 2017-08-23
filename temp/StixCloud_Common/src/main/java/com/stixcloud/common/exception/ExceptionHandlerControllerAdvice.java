package com.stixcloud.common.exception;

import java.time.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
  private static final Logger logger = LogManager.getLogger(ExceptionHandlerControllerAdvice.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
    logger.error(ex.getMessage(), ex);
    JsonResponse resp = generateResponseBody(ex, request);
    resp.setHttpStatus(HttpStatus.BAD_REQUEST.toString());
    return resp;

  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
    logger.error(ex.getMessage(), ex);
    JsonResponse resp = generateResponseBody(ex, request);
    resp.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    return resp;
  }
  
  @ExceptionHandler(SisticApiException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse applicationExceptionHandler(SisticApiException ex, HttpServletRequest request) {
    logger.error(ex.getMessage(), ex);
    JsonResponse resp = generateResponseBody(ex, request);
    resp.setHttpStatus(HttpStatus.BAD_REQUEST.toString());
    resp.setErrorCode(ex.getErrorCode());
    resp.setStatusMessage(ex.getStatusMessage());
    return resp;
  }
  
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse defaultExceptionHandler(Exception ex, HttpServletRequest request) {
    logger.error(ex.getMessage(), ex);
    JsonResponse resp = generateResponseBody(ex, request);
    resp.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
    return resp;
  }

  private JsonResponse generateResponseBody(Exception ex, HttpServletRequest request){
    JsonResponse response = new JsonResponse();
    response.setUrl(request.getRequestURL().toString());
    response.setExceptionName(ex.getClass().getName());  
    response.setStatusMessage(ExceptionUtils.getRootCauseMessage(ex));
    response.setErrorTime(OffsetDateTime.now());
    return response;
  }
}
