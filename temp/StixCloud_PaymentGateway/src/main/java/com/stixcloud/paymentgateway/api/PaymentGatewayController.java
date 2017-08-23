package com.stixcloud.paymentgateway.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.paymentgateway.IPaymentGatewayConfigService;
import com.stixcloud.paymentgateway.IPaymentGatewayService;
import com.stixcloud.paymentgateway.IQueueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/{tenant}")
public class PaymentGatewayController {
  private static final Logger LOGGER = LogManager.getLogger(PaymentGatewayController.class);

  @Autowired
  private IQueueService queueService;
  @Autowired
  private IPaymentGatewayConfigService iPaymentGatewayConfigService;
  @Autowired
  private IPaymentGatewayService paymentGatewayService;
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping(value = "/payment/migs", method = RequestMethod.POST)
  @ResponseBody
  public MigsResponse migsPayment(@RequestBody MigsRequest migsRequest) {
    MultiValueMap<String, String> map = new MigsRequest.PostDataBuilder().build(migsRequest, true);
    LOGGER.info("migsPayment api called ");
   // LOGGER.debug("migsRequest " + migsRequest);
    Instant start = Instant.now();
    LOGGER.info(
        "migs payment gateway start time for transaction " + migsRequest.getVpcOrderInfo() + ":"
            + start.toString());
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    //  headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
    HttpEntity<MultiValueMap<String, String>>
        request =
        new HttpEntity<MultiValueMap<String, String>>(map, headers);
    String result = restTemplate.postForObject(migsRequest.getVpcUrl(), request, String.class);
    LOGGER.info("migsResponse " + result);
    LOGGER.info(
        "migs payment gateway for transaction " + migsRequest.getVpcOrderInfo() + ":" + Duration
            .between(start, Instant.now()));
    //  LOGGER.debug(result);
    //mask creditcard
    String cc = map.get("vpc_CardNum").get(0);
    String ccMask = SecurityUtils.maskPartial(cc);
    map.replace("vpc_CardNum", Arrays.asList(ccMask));
    String reqStr = map.toSingleValueMap().entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .reduce((a, b) -> String.join("&", a, b)).get();
    MigsResponse response = new MigsResponse.Builder(result, reqStr).build();
    LOGGER.debug("Migs Json Response " + response);
    LOGGER.info("migsPayment api ended ");
    return response;
  }

  @RequestMapping(value = "/payment/telemoney", method = RequestMethod.POST)
  @ResponseBody
  public TelemoneyResponse telemoneyPayment(@RequestBody TelemoneyRequest telemoneyRequest) {
	  LOGGER.info("telemoneyPayment api called ");
    Instant start = Instant.now();
    LOGGER.info(
        "telemoney payment gateway for transaction " + telemoneyRequest.getRef() + ":" + start
            .toString());
    telemoneyRequest.setRecurrentid(TelemoneyConstants.RECURRENT_INIT);
    MultiValueMap<String, String>
        reqMap =
        new TelemoneyRequest.Builder.PostDataBuilder(telemoneyRequest, true).buildQuery();
    String
        responseStr =
        restTemplate.postForObject(telemoneyRequest.getPostURL(), reqMap, String.class);
    //remove the & that sometimes appear at the end of the response string
    LOGGER.info(
        "telemoney payment gateway start time for transaction " + telemoneyRequest.getRef() + ":"
            + Duration.between(start, Instant.now()));
    responseStr =
        (responseStr.endsWith("&") ? responseStr.substring(0, responseStr.length() - 1)
            : responseStr);
    LOGGER.info("response telemoney: " + responseStr);

    Map<String, String> responseMap = new HashMap<>();
    for (String keyValuePair : responseStr.split("&")) {
      String[] kvArr = keyValuePair.split("=");
      if (kvArr.length == 2) {
        responseMap.put(kvArr[0], kvArr[1]);
      }
    }
    LOGGER.debug("responseMap " + responseMap);

    ObjectMapper objectMapper = new ObjectMapper();
    TelemoneyResponse
        telemoneyResponse =
        objectMapper.convertValue(responseMap, TelemoneyResponse.class);

    //mask creditcard
    String ccMask = SecurityUtils.maskPartial(reqMap.get("ccnum").get(0));
    reqMap.replace("ccnum", Arrays.asList(ccMask));
    String reqStr = reqMap.toSingleValueMap().entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .reduce((a, b) -> String.join("&", a, b)).get();
    telemoneyResponse =
        new TelemoneyResponse.Builder(telemoneyResponse).reqData(reqStr).respData(responseStr)
            .build();

    LOGGER.debug("telemoneyResponse " + telemoneyResponse);
    LOGGER.info("telemoneyPayment api ended ");
    return telemoneyResponse;
  }

  @RequestMapping(value = "/payment/cup", method = RequestMethod.POST)
  @ResponseBody
  public CupResponse cupPayment(@RequestBody CupRequest cupRequest) {
	  LOGGER.info("cupPayment api called ");
    MultiValueMap<String, String>
        reqMap =
        new CupRequest.PostDataBuilder(cupRequest, true).buildQuery();
    
    Instant start = Instant.now();
    LOGGER.info(
        "cup payment gateway start time for transaction " + cupRequest.getOrderNumber() + ":"
            + start.toString());
    String responseStr = restTemplate.postForObject(cupRequest.getPostUrl(), reqMap, String.class);
    //remove the & that sometimes appear at the end of the response string
    LOGGER.info(
        "cup payment gateway for transaction " + cupRequest.getOrderNumber() + ":" + Duration
            .between(start, Instant.now()));

    responseStr =
        (responseStr.endsWith("&") ? responseStr.substring(0, responseStr.length() - 1)
            : responseStr);
    LOGGER.info("response cup: " + responseStr);

    Map<String, String> responseMap = new HashMap<>();
    for (String keyValuePair : responseStr.split("&")) {
      String[] kvArr = keyValuePair.split("=");
      if (kvArr.length == 2) {
        responseMap.put(kvArr[0], kvArr[1]);
      }
    }
    LOGGER.debug("responseMap " + responseMap);

    ObjectMapper objectMapper = new ObjectMapper();
    CupResponse cupResponse = objectMapper.convertValue(responseMap, CupResponse.class);

    String reqStr = reqMap.toSingleValueMap().entrySet().stream()
        .map(entry -> entry.getKey() + "=" + entry.getValue())
        .reduce((a, b) -> String.join("&", a, b)).get();
    cupResponse =
        new CupResponse.Builder(cupResponse).reqData(reqStr).respData(responseStr).build();
    LOGGER.debug("cupResponse " + cupResponse);

    boolean isResponseValid = paymentGatewayService.isResponseValid(cupResponse);
    cupResponse = new CupResponse.Builder(cupResponse).responseValid(isResponseValid).build();
    LOGGER.info("cupPayment api ended ");
    return cupResponse;
  }

  @RequestMapping(value = "/payment/queue", method = RequestMethod.POST)
  @ResponseBody
  public Object sendMessage(@Valid @RequestBody PaymentGatewayRequest paymentGatewayRequest) {
	  LOGGER.info("sendMessage api called with paymentGatewayRequest "+paymentGatewayRequest);
    JsonResponse.Builder builder = new JsonResponse.Builder();
    String uuid;
    try {
      uuid = queueService.sendMessage(paymentGatewayRequest);
      LOGGER.info("sendMessage uuid "+uuid);
      builder.statusMessage("UUID : " + uuid);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(builder.httpStatus(HttpStatus.BAD_REQUEST.toString()).statusMessage(e.getMessage())
              .build());
    }
//    return builder.httpStatus(HttpStatus.OK.toString()).build();
    LOGGER.info("sendMessage api ended ");
    return uuid;
  }

  @RequestMapping(value = "/payment/queue/{messageId}", method = RequestMethod.GET)
  @ResponseBody
  public Object getMessage(@PathVariable String messageId) {
    LOGGER.info("retreiving message " + messageId);
    PaymentGatewayResponse response = queueService.getMessage(messageId);
    if (response != null) {
      return new JsonResponse.Builder()
          .httpStatus(HttpStatus.OK.toString())
          .proceed(true)
          .statusMessage(messageSource
              .getMessage("queue.status.proceed", null, LocaleContextHolder.getLocale()))
          .build();
    } else {
      return new JsonResponse.Builder()
          .httpStatus(HttpStatus.OK.toString())
          .proceed(false)
          .statusMessage(
              messageSource.getMessage("queue.status.wait", null, LocaleContextHolder.getLocale()))
          .build();
    }
  }

  @RequestMapping(value = "/payment/queue/{messageId}", method = RequestMethod.DELETE)
  @ResponseBody
  public void removeMessage(@PathVariable String messageId) {
    LOGGER.info("removing message " + messageId);
    queueService.removeMessage(messageId);
    LOGGER.info("removeMessage successful");
  }

  @RequestMapping(value = "/payment/config/{paymentMethodCode}", method = RequestMethod.GET)
  @ResponseBody
  public Object getPaymentGatewayConfiguration(@PathVariable String paymentMethodCode) {
	  LOGGER.info("getPaymentGatewayConfiguration called for paymentMethodCode "+paymentMethodCode);
    return iPaymentGatewayConfigService
        .getPaymentConfigDetailsJson(paymentMethodCode,
            TenantContextHolder.getTenant().getProfileInfoId());
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonResponse handleMissingParameterException(MissingServletRequestParameterException e) {
    LOGGER.error(e.getMessage(), e);
    JsonResponse.Builder builder = new JsonResponse.Builder();
    builder.httpStatus(HttpStatus.BAD_REQUEST.toString());
    switch (e.getParameterName()) {
      case "contentCode":
        builder
            .statusMessage(messageSource
                .getMessage("missing.param.contentCode", null, LocaleContextHolder.getLocale()));
      default:
        builder.statusMessage(e.getMessage());
        break;
    }
    return builder.build();
  }
}
