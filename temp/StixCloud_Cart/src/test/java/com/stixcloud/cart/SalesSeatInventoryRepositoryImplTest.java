package com.stixcloud.cart;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.domain.SalesSeatInventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by chongye on 7/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class SalesSeatInventoryRepositoryImplTest {
  @Autowired
  SalesSeatInventoryRepository salesSeatInventoryRepository;

  @Test
  @Sql("/cart/sql/availableInvForGA.sql")
  public void findAvailableSeatsForGA() {
    List<SalesSeatInventory>
        salesSeatInventories =
        salesSeatInventoryRepository.findAvailableSeatsForGA(245632L, 12176L, 356880L, 10);

    assertFalse(salesSeatInventories.isEmpty());
    assertTrue(salesSeatInventories.size() == 10);
    long filterCount = salesSeatInventories.stream()
        .filter(i -> i.getProductId() == 245632L)
        .filter(i -> i.getPriceCatId() == 12176L)
        .filter(i -> i.getVenueSectionLcId() == 356880L)
        .filter(i -> i.getSeatsalesstatus() == 0)
        .count();
    assertTrue(filterCount == 10);
  }
}