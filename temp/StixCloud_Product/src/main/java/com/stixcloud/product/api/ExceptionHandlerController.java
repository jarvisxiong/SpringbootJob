package com.stixcloud.product.api;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by chongye on 18-Aug-16.
 */
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
  private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse defaultExceptionHandler(Exception ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder()
        .status(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .statusMessage(ExceptionUtils.getStackTrace(ex)).build();
  }

  @Override
  public ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.error(ex.getMessage(), ex);
    JsonResponse body =
        new JsonResponse.Builder()
            .status(Integer.toString(status.value()))
            .statusMessage(ExceptionUtils.getStackTrace(ex)).build();
    return new ResponseEntity<>(body, headers, status);
  }
}
