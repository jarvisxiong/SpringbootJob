package com.stixcloud.cart.api;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.stixcloud.cart.CartException;
import com.stixcloud.cart.PreCommitCartException;

import feign.FeignException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chongye on 18-Aug-16.
 */
@ControllerAdvice
public class ExceptionHandlerController {
  private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse handleValidationException(MethodArgumentNotValidException ex) {
    logger.error(ex.getMessage(), ex);
    String errorMessages =
        ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> Arrays.asList(fe.getCodes()))
            .flatMap(List::stream)
            .filter(code -> {
              String message = null;
              try {
                message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
              } catch (NoSuchMessageException e) {

              }
              return StringUtils.isNotBlank(message);
            })
            .collect(Collectors.toList()).stream()
            .map(code -> messageSource.getMessage(code, null, LocaleContextHolder.getLocale()))
            .collect(Collectors.joining(", "));

    return new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
        .statusMessage(errorMessages)
        .build();

  }

  @ExceptionHandler(CartException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse cartExceptionHandler(CartException ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
        .statusMessage(ex.getMessage())
        .build();
  }

  @ExceptionHandler(PreCommitCartException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public JsonResponse preCommitExceptionHandler(CartException ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder().httpStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString())
        .statusMessage(ex.getMessage())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse defaultExceptionHandler(Exception ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        .statusMessage(ExceptionUtils.getStackTrace(ex))
        .build();
  }
  
  @ExceptionHandler(HystrixRuntimeException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public JsonResponse hystrixRuntimeException(HystrixRuntimeException ex) {
    if (ex.getCause() instanceof FeignException) {
      FeignException fe = (FeignException) ex.getCause();
      if(fe.status() == HttpStatus.UNAUTHORIZED.value()){
        logger.error(fe.getMessage());
        return new JsonResponse.Builder().httpStatus(HttpStatus.UNAUTHORIZED.toString())
            .statusMessage("token_expired")
            .build();
      }
    }
    logger.error(ex.getMessage(), ex);
    throw ex;
  }
  
  
  @ExceptionHandler(FeignException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public JsonResponse feignException(FeignException ex) {
    logger.error("FeignException: " + ex.status() + ": " + ex.getMessage());
    if(ex.status() == HttpStatus.UNAUTHORIZED.value()){
      
      return new JsonResponse.Builder().httpStatus(HttpStatus.UNAUTHORIZED.toString())
          .statusMessage("token_expired")
          .build();
    }
    throw ex;
  }
  
  @ExceptionHandler(InvalidTokenException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public JsonResponse invalidTokenException(InvalidTokenException ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder().httpStatus(HttpStatus.UNAUTHORIZED.toString())
        .statusMessage("token_expired")
        .build();
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse handleNoHandlerFoundException(NoHandlerFoundException ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        .statusMessage(ExceptionUtils.getStackTrace(ex))
        .build();
  }

  @ExceptionHandler(RestClientException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse restClientException(Exception ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder()
        .httpStatus(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .status("status.payment.timeout")
        .statusMessage(ExceptionUtils.getStackTrace(ex))
        .build();
  }
}
