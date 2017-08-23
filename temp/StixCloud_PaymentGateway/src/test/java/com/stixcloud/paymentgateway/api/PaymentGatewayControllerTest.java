package com.stixcloud.paymentgateway.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.paymentgateway.IPaymentGatewayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sengkai on 11/21/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentGatewayControllerTest {
  private final Logger logger = LogManager.getLogger(PaymentGatewayControllerTest.class);

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TestRestTemplate testRestTemplate;
  @MockBean
  private RestTemplate restTemplate;

  TenantContextHolder tenantContextHolder;
  @MockBean
  IPaymentGatewayService iPaymentGatewayService;

  @Before
  public void setUp() throws Exception {
    
  }

 @Test
  @Sql("/sql/paymentGatewayConfiguration.sql")
  public void getPaymentGatewayConfiguration() throws Exception {

    Map<String, Object> gatewayConfig = new HashMap<String, Object>();
    gatewayConfig.put("merchant.user.id", "SAWOPERATORAMA");
    PaymentConfigDetailsJson paymentConfigDetailsJson =
        new PaymentConfigDetailsJson.Builder()
            .paymentMethodCode("MASTER_SEPANG")
            .paymentGatewayConfig(new PaymentGatewayConfig.Builder()
                .merchantId("TEST20161116")
                .enableCSC(true)
                .clientServer("https://migs.mastercard.com.au/vpcdps")
                .secureType("threeDSecure")
                .secureCode("15CA5304F58CCB9B6047972B1DD9FB8B")
                .accessCode("68D54E4F")
                .redirectUrl("/SisticWebApp/PostConfirmation.do")
                .returnUrl("https://booking.sistic.stixcloud.com/SisticWebApp/redirect3D.jsp")
                .additionalProperties(gatewayConfig)
                .build())
            .build();

    //assertThat(paymentConfigDetailsJson).hasNoNullFieldsOrProperties();
    PaymentConfigDetailsJson paymentConfigDetailsJsonFromFile =
        objectMapper
            .readValue(this.getClass().getResource("/json/paymentGatewayConfig.json"),
                PaymentConfigDetailsJson.class);

    assertThat(paymentConfigDetailsJson)
        .isEqualToComparingFieldByFieldRecursively(paymentConfigDetailsJsonFromFile);
    logger.debug(
        " [Json from File] >> \n" + paymentConfigDetailsJsonFromFile.getPaymentGatewayConfig()
            .toString());
  }

  @Test
  public void migsPaymentUnSuccesfull() throws Exception {
      
      String str="vpc_Amount=504&vpc_BatchNo=0&vpc_Command=pay&vpc_Locale=en_SG&vpc_MerchTxnRef=20170215-000018&vpc_Merchant=TEST001500059803&vpc_Message=E5431-02161914%3A+Invalid+Field+%3A+Field+in+error%3A+%27transaction.merchantTransactionReference%27%2C++value+%2720170215-000018%27+-+reason%3A+Merchant+Transaction+Reference+must+be+set+to+a+unique+value&vpc_OrderInfo=20170215-000018&vpc_TransactionNo=0&vpc_TxnResponseCode=7&vpc_Version=1";
      when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(str);
      
    MigsRequest migsRequest = null;
    migsRequest =
        objectMapper.readValue(this.getClass().getResourceAsStream("/json/migsRequestUnSuccessfull.json"),
            MigsRequest.class);
    MigsResponse
    mockedMigsResponse =
	        objectMapper.readValue(this.getClass().getResourceAsStream("/json/migsResponseUnSuccessfull.json"),
	        	MigsResponse.class);
    MigsResponse
        migsResponse =
        testRestTemplate.postForObject("/SISTIC/payment/migs", migsRequest, MigsResponse.class);
    logger.debug(migsResponse.toString());
    assertThat(migsResponse).isEqualToComparingFieldByFieldRecursively(mockedMigsResponse);
  }
  @Test
  public void migsPaymentSuccesfull() throws Exception {
      String str="vpc_AVSRequestCode=Z&vpc_AVSResultCode=Unsupported&vpc_AcqAVSRespCode=Unsupported&vpc_AcqCSCRespCode=Unsupported&vpc_AcqResponseCode=00&vpc_Amount=50400&vpc_AuthorizeId=631018&vpc_BatchNo=20170216&vpc_CSCResultCode=Unsupported&vpc_Card=MC&vpc_Command=pay&vpc_Locale=en_SG&vpc_MerchTxnRef=JUnitTest1&vpc_Merchant=TEST001500059803&vpc_Message=Approved&vpc_OrderInfo=JUnitTest1&vpc_ReceiptNo=704720631018&vpc_TransactionNo=2000000042&vpc_TxnResponseCode=0&vpc_Version=1";
      when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(str);
      
    MigsRequest migsRequest = null;
    migsRequest =
        objectMapper.readValue(this.getClass().getResourceAsStream("/json/migsRequestSuccessfull.json"),
            MigsRequest.class);
    MigsResponse
    mockedMigsResponse =
	        objectMapper.readValue(this.getClass().getResourceAsStream("/json/migsResponseSuccessfull.json"),
	        	MigsResponse.class);
    MigsResponse
        migsResponse =
        testRestTemplate.postForObject("/SISTIC/payment/migs", migsRequest, MigsResponse.class);
    logger.debug(migsResponse.toString());
    assertThat(migsResponse).isEqualToComparingFieldByFieldRecursively(mockedMigsResponse);
  }

  @Test
  public void cUPPaymentUnSuccesfull() throws Exception {
      
      String str="charset=UTF-8&cupReserved=&qid=&respCode=301&respMsg=Merchant information do not exist&respTime=20170217110950&signMethod=SHA&signature=7e9d45dca874cf1d57786d783aba4788d2ae40470a77a0bd4d53e5a9dabd44ee&version=1.0.0";
      when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(str);
      
      when(iPaymentGatewayService.isResponseValid(any())).thenReturn(Boolean.FALSE);
      
    CupRequest cupRequest = null;
    cupRequest =
        objectMapper.readValue(this.getClass().getResourceAsStream("/json/cupRequestUnSuccessfull.json"),
        	CupRequest.class);
    CupResponse
    mockedCupResponse =
	        objectMapper.readValue(this.getClass().getResourceAsStream("/json/cupResponseUnSuccessfull.json"),
	        	CupResponse.class);
    CupResponse
    cupResponse =
        testRestTemplate.postForObject("/SISTIC/payment/cup", cupRequest, CupResponse.class);
    logger.debug(cupResponse.toString());
    assertThat(cupResponse).isEqualToComparingFieldByFieldRecursively(mockedCupResponse);
  }
  
  @Test
  public void cUPPaymentSuccesfull() throws Exception {
      
      String str="charset=UTF-8&cupReserved={cardNumber=6212******315017&cardType=01&orderAmount=950&orderCurrency=702&payMode=LitePay}&merAbbr=SISTIC&merId=875070279220001&orderAmount=950&orderCurrency=702&orderNumber=20160301-000470&qid=201603011517174722778&respCode=00&respMsg=Payment successfully&respTime=20160301151841&signature=8ddf274710feafddd98bae7bb2ead65c60678e327520abba3581b0077cdadfbd&signMethod=SHA&transType=01&version=1.0.0";
      when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(str);
      
      when(iPaymentGatewayService.isResponseValid(any())).thenReturn(Boolean.TRUE);
      
    CupRequest cupRequest = null;
    cupRequest =
        objectMapper.readValue(this.getClass().getResourceAsStream("/json/cupRequestSuccessfull.json"),
        	CupRequest.class);
    CupResponse
    mockedCupResponse =
	        objectMapper.readValue(this.getClass().getResourceAsStream("/json/cupResponseSuccessfull.json"),
	        	CupResponse.class);
    CupResponse
    cupResponse =
        testRestTemplate.postForObject("/SISTIC/payment/cup", cupRequest, CupResponse.class);
    logger.debug(cupResponse.toString());
    assertThat(cupResponse).isEqualToComparingFieldByFieldRecursively(mockedCupResponse);
  }
 
  @Test
  public void teleMoneyPaymentUnSuccesfull() throws Exception {
      
      String str="TM_MCode=20110811002&TM_RefNo=20160620-000722&TM_TrnType=sale&TM_SubTrnType=&TM_Status=NO&TM_Error=9506&TM_Currency=SGD&TM_DebitAmt=425.25&TM_PaymentType=5&TM_BankRespCode=12&TM_ApprovalCode=&TM_ErrorMsg=TRANSACTION FAILED - Sorry, duplicate records found. Please try again. Message reference #9506&TM_UserField1=&TM_UserField2=&TM_UserField3=&TM_UserField4=&TM_UserField5=&TM_Original_RefNo=&TM_CCLast4Digit=0005&TM_RecurrentId=INIT&TM_CCNum=xxxxxxxxxxx0005&TM_ExpiryDate=1707&TM_IPP_FirstPayment=&TM_IPP_LastPayment=&TM_IPP_MonthlyPayment=&TM_IPP_TransTenure=&TM_IPP_TotalInterest=&TM_IPP_DownPayment=&TM_IPP_MonthlyInterest=&TM_OriginalPayType=&TM_Signature=";
      when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(str);
      
    TelemoneyRequest teleRequest = null;
    teleRequest =
        objectMapper.readValue(this.getClass().getResourceAsStream("/json/teleMoneyRequestUnSuccessfull.json"),
        	TelemoneyRequest.class);
    TelemoneyResponse
    mockedTeleResponse =
	        objectMapper.readValue(this.getClass().getResourceAsStream("/json/teleMoneyResponseUnSuccessfull.json"),
	        	TelemoneyResponse.class);
    TelemoneyResponse
    teleResponse =
        testRestTemplate.postForObject("/SISTIC/payment/telemoney", teleRequest, TelemoneyResponse.class);
    logger.debug(teleResponse.toString());
    assertThat(teleResponse).isEqualToComparingFieldByFieldRecursively(mockedTeleResponse);
  }
  
  @Test
  public void telePaymentSuccesfull() throws Exception {
      
      String str="TM_MCode=20110811002&TM_RefNo=20160620-002722&TM_TrnType=sale&TM_SubTrnType=&TM_Status=YES&TM_Error=&TM_Currency=SGD&TM_DebitAmt=425.25&TM_PaymentType=5&TM_BankRespCode=00&TM_ApprovalCode=336120&TM_ErrorMsg=&TM_UserField1=&TM_UserField2=&TM_UserField3=&TM_UserField4=&TM_UserField5=&TM_Original_RefNo=&TM_CCLast4Digit=0005&TM_RecurrentId=20110811002-20022017-1487561260333&TM_CCNum=xxxxxxxxxxx0005&TM_ExpiryDate=1707&TM_IPP_FirstPayment=&TM_IPP_LastPayment=&TM_IPP_MonthlyPayment=&TM_IPP_TransTenure=&TM_IPP_TotalInterest=&TM_IPP_DownPayment=&TM_IPP_MonthlyInterest=&TM_OriginalPayType=&TM_Signature=";
      when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(str);
      
    TelemoneyRequest teleRequest = null;
    teleRequest =
        objectMapper.readValue(this.getClass().getResourceAsStream("/json/teleMoneyRequestSuccessfull.json"),
        	TelemoneyRequest.class);
    TelemoneyResponse
    mockedTeleResponse =
	        objectMapper.readValue(this.getClass().getResourceAsStream("/json/teleMoneyResponseSuccessfull.json"),
	        	TelemoneyResponse.class);
    TelemoneyResponse
    teleResponse =
        testRestTemplate.postForObject("/SISTIC/payment/telemoney", teleRequest, TelemoneyResponse.class);
    logger.debug(teleResponse.toString());
    assertThat(teleResponse).isEqualToComparingFieldByFieldRecursively(mockedTeleResponse);
  }
}
