package com.stixcloud.product.api;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.domain.InternetContentProducts;
import com.stixcloud.domain.PriceTableInfo;
import com.stixcloud.domain.RetrievePriceTypeView;
import com.stixcloud.domain.RetrieveSectionGaView;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.SeatmapAvailablityProducts;
import com.stixcloud.domain.SeatmapOverviewProducts;
import com.stixcloud.product.TenantPropertiesTest;
import com.stixcloud.product.constant.EventConstants;
import com.stixcloud.product.constant.InventoryConstants;
import com.stixcloud.product.event.EventService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by chongye on 19-Aug-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private MockMvc mvc;
  @MockBean
  private EventService eventService;
  @Autowired
  private ObjectMapper objectMapper;
  //@Value("${default.reservation.timeout}")
  //private Long defaultReservationTimeout = TenantContextHolder.getTenant().getSeatInventoryExpiry();
  @Value("${spring.jackson.date-format}")
  private String dateFormat;

  private List<InternetContentProducts> contentProductsList;
  private List<SeatmapOverviewProducts> seatmapOverviewList;
  private List<SeatmapAvailablityProducts> seatmapAvailabilityList;
  private List<PriceTableInfo> priceTableInfoList;
  private List<RetrievePriceTypeView> retrieveList;
  private List<SalesSeatInventory> seatInventoryList;
  private List<DetailSeatmapProducts> detailSeatmapProductsList;
  private List<RetrieveSectionGaView> gaSeatSectionList;

  @Before
  public void setUp() throws Exception {
    /*setUpForShowTimingListTest();
    setUpForSeatmapOverviewTest();
    setUpSeatmapAvailabilityList();
    setUpPriceTableInfoList();
    setUpForGetTicketType();
    setUpSalesSeatInventoryList();
    setUpDetailSeatmapProductsList();
    setUpRetrieveSectionGaView();
    TenantPropertiesTest.setUp();*/
  }

  /*private void setUpSeatmapAvailabilityList() {
    seatmapAvailabilityList = new ArrayList<>();
    SeatmapAvailablityProducts products =
        new SeatmapAvailablityProducts(218991L, 51331L, 10200L, 11L, 1);
    seatmapAvailabilityList.add(products);
    given(eventService.getSeatmapAvailability(anyLong(), anyString()))
        .willReturn(seatmapAvailabilityList);
  }*/

  /*private void setUpForSeatmapOverviewTest() {
    seatmapOverviewList = new ArrayList<>();
    SeatmapOverviewProducts seatmapOverviewProduct = new SeatmapOverviewProducts(1L, "Stall",
        "Center", 51331L, "RS", "Stall", 10200L, "Cat 1", 1, new BigDecimal("48.00"), "A", "ASD", 3,
        "T", 11L, 0, 1, "SP", "450,633,456,616,711,571,769,651,514,694,506,712",
        "esrt1608061100_The_Three_Little_Pigs_weekends.jpg", "F", 539L, 0L);
    seatmapOverviewList.add(seatmapOverviewProduct);
    given(eventService.getSeatmapOverview(anyLong(), anyString())).willReturn(seatmapOverviewList);
  }*/

  /*private void setUpForShowTimingListTest() throws Exception {
    contentProductsList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/InternetContentProducts.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
    given(
        eventService.getProductsByInternetContentCode(anyString(), any(LocalDate.class), anyInt(),
            anyString()))
        .willReturn(contentProductsList);
  }*/

  /*private void setUpPriceTableInfoList() throws Exception {
    priceTableInfoList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/PriceTableInfo.json"),
        new TypeReference<List<PriceTableInfo>>() {
        });
    logger.debug(Arrays.toString(priceTableInfoList.toArray()));

    given(eventService.getSeatmapPriceTable(anyLong(), anyString())).willReturn(priceTableInfoList);
  }*/

  /**
   * Setup basic data for testing method convertToPriceCLass.
   */
  /*private void setUpForGetTicketType() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    retrieveList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/retrievePriceTypeList.json"),
        new TypeReference<List<RetrievePriceTypeView>>() {
        });
  }

  private void setUpSalesSeatInventoryList() throws Exception {
    ReflectionTestUtils.setField(eventService, "defaultReservationTimeout",
        TenantContextHolder.getTenant().getSeatInventoryExpiry());

    seatInventoryList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/SalesSeatInventory.json"),
        new TypeReference<List<SalesSeatInventory>>() {
        });
  }

  private void setUpDetailSeatmapProductsList() throws Exception {
    detailSeatmapProductsList = objectMapper.readValue(
        this.getClass().getResource(
            "/sample/product/api/retrieveDetailSeatmapProducts-235687-12079-55247.json"),
        new TypeReference<List<DetailSeatmapProducts>>() {
        });

    given(eventService.getDetailSeatmap(anyLong(), anyLong()))
        .willReturn(detailSeatmapProductsList);
  }


  @Test
  public void getShowTimings() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/sample/product/api/icc.json").toURI())));
    this.mvc.perform(get("/SISTIC/icc/cpigs0916"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void getShowTimingsCaseHasPromotionIsTrue() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/sample/product/api/iccForPromo.json").toURI())));
    List<InternetContentProducts> contentProducts = objectMapper.readValue(
        this.getClass()
            .getResource("/sample/product/api/InternetContentProductsWhenHasPromotion.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
    given(
        eventService.getProductsByInternetContentCode(anyString(), any(LocalDate.class), anyInt(),
            anyString()))
        .willReturn(contentProducts);
    this.mvc.perform(get("/SISTIC/icc/cpigs0916?startDate=20160819&numOfDays=31"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());

  }

  @Test
  public void getShowTimingsCaseHasPromotionIsFalse() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(
            this.getClass().getResource("/sample/product/api/iccForPromoIsFalse.json").toURI())));
    List<InternetContentProducts> contentProducts = objectMapper.readValue(
        this.getClass()
            .getResource("/sample/product/api/InternetContentProductsWhenNotHavePromotion.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
    given(
        eventService.getProductsByInternetContentCode(anyString(), any(LocalDate.class), anyInt(),
            anyString()))
        .willReturn(contentProducts);
    this.mvc.perform(get("/SISTIC/icc/cpigs0916?startDate=20160820&numOfDays=31"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());

  }

  @Ignore
  @Test
  public void getShowTimingsCaseIsValidPromotionIsNull() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/sample/product/api/iccForPromo.json").toURI())));
    List<InternetContentProducts> contentProducts = objectMapper.readValue(
        this.getClass().getResource(
            "/sample/product/api/InternetContentProductsWhenPromotionCodeIsNotGiven.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
    given(
        eventService.getProductsByInternetContentCode(anyString(), any(LocalDate.class), anyInt(),
            anyString()))
        .willReturn(contentProducts);
    this.mvc.perform(get("/SISTIC/icc/cpigs0916?startDate=20160819&numOfDays=31&"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());

  }

  @Test
  public void getShowTimingsCaseIsValidPromotionIsFalse() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(
            this.getClass().getResource("/sample/product/api/iccForInvalidPromo.json").toURI())));
    List<InternetContentProducts> contentProducts = objectMapper.readValue(
        this.getClass().getResource(
            "/sample/product/api/InternetContentProductsWhenInvalidPromotionCode.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
    given(
        eventService.getProductsByInternetContentCode(anyString(), any(LocalDate.class), anyInt(),
            anyString()))
        .willReturn(contentProducts);
    this.mvc.perform(get("/SISTIC/icc/cpigs0916?startDate=20160819&numOfDays=31&promoCode=test"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());

  }

  @Test
  public void getShowTimingsCaseIsValidPromotionIsTrue() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(
            this.getClass().getResource("/sample/product/api/iccForValidPromo.json").toURI())));
    List<InternetContentProducts> contentProducts = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/InternetContentProductsForPromo.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
    given(
        eventService
            .getProductsByInternetContentCode("cpigs0916", LocalDate.of(2016, 8, 19), 31, "test1"))
        .willReturn(contentProducts);
    this.mvc.perform(get("/SISTIC/icc/cpigs0916?startDate=20160819&numOfDays=31&promoCode=test1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());

  }

  *//**
   * Setup basic data for testing method getSeatmapForGA.
   *//*
  private void setUpRetrieveSectionGaView() {
    gaSeatSectionList = new ArrayList<RetrieveSectionGaView>();
    RetrieveSectionGaView retrieveSectionGaView =
        new RetrieveSectionGaView(10L, 9L, "Section Alias", "GA", 8, 7L, 6, new BigDecimal(50), 1L);
    gaSeatSectionList.add(retrieveSectionGaView);
    given(eventService.getSectionGaList(anyLong(), anyLong())).willReturn(gaSeatSectionList);
  }*/


  @Test
  public void getSeatmapOverview() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths
        .get(this.getClass().getResource("/sample/product/api/seatmapoverview.json").toURI())));
    this.mvc.perform(get("/SISTIC/products/218991/seatmap/overview/")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void getSeatmapAvailability() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths
        .get(this.getClass().getResource("/sample/product/api/seatmapavailability.json").toURI())));
    this.mvc.perform(get("/SISTIC/products/218991/seatmap/availability/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void getSeatmapPriceTable() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/sample/product/api/pricetable.json").toURI())));
    this.mvc.perform(get("/SISTIC/products/218991/seatmap/pricetable/")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  /**
   * Test for method getPriceClassList in EventController in case normal for Mvc.
   */
  @Test
  public void getTicketTypeListForMvc() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/sample/product/api/priceClassConvertList.json").toURI())));
    given(eventService.getPriceClassList(anyLong(), anyLong(), anyLong(), anyLong(), anyString(), EventConstants.PACKAGE_CLASS_F))
        .willReturn(retrieveList);

    this.mvc.perform(get("/SISTIC/products/151078/tickettype?priceCatId=4960"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  /**
   * Test for method getPriceClassList in EventController in case miss path param for Mvc.
   */
  @Test
  public void priceClassMissingPathParamHandlerForMvc() throws Exception {
    this.mvc.perform(get("/SISTIC/products/")).andExpect(status().isNotFound());
  }

  /**
   * Test for method getPriceClassList in EventController in case miss variable param for Mvc.
   */
  @Test
  public void priceClassMissingRequestParamForMvc() throws Exception {
    this.mvc.perform(get("/SISTIC/products/12176/tickettype")).andExpect(status().isOk());
  }

  @Test
  public void confirmSeats() throws Exception {
    JsonResponse success = new JsonResponse.Builder().status("0").statusMessage("SUCCESS").build();
    String successResponse = objectMapper.writeValueAsString(success);

    // reserve seats
    doAnswer(invocationOnMock -> {
      List<SalesSeatInventory> list = seatInventoryList;
      for (SalesSeatInventory seat : list) {
        seat.setSeatsalesstatus(InventoryConstants.SALES_STATUS_RESERVED.getValue());
      }
      return list;
    }).when(eventService).reserveSeats(anyLong(), anyListOf(Long.class));

    // release seats
    doAnswer(invocationOnMock -> {
      List<SalesSeatInventory> list = seatInventoryList;
      for (SalesSeatInventory seat : list) {
        seat.setSeatsalesstatus(InventoryConstants.SALES_STATUS_AVAILABLE.getValue());
      }
      return list;
    }).when(eventService).releaseSeats(anyLong(), anyListOf(Long.class));

    this.mvc
        .perform(post("/SISTIC/products/218977/seats").contentType(MediaType.APPLICATION_JSON)
            .content("{\"releasedSeatList\": [118488165], \"reservedSeatList\": [118488165] }"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(successResponse)).andDo(print());
  }

  @Test
  public void handleMissingParameterException() throws Exception {
    this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?seatSectionId=55247&quantity=0&mode=SP"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.toString()))
        .andExpect(jsonPath("$.statusMessage").value(
            messageSource.getMessage("missing.param.priceCatId", null, Locale.getDefault())));

    this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?priceCatId=12079&quantity=0&mode=SP"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.toString()))
        .andExpect(jsonPath("$.statusMessage").value(
            messageSource.getMessage("missing.param.seatSectionId", null, Locale.getDefault())));

    /*this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&mode=SP"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.toString()))
        .andExpect(jsonPath("$.statusMessage")
            .value(messageSource.getMessage("missing.param.quantity", null, Locale.getDefault())));*/

    this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&quantity=0"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.toString()))
        .andExpect(jsonPath("$.statusMessage")
            .value(messageSource.getMessage("missing.param.mode", null, Locale.getDefault())));
  }

  /**
   * Test for method getSeatmapForGA.
   * @throws Exception If can't read json file
   */
  @Test
  public void retrieveShowDetail() throws Exception {

    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/sample/product/api/sectionGa.json").toURI())));
    this.mvc.perform(get("/SISTIC/products/15122/seatmap/ga")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void getDetailSeatmapProductsSelfPick() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths.get(this.getClass()
        .getResource("/sample/product/api/retrieveDetailSeatmapSelfPick-235687-12079-55247.json")
        .toURI())));

    this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&quantity=0&mode=SP"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void getDetailSeatmapProductsHotShow() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths.get(this.getClass()
        .getResource("/sample/product/api/retrieveDetailSeatmapHotShow-235687-12079-55247.json")
        .toURI())));

    this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&quantity=2&mode=HS"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void getDetailSeatmapProductsBestAvailable() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths.get(this.getClass()
        .getResource(
            "/sample/product/api/retrieveDetailSeatmapBestAvailable-235687-12079-55247.json")
        .toURI())));

    this.mvc
        .perform(
            get("/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&quantity=2&mode=BA"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void get2DSeatmap() throws Exception {
    String mockOverview = random(String.class);
    given(eventService.getOverviewImage(anyListOf(Long.class), anyLong())).willReturn(mockOverview);

    String mockDetail = random(String.class);
    given(eventService.getDetailImages(anyListOf(Long.class), anyLong())).willReturn(mockDetail);

    Seatmap2D seatmap2D = new Seatmap2D(mockOverview, mockDetail);
    String jsonSeatmap2D = objectMapper.writeValueAsString(seatmap2D);

    this.mvc
        .perform(post("/SISTIC/products/218987/seatmap/2d").contentType(MediaType.APPLICATION_JSON)
            .content("[118492204]"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonSeatmap2D)).andDo(print());
  }
}
