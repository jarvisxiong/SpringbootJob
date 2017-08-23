package com.stixcloud.paymentgateway;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.PaymentGatewayConfig;
import com.stixcloud.paymentgateway.api.CupResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentGatewayService implements IPaymentGatewayService {
  private static final Logger logger = LogManager.getLogger(PaymentGatewayService.class);
  private static final String MERCHANT_PASSWORD = "merchant.password";

  @Autowired
  private IPaymentGatewayConfigService iPaymentGatewayConfigService;

  @Override
  public boolean isResponseValid(CupResponse cupResponse) {
    List<PaymentGatewayConfig> gatewayConfigList = iPaymentGatewayConfigService
        .getPaymentGatewayConfigs("CUP", TenantContextHolder.getTenant().getProfileInfoId());

    Optional<String>
        password =
        gatewayConfigList.stream().filter(x -> x.getConfigurationname().equals(MERCHANT_PASSWORD))
            .map(PaymentGatewayConfig::getConfigurationvalue).findFirst();

    if (password.isPresent()) {
      String
          respData =
          cupResponse.getRespData().replaceAll("&signature=" + cupResponse.getSignature(), "")
              .replaceAll("&signMethod=" + cupResponse.getSignMethod(), "");

      String sha256Hex = DigestUtils.sha256Hex(password.get());
      String paramsWithoutSignature = respData.concat("&" + sha256Hex);
      sha256Hex = DigestUtils.sha256Hex(paramsWithoutSignature);
      return sha256Hex.equals(cupResponse.getSignature());
    }

    return false;
  }
}
