package com.stixcloud.cart;

import static org.assertj.core.api.Assertions.assertThat;

import com.stixcloud.cart.repo.CartLineItemViewRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartLineItemsView;
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

/**
 * Created by sengkai on 10/20/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CartLineItemViewRepositoryImplTest {
  private final Logger logger = LogManager.getLogger(CartLineItemViewRepositoryImplTest.class);

  @Autowired
  CartLineItemViewRepository cartLineItemViewRepository;

  @BeforeClass
  public static void setUp() throws Exception {
    TenantPropertiesTest.setUp();
  }

  @Test
  @Sql("/cart/sql/RetrieveCartLineItems.sql")
  public void findCartLineItems() {

    CartLineItemsView
        cartLineItemsView =
        cartLineItemViewRepository
            .findCartLineItem(254999L, 2663L, 359156L, "RS", "A", 123002697L,
                TenantContextHolder.getTenant().getProfileInfoId());

    logger.debug(" [findCartLineItems] ");
    assertThat(cartLineItemsView).isNotNull();

  }
}
