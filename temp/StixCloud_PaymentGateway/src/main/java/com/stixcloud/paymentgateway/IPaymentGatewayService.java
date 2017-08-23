package com.stixcloud.paymentgateway;

import com.stixcloud.paymentgateway.api.CupResponse;

public interface IPaymentGatewayService {

  boolean isResponseValid(CupResponse cupResponse);
}
