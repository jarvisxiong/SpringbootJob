package com.stixcloud.cart;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stixcloud.cart.api.PaymentConfigDetailsJson;
import com.stixcloud.paymentgateway.api.CupRequest;
import com.stixcloud.paymentgateway.api.CupResponse;
import com.stixcloud.paymentgateway.api.MigsRequest;
import com.stixcloud.paymentgateway.api.MigsResponse;
import com.stixcloud.paymentgateway.api.PaymentGatewayRequest;
import com.stixcloud.paymentgateway.api.TelemoneyRequest;
import com.stixcloud.paymentgateway.api.TelemoneyResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("stixcloud-paymentgateway")
//@RequestMapping("${server.contextPath:}")
public interface IPaymentGatewayAPIService {
	
	public static final String HEADER_AUTHORIZATION = "Authorization";
	
//  @HystrixCommand(commandKey = "sendMessage")
  @RequestMapping(value = "${server.contextPath:}/{tenant}/payment/queue", method = RequestMethod.POST)
  ResponseEntity<String> sendMessage(@PathVariable(value = "tenant") String tenant, @RequestHeader(HEADER_AUTHORIZATION) String token,
                                     @RequestBody PaymentGatewayRequest paymentGatewayRequest);

  //@HystrixCommand(commandKey = "removeMessage")
  @RequestMapping(value = "${server.contextPath:}/{tenant}/payment/queue/{messageUuid}", method = RequestMethod.DELETE)
  ResponseEntity<String> removeMessage(@PathVariable(value = "tenant") String tenant, @RequestHeader(HEADER_AUTHORIZATION) String token,
                                       @PathVariable(value = "messageUuid") String messageUuid);

  //@HystrixCommand(commandKey = "getPaymentGatewayConfiguration")
  @RequestMapping(value = "${server.contextPath:}/{tenant}/payment/config/{paymentMethodCode}", method = RequestMethod.GET)
  PaymentConfigDetailsJson getPaymentGatewayConfiguration(
      @PathVariable(value = "tenant") String tenant, @RequestHeader(HEADER_AUTHORIZATION) String token,
      @PathVariable(value = "paymentMethodCode") String paymentMethodCode);

  @RequestMapping(value = "${server.contextPath:}/{tenant}/payment/migs", method = RequestMethod.POST)
 // @HystrixCommand(commandKey = "migs")
  MigsResponse processPayment(@PathVariable(value = "tenant") String tenant, @RequestHeader(HEADER_AUTHORIZATION) String token,
                              @RequestBody MigsRequest migsRequest);

  @RequestMapping(value = "${server.contextPath:}/{tenant}/payment/cup", method = RequestMethod.POST)
  //@HystrixCommand(commandKey = "cup")
  CupResponse processPayment(@PathVariable(value = "tenant") String tenant, @RequestHeader(HEADER_AUTHORIZATION) String token,
                             @RequestBody CupRequest cupRequest);

  @RequestMapping(value = "${server.contextPath:}/{tenant}/payment/telemoney", method = RequestMethod.POST)
 // @HystrixCommand(commandKey = "telemoney")
  TelemoneyResponse processPayment(@PathVariable(value = "tenant") String tenant, @RequestHeader(HEADER_AUTHORIZATION) String token,
                                   TelemoneyRequest telemoneyRequest);
}
