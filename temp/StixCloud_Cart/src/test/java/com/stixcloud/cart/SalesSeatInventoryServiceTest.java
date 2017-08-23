package com.stixcloud.cart;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.domain.SalesSeatInventory;
import org.exparity.hamcrest.date.ZonedDateTimeMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chongye on 10/10/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesSeatInventoryServiceTest {
  @Autowired
  SalesSeatInventoryRepository salesSeatInventoryRepository;
  @Autowired
  SalesSeatInventoryService salesSeatInventoryService;

  @Before
  public void setup() throws Exception {
    TenantPropertiesTest.setUp();
  }

  @Test
  @Sql("/cart/sql/reservedInvForGA.sql")
  public void releaseSeatsForGA() throws Exception {
    /*SalesSeatInventory salesSeatInventory = salesSeatInventoryRepository.findOne(121553775L);
    assertThat(salesSeatInventory,
        allOf(
            hasProperty("salesseatinventoryid", is(121553775L)),
            hasProperty("seatsalesstatus",
                is(InventoryConstants.SALES_STATUS_RESERVED.getValue())),
            hasProperty("reserveexpirydate", not(nullValue()))
        ));

    Set<Long> invList = new HashSet<>(Arrays.asList(121553775L));
    Map<String, Integer> priceClassMap = new HashMap<>();
    priceClassMap.put("A", 1);

    CartItem cartItem =
        new CartItem.Builder().productId(245632L)
            .priceCatId(12176L).seatSectionId(356880L)
            .mode(InventoryConstants.SECTION_TYPE_GA.getName()).inventoryIdList(invList)
            .priceClassMap(priceClassMap).build();
    salesSeatInventoryService.releaseSeatsForGA(cartItem);

    salesSeatInventory = salesSeatInventoryRepository.findOne(121553775L);
    assertThat(salesSeatInventory,
        allOf(
            hasProperty("salesseatinventoryid", is(121553775L)),
            hasProperty("seatsalesstatus",
                is(InventoryConstants.SALES_STATUS_AVAILABLE.getValue())),
            hasProperty("reserveexpirydate", nullValue())
        ));*/
  }

  @Test
  @Sql("/cart/sql/availableInvForGA.sql")
  public void findSeatsAndReserveForGA() throws Exception {
    Iterable<SalesSeatInventory> invIterable = salesSeatInventoryRepository.findAll();
    List<SalesSeatInventory> inventories = new ArrayList<>();
    invIterable.forEach(inventories::add);

    assertThat(inventories, everyItem(
        allOf(
            hasProperty("productId", is(245632L)),
            hasProperty("priceCatId", is(12176L)),
            hasProperty("venueSectionLcId", is(356880L)),
            hasProperty("seatsalesstatus",
                is(InventoryConstants.SALES_STATUS_AVAILABLE.getValue())),
            hasProperty("reserveexpirydate", nullValue())
        ))
    );

    List<SalesSeatInventory> reservedInventories =
        salesSeatInventoryService.findSeatsAndReserveForGA(245632L, 12176L, 356880L, 10);

    assertInventoryAreReserved(reservedInventories);
  }

  @Test
  @Sql("/cart/sql/reservedInvForGA.sql")
  public void updateSeatsReserveExpiry() throws Exception {
    Iterable<SalesSeatInventory> invItr = salesSeatInventoryRepository.findAll();
    assertInventoryAreReserved(invItr);

    List<Long> inventories = new ArrayList<>();
    invItr.forEach(i -> inventories.add(i.getSalesseatinventoryid()));
    ZonedDateTime reserveDateTime = ZonedDateTime.now();
    salesSeatInventoryService.updateSeatsReserveExpiry(inventories);

    invItr = salesSeatInventoryRepository.findAll();
    assertInventoryAreReserved(invItr);

    //assert that seats reserve expiry time is updated
    invItr.forEach(i -> {
      ZonedDateTime converted = i.getReserveexpirydate().toZonedDateTime();
      assertThat(converted, ZonedDateTimeMatchers
          .within(15, ChronoUnit.MINUTES, reserveDateTime));
    });
  }

  private void assertInventoryAreReserved(Iterable<SalesSeatInventory> seatInventories) {
    assertThat(seatInventories, everyItem(
        allOf(
            hasProperty("productId", is(245632L)),
            hasProperty("priceCatId", is(12176L)),
            hasProperty("venueSectionLcId", is(356880L)),
            hasProperty("seatsalesstatus",
                is(InventoryConstants.SALES_STATUS_RESERVED.getValue())),
            hasProperty("reserveexpirydate", not(nullValue()))
        ))
    );
  }
}