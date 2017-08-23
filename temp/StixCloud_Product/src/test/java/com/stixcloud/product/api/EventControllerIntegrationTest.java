package com.stixcloud.product.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.product.TenantPropertiesTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chongye on 19-Aug-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerIntegrationTest {
  private final Logger logger = LogManager.getLogger(EventControllerIntegrationTest.class);
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  private TestRestTemplate restTemplate;

  private JacksonTester<ShowTimes> showTimesJacksonTester;
  private JacksonTester<SeatmapOverview> seatmapOverviewJacksonTester;
  private JacksonTester<SeatmapAvailability[]> seatmapAvailabilityJacksonTester;
  private JacksonTester<PriceTable[]> priceTableJacksonTester;
  private JacksonTester<DetailSeatmap> detailSeatmapJacksonTester;
  private JacksonTester<JsonResponse> jsonResponseJacksonTester;
  private JacksonTester<PriceClass[]> priceClassJacksonTester;
  private JacksonTester<Seatmap2D> seatmap2DJacksonTester;
  private JacksonTester<SectionGaList> seatmapGAJacksonTester;

  @Before
  public void setup() throws Exception {
    JacksonTester.initFields(this, objectMapper);
    TenantPropertiesTest.setUp();
  }

  @Test
  @Sql("/integration_tests/internetcontentproducts-data.sql")
  public void showTimingList() throws Exception {
    String uri = "/SISTIC/icc/rent2016";

    ResponseEntity<ShowTimes> entity = restTemplate
        .getForEntity(uri, ShowTimes.class);
    ShowTimes showTimes = entity.getBody();

    logger.debug(showTimes);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
//    assertEquals(showTimesJson, showTimes);
    assertThat(showTimesJacksonTester.write(showTimes))
        .isEqualToJson("/integration_tests/internetcontentproducts-result.json");
  }

  @Test
  @Sql({"/integration_tests/seatmapoverview-data.sql",
      "/integration_tests/2dseatmap-data.sql"})
  @Sql(statements = "ALTER TABLE SEATMAP_OVERVIEW_PRODUCTS drop column promotion_password_id;",
      executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void getSeatmapOverview() throws Exception {
    String uri = "/SISTIC/products/254999/seatmap/overview";

    ResponseEntity<SeatmapOverview> entity = restTemplate
        .getForEntity(uri, SeatmapOverview.class);
    SeatmapOverview restResult = entity.getBody();

    logger.debug("\n\n\n [getSeatmapOverview] " + restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(seatmapOverviewJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/seatmapoverview-result.json");
  }

  @Test
  @Sql("/integration_tests/seatmapavailability-data.sql")
  public void getSeatmapAvailability() throws Exception {
    String uri = "/SISTIC/products/243851/seatmap/availability";

    ResponseEntity<SeatmapAvailability[]> entity = restTemplate
        .getForEntity(uri, SeatmapAvailability[].class);
    SeatmapAvailability[] restResult = entity.getBody();

    logger.debug(Arrays.asList(restResult));
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(seatmapAvailabilityJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/seatmapavailability-result.json");
  }

  @Test
  @Sql("/integration_tests/pricetableinfo-data.sql")
  public void getSeatmapPriceTable() throws Exception {
    String uri = "/SISTIC/products/243851/seatmap/pricetable";

    ResponseEntity<PriceTable[]> entity = restTemplate
        .getForEntity(uri, PriceTable[].class);
    PriceTable[] restResult = entity.getBody();

    logger.debug(Arrays.asList(restResult));
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(priceTableJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/pricetableinfo-result.json");
  }

  @Test
  @Sql("/integration_tests/detailseatmap-ba.sql")
  public void getDetailSeatmapBestAvailable() throws Exception {
    String
        uri =
        "/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&quantity=2&mode=BA";

    ResponseEntity<DetailSeatmap> entity = restTemplate
        .getForEntity(uri, DetailSeatmap.class);
    DetailSeatmap restResult = entity.getBody();

    logger.debug(restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(detailSeatmapJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/detailseatmap-ba.json");
  }

  @Test
  @Sql("/integration_tests/detailseatmap-hotshow.sql")
  public void getDetailSeatmapHotShow() throws Exception {
    String
        uri =
        "/SISTIC/products/243851/seatmap/availability?priceCatId=18284&seatSectionId=356153&quantity=1&mode=HS";

    ResponseEntity<DetailSeatmap> entity = restTemplate
        .getForEntity(uri, DetailSeatmap.class);
    DetailSeatmap restResult = entity.getBody();

    logger.debug(restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(detailSeatmapJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/detailseatmap-hotshow.json");
  }

  @Test
  @Sql("/integration_tests/detailseatmap-selfpick.sql")
  public void getDetailSeatmapSelfPick() throws Exception {
    String
        uri =
        "/SISTIC/products/235687/seatmap/availability?priceCatId=12079&seatSectionId=55247&quantity=1&mode=SP";

    ResponseEntity<DetailSeatmap> entity = restTemplate
        .getForEntity(uri, DetailSeatmap.class);
    DetailSeatmap restResult = entity.getBody();

    logger.debug(restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(detailSeatmapJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/detailseatmap-selfpick.json");
  }

  @Test
  @Sql("/integration_tests/confirmseat-reserve.sql")
  public void confirmSeatsReserve() throws Exception {
    String uri = "/SISTIC/products/243851/seats";

    ConfirmSeat request = new ConfirmSeat();
    request.setReservedSeatList(
        new ArrayList<>(
            Arrays.asList(120943457L, 120943458L, 120943459L, 120943460L, 120943461L, 120943462L,
                120943463L, 120943464L, 120943465L, 120943466L)));

    ResponseEntity<JsonResponse> entity = restTemplate
        .postForEntity(uri, request, JsonResponse.class);
    JsonResponse restResult = entity.getBody();

    logger.debug(restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(jsonResponseJacksonTester.write(restResult))
        .extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo("SUCCESS");
  }

  @Test
  @Sql("/integration_tests/confirmseat-release.sql")
  public void confirmSeatsRelease() throws Exception {
    String uri = "/SISTIC/products/243851/seats";

    ConfirmSeat request = new ConfirmSeat();
    request.setReleasedSeatList(
        new ArrayList<>(
            Arrays.asList(120943457L, 120943458L, 120943459L, 120943460L, 120943461L, 120943462L,
                120943463L, 120943464L, 120943465L, 120943466L)));

    ResponseEntity<JsonResponse> entity = restTemplate
        .postForEntity(uri, request, JsonResponse.class);
    JsonResponse restResult = entity.getBody();

    logger.debug(restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(jsonResponseJacksonTester.write(restResult))
        .extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo("SUCCESS");
  }

  @Test
  @Sql("/integration_tests/tickettype.sql")
  //TODO : test will fail due to price value issue, need to discuss with the rest of them
  public void getTicketTypeList() throws Exception {
    String uri = "/SISTIC/products/243851/tickettype?priceCatId=18284";

    ResponseEntity<PriceClass[]> entity = restTemplate
        .getForEntity(uri, PriceClass[].class);
    PriceClass[] restResult = entity.getBody();

    logger.debug(Arrays.asList(restResult));
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(priceClassJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/tickettype.json");
  }

  @Test
  @Sql("/integration_tests/2dseatmap-data.sql")
  public void get2DSeatmap() throws Exception {
    String uri = "/SISTIC/products/243851/seatmap/2d";

    List<Long> seatInvIds = new ArrayList<>(Arrays.asList(120943544L));

    ResponseEntity<Seatmap2D> entity = restTemplate
        .postForEntity(uri, seatInvIds, Seatmap2D.class);
    Seatmap2D restResult = entity.getBody();

    logger.debug(restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(seatmap2DJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/2dseatmap.json");
  }

  @Test
  @Sql("/integration_tests/seatmapga.sql")
  public void getSeatmapForGA() throws Exception {
    String uri = "/SISTIC/products/245629/seatmap/ga";

    ResponseEntity<SectionGaList> entity = restTemplate
        .getForEntity(uri, SectionGaList.class);
    SectionGaList restResult = entity.getBody();

    logger.debug(Arrays.asList(restResult));
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(seatmapGAJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/seatmapga.json");
  }

  @Test
  public void defaultExceptionHandler() throws Exception {
    String uri = "/SISTIC/products/235687/seatmap/availability?priceCatId=12079&quantity=2&mode=BA";

    ResponseEntity<JsonResponse> entity = restTemplate
        .getForEntity(uri, JsonResponse.class);
    assertEquals(entity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertThat(jsonResponseJacksonTester.write(entity.getBody()))
        .extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo("Missing Seat Section ID");
    assertThat(jsonResponseJacksonTester.write(entity.getBody()))
        .extractingJsonPathStringValue("@.httpStatus")
        .isEqualTo(HttpStatus.BAD_REQUEST.toString());
  }

  @Test
  public void getShowTimingsNotFound() throws Exception {
    String uri = "/SISTIC/icc/rent2016xd";

    //<ShowTimes> showTimesJacksonTester
    ResponseEntity<ShowTimes> entity = restTemplate
        .getForEntity(uri, ShowTimes.class);
    ShowTimes showTimes = entity.getBody();

    logger.debug("[getNoShowFound] >> " + showTimes);
    assertEquals(entity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertThat(showTimes.getSummaryImagePath()).isNull();
    assertThat(showTimes.getIcAttributesList()).isEmpty();
    assertThat(showTimes.getShowTimingList()).isEmpty();
  }

  @Test
  @Sql("/integration_tests/seatmapoverview-data.sql")
  @Sql(statements = "ALTER TABLE SEATMAP_OVERVIEW_PRODUCTS drop column promotion_password_id;",
      executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  public void getSeatmapOverviewNotFound() throws Exception {
    String uri = "/SISTIC/products/24385159/seatmap/overview";

    ResponseEntity<SeatmapOverview> entity = restTemplate
        .getForEntity(uri, SeatmapOverview.class);
    SeatmapOverview restResult = entity.getBody();

    logger.debug("[getSeatmapOverviewNotFound] >> " + restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(restResult).isNull();
  }

  //TODO : Wait for bug fix then do this test case
  @Ignore
  @Test
  @Sql("/integration_tests/detailseatmap-ba.sql")
  public void getSeatmapAvailabilityNotFound() throws Exception {
    String
        uri =
        "/products/235687/seatmap/availability?priceCatId=120799&seatSectionId=552479&quantity=29&mode=BA";

    ResponseEntity<DetailSeatmap> entity = restTemplate
        .getForEntity(uri, DetailSeatmap.class);
    DetailSeatmap restResult = entity.getBody();

    logger.debug("[getSeatmapAvailabilityNotFound] >> " + restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(restResult).isNull();
  }

  @Test
  @Sql("/integration_tests/tickettype.sql")
  public void getTicketTypeListNotFound() throws Exception {
    String uri = "/SISTIC/products/243851/tickettype?priceCatId=182849";

    ResponseEntity<PriceClass[]> entity = restTemplate
        .getForEntity(uri, PriceClass[].class);
    PriceClass[] restResult = entity.getBody();

    logger.debug(Arrays.asList(restResult));
    logger.debug("[getTicketTypeListNotFound] >> " + Arrays.asList(restResult));
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(restResult).isNullOrEmpty();
  }

  @Test
  public void getSeatmapForGANotFound() throws Exception {
    String uri = "/SISTIC/products/2456299/seatmap/ga";

    ResponseEntity<SectionGaList> entity = restTemplate
        .getForEntity(uri, SectionGaList.class);
    SectionGaList restResult = entity.getBody();

    logger.debug("[getSeatmapForGANotFound] >> " + Arrays.asList(restResult));
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(seatmapGAJacksonTester.write(restResult))
        .isEqualToJson("/integration_tests/seatmapganotfound.json");
  }

  @Test
  public void confirmSeatsReserveNotFound() throws Exception {
    String uri = "/SISTIC/products/243851/seats";

    ConfirmSeat request = new ConfirmSeat();
    request.setReservedSeatList(
        new ArrayList<>(
            Arrays.asList(1209434579L)));

    ResponseEntity<JsonResponse> entity = restTemplate
        .postForEntity(uri, request, JsonResponse.class);
    JsonResponse restResult = entity.getBody();

    logger.debug(restResult);
    logger.debug("[confirmSeatsReserveNotFound] >> " + restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(jsonResponseJacksonTester.write(restResult))
        .extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo("Error reserving seats");
  }

  @Test
  public void releasedSeatNotFound() throws Exception {
    String uri = "/SISTIC/products/243851/seats";

    ConfirmSeat request = new ConfirmSeat();
    request.setReleasedSeatList(
        new ArrayList<>(
            Arrays.asList(1209434579L)));

    ResponseEntity<JsonResponse> entity = restTemplate
        .postForEntity(uri, request, JsonResponse.class);
    JsonResponse restResult = entity.getBody();

    logger.debug(restResult);
    logger.debug("[releasedSeatNotFound] >> " + restResult);
    assertEquals(entity.getStatusCode(), HttpStatus.OK);
    assertThat(jsonResponseJacksonTester.write(restResult))
        .extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo("Error releasing seats");
  }

}
