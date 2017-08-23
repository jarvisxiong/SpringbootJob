package com.stixcloud.paymentgateway;

import com.stixcloud.domain.PaymentGatewayConfig;
import com.stixcloud.paymentgateway.api.PaymentConfigDetailsJson;

import java.util.List;

/**
 * Created by sengkai on 11/18/2016.
 */
public interface IPaymentGatewayConfigService {

  PaymentConfigDetailsJson getPaymentConfigDetailsJson(String paymentMethodCode,
                                                       Long profileInfoId);

  List<PaymentGatewayConfig> getPaymentGatewayConfigs(String paymentMethodCode,
                                                      Long profileInfoId);
}
