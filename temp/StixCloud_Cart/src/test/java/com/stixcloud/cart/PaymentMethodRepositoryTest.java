package com.stixcloud.cart;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import com.stixcloud.cart.repo.PaymentMethodRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.PaymentMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by sengkai on 10/20/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentMethodRepositoryTest {
  private final Logger logger = LogManager.getLogger(PaymentMethodRepositoryTest.class);

  @Autowired
  PaymentMethodRepository paymentMethodRepository;

  @BeforeClass
  public static void setUp() throws Exception {
    TenantPropertiesTest.setUp();
  }

  @Test
  @Sql("/cart/sql/PaymentMethod.sql")
  public void findCommonPaymentMethods() {
    //Test data
    //ProductsIDs :: 120702, 120701
    //profileInfoIDs :: 11

    logger.debug(" [PaymentMethodRepositoryTest.findCommonPaymentMethods] :: >> ");
    //Prepare to test the data
    List<PaymentMethod> commonPaymentList =
        paymentMethodRepository
            .findCommonPaymentMethods(120702l, TenantContextHolder.getTenant().getProfileInfoId());

    assertFalse(commonPaymentList.isEmpty());
    assertTrue(commonPaymentList.size() > 0);
  }
}
