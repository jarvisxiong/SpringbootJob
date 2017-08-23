package com.stixcloud.paymentgateway;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.paymentgateway.api.PaymentConfigDetailsJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sengkai on 11/21/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentGatewayConfigServiceTest {
  private final Logger logger = LogManager.getLogger(PaymentGatewayConfigServiceTest.class);
  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private PaymentGatewayConfigService paymentGatewayConfigService;

  @Test
  @Sql("/sql/paymentGatewayConfiguration.sql")
  public void getPaymentGatewayConfig() throws Exception {
    String paymentMethodCode = "MASTER_SEPANG";

    PaymentConfigDetailsJson
        gatewayDetailJson =
        paymentGatewayConfigService.getPaymentConfigDetailsJson(paymentMethodCode, 11L);

    assertThat(gatewayDetailJson).toString();

    String
        jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(gatewayDetailJson);
    logger.debug("Json Object : " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotEmpty();
  }

  @Test
  @Sql("/sql/paymentGatewayConfigurationDummy.sql")
  public void getPaymentGatewayConfig_Dummy() throws Exception {
    String paymentMethodCode = "MASTER";

    PaymentConfigDetailsJson
        gatewayDetailJson =
        paymentGatewayConfigService.getPaymentConfigDetailsJson(paymentMethodCode, 11L);

    assertThat(gatewayDetailJson).toString();

    String
        jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(gatewayDetailJson);
    logger.debug("Json Object : " + jsonMapperOutput);

    assertThat(jsonMapperOutput).isNotEmpty();
  }
}
