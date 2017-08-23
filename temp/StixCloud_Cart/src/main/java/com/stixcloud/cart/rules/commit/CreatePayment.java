package com.stixcloud.cart.rules.commit;

import com.stixcloud.cart.EVoucherConstants;
import com.stixcloud.cart.SecurityUtils;
import com.stixcloud.cart.builder.TransactionHashBuilder;
import com.stixcloud.cart.builder.TransactionPaymentBuilder;
import com.stixcloud.cart.repo.EVoucherRepository;
import com.stixcloud.cart.repo.PaymentMethodRepository;
import com.stixcloud.cart.repo.TransactionHashRepository;
import com.stixcloud.cart.repo.TransactionPaymentRepository;
import com.stixcloud.cart.rules.CommitCartRule;
import com.stixcloud.domain.Evoucher;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.Transaction;
import com.stixcloud.domain.TransactionHash;
import com.stixcloud.domain.TransactionPayment;
import org.apache.commons.collections.IteratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This rule is for all product related validation
 */
@SpringRule
public class CreatePayment extends CommitCartRule {
  private static final Logger logger = LogManager.getLogger(CreatePayment.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private TransactionPaymentRepository transactionPaymentRepository;

  @Autowired
  private TransactionHashRepository transactionHashRepository;

  @Autowired
  private PaymentMethodRepository paymentMethodRepository;

  @Autowired
  private EVoucherRepository eVoucherRepository;

  private TransactionPayment transactionPayment;
  private boolean createTransactionPayment = false;
  private BigDecimal evoucherValue;

  @Priority
  public int getPriority() {
    return 2;
  }

  @Condition
  public boolean when() {
    createTransactionPayment = false;
    evoucherValue = null;
    transactionPayment = null;

    return isCartNotEmpty();
  }

  @Action(order = Integer.MIN_VALUE)
  public void createList() {
    this.getCommitOrder().setTransactionPayments(new ArrayList<>());
  }

  @Action
  public void evouchers() throws Exception {
    this.setExecuted(true);
    logger.info("CreatePayment start time "+System.currentTimeMillis());
    Transaction transaction = this.getCommitOrder().getTransaction();

    List<Evoucher> evouchers = this.getShoppingCart().getEvoucherIdList().stream()
        .map(eVoucherId ->
            eVoucherRepository.findOne(eVoucherId)).collect(Collectors.toList());

    if (evouchers != null && !evouchers.isEmpty()) {
      List<TransactionPayment>
          voucherPaymentList =
          evouchers.stream().map(eVoucher -> {
                PaymentMethod paymentMethod =
                    paymentMethodRepository
                        .getPaymentMethod(eVoucher.getEvoucherType(),
                            this.getCommitOrder().getProfileInfoId());

                return TransactionPaymentBuilder.aTransactionPayment()
                    .transactionId(transaction.getTransactionid())
                    .transactionrefnumber(transaction.getTransactionrefnumber())
                    .paymentdate(transaction.getTransactedtime())
                    .paymentmethodname(paymentMethod.getPaymentmethodname())
                    .isintegrated(false)
                    .paymentreferenceno(eVoucher.getEvoucherId())//set evouchercode here
                    .transacedamount(BigDecimal.valueOf(eVoucher.getRedeemValue()))
                    .iscredit(true).ispaymentoffline(false)
                    .paymentMethodId(paymentMethod.getPaymentmethodid())
                    .isevoucherpayment(true).build();
              }
          ).collect(Collectors.toList());

      Iterable<TransactionPayment>
          voucherPaymentItr =
          transactionPaymentRepository.save(voucherPaymentList);
      voucherPaymentItr.forEach(logger::debug);
      List<TransactionPayment>
          evoucherPayments =
          IteratorUtils.toList(voucherPaymentItr.iterator());
      this.getCommitOrder().getTransactionPayments().addAll(evoucherPayments);

     /* evouchers = evouchers.stream().map(evoucher -> {
        evoucher.setStatus(Integer.valueOf(EVoucherConstants.REDEEMED.value()));
        evoucher.setRedeemTxnId(transaction.getTransactionrefnumber());
        return evoucher;
      }).collect(Collectors.toList());
      eVoucherRepository.save(evouchers);*/

      evoucherValue = evouchers.stream()
          .map(Evoucher::getRedeemValue)
          .map(BigDecimal::new)
          .reduce(BigDecimal::add).get();
    }
    logger.info("CreatePayment end time "+System.currentTimeMillis());
  }

  @Action(order = 1)
  public void creditCard() throws Exception {
    this.setExecuted(true);

    Transaction transaction = this.getCommitOrder().getTransaction();
    String creditCard = this.getShoppingCart().getCreditCardNo();

    BigDecimal transactedAmount = transaction.getTotalPayable();
    transactedAmount = (evoucherValue != null ? transactedAmount.subtract(evoucherValue)
        : transactedAmount);

    if (transactedAmount.compareTo(BigDecimal.ZERO) > 0) {
      PaymentMethod paymentMethod =
          paymentMethodRepository
              .getPaymentMethod(this.getShoppingCart().getPaymentMethodCode(),
                  this.getCommitOrder().getProfileInfoId());

      createTransactionPayment = true;
      transactionPayment = TransactionPaymentBuilder.aTransactionPayment()
          .transactionId(transaction.getTransactionid())
          .transactionrefnumber(transaction.getTransactionrefnumber())
          .paymentdate(transaction.getTransactedtime())
          .paymentmethodname(paymentMethod.getPaymentmethodname())
          .isintegrated(true)
//        .paymentstatus(0)//0 is approved, com.ecquaria.commerce.payment.PaymentStatus
          .transacedamount(transactedAmount)
          .ccnumber(SecurityUtils.encrypt(SecurityUtils.maskPartial(creditCard)))
          .ccexpirymonth(Integer.valueOf(this.getShoppingCart().getCreditCardExpiryMM()))
          .ccexpiryyear(Integer.valueOf(this.getShoppingCart().getCreditCardExpiryYY()))
          .cctype(1)//this value is fixed to this in BE
          .iscredit(true).ispaymentoffline(false)
          .paymentMethodId(paymentMethod.getPaymentmethodid())
          .isevoucherpayment(false).build();

      transactionPayment = transactionPaymentRepository.save(transactionPayment);
      logger.debug("Payment = " + transactionPayment.toString());

      this.getCommitOrder().getTransactionPayments().add(transactionPayment);
      this.getCommitOrder().setPayableAmount(transactedAmount);
    } else {
      this.getCommitOrder().setPayableAmount(BigDecimal.ZERO);
      this.getCommitOrder().setIsFullyRedeemed(true);
    }
  }

  @Action(order = 2)
  public void transactionHash() throws Exception {
    if (createTransactionPayment) {
      String creditCard = this.getShoppingCart().getCreditCardNo();

      String salt = SecurityUtils.generateSalt();
      String hash = SecurityUtils.getPBKDF2(creditCard, salt);
      String fingerprint = SecurityUtils.getSHA3Hash(SecurityUtils.maskPartial(creditCard));

      TransactionHash
          transactionHash =
          TransactionHashBuilder.aTransactionHash()
              .txnpaymentid(transactionPayment.getTxnpaymentid())
              .hash(hash)
              .salt(salt)
              .fingerprint(fingerprint)
              .build();

      logger.debug("hash = " + transactionHash.toString());
      transactionHashRepository.save(transactionHash);
    }
  }
}
