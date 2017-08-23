package com.stixcloud.evoucher.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
// @JsonTest
// @ImportAutoConfiguration({MessageSourceAutoConfiguration.class})
@SpringBootTest
public class EvoucherValidationResponseTest {

  @Autowired
  private ObjectMapper objectMapper;

  private JacksonTester<EvoucherValidationResponse> json;
  private List<EVoucherValidation> validationResultList;
  private CurrencyUnit currencyUnit;
  @Before
  public void setup() {
    Locale.setDefault(new Locale("en", "SG"));
    currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    JacksonTester.initFields(this, objectMapper);
    validationResultList = new ArrayList<EVoucherValidation>();
    EVoucherValidation result1 = new EVoucherValidation("a5pQCa9p", Money.of(10, currencyUnit),
        "DBS_E_VOUCHER", Boolean.TRUE, "VALIDATION_RULE_PASS");
    EVoucherValidation result2 = new EVoucherValidation("SHS0GWcSxr", Money.of(10, currencyUnit),
        "DBS_E_VOUCHER", Boolean.FALSE, "VALIDATION_RULE_FAILED");
    EVoucherValidation result3 = new EVoucherValidation("SHShbe0eCM", Money.of(10, currencyUnit),
        "DBS_E_VOUCHER", Boolean.FALSE, "VALIDATION_BASIC_EXPIRED");
    validationResultList.add(result1);
    validationResultList.add(result2);
    validationResultList.add(result3);

  }

  @Test
  public void serializeJson() throws Exception {

    EvoucherValidationResponse response =
        new EvoucherValidationResponse(Boolean.FALSE, validationResultList, Money.of(10, currencyUnit));
    JsonContent<EvoucherValidationResponse> validationResponseJson = this.json.write(response);
    assertThat(validationResponseJson)
        .isEqualToJson("/evoucher/json/EVoucherValidationResponse.json");
    assertThat(validationResponseJson).hasJsonPathBooleanValue("@.exceedMaxQtyAllowed")
        .hasJsonPathArrayValue("@.validationResultList").hasJsonPathValue("@.totalRedeemValue");

    assertThat(validationResponseJson).extractingJsonPathBooleanValue("@.exceedMaxQtyAllowed")
        .isEqualTo(response.isExceedMaxQtyAllowed());
//    assertThat(validationResponseJson).extractingJsonPathValue("@.totalRedeemValue")
//    .isEqualTo(response.getTotalRedeemValue());
    // TO-DO
    // assertThat(validationResponseJson).extractingJsonPathArrayValue("@.validationResultList")
    // .isEqualTo(validationResultList);
  }

  @Test
  public void deserializeJson() throws Exception {

    String json = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/evoucher/json/EVoucherValidationResponse.json").toURI())));
    ObjectContent<EvoucherValidationResponse> content = this.json.parse(json);
    EvoucherValidationResponse response =
        new EvoucherValidationResponse(Boolean.FALSE, validationResultList, Money.of(10, currencyUnit));
    assertThat(content).isEqualTo(response);
  }


}
