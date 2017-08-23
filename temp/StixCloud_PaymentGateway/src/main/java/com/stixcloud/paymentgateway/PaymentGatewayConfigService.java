package com.stixcloud.paymentgateway;

import com.stixcloud.domain.PaymentGatewayConfig;
import com.stixcloud.paymentgateway.api.PaymentConfigDetailsJson;
import com.stixcloud.paymentgateway.repo.PaymentGatewayConfigRepository;
import com.stixcloud.paymentgateway.repo.RevenueCenterParamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sengkai on 11/18/2016.
 */
@Service
public class PaymentGatewayConfigService implements IPaymentGatewayConfigService {
  private static final Logger logger = LogManager.getLogger(PaymentGatewayConfigService.class);

  @Autowired
  private PaymentGatewayConfigRepository gatewayConfigRepository;
  @Autowired
  private RevenueCenterParamRepository revenueCenterParamRepository;

  @Override
  public PaymentConfigDetailsJson getPaymentConfigDetailsJson(String paymentMethodCode,
                                                              Long profileInfoId) {
    List<PaymentGatewayConfig>
        paymentGatewayConfiguration =
        getPaymentGatewayConfigs(paymentMethodCode, profileInfoId);

    String revenueCenterCode =
        paymentGatewayConfiguration.stream()
            .filter(paymentGatewayConfig -> paymentGatewayConfig.getRevenuecentercode() != null)
            .map(PaymentGatewayConfig::getRevenuecentercode)
            .findFirst().orElse(null);

    String paymentGatewayStatus =
        paymentGatewayConfiguration.stream().map(PaymentGatewayConfig::getPaymentGatewayStatus)
            .findFirst().orElse(null);

    Long
        revenueCenterId =
        paymentGatewayConfiguration.stream()
            .filter(paymentGatewayConfig -> paymentGatewayConfig.getRevenuecenterid() != null)
            .map(PaymentGatewayConfig::getRevenuecenterid)
            .findFirst().orElse(null);

    //Stream into map of Configuration IDs & Keys
    Map<String, String> gatewayConfig =
        paymentGatewayConfiguration.stream()
            .filter(paymentGatewayConfig -> paymentGatewayConfig.getRevenuecenterid() != null)
            .collect(Collectors.toMap(PaymentGatewayConfig::getConfigurationname,
                PaymentGatewayConfig::getConfigurationvalue));
    if (revenueCenterId != null) {
      gatewayConfig.put("merchant.id", revenueCenterCode);
      gatewayConfig.put("revenue.center.id", revenueCenterId.toString());
    }

    // gatewayConfig.forEach(logger::debug);

    if (gatewayConfig.size() == 0) {
      return new PaymentGatewayConfigBuilder().build(paymentMethodCode, paymentGatewayStatus);
    }
    return new PaymentGatewayConfigBuilder()
        .build(paymentMethodCode, paymentGatewayStatus, gatewayConfig);
  }

  public List<PaymentGatewayConfig> getPaymentGatewayConfigs(String paymentMethodCode,
                                                             Long profileInfoId) {
    List<Long>
        revenueCenterIds =
        revenueCenterParamRepository.findRevenueCenterIdByProfileInfoId(profileInfoId);

    if (revenueCenterIds.size() == 0) {
      return gatewayConfigRepository
          .findPaymentGatewayConfiguration(paymentMethodCode, profileInfoId);
    }

    return gatewayConfigRepository
        .findPaymentGatewayConfiguration(paymentMethodCode, revenueCenterIds, profileInfoId);
  }
}
