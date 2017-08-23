package com.stixcloud.evoucher.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
// @AutoConfigureJsonTesters
@SpringBootTest
public class EVoucherValidationRequestTest {
  @Autowired
  private ObjectMapper objectMapper;

  private JacksonTester<EVoucherValidationRequest> json;
  private List<String> evoucherIdList;
  private String creditCardNo;
  private String paymentMethodCode;

  @Before
  public void setup() throws Exception {
    JacksonTester.initFields(this, objectMapper);

    evoucherIdList = new ArrayList<String>();
    evoucherIdList.add("a5pQCa9p");
    evoucherIdList.add("SHS0GWcSxr");
    evoucherIdList.add("SHShbe0eCM");

  }

  @Test
  public void serializeJsonForApplyStep() throws Exception {

    creditCardNo = "";
    paymentMethodCode = "";
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(evoucherIdList, creditCardNo, paymentMethodCode);
    JsonContent<EVoucherValidationRequest> eVoucherValidationRequestJson = this.json.write(request);
    assertThat(eVoucherValidationRequestJson)
        .isEqualToJson("/evoucher/json/eVoucherRequestForApplyStep.json");
    assertThat(eVoucherValidationRequestJson).hasJsonPathArrayValue("@.evoucherIdList")
        .hasEmptyJsonPathValue("@.paymentMethodCode").hasEmptyJsonPathValue("@.creditCardNo");
    assertThat(eVoucherValidationRequestJson).extractingJsonPathArrayValue("@.evoucherIdList")
        .isEqualTo(request.getEvoucherIdList());
    assertThat(eVoucherValidationRequestJson).extractingJsonPathStringValue("@.paymentMethodCode")
        .isNullOrEmpty();
    assertThat(eVoucherValidationRequestJson).extractingJsonPathStringValue("@.creditCardNo")
        .isNullOrEmpty();
  }

  @Test
  public void serializeJsonForPreCommitStep() throws Exception {

    creditCardNo = "4123456789012349";
    paymentMethodCode = "MASTER";
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(evoucherIdList, creditCardNo, paymentMethodCode);
    JsonContent<EVoucherValidationRequest> eVoucherValidationRequestJson = this.json.write(request);
    assertThat(eVoucherValidationRequestJson)
        .isEqualToJson("/evoucher/json/eVoucherRequestForPreCommitStep.json");
    assertThat(eVoucherValidationRequestJson).hasJsonPathArrayValue("@.evoucherIdList")
        .hasJsonPathStringValue("@.paymentMethodCode").hasJsonPathStringValue("@.creditCardNo");
    assertThat(eVoucherValidationRequestJson).extractingJsonPathArrayValue("@.evoucherIdList")
        .isEqualTo(request.getEvoucherIdList());
    assertThat(eVoucherValidationRequestJson).extractingJsonPathStringValue("@.paymentMethodCode")
        .isEqualTo(request.getPaymentMethodCode());
    assertThat(eVoucherValidationRequestJson).extractingJsonPathStringValue("@.creditCardNo")
        .isEqualTo(request.getCreditCardNo());
  }

  @Test
  public void deserializeJsonForApplyStep() throws Exception {

    creditCardNo = "";
    paymentMethodCode = "";
    String json = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/evoucher/json/eVoucherRequestForApplyStep.json").toURI())));
    ObjectContent<EVoucherValidationRequest> content = this.json.parse(json);
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(evoucherIdList, creditCardNo, paymentMethodCode);
    assertThat(content).isEqualTo(request);
  }

  @Test
  public void deserializeJsonForPreCommitStep() throws Exception {

    creditCardNo = "4123456789012349";
    paymentMethodCode = "MASTER";
    String json = new String(Files.readAllBytes(Paths.get(this.getClass()
        .getResource("/evoucher/json/eVoucherRequestForPreCommitStep.json").toURI())));
    ObjectContent<EVoucherValidationRequest> content = this.json.parse(json);
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(evoucherIdList, creditCardNo, paymentMethodCode);
    assertThat(content).isEqualTo(request);
  }
}
