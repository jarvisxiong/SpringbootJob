package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.IPaymentGatewayAPIService;
import com.stixcloud.cart.PostCommitCartException;
import com.stixcloud.cart.api.PaymentConfigDetailsJson;
import com.stixcloud.cart.api.PostCommitRequest;
import com.stixcloud.cart.repo.TransactionPaymentRepository;
import com.stixcloud.cart.repo.TransactionRepository;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.cart.util.Utils;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.domain.Transaction;
import com.stixcloud.domain.TransactionPayment;
import com.stixcloud.paymentgateway.api.CupRequest;
import com.stixcloud.paymentgateway.api.CupResponse;
import com.stixcloud.paymentgateway.api.MigsRequest;
import com.stixcloud.paymentgateway.api.MigsResponse;
import com.stixcloud.paymentgateway.api.PaymentGatewayConfig;
import com.stixcloud.paymentgateway.api.PaymentGatewayConstants;
import com.stixcloud.paymentgateway.api.TelemoneyConstants;
import com.stixcloud.paymentgateway.api.TelemoneyRequest;
import com.stixcloud.paymentgateway.api.TelemoneyResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

@SpringRule
public class Payment extends PostCommitCartRule {
  private static final Logger logger = LogManager.getLogger(Payment.class);

  @Autowired
  private IPaymentGatewayAPIService paymentGatewayAPIService;

  @Autowired
  private TransactionPaymentRepository transactionPaymentRepository;

  @Value("${paymentgateway.gateway.sinopay}")
  private String sinopayPaymentList;

  @Value("${paymentgateway.gateway.migs}")
  private String migsPaymentList;

  @Value("${paymentgateway.gateway.telemoneyAmex}")
  private String telemoneyPaymentListAmex;

  @Value("${paymentgateway.gateway.telemoneyDiners}")
  private String telemoneyPaymentListDiners;

  @Autowired
  private TransactionRepository transactionRepository;

  private BigDecimal totalAmount;
  private PostCommitRequest postCommitRequest;
  private PaymentGatewayConfig paymentGatewayConfig;
  private CurrencyUnit currencyUnit;
  private String paymentGatewayStatus;

  public void setPostCommitRequest(PostCommitRequest postCommitRequest) {
    this.postCommitRequest = postCommitRequest;
  }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE + 1;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && (!(this.getShoppingCart().getIsFullyRedeemed()));
  }

  @Action
  public void then() throws PostCommitCartException{
    PaymentConfigDetailsJson
        paymentConfigDetailsJson =
        paymentGatewayAPIService
            .getPaymentGatewayConfiguration(TenantContextHolder.getTenant().getName(), Utils.getToken(),
                this.getShoppingCart().getPaymentMethodCode());
    paymentGatewayConfig = paymentConfigDetailsJson.getPaymentGatewayConfig();

    paymentGatewayStatus = paymentConfigDetailsJson.getPaymentGatewayStatus();
    List<TransactionPayment> txnPayments = transactionPaymentRepository
        .findByTransactionRefNumber(this.getShoppingCart().getTransactionReferenceNumber());
    totalAmount =
        txnPayments.stream().filter(tp -> Boolean.TRUE.equals(tp.getIscredit() && (tp.getIsevoucherpayment()!=null && !tp.getIsevoucherpayment())))
            .map(TransactionPayment::getTransacedamount).reduce(BigDecimal::add).get();
    
    if(totalAmount.doubleValue()!=this.getShoppingCart().getPayableAmount().doubleValue()){
    	logger.info("Calculated total amount "+totalAmount+" not matching with precommit total amount "+this.getShoppingCart().getPayableAmount());
    	throw new PostCommitCartException("Calculated total amount not matching with precommit total amount");
    }

    currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
  }

  @Action(order = 1)
  public void migs() throws Exception {
    if (migsPaymentList.contains(this.getShoppingCart().getPaymentMethodCode())
        && PaymentGatewayConstants.ONLINE.equals(paymentGatewayStatus)) {
      this.setExecuted(true);
      ShoppingCart shoppingCart = this.getShoppingCart();

      DecimalFormat df = new DecimalFormat();
      df.setMaximumFractionDigits(0);
      df.setGroupingSize(0);
      BigDecimal vpcAmount = this.totalAmount.multiply(BigDecimal.valueOf(100L));

      MigsRequest
          migsRequest =
          new MigsRequest.Builder()._3DSecureOn(
              (StringUtils.isNotEmpty(postCommitRequest.getVpc3DSECI()) || StringUtils
                  .isNotEmpty(postCommitRequest.getVpcVerToken())))
              .creditCardNumber(shoppingCart.getCreditCardNo())
              .expiryMonth(shoppingCart.getCreditCardExpiryMM())
              .expiryYear(shoppingCart.getCreditCardExpiryYY())
              .vpc3DSECI(postCommitRequest.getVpc3DSECI())
              .vpc3DSenrolled(postCommitRequest.getVpc3DSenrolled())
              .vpc3DSstatus(postCommitRequest.getVpc3DSstatus())
              .vpc3DSXID(postCommitRequest.getVpc3DSXID())
              .vpcVerToken(postCommitRequest.getVpcVerToken())
              .vpcVerType(postCommitRequest.getVpcVerType())
              .vpcVerStatus(postCommitRequest.getVpcVerStatus())
              .vpcVerSecurityLevel(postCommitRequest.getVpcVerSecurityLevel())
              // PaymentGateway Config Related
              .vpcMerchant((String)
                  paymentGatewayConfig.getAdditionalProperties().get("merchant.id"))
              .vpcAccessCode((String)
                  paymentGatewayConfig.getAdditionalProperties().get("merchant.access.code"))
              .vpcUrl((String)
                  paymentGatewayConfig.getAdditionalProperties().get("sales.url"))
              .vpcCommand("pay")
              .vpcOrderInfo(shoppingCart.getTransactionReferenceNumber()).vpcVersion("1")
              .vpcAmount(df.format(vpcAmount))
              .vpcMerchantTxnRef(shoppingCart.getTransactionReferenceNumber())
              .build();
         logger.info("About to hit paymentgateway migs api ");
      MigsResponse
          migsResponse =
          paymentGatewayAPIService
              .processPayment(TenantContextHolder.getTenant().getName(), Utils.getToken(), migsRequest);

      logger.info(
          "Payment Rule -> Migs Response " + "Request " + migsResponse.getReqData() + "Reponse "
              + migsResponse.getResData());

      PaymentResponse
          paymentResponse =
          new PaymentResponse.Builder()
              .paymentReferenceNo(migsResponse.getVpcTransactionNo())
              .paymentStatus(Integer.parseInt(migsResponse.getVpcTxnResponseCode()))
              .approvalCode(migsResponse.getVpcAuthorizeId())
              .totalPaymentAmount(
                  Money.of((Double.valueOf(migsResponse.getVpcAmount()) / 100), currencyUnit))
              .orderId(migsResponse.getVpcTransactionNo())
              .orderStatus(Integer.valueOf(migsResponse.getVpcTxnResponseCode()))
              .reqData(migsResponse.getReqData())
              .respData(migsResponse.getResData())
              .respMessage(migsResponse.getVpcMessage())
              .build();
      this.getPostCommitOrder().setPaymentResponse(paymentResponse);
    }
  }

  @Action(order = 1)
  public void sinopay() throws Exception {
    if (sinopayPaymentList.contains(this.getShoppingCart().getPaymentMethodCode())
        && PaymentGatewayConstants.ONLINE.equals(paymentGatewayStatus)) {
      this.setExecuted(true);
      ShoppingCart shoppingCart = this.getShoppingCart();
      Transaction
          transaction =
          transactionRepository
              .findByTransactionRefNumber(shoppingCart.getTransactionReferenceNumber());
      CupRequest.Builder
          builder =
          new CupRequest.Builder()
              .version((String) paymentGatewayConfig.getAdditionalProperties().get("cup.version"))
              .charset((String) paymentGatewayConfig.getAdditionalProperties().get("cup.encode"))
              .signMethod(
                  (String) paymentGatewayConfig.getAdditionalProperties().get("cup.sign.method"))
              .transType("01")
              .merId((String)
                  paymentGatewayConfig.getAdditionalProperties().get("merchant.user.id"))
              .orderNumber(shoppingCart.getTransactionReferenceNumber())
              .orderTime(transaction.getTransactedtime()
                  .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
              .postUrl((String) paymentGatewayConfig.getAdditionalProperties().get("query.url"));
      CupRequest cupRequest = builder.build();
      String signature =
          new CupRequest.PostDataBuilder(cupRequest, false).buildCUPSignature(
              (String) paymentGatewayConfig.getAdditionalProperties().get("merchant.password"));
      cupRequest = builder.signature(signature).build();
      logger.info("About to hit paymentgateway SINOPAY api ");
      CupResponse
          cupResponse =
          paymentGatewayAPIService
              .processPayment(TenantContextHolder.getTenant().getName(), Utils.getToken(), cupRequest);

      PaymentResponse paymentResponse =
          new PaymentResponse.Builder()
              .paymentReferenceNo(cupResponse.getQid())
              .paymentStatus(Integer.parseInt(cupResponse.getRespCode()))
              .approvalCode(cupResponse.getRespCode())
              .totalPaymentAmount(StringUtils.isNotBlank(cupResponse.getOrderAmount()) ?
                  Money.of((Double.valueOf(cupResponse.getOrderAmount()) / 100), currencyUnit)
                  : null)
              .orderId(cupResponse.getOrderNumber())
              .orderStatus(Integer.parseInt(cupResponse.getRespCode()))
              .reqData(cupResponse.getReqData())
              .respData(cupResponse.getRespData())
              .respMessage(cupResponse.getRespMsg())
              .build();
      this.getPostCommitOrder().setPaymentResponse(paymentResponse);

    }
  }

  @Action(order = 1)
  public void telemoneyAmex() throws Exception {
    if (telemoneyPaymentListAmex.contains(this.getShoppingCart().getPaymentMethodCode())
        && PaymentGatewayConstants.ONLINE.equals(paymentGatewayStatus)) {
      this.setExecuted(true);
      ShoppingCart shoppingCart = this.getShoppingCart();

      TelemoneyRequest telemoneyRequest = new TelemoneyRequest.Builder()
          .mid((String)
              paymentGatewayConfig.getAdditionalProperties()
                  .get(TelemoneyConstants.RC_LABEL_MERCHANT_ID))
          .ref(shoppingCart.getTransactionReferenceNumber())
          .cur(TelemoneyConstants.CURRENCY_SGD)
          .paytype(TelemoneyConstants.PT_AMEX)
          .transtype(TelemoneyConstants.ACTION_SALE)
          .amt(this.totalAmount.toString())
          .recurrentid(TelemoneyConstants.RECURRENT_INIT)
          .subsequentmid((String)
              paymentGatewayConfig.getAdditionalProperties()
                  .get(TelemoneyConstants.RC_LABEL_MERCHANT_ID))
          .ccnum(shoppingCart.getCreditCardNo())
          .ccdate(shoppingCart.getCreditCardExpiryYY() + shoppingCart.getCreditCardExpiryMM())
          .cccvv(shoppingCart.getCreditCardCSC().toString())
          ._3ds((StringUtils.isNotEmpty(postCommitRequest.getVpc3DSECI()) || StringUtils
              .isNotEmpty(postCommitRequest.getVpcVerToken()) ? "Y" : "N"))
          ._3dsstatus(postCommitRequest.getVpc3DSstatus())
          .eci(postCommitRequest.getVpc3DSECI())
          .cavv(postCommitRequest.getVpcVerToken())
          .xid(postCommitRequest.getVpc3DSXID())
          .refundauthcode("")
          .originalref("")
          .postURL((String)
              paymentGatewayConfig.getAdditionalProperties().get("sales.url"))
          .build();
      logger.debug("About to hit paymentgateway TELEMONEY api for Amex");
      TelemoneyResponse telemoneyResponse = paymentGatewayAPIService
          .processPayment(TenantContextHolder.getTenant().getName(), Utils.getToken(), telemoneyRequest);

      PaymentResponse
          paymentResponse =
          new PaymentResponse.Builder()
              .paymentReferenceNo(telemoneyResponse.gettMRefNo())
              .paymentStatus((telemoneyResponse.gettMStatus().equalsIgnoreCase("YES") ? 0 : 1))
              .approvalCode(StringUtils.isNotEmpty(telemoneyResponse.gettMRecurrentId()) == true
                  ? telemoneyResponse.gettMRecurrentId() : telemoneyResponse.gettMApprovalCode())
              .totalPaymentAmount(
                  Money.of((Double.valueOf(telemoneyResponse.gettMDebitAmt())), currencyUnit))
              .orderId(telemoneyResponse.gettMRefNo())
              .orderStatus((telemoneyResponse.gettMStatus().equalsIgnoreCase("YES") ? 0 : 1))
              .reqData(telemoneyResponse.getReqData())
              .respData(telemoneyResponse.getRespData())
              .respMessage(telemoneyResponse.gettMErrorMsg())
              .build();

      this.getPostCommitOrder().setPaymentResponse(paymentResponse);
    }
  }

  @Action(order = 1)
  public void telemoneyDiners() throws Exception {
    if (telemoneyPaymentListDiners.contains(this.getShoppingCart().getPaymentMethodCode())
        && PaymentGatewayConstants.ONLINE.equals(paymentGatewayStatus)) {
      this.setExecuted(true);
      ShoppingCart shoppingCart = this.getShoppingCart();

      TelemoneyRequest telemoneyRequest = new TelemoneyRequest.Builder()
          .mid((String)
              paymentGatewayConfig.getAdditionalProperties()
                  .get(TelemoneyConstants.RC_LABEL_MERCHANT_ID))
          .ref(shoppingCart.getTransactionReferenceNumber())
          .cur(TelemoneyConstants.CURRENCY_SGD)
          .paytype(TelemoneyConstants.PT_DINERS)
          .transtype(TelemoneyConstants.ACTION_SALE)
          .amt(this.totalAmount.toString())
          .recurrentid(TelemoneyConstants.RECURRENT_INIT)
          .subsequentmid((String)
              paymentGatewayConfig.getAdditionalProperties()
                  .get(TelemoneyConstants.RC_LABEL_MERCHANT_ID))
          .ccnum(shoppingCart.getCreditCardNo())
          .ccdate(shoppingCart.getCreditCardExpiryYY() + shoppingCart.getCreditCardExpiryMM())
          //.cccvv(shoppingCart.getCreditCardCSC().toString())
          ._3ds((StringUtils.isNotEmpty(postCommitRequest.getVpc3DSECI()) || StringUtils
              .isNotEmpty(postCommitRequest.getVpcVerToken()) ? "Y" : "N"))
          ._3dsstatus(postCommitRequest.getVpc3DSstatus())
          .eci(postCommitRequest.getVpc3DSECI())
          .cavv(postCommitRequest.getVpcVerToken())
          .xid(postCommitRequest.getVpc3DSXID())
          .refundauthcode("")
          .originalref("")
          .postURL((String)
              paymentGatewayConfig.getAdditionalProperties().get("sales.url"))
          .build();
      logger.debug("About to hit paymentgateway TELEMONEY api for Diners ");
      TelemoneyResponse telemoneyResponse = paymentGatewayAPIService
          .processPayment(TenantContextHolder.getTenant().getName(), Utils.getToken(), telemoneyRequest);

      PaymentResponse
          paymentResponse =
          new PaymentResponse.Builder()
              .paymentReferenceNo(telemoneyResponse.gettMRefNo())
              .paymentStatus((telemoneyResponse.gettMStatus().equalsIgnoreCase("YES") ? 0 : 1))
              .approvalCode(StringUtils.isNotEmpty(telemoneyResponse.gettMRecurrentId()) == true
                  ? telemoneyResponse.gettMRecurrentId() : telemoneyResponse.gettMApprovalCode())
              .totalPaymentAmount(
                  Money.of((Double.valueOf(telemoneyResponse.gettMDebitAmt())), currencyUnit))
              .orderId(telemoneyResponse.gettMRefNo())
              .orderStatus((telemoneyResponse.gettMStatus().equalsIgnoreCase("YES") ? 0 : 1))
              .reqData(telemoneyResponse.getReqData())
              .respData(telemoneyResponse.getRespData())
              .respMessage(telemoneyResponse.gettMErrorMsg())
              .build();

      this.getPostCommitOrder().setPaymentResponse(paymentResponse);
    }
  }

  @Action(order = 1)
  public void dummyGateway() throws Exception {
    if (PaymentGatewayConstants.TEST.equals(paymentGatewayStatus)) {
      logger.debug("About to hit Dummy Payment");
      ShoppingCart shoppingCart = this.getShoppingCart();
      PaymentResponse
          paymentResponse =
          new PaymentResponse.Builder()
              .paymentReferenceNo("")
              .paymentStatus(0)
              .approvalCode("")
              .totalPaymentAmount(
                  Money.of((Double.valueOf(totalAmount.toString())), "SGD"))
              .orderId(shoppingCart.getTransactionReferenceNumber())
              .orderStatus(0)
              .reqData("")
              .respData("")
              .respMessage("Sales Payment successfully made to Dummy Gateway")
              .build();
      this.getPostCommitOrder().setPaymentResponse(paymentResponse);
    }
  }
}
