package com.stixcloud.paymentgateway.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sengkai on 11/21/2016.
 */
@ActiveProfiles("test")
@JsonTest
@RunWith(SpringRunner.class)
public class PaymentConfigDetailsJsonTest {
  @Autowired
  ObjectMapper objectMapper;
  private JacksonTester<PaymentConfigDetailsJson> json;

  @Before
  public void setUp() throws Exception {
    JacksonTester.initFields(this, objectMapper);
  }

  @Test
  public void serializeJson_Success() throws Exception {

    Map<String, Object> gatewayConfig = new HashMap<String, Object>();
    gatewayConfig.put("merchant.user.id", "SAWOPERATORAMA");
    PaymentConfigDetailsJson gatewayDetailJson =
        new PaymentConfigDetailsJson.Builder()
            .paymentMethodCode("MASTER_SEPANG")
            .paymentGatewayConfig(new PaymentGatewayConfig.Builder()
                .merchantId("TEST20161116")
                .enableCSC(true)
                .clientServer("https://migs.mastercard.com.au/vpcdps")
                .secureType("threeDSecure")
                .secureCode("15CA5304F58CCB9B6047972B1DD9FB8B")
                .accessCode("68D54E4F")
                .redirectUrl("/SisticWebApp/PostConfirmation.do")
                .returnUrl("https://booking.sistic.stixcloud.com/SisticWebApp/redirect3D.jsp")
                .additionalProperties(gatewayConfig)
                .build())
            .build();

    JsonContent<PaymentConfigDetailsJson>
        paymentConfigDetailsJsonJsonContent =
        this.json.write(gatewayDetailJson);

    assertThat(paymentConfigDetailsJsonJsonContent)
        .isEqualToJson("/json/paymentGatewayConfig.json");

    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentMethodCode");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentGatewayConfig.merchantId");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathBooleanValue("@.paymentGatewayConfig.enableCSC");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentGatewayConfig.clientServer");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentGatewayConfig.secureType");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentGatewayConfig.secureCode");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentGatewayConfig.accessCode");
    assertThat(paymentConfigDetailsJsonJsonContent)
        .hasJsonPathStringValue("@.paymentGatewayConfig.returnUrl");

    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathStringValue("@.paymentGatewayConfig.merchantId")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getMerchantId());
    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathBooleanValue("@.paymentGatewayConfig.enableCSC")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getEnableCSC());
    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathStringValue("@.paymentGatewayConfig.clientServer")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getClientServer());
    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathStringValue("@.paymentGatewayConfig.secureType")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getSecureType());
    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathStringValue("@.paymentGatewayConfig.secureCode")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getSecureCode());
    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathStringValue("@.paymentGatewayConfig.accessCode")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getAccessCode());
    assertThat(paymentConfigDetailsJsonJsonContent)
        .extractingJsonPathStringValue("@.paymentGatewayConfig.returnUrl")
        .isEqualTo(gatewayDetailJson.getPaymentGatewayConfig().getReturnUrl());
  }
}
