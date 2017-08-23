package com.stixcloud.product.event;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.config.multitenant.MultiTenantProperties;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.domain.InternetContentProducts;
import com.stixcloud.domain.PriceTableInfo;
import com.stixcloud.domain.RetrievePriceTypeView;
import com.stixcloud.domain.RetrieveSectionGaView;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.SeatmapAvailablityProducts;
import com.stixcloud.domain.SeatmapOverviewProducts;
import com.stixcloud.product.constant.EventConstants;
import com.stixcloud.product.constant.InventoryConstants;
import com.stixcloud.seatmap.service.ImageCreationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by chongye on 29-Jul-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IEventServiceTest {
  private final Logger logger = LogManager.getLogger(IEventServiceTest.class);
  @InjectMocks
  EventService eventService;
  @Mock
  IInternetContentProductsRepository showTimingRepository;
  @Mock
  ISeatmapOverviewProductsRepository seatmapOverviewProductsRepository;
  @Mock
  ISeatmapAvailablityProductsRepository seatmapAvailablityProductsRepository;
  @Mock
  IPriceClassRepository priceClassRepository;
  @Mock
  PriceTableInfoRepository repository;
  @Mock
  IDetailSeatmapRepository detailSeatmapRepository;
  @Mock
  SalesSeatInventoryRepository seatInventoryRepository;
  @Mock
  ImageCreationService imageCreationService;
  @Mock
  ISectionGaRepository sectionGaRepository;
  @Autowired
  ObjectMapper objectMapper;

  //@Value("${default.reservation.timeout}")
  //private Long defaultReservationTimeout = TenantContextHolder.getTenant().getSeatInventoryExpiry();

  private List<InternetContentProducts> contentProductsList;
  private List<SeatmapOverviewProducts> seatmapOverviewList;
  private List<SeatmapAvailablityProducts> seatmapAvailabilityList;
  private List<RetrievePriceTypeView> priceList;
  private List<PriceTableInfo> priceTableInfoList;
  private List<DetailSeatmapProducts> detailSeatmapProductsList;
  private List<SalesSeatInventory> seatInventoryList;
  private List<RetrieveSectionGaView> sectionGaViewList;

  @Before
  public void setUp() throws Exception {
    /*setUpShowTimingsList();
    setUpSeatmapOverviewList();
    setUpSeatmapAvailabilityList();
    setUpGetPriceCLassList();
    setUpPriceTableInfoList();
    setUpDetailSeatmapList();
    setUpSalesSeatInventoryList();
    setUpSectionGaViewList();
    setupTenantContextHolder();*/
  }

  /**
   * Setup TenantContextHolder as it is unable to grab automatically
   * as this is a JUnit
   */
  private void setupTenantContextHolder() {
    MultiTenantProperties.Tenant tenant = new MultiTenantProperties.Tenant();
    tenant.setName("SISTIC");
    tenant.setUrl("jdbc:oracle:thin:@//192.168.11.202:1521/devcloud");
    tenant.setUserInfoId(59L);
    tenant.setProfileInfoId(11L);
    tenant.setUsername("devcloud");
    tenant.setPassword("devcloud");
    tenant.setSeatInventoryExpiry(15L);
    TenantContextHolder.setTenant(tenant);
  }

  /**
   * Setup base information for testing getPriceCLassList().
   */
  /*private void setUpGetPriceCLassList() throws java.io.IOException {

    RetrievePriceTypeView view =
        new RetrievePriceTypeView("Test class name", "D1", new BigDecimal("30"), 1L, 10L, 1101L,
            2202L, 59L, 11L, 3303L, false, "testcode", "grouptestcode");
    priceList = new ArrayList<RetrievePriceTypeView>();
    priceList.add(view);
    when(priceClassRepository
        .getPriceClassList(anyLong(), anyLong(), anyLong(), anyLong(), anyString(), "F"))
        .thenReturn(priceList);
  }

  private void setUpSeatmapAvailabilityList() {
    seatmapAvailabilityList = new ArrayList<>();
    SeatmapAvailablityProducts products =
        new SeatmapAvailablityProducts(218991L, 51331L, 10200L, 11L, 1);
    seatmapAvailabilityList.add(products);
    when(seatmapAvailablityProductsRepository.getSeatmapAvailability(anyLong(), anyString()))
        .thenReturn(seatmapAvailabilityList);
  }

  private void setUpSeatmapOverviewList() {
    seatmapOverviewList = new ArrayList<>();
    SeatmapOverviewProducts seatmapOverviewProduct = new SeatmapOverviewProducts(1L, "Stall",
        "Center", 51331L, "RS", "Stall", 10200L, "Cat 1", 1, new BigDecimal("48.00"), "A", "ASD", 3,
        "T", 11L, 0, 1, "SP", "450,633,456,616,711,571,769,651,514,694,506,712",
        "esrt1608061100_The_Three_Little_Pigs_weekends.jpg", "F", 539L, 0L);
    seatmapOverviewList.add(seatmapOverviewProduct);
    when(seatmapOverviewProductsRepository.getSeatmapOverview(anyLong(), anyString()))
        .thenReturn(seatmapOverviewList);
  }

  private void setUpShowTimingsList() throws java.io.IOException {
    contentProductsList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/InternetContentProducts.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });

    when(showTimingRepository
        .getProductsByInternetContentCode(anyString(), anyObject(), anyObject(), anyString()))
        .thenReturn(contentProductsList);
  }

  private void setUpPriceTableInfoList() throws Exception {
    priceTableInfoList = new ArrayList<>();
    PriceTableInfo priceTableInfo = new PriceTableInfo(218977L, 11L, "A", "Standard", "Cat 1", 1,
        new BigDecimal("28.0000"), 18);
    priceTableInfoList.add(priceTableInfo);
    when(repository.getPriceInfoTable(anyLong(), anyString())).thenReturn(priceTableInfoList);
  }

  private void setUpDetailSeatmapList() throws Exception {
    detailSeatmapProductsList = new ArrayList<>();

    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 287, 0, 118494547L, "AA", "4", 1, "84,55,101,72", "84,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 289, 0, 118494548L, "AA", "3", 1, "67,55,84,74", "67,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 292, 0, 118494542L, "AA", "15", 1, "276,55,293,72", "276,55",
        "0", "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 298, 0, 118494546L, "AA", "2", 1, "49,55,67,72", "49,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 299, 0, 118494540L, "AA", "16", 1, "293,55,310,72", "293,55",
        "0", "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 303, 0, 118494545L, "AA", "1", 1, "32,55,49,72", "32,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 305, 0, 118494538L, "AA", "17", 1, "310,55,328,72", "310,55",
        "0", "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 314, 0, 118494552L, "AA", "18", 1, "328,55,345,72", "328,55",
        "0", "Center", 11));

    when(detailSeatmapRepository.getDetailSeatmap(anyLong(), anyLong()))
        .thenReturn(detailSeatmapProductsList);
  }

  private void setUpSalesSeatInventoryList() throws Exception {
    ReflectionTestUtils.setField(
        eventService, "defaultReservationTimeout", TenantContextHolder.getTenant().getSeatInventoryExpiry());

    seatInventoryList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/SalesSeatInventory.json"),
        new TypeReference<List<SalesSeatInventory>>() {
        });

    when(seatInventoryRepository.findAllByProductIdAndSeatSalesStatus(
        anyLong(), anyInt(), anyListOf(Long.class))).thenReturn(seatInventoryList);

    //return the seats to simulate commit
    doAnswer(invocationOnMock -> {
      List<SalesSeatInventory> list = invocationOnMock.getArgumentAt(0, ArrayList.class);
      return list;
    }).when(seatInventoryRepository).save(anyCollectionOf(SalesSeatInventory.class));
  }*/

  /**
   * Setup base data for testing method getSectionGaList.
   */
  /*private void setUpSectionGaViewList() {
    sectionGaViewList = new ArrayList<RetrieveSectionGaView>();
    RetrieveSectionGaView retrieveSectionGaView =
        new RetrieveSectionGaView(10L, 9L, "Section Alias", "GA", 8, 7L, 6, new BigDecimal(50), 1L);
    sectionGaViewList.add(retrieveSectionGaView);
    when(sectionGaRepository.getSectionGaList(anyLong(), anyLong())).thenReturn(sectionGaViewList);
  }

  @Test
  public void getProductsByInternetContentCode() {
    long startTime = System.nanoTime();
    List<InternetContentProducts> list =
        eventService.getProductsByInternetContentCode("cpigs0916", LocalDate.now(), 31, "ABCD");
    logger.info("Time taken " + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),
        TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));

    assertFalse(list.isEmpty());
    assertEquals(list.size(), contentProductsList.size());
    assertEquals(list.get(0), contentProductsList.get(0));
  }

  @Test
  public void getSeatmapOverview() {
    long startTime = System.nanoTime();

    List<SeatmapOverviewProducts> list = eventService.getSeatmapOverview(1L, "Test");
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));

    assertFalse(list.isEmpty());
    assertEquals(list.size(), seatmapOverviewList.size());
    assertEquals(list.get(0), seatmapOverviewList.get(0));
  }*/

  @Test
  public void getSeatmapAvailability() {
    long startTime = System.nanoTime();

    List<SeatmapAvailablityProducts> list = eventService.getSeatmapAvailability(1L, "Test");
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));

    assertFalse(list.isEmpty());
    assertEquals(list.size(), seatmapAvailabilityList.size());
    assertEquals(list.get(0), seatmapAvailabilityList.get(0));
  }

  @Test
  public void getSeatmapPriceTable() throws Exception {
    long startTime = System.nanoTime();

    List<PriceTableInfo> list = eventService.getSeatmapPriceTable(1L, "Test", EventConstants.PACKAGE_CLASS_F);
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));

    assertFalse(list.isEmpty());
    assertEquals(list.size(), priceTableInfoList.size());
    assertEquals(list.get(0), priceTableInfoList.get(0));
  }

  /**
   * Test for getPriceClassList method.
   */
  @Test
  public void getPriceClassList() {
    long startTime = System.nanoTime();
    List<RetrievePriceTypeView> list = eventService.getPriceClassList(1L, 1L, 1L, 1L, "promo code", EventConstants.PACKAGE_CLASS_F);
    logger.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    logger.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(list.size(), priceList.size());
    assertEquals(list.get(0), priceList.get(0));
  }

  /*@Test
  public void getDetailSeatmap() throws Exception {
    long startTime = System.nanoTime();

    List<DetailSeatmapProducts> list = eventService.getDetailSeatmap(1L, 1L);
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));

    assertFalse(list.isEmpty());
    assertEquals(list.size(), detailSeatmapProductsList.size());
    assertEquals(list.get(0), detailSeatmapProductsList.get(0));

  }

  @Test
  public void reserveSeats() throws Exception {
    ArrayList<Long> list = new ArrayList<>();
    list.add(118488165L);
    List<SalesSeatInventory> seatInventories = eventService.reserveSeats(218977L, list);
    assertEquals(seatInventories.size(), seatInventoryList.size());
    for (SalesSeatInventory seatInventory : seatInventories) {
      assertEquals(seatInventory.getSeatsalesstatus(),
          InventoryConstants.SALES_STATUS_RESERVED.getValue());
    }
  }

  @Test
  public void releaseSeats() throws Exception {
    ArrayList<Long> list = new ArrayList<>();
    list.add(118488165L);
    List<SalesSeatInventory> seatInventories = eventService.releaseSeats(218977L, list);
    assertEquals(seatInventories.size(), seatInventoryList.size());
    for (SalesSeatInventory seatInventory : seatInventories) {
      assertEquals(seatInventory.getSeatsalesstatus(),
          InventoryConstants.SALES_STATUS_AVAILABLE.getValue());
    }
  }

  *//**
   * Test for getSectionGaList method.
   *//*
  @Test
  public void getSectionGaList() {
    long startTime = System.nanoTime();

    List<RetrieveSectionGaView> list = eventService.getSectionGaList(1L, 1L);
    logger.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    logger.info(Arrays.toString(list.toArray()));

    assertFalse(list.isEmpty());
    assertEquals(list.size(), sectionGaViewList.size());
    assertEquals(list.get(0), sectionGaViewList.get(0));
  }*/

  @Test
  public void getOverviewImage() throws Exception {
    String mockImagePath = random(String.class);
    when(imageCreationService.getOverviewImage(anyListOf(Long.class), anyLong(),
        anyBoolean(), anyBoolean(), anyString())).thenReturn(mockImagePath);

    String resultImagePath = eventService.getOverviewImage(random(Long.class));
    verify(imageCreationService).getOverviewImage(anyListOf(Long.class), anyLong(),
        anyBoolean(), anyBoolean(), anyString());
    assertEquals(mockImagePath, resultImagePath);
  }

  @Test
  public void getOverviewImage1() throws Exception {
    String mockImagePath = random(String.class);
    when(imageCreationService.getOverviewImage(anyListOf(Long.class), anyLong(),
        anyBoolean(), anyBoolean(), anyString())).thenReturn(mockImagePath);

    String resultImagePath =
        eventService.getOverviewImage(randomListOf(5, Long.class), random(Long.class));
    verify(imageCreationService).getOverviewImage(anyListOf(Long.class), anyLong(),
        anyBoolean(), anyBoolean(), anyString());
    assertEquals(mockImagePath, resultImagePath);
  }

  @Test
  public void getDetailImages() throws Exception {
    String mockImagePath = random(String.class);
    when(imageCreationService.getDetailImages(anyListOf(Long.class), anyLong(), anyString()))
        .thenReturn(mockImagePath);

    String resultImagePath =
        eventService.getDetailImages(randomListOf(5, Long.class), random(Long.class));
    verify(imageCreationService).getDetailImages(anyListOf(Long.class), anyLong(), anyString());
    assertEquals(mockImagePath, resultImagePath);
  }
}
