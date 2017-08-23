package com.stixcloud.cart.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.InventoryConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by chongye on 11/10/2016.
 */
@ActiveProfiles("test")
@JsonTest
@RunWith(SpringRunner.class)
public class AddToCartRequestJsonTest {
  private JacksonTester<AddToCartRequest> json;

  @Autowired
  private ObjectMapper objectMapper;

  @Before
  public void setUp() throws Exception {
    JacksonTester.initFields(this, objectMapper);
  }

  @Test
  public void serializeJsonForGA() throws Exception {
    AddToCartRequest addToCartRequest =
        new AddToCartRequest.Builder().cartGuid("afabb997-928e-4da3-92eb-fb4e3a400cbe")
            .productId(245632L).priceCatId(12176L).seatSectionId(356880L).priceClassMap("A:2")
            .mode(InventoryConstants.SECTION_TYPE_GA.getName()).build();

    JsonContent<AddToCartRequest> addToCartRequestJson = this.json.write(addToCartRequest);

    assertThat(addToCartRequestJson)
        .isEqualToJson("/cart/json/addToCartRequest_GA.json");

    assertThat(addToCartRequestJson)
        .hasJsonPathStringValue("@.cartGuid")
        .hasJsonPathNumberValue("@.productId")
        .hasJsonPathStringValue("@.priceClassMap")
        .hasJsonPathNumberValue("@.priceCatId")
        .hasJsonPathNumberValue("@.seatSectionId")
        .hasEmptyJsonPathValue("@.inventoryList")
        .hasJsonPathStringValue("@.mode");

    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.cartGuid")
        .isEqualTo(addToCartRequest.getCartGuid());
    assertThat(addToCartRequestJson).extractingJsonPathNumberValue("@.productId")
        .isEqualTo(addToCartRequest.getProductId().intValue());
    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.priceClassMap")
        .isEqualTo(addToCartRequest.getPriceClassMap());
    assertThat(addToCartRequestJson).extractingJsonPathNumberValue("@.priceCatId")
        .isEqualTo(addToCartRequest.getPriceCatId().intValue());
    assertThat(addToCartRequestJson).extractingJsonPathNumberValue("@.seatSectionId")
        .isEqualTo(addToCartRequest.getSeatSectionId().intValue());
    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.inventoryList")
        .isNullOrEmpty();
    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.mode")
        .isEqualTo(addToCartRequest.getMode());
  }

  @Test
  public void deserializeJsonForGA() throws Exception {
    String json =
        new String(Files.readAllBytes(
            Paths.get(this.getClass().getResource("/cart/json/addToCartRequest_GA.json").toURI())));

    ObjectContent<AddToCartRequest> content = this.json.parse(json);

    AddToCartRequest addToCartRequest =
        new AddToCartRequest.Builder().cartGuid("afabb997-928e-4da3-92eb-fb4e3a400cbe")
            .productId(245632L).priceCatId(12176L).seatSectionId(356880L).priceClassMap("A:2")
            .mode(InventoryConstants.SECTION_TYPE_GA.getName()).build();

    assertThat(content).isEqualTo(addToCartRequest);
  }

  @Test
  public void serializeJsonForRS() throws Exception {
    Set<Long> invSet = new HashSet<>(Arrays.asList(121555775L, 121555776L));
    AddToCartRequest addToCartRequest =
        new AddToCartRequest.Builder().cartGuid("afabb997-928e-4da3-92eb-fb4e3a400cbe")
            .productId(245634L).priceCatId(12176L).priceClassMap("A:2")
            .mode(InventoryConstants.SECTION_TYPE_SP.getName()).inventoryList(invSet).build();

    JsonContent<AddToCartRequest> addToCartRequestJson = this.json.write(addToCartRequest);

    assertThat(addToCartRequestJson)
        .isEqualToJson("/cart/json/addToCartRequest_RS.json");

    assertThat(addToCartRequestJson)
        .hasJsonPathStringValue("@.cartGuid")
        .hasJsonPathNumberValue("@.productId")
        .hasJsonPathStringValue("@.priceClassMap")
        .hasJsonPathNumberValue("@.priceCatId")
        .hasEmptyJsonPathValue("@.seatSectionId")
        .hasJsonPathArrayValue("@.inventoryList")
        .hasJsonPathStringValue("@.mode");

    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.cartGuid")
        .isEqualTo(addToCartRequest.getCartGuid());
    assertThat(addToCartRequestJson).extractingJsonPathNumberValue("@.productId")
        .isEqualTo(addToCartRequest.getProductId().intValue());
    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.priceClassMap")
        .isEqualTo(addToCartRequest.getPriceClassMap());
    assertThat(addToCartRequestJson).extractingJsonPathNumberValue("@.priceCatId")
        .isEqualTo(addToCartRequest.getPriceCatId().intValue());
    assertThat(addToCartRequestJson).extractingJsonPathNumberValue("@.seatSectionId").isNull();
    List<Integer> invIdList =
        addToCartRequest.getInventoryList().stream().map(Long::intValue)
            .collect(Collectors.toList());
    assertThat(addToCartRequestJson).extractingJsonPathArrayValue("@.inventoryList")
        .isEqualTo(invIdList);
    assertThat(addToCartRequestJson).extractingJsonPathStringValue("@.mode")
        .isEqualTo(addToCartRequest.getMode());
  }

  @Test
  public void deserializeJsonForRS() throws Exception {
    String json =
        new String(Files.readAllBytes(
            Paths.get(this.getClass().getResource("/cart/json/addToCartRequest_RS.json").toURI())));

    ObjectContent<AddToCartRequest> content = this.json.parse(json);

    Set<Long> invSet = new HashSet<>(Arrays.asList(121555775L, 121555776L));
    AddToCartRequest addToCartRequest =
        new AddToCartRequest.Builder().cartGuid("afabb997-928e-4da3-92eb-fb4e3a400cbe")
            .productId(245634L).priceCatId(12176L).priceClassMap("A:2")
            .mode(InventoryConstants.SECTION_TYPE_SP.getName()).inventoryList(invSet).build();

    assertThat(content).isEqualTo(addToCartRequest);
  }

}
