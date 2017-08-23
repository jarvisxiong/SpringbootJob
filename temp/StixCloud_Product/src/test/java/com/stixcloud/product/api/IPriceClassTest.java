package com.stixcloud.product.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.domain.RetrievePriceTypeView;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.money.Monetary;

/**
 * Created by dbthan on 13-Sep-16.
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPriceClassTest {

  @Autowired
  IPriceClass priceClass;

  @Autowired
  ObjectMapper objectMapper;

  private List<RetrievePriceTypeView> retrieveList;
  private List<PriceClass> priceClassList;

  @Before
  public void setUp() throws Exception {
    Locale.setDefault(new Locale("en", "SG"));
    setUpForGetPriceClassListFromPriceTypeView();
  }

  /**
   * Setup base data for testing method getPriceClassListFromPriceTypeView.
   * @throws Exception If it can't read json file
   */
  private void setUpForGetPriceClassListFromPriceTypeView() throws Exception {
    retrieveList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/retrievePriceTypeList.json"),
        new TypeReference<List<RetrievePriceTypeView>>() {
        });
    priceClassList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/priceClassConvertList.json"),
        new TypeReference<List<PriceClass>>() {
        });
  }

  /**
   * Test for method getPriceClassListFromPriceTypeView case normal.
   */
  @Test
  public void getPriceClassListFromPriceTypeViewCaseNormal() {
    List<PriceClass> actual = priceClass.getPriceClassListFromPriceTypeView(retrieveList);

    assertFalse(actual.isEmpty());
    assertEquals(actual.size(), priceClassList.size());
    assertEquals(actual.get(0), priceClassList.get(0));
  }

  /**
   * Test for method getPriceClassListFromPriceTypeView case max quantity is 0.
   */
/*  @Test
  public void getPriceClassListFromPriceTypeViewCaseMaxQuantityIsZero() {
    List<RetrievePriceTypeView> retrievePriceList = new ArrayList<RetrievePriceTypeView>();
    RetrievePriceTypeView view =
        new RetrievePriceTypeView("Price class name", "A1", new BigDecimal(20), 1L, 0L, 1101L,
            2202L, 11L, 59L, 3303L, false, "ProductCodeTest1", "ProductGroupCodeTest1");
    view.setQuantityStatus(1);
    retrievePriceList.add(view);
    List<PriceClass> actual = priceClass.getPriceClassListFromPriceTypeView(retrievePriceList);
    List<PriceClass> priceClasses = new ArrayList<PriceClass>();
    PriceClass priceClass =
        new PriceClass("A1", "Price class name", Money.of(new BigDecimal(20), Monetary
            .getCurrency(Locale.getDefault())), "1,2,3,4,5,6,7,8,9,10,11,12");
    priceClasses.add(priceClass);
    assertFalse(actual.isEmpty());
    assertEquals(actual.size(), priceClasses.size());
    assertEquals(actual.get(0), priceClasses.get(0));
  }*/

  /**
   * Test for method getPriceClassListFromPriceTypeView case sequence is 1.
   */
  /*@Test
  public void getPriceClassListFromPriceTypeViewCaseSequenceIs1() {
    List<RetrievePriceTypeView> retrievePriceList = new ArrayList<RetrievePriceTypeView>();
    RetrievePriceTypeView view =
        new RetrievePriceTypeView("Price class name", "A1", new BigDecimal(20), 1L, 5L, 1101L,
            2202L, 11L, 59L, 3303L, false, "ProductCodeTest1", "ProductGroupCodeTest1");
    view.setQuantityStatus(2);
    retrievePriceList.add(view);
    List<PriceClass> actual = priceClass.getPriceClassListFromPriceTypeView(retrievePriceList);
    List<PriceClass> priceClasses = new ArrayList<PriceClass>();
    PriceClass
        priceClass =
        new PriceClass("A1", "Price class name", Money.of(new BigDecimal(20), Monetary
            .getCurrency(Locale.getDefault())), "1,2,3,4,5");
    priceClasses.add(priceClass);
    assertFalse(actual.isEmpty());
    assertEquals(actual.size(), priceClasses.size());
    assertEquals(actual.get(0), priceClasses.get(0));
  }*/

  /**
   * Test for method getPriceClassListFromPriceTypeView case result is "0".
   */
  /*@Test
  public void getPriceClassListFromPriceTypeViewCaseEmpty() {
    List<RetrievePriceTypeView> retrievePriceList = new ArrayList<RetrievePriceTypeView>();
    RetrievePriceTypeView view =
        new RetrievePriceTypeView("Price class name", "A1", new BigDecimal(20), 6L, 5L, 1101L,
            2202L, 11L, 59L, 3303L, false, "ProductCodeTest1", "ProductGroupCodeTest1");
    view.setQuantityStatus(1);
    retrievePriceList.add(view);
    List<PriceClass> actual = priceClass.getPriceClassListFromPriceTypeView(retrievePriceList);
    List<PriceClass> priceClasses = new ArrayList<PriceClass>();
    PriceClass
        priceClass =
        new PriceClass("A1", "Price class name", Money.of(new BigDecimal(20), Monetary
            .getCurrency(Locale.getDefault())), "0");
    priceClasses.add(priceClass);
    assertFalse(actual.isEmpty());
    assertEquals(actual.size(), priceClasses.size());
    assertEquals(actual.get(0), priceClasses.get(0));
  }*/

  /**
   * Test for method getPriceClassListFromPriceTypeView case sequence is 3.
   */
  /*@Test
  public void getPriceClassListFromPriceTypeViewCaseSequenceIs3() {
    List<RetrievePriceTypeView> retrievePriceList = new ArrayList<RetrievePriceTypeView>();
    RetrievePriceTypeView view =
        new RetrievePriceTypeView("Price class name", "A1", new BigDecimal(20), 3L, 10L, 1101L,
            2202L, 11L, 59L, 3303L, false, "ProductCodeTest1", "ProductGroupCodeTest1");
    view.setQuantityStatus(1);
    retrievePriceList.add(view);
    List<PriceClass> actual = priceClass.getPriceClassListFromPriceTypeView(retrievePriceList);
    List<PriceClass> priceClasses = new ArrayList<PriceClass>();
    PriceClass
        priceClass =
        new PriceClass("A1", "Price class name", Money.of(new BigDecimal(20), Monetary
            .getCurrency(Locale.getDefault())), "3,6,9");
    priceClasses.add(priceClass);
    assertFalse(actual.isEmpty());
    assertEquals(actual.size(), priceClasses.size());
    assertEquals(actual.get(0), priceClasses.get(0));
  }*/
}
