package com.stixcloud.paymentgateway;

import com.stixcloud.paymentgateway.api.PaymentGatewayRequest;
import com.stixcloud.paymentgateway.api.PaymentGatewayResponse;

/**
 * Created by chongye on 16/11/2016.
 */
public interface IQueueService {
  String sendMessage(PaymentGatewayRequest paymentGatewayRequest);

  PaymentGatewayResponse getMessage(String uuid);

  void removeMessage(String uuid);
}
