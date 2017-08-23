package com.stixcloud.cart.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.MessageSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by chongye on 11/10/2016.
 */
@ActiveProfiles("test")
@JsonTest
@RunWith(SpringRunner.class)
@ImportAutoConfiguration({MessageSourceAutoConfiguration.class})
public class CartResponseJsonTest {
  private JacksonTester<JsonResponse> json;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MessageSource messageSource;
  private final String cartGuid = "afabb997-928e-4da3-92eb-fb4e3a400cbe";
  private String successMessage;
  private String failureMessage;

  @Before
  public void setUp() throws Exception {
    JacksonTester.initFields(this, objectMapper);
    successMessage =
        messageSource.getMessage("addtocart.success", null, LocaleContextHolder.getLocale());
    failureMessage =
        messageSource.getMessage("addtocart.failure", null, LocaleContextHolder.getLocale());
  }

  @Test
  public void serializeJson_Success() throws Exception {

    JsonResponse
        cartResponse =
        new JsonResponse.Builder().httpStatus(HttpStatus.OK.toString())
            .cartGuid(cartGuid).statusMessage(successMessage).build();
    JsonContent<JsonResponse> addToCartResponseJson = this.json.write(cartResponse);
    System.out.println("\n print addCartResponse >> " + addToCartResponseJson);
    assertThat(addToCartResponseJson)
        .isEqualToJson("/cart/json/AddToCartResponse_Success.json");

    assertThat(addToCartResponseJson)
        .hasJsonPathStringValue("@.httpStatus")
        .hasJsonPathStringValue("@.cartGuid")
        .hasJsonPathStringValue("@.statusMessage");

    assertThat(addToCartResponseJson).extractingJsonPathStringValue("@.httpStatus")
        .isEqualTo(cartResponse.getHttpStatus());
    assertThat(addToCartResponseJson).extractingJsonPathStringValue("@.cartGuid")
        .isEqualTo(cartResponse.getAdditionalProperties().get("cartGuid"));
    assertThat(addToCartResponseJson).extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo(cartResponse.getStatusMessage());
  }

  @Test
  public void deserializeJson_Success() throws Exception {
    String json =
        new String(Files.readAllBytes(
            Paths
                .get(this.getClass().getResource("/cart/json/AddToCartResponse_Success.json")
                    .toURI())));

    ObjectContent<JsonResponse> content = this.json.parse(json);

    JsonResponse
        cartResponse =
        new JsonResponse.Builder().httpStatus(HttpStatus.OK.toString())
            .cartGuid(cartGuid).statusMessage(successMessage).build();

    assertThat(content).isEqualTo(cartResponse);
  }

  @Test
  public void serializeJson_Failure() throws Exception {
    JsonResponse
        cartResponse =
        new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
            .statusMessage(failureMessage).build();
    JsonContent<JsonResponse> addToCartResponseJson = this.json.write(cartResponse);

    assertThat(addToCartResponseJson)
        .isEqualToJson("/cart/json/AddToCartResponse_Failure.json");

    assertThat(addToCartResponseJson)
        .hasJsonPathStringValue("@.httpStatus")
        .doesNotHaveJsonPathValue("@.cartGuid")
        .hasJsonPathStringValue("@.statusMessage");

    assertThat(addToCartResponseJson).extractingJsonPathStringValue("@.httpStatus")
        .isEqualTo(cartResponse.getHttpStatus());
    assertThat(addToCartResponseJson).extractingJsonPathStringValue("@.statusMessage")
        .isEqualTo(cartResponse.getStatusMessage());
  }

  @Test
  public void deserializeJson_Failure() throws Exception {
    String json =
        new String(Files.readAllBytes(
            Paths
                .get(this.getClass().getResource("/cart/json/AddToCartResponse_Failure.json")
                    .toURI())));

    ObjectContent<JsonResponse> content = this.json.parse(json);
    JsonResponse
        cartResponse =
        new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
            .statusMessage(failureMessage).build();

    assertThat(content).isEqualTo(cartResponse);
  }
}
