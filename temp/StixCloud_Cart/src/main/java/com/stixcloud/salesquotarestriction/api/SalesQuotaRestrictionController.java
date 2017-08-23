package com.stixcloud.salesquotarestriction.api;

import com.stixcloud.salesquotarestriction.service.SalesQuotaRestrictionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mango on 14/3/2017.
 */
@RestController
@CrossOrigin
public class SalesQuotaRestrictionController {

    private static final Logger LOGGER = LogManager.getLogger(SalesQuotaRestrictionController.class);

    @Autowired
    private SalesQuotaRestrictionService salesQuotaRestrictionService;

    @RequestMapping(value = "/{tenant}/cart/validation/sharedquota", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity checkSalesQuota(@RequestBody @Valid ValidateRequest body) {
        if (LOGGER.isDebugEnabled())
            LOGGER.debug(String.format("invoke checkSharedQuota(body=%s)\n", body));

        return ResponseEntity.ok(salesQuotaRestrictionService.checkSharedQuota(body));
    }

    @RequestMapping(value = "/{tenant}/cart/validation/quantityrestriction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity checkQuantityRestriction(@RequestBody @Valid ValidateRequest body) {
        if (LOGGER.isDebugEnabled())
            LOGGER.debug(String.format("invoke checkQuantityRestriction(body=%s)\n", body));

        return ResponseEntity.ok(salesQuotaRestrictionService.checkQuantityRestriction(body));
    }

    /*
    // Unmapped url path
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    @ResponseBody
    public void noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        LOGGER.error(e);
    }

    // Invalid request method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public void httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        LOGGER.error(e);
    }
    */

    // Invalid content type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public void httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        //LOGGER.error(e);
    }

    // Fail to parse input
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        //LOGGER.error(e);
    }

    // Input parsed successfully but fail validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public void methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        //LOGGER.error(e);
    }

    // Other exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void defaultExceptionHandler(Exception e) {
        //LOGGER.error(e);
    }
}
