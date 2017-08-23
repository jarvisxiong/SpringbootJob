package com.stixcloud.paymentgateway.api;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by chongye on 18-Aug-16.
 */
@ControllerAdvice
public class ExceptionHandlerController {
  private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);

  @Autowired
  MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonResponse handleValidationException(MethodArgumentNotValidException ex) {
    logger.error(ex.getMessage(), ex);
    Locale currentLocale = LocaleContextHolder.getLocale();
    List<String> errorList =
        ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> Arrays.asList(fe.getCodes()))
            .flatMap(List::stream)
            .filter(code -> {
              String message = null;
              try {
                message = messageSource.getMessage(code, null, currentLocale);
              } catch (NoSuchMessageException e) {

              }
              return StringUtils.isNotBlank(message);
            })
            .collect(Collectors.toList());

    String errorMessages = null;
    if (!errorList.isEmpty()) {
      errorMessages =
          errorList.stream().map(code -> messageSource.getMessage(code, null, currentLocale))
              .collect(Collectors.joining(", "));
    } else {
      errorMessages = ex.toString();
    }

    return new JsonResponse.Builder()
        .httpStatus(Integer.toString(HttpStatus.BAD_REQUEST.value()))
        .statusMessage(errorMessages)
        .build();
  }

  @ExceptionHandler({NoHandlerFoundException.class, Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse defaultExceptionHandler(Exception ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder()
        .httpStatus(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
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

  /*@ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public JsonResponse handleNoHandlerFoundException(NoHandlerFoundException ex) {
    logger.error(ex.getMessage(), ex);
    return new JsonResponse.Builder()
        .httpStatus(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()))
        .statusMessage(ex.getMessage())
        .build();
  }*/
}
