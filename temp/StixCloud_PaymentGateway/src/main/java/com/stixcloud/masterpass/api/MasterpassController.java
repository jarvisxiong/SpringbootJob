package com.stixcloud.masterpass.api;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.exception.DataNotFoundException;
import com.stixcloud.masterpass.domain.MasterpassConfiguration;
import com.stixcloud.masterpass.service.IMasterpassConfigurationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/{tenant}/payment/masterpass")
public class MasterpassController {

  private static final Logger LOGGER = LogManager.getLogger(MasterpassController.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IMasterpassConfigurationService masterpassConfigurationService;

  @RequestMapping(value = "/config", method = RequestMethod.GET)
  public MasterpassConfigurationResponse getMasterpassConfig(Locale locale) throws Exception {

    MasterpassConfiguration config = null;
    try {
      config = masterpassConfigurationService.getMasterpassConfiguration(
          TenantContextHolder.getTenant().getName());
    } catch (Exception e) {
      throw new Exception(messageSource.getMessage("payment.config.error", null, locale));
    }

    if (config == null) {
      throw new DataNotFoundException(
          messageSource.getMessage("payment.config.not.found", null, locale));
    }

    MasterpassConfigurationResponse response = new MasterpassConfigurationResponse(
        config.getConsumerKey(), config.getMerchantIdentifierId(), config.getCertificateFileName(),
        config.getKeystorePassword(), config.getKeystoreAlias(), config.getCheckoutCallbackUrl(),
        config.getPairingCallbackUrl());

    LOGGER.debug("Get Masterpass Configuration done -> " + config.toString());

    return response;
  }

  @ExceptionHandler(DataNotFoundException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonResponse handleDataNotFoundException(DataNotFoundException e) {
    LOGGER.error(e.getMessage(), e);
    JsonResponse response = new JsonResponse(HttpStatus.BAD_REQUEST.toString());
    response.setStatusMessage(e.getMessage());

    return response;
  }
}
