package com.stixcloud.paymentgateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sengkai on 11/16/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest
public class PaymentGatewayConfigurationRepositoryTest {
  private final Logger
      logger =
      LogManager.getLogger(PaymentGatewayConfigurationRepositoryTest.class);

  /*@Autowired
  PaymentGatewayConfigurationRepository paymentGatewayConfigurationRepository;*/

  /*@Autowired
  ObjectMapper objectMapper;*/

  @Test
  @Sql("/sql/paymentGatewayConfiguration.sql")
  public void findPaymentGatewayConfiguration() {
    /*logger.debug(
        " [PaymentGatewayConfigurationRepositoryTest.findPaymentGatewayConfiguration] :: >> ");
    String paymentMethodCode = "MASTER_SEPANG";
    List<Object[]>
        paymentGatewayConfiguration =
        paymentGatewayConfigurationRepository
            .findPaymentGatewayConfiguration(paymentMethodCode, 11L);
    assertThat(paymentGatewayConfiguration).toString();

    Assert.notEmpty(paymentGatewayConfiguration);
    String revenueCenterCode = "";
    for (Object[] values : paymentGatewayConfiguration) {
      revenueCenterCode = (String) values[1];
      break;
    }

    //Stream into map of Configuration IDs & Keys
    Map<String, String> gatewayConfig =
        paymentGatewayConfiguration.stream().map(o -> o)
            .collect(Collectors.toMap(o -> (String) o[2], o -> (String) o[3]));
    gatewayConfig.put("merchant.id", revenueCenterCode);
    assertThat(gatewayConfig).isNotEmpty().hasSize(17);
    gatewayConfig.forEach((key, value) -> {
      logger.debug(" Key :: " + key + " Value :: " + value);
    });

    PaymentConfigDetailsJson
        gatewayDetailJson =
        new PaymentGatewayConfigBuilder().build(paymentMethodCode, gatewayConfig);

    *//*assertThat(gatewayDetailJson.getPaymentGatewayConfig())
        .hasNoNullFieldsOrProperties();*//*

    logger.debug("Jason Object : " + gatewayDetailJson.toString());*/
    //logger.debug("Jason Object : "+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(gatewayDetailJson));
  }
}
