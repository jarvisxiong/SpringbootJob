package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.IPaymentGatewayAPIService;
import com.stixcloud.cart.IPaymentGatewayTxnService;
import com.stixcloud.cart.TransactionPaymentDetails;
import com.stixcloud.cart.api.PaymentConfigDetailsJson;
import com.stixcloud.cart.repo.PaymentMethodRepository;
import com.stixcloud.cart.repo.TransactionPaymentRepository;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.cart.util.Utils;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.PaymentGatewayTxn;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.domain.TransactionPayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

@SpringRule
public class UpdatePayment extends PostCommitCartRule {
  public static final long PAYMENT_GATEWAY_TXN_FAILURE = 0;
  public static final long PAYMENT_GATEWAY_TXN_SUCCESS = 1;
  public static final long PAYMENT_GATEWAY_CON_ERROR = 2;
  public static final long PAYMENT_GATEWAY_TXN_TYPE_SALES = 0;
  private static final Logger logger = LogManager.getLogger(UpdatePayment.class);
  @Autowired
  IPaymentGatewayAPIService paymentGatewayAPIService;
  @Autowired
  IPaymentGatewayTxnService paymentGatewayTxnService;
  @Autowired
  private TransactionPaymentRepository transactionPaymentRepository;
  @Autowired
  private PaymentMethodRepository paymentMethodRepository;

  private CurrencyUnit currencyUnit;

  @Priority
  public int getPriority() {
    return 2;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void payment() throws Exception {
    if (!this.getShoppingCart().getIsFullyRedeemed()) {
      this.setExecuted(true);

      PaymentResponse paymentResponse = this.getPostCommitOrder().getPaymentResponse();
      ShoppingCart cart = this.getShoppingCart();
      logger.debug(
          "Updating Payment Details for transaction " + cart.getTransactionReferenceNumber());
      PaymentMethod
          pm =
          paymentMethodRepository
              .getPaymentMethod(cart.getPaymentMethodCode(), TenantContextHolder
                  .getTenant().getProfileInfoId());
      PaymentConfigDetailsJson
          paymentConfigDetailsJson =
          paymentGatewayAPIService
              .getPaymentGatewayConfiguration(TenantContextHolder.getTenant().getName(), Utils.getToken(),
                  cart.getPaymentMethodCode());
      List<TransactionPayment> transactionPayments = transactionPaymentRepository
          .findByTransactionRefNumber(cart.getTransactionReferenceNumber());
      List<TransactionPayment> transactionPaymentlist = new ArrayList<TransactionPayment>();
      List<PaymentGatewayTxn> paymentGatewayTxnList = new ArrayList<PaymentGatewayTxn>();
      transactionPayments.forEach(transactionPayment -> {
        if (transactionPayment.getPaymentmethodname().equals(pm.getPaymentmethodname())) {
          //Transaction Payment
          if (isPaymentApproved()) { //only if approved
            transactionPayment.setPaymentreferenceno(paymentResponse.getPaymentReferenceNo());
            transactionPayment
                .setPaymentstatus(paymentResponse.getPaymentStatus());
            if (paymentConfigDetailsJson.getPaymentGatewayConfig() != null
                && paymentConfigDetailsJson.getPaymentGatewayConfig().getAdditionalProperties()
                != null) {
              transactionPayment.setRevenuecentreid(
                  Long.valueOf((String)
                      paymentConfigDetailsJson.getPaymentGatewayConfig().getAdditionalProperties()
                          .get("revenue.center.id")));
            }
            transactionPaymentlist.add(transactionPayment);
          }
          //PaymentGatewayTxn
          PaymentGatewayTxn pgt = new PaymentGatewayTxn();
          pgt.setPaymentId(transactionPayment.getTxnpaymentid());
          pgt.setPaytxnref(paymentResponse.getPaymentReferenceNo());
          pgt.setTxnamount(transactionPayment.getTransacedamount());
          pgt.setTxntype(PAYMENT_GATEWAY_TXN_TYPE_SALES);
          pgt.setTxndate(OffsetDateTime.now());
          pgt.setStatus(
              isPaymentApproved() ? PAYMENT_GATEWAY_TXN_SUCCESS : PAYMENT_GATEWAY_TXN_FAILURE);
          pgt.setErrmsg(paymentResponse.getRespMessage());
          pgt.setPaymentGateway(pm.getPaymentGatewayId());
          if (paymentConfigDetailsJson.getPaymentGatewayConfig() != null
              && paymentConfigDetailsJson.getPaymentGatewayConfig().getAdditionalProperties()
              != null) {
            pgt.setRevenueCentre(
                Long.valueOf((String)
                    paymentConfigDetailsJson.getPaymentGatewayConfig().getAdditionalProperties()
                        .get("revenue.center.id")));
          }
          pgt.setApprovalcode(paymentResponse.getApprovalCode());
          pgt.setReqdata(paymentResponse.getReqData());
          pgt.setRespdata(paymentResponse.getRespData());
          paymentGatewayTxnList.add(pgt);
        }

      });
      logger.debug("transactionPaymentlist " + transactionPaymentlist);
      transactionPaymentRepository.save(transactionPaymentlist);
      logger.debug("paymentGatewayTxnList " + paymentGatewayTxnList);
      paymentGatewayTxnService.createPaymentGateway(paymentGatewayTxnList);
    }
  }

  @Action(order = Integer.MAX_VALUE)
  public void preparePaymentDetailList() throws Exception {
    logger.debug("Preparing Payment Details");
    ShoppingCart cart = this.getShoppingCart();
    List<TransactionPayment> transactionPayments = transactionPaymentRepository
        .findByTransactionRefNumber(cart.getTransactionReferenceNumber());

    currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    List<TransactionPaymentDetails>
        transactionPaymentDetailsList =
        new ArrayList<TransactionPaymentDetails>();
    transactionPayments.forEach(transactionPayment -> {
      PaymentMethod
          pm =
          paymentMethodRepository
              .getPaymentMethodByName(transactionPayment.getPaymentmethodname(), TenantContextHolder
                  .getTenant().getProfileInfoId());

      //Add this path to include txnPaymentList aka paymentList PostCommitRespond
      TransactionPaymentDetails paymentDetails = new TransactionPaymentDetails();
      paymentDetails.setPaymentMethodCode(pm.getPaymentmethodcode());
      paymentDetails
          .setSubAmount(Money.of(transactionPayment.getTransacedamount(), currencyUnit));
      transactionPaymentDetailsList.add(paymentDetails);
    });

    this.getPostCommitOrder().setPaymentList(transactionPaymentDetailsList);
    logger.debug("Finished Preparing Payment Details");
  }
}
