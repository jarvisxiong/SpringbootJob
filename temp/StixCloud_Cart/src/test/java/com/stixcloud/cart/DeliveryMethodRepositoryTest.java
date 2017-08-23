package com.stixcloud.cart;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import com.stixcloud.cart.repo.DeliveryMethodRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sengkai on 11/30/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class DeliveryMethodRepositoryTest {
  private final Logger logger = LogManager.getLogger(DeliveryMethodRepositoryTest.class);

  @Autowired
  DeliveryMethodRepository deliveryMethodRepository;

  @Test
  @Sql("/cart/sql/deliveryMethodIsAddressRequired.sql")
  public void getDeliveryMethodIsAddressRequired() {

    boolean
        isAddressRequired =
        deliveryMethodRepository.getDeliveryMethodIsAddressRequired("MAIL_MC_WAIVER")
            != null ? true : false;

    assertThat(isAddressRequired).isNotNull();
    assertTrue(isAddressRequired);
  }
}
