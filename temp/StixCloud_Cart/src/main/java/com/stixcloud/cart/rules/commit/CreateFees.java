package com.stixcloud.cart.rules.commit;

import com.stixcloud.cart.FeeConstants;
import com.stixcloud.cart.IFeeService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.builder.TransactionPaymentBuilder;
import com.stixcloud.cart.builder.TransactionPaymentFeeBuilder;
import com.stixcloud.cart.builder.TransactionProductFeeBuilder;
import com.stixcloud.cart.builder.TransactionProductPaymentBuilder;
import com.stixcloud.cart.repo.FeeRuleTableRepository;
import com.stixcloud.cart.repo.TransactionPaymentFeeRepository;
import com.stixcloud.cart.repo.TransactionProductFeeRepository;
import com.stixcloud.cart.repo.TransactionProductPaymentRepository;
import com.stixcloud.cart.rules.CommitCartRule;
import com.stixcloud.domain.FeeRule;
import com.stixcloud.domain.FeeRuleTable;
import com.stixcloud.domain.TransactionLineItem;
import com.stixcloud.domain.TransactionLineItemProduct;
import com.stixcloud.domain.TransactionPayment;
import com.stixcloud.domain.TransactionPaymentFee;
import com.stixcloud.domain.TransactionProductFee;
import com.stixcloud.domain.TransactionProductPayment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This rule is for all product related validation
 */
@SpringRule
public class CreateFees extends CommitCartRule {
  private static final Logger logger = LogManager.getLogger(CreateFees.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private FeeRuleTableRepository feeRuleTableRepository;

  @Autowired
  private IFeeService feeService;

  @Autowired
  private TransactionProductFeeRepository transactionProductFeeRepository;
  @Autowired
  private TransactionPaymentFeeRepository transactionPaymentFeeRepository;
  @Autowired
  private TransactionProductPaymentRepository transactionProductPaymentRepository;

  @Priority
  public int getPriority() {
    return 4;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    logger.info("createFees start time "+System.currentTimeMillis());
    TransactionLineItem handlingLineItem =
        this.getCommitOrder().getTransactionLineItems().stream()
            .filter(tli -> tli.getLineitemtype() == 2 && tli.getChargetype()
                .equals(FeeConstants.FeeChargeType.OUTSIDE_CHARGE.getValue())
                && tli.getFeecategory()
                .equals(FeeConstants.FeeCategory.DELIVERY_BASED.getName()) && !tli.getIsfeewaived())
            .findFirst().orElse(null);

    Integer ticketsQuantity = this.getCommitOrder().getTransactionLineItems().stream()
        .filter(tli -> tli.getQuantity() != null)
        .mapToInt(TransactionLineItem::getQuantity).sum();

    /**
     * Reverse sort the payment methods
     * payment methods are sorted in the below order
     * - eVoucher
     *  - high value
     *  - lower value
     * - CC
     */
    List<TransactionPayment>
        transactionPayments =
        this.getCommitOrder().getTransactionPayments().stream()
            .map(tp -> TransactionPaymentBuilder.aTransactionPayment(tp).build())
            .sorted((a, b) -> {
              if (a.getIsevoucherpayment() & b.getIsevoucherpayment()) {
                return b.getTransacedamount().compareTo(a.getTransacedamount());
              } else if (b.getIsevoucherpayment() && !a.getIsevoucherpayment()) {
                return 1;
              } else {
                return -1;
              }
            }).collect(Collectors.toList());

    for (TransactionLineItemProduct lineItemProduct : this.getCommitOrder()
        .getTransactionLineItemProducts()) {

      TransactionLineItem transactionLineItem =
          this.getCommitOrder().getTransactionLineItems().stream()
              .filter(
                  li -> li.getTxnlineitemid().equals(lineItemProduct.getTxnLineItemId()))
              .findFirst().get();

      //only create fees if it is not waived
      TransactionLineItem
          bookingFeeLineItem =
          this.getCommitOrder().getTransactionLineItems().stream()
              .filter(li -> !li.getIsfeewaived())
              .filter(li -> li.getChargetype() != null && li.getChargetype()
                  .equals(FeeConstants.FeeChargeType.OUTSIDE_CHARGE.getValue()))
              .filter(li -> li.getProductId() != null && li.getProductId()
                  .equals(transactionLineItem.getProductId()))
              .filter(li -> li.getTxnlineitemid() > lineItemProduct.getTxnLineItemId()).findFirst()
              .orElse(null);

      FeeRuleTable feeRuleTable =
          feeRuleTableRepository
              .getFeeRuleTable(transactionLineItem.getProductId());

      FeeRule
          feeRule =
          feeService.getMatchingFeeRule(transactionLineItem.getProductId(),
              transactionLineItem.getPriceCategoryId(),
              transactionLineItem.getPriceclasscode(),
              feeRuleTable);

      BigDecimal unitPrice = transactionLineItem.getUnitprice();
      BigDecimal
          bookingFee =
          (bookingFeeLineItem != null ? bookingFeeLineItem.getUnitprice() : BigDecimal.ZERO);
      BigDecimal
          handlingFee =
          (handlingLineItem != null ? handlingLineItem.getTotallineitemvalue()
              .divide(BigDecimal.valueOf(ticketsQuantity), 5, RoundingMode.HALF_UP)
              : BigDecimal.ZERO);

      //booking fee might be null if it is waived?
      TransactionProductFee
          bookingProductFee =
          (bookingFeeLineItem != null ? createTransactionProductFee(lineItemProduct,
              bookingFeeLineItem, feeRule.getFeeruleid(), bookingFee) : null);

      //handling fee might be null if it is waived
      TransactionProductFee
          handlingProductFee =
          (handlingLineItem != null ? createTransactionProductFee(lineItemProduct, handlingLineItem,
              feeRule.getFeeruleid(), handlingFee) : null);

      for (Iterator<TransactionPayment> itr = transactionPayments.iterator(); itr.hasNext(); ) {
        TransactionPayment transactionPayment = itr.next();

        BigDecimal paymentAmount = transactionPayment.getTransacedamount();

        //if payment is more than ticket price
        if (paymentAmount.compareTo(BigDecimal.ZERO) != 0
            && unitPrice.compareTo(BigDecimal.ZERO) == 1) {
          CreatePaymentCommand
              createPaymentCommand =
              new CreatePaymentCommand(lineItemProduct, transactionLineItem, null, unitPrice,
                  null, transactionPayment, paymentAmount).invoke();
          paymentAmount = createPaymentCommand.getPaymentAmount();
          unitPrice = createPaymentCommand.getFeePrice();
        }

        if (//paymentAmount.compareTo(BigDecimal.ZERO) != 0
          //   && bookingFee.compareTo(BigDecimal.ZERO) == 1
            /*&&*/ bookingFeeLineItem != null //BookingFee can be 0 for scbwaiver
            && bookingProductFee != null) {
          CreatePaymentCommand
              createPaymentCommand =
              new CreatePaymentCommand(lineItemProduct, bookingFeeLineItem, feeRule, bookingFee,
                  bookingProductFee,
                  transactionPayment, paymentAmount).invoke();
          paymentAmount = createPaymentCommand.getPaymentAmount();
          bookingFee = createPaymentCommand.getFeePrice();
        }

        if (paymentAmount.compareTo(BigDecimal.ZERO) != 0
            && handlingFee.compareTo(BigDecimal.ZERO) == 1
            && handlingLineItem != null
            && handlingProductFee != null) {
          CreatePaymentCommand
              createPaymentCommand =
              new CreatePaymentCommand(lineItemProduct, handlingLineItem, feeRule, handlingFee,
                  handlingProductFee, transactionPayment, paymentAmount).invoke();
          paymentAmount = createPaymentCommand.getPaymentAmount();
          handlingFee = createPaymentCommand.getFeePrice();
        }

        if (paymentAmount.compareTo(BigDecimal.ZERO) == 0) {
          //remove payment method once there's nothing to deuduct
          itr.remove();
        } else {
          //update the available amount left to deduct
          transactionPayment.setTransacedamount(paymentAmount);
        }
        if (unitPrice.compareTo(BigDecimal.ZERO) == 0
            //&& bookingFee.compareTo(BigDecimal.ZERO) == 0
            && handlingFee.compareTo(BigDecimal.ZERO) == 0) {
          //escape the for loop if everything is already paid
          break;
        }
      }
    }
    logger.info("createFees end time "+System.currentTimeMillis());
  }

  private TransactionProductFee createTransactionProductFee(
      TransactionLineItemProduct lineItemProduct,
      TransactionLineItem feeLineItem, Long feeRuleId, BigDecimal feeamount) {
    TransactionProductFee
        transactionProductFee =
        TransactionProductFeeBuilder.aTransactionProductFee()
            .txnProductId(lineItemProduct.getTxnProductId())
            .transactionrefnumber(lineItemProduct.getTransactionrefnumber())
            .txnLineItemId(feeLineItem.getTxnlineitemid())
            .txnCodeId(getCommitOrder().getTransactionCode().getTransactioncodeid())//SALES
            .feeId(feeLineItem.getFeeId())
            .feeRuleId(feeRuleId)
            .feeamount(feeamount)
            .feename(feeLineItem.getFeename())
            .levyby(feeLineItem.getLevyby())
            .charge(FeeConstants.FeeChargeType.OUTSIDE_CHARGE.getValue())
            .iscredit(1)
            .build();
    return transactionProductFeeRepository.save(transactionProductFee);
  }

  private TransactionPaymentFee createTransactionPaymentFee(
      String transactionReferenceNumber, TransactionLineItem feeLineItem,
      Long feeRuleId, Long paymentMethodId,
      Long transactionProductFeeId, BigDecimal amount) {
    TransactionPaymentFee
        transactionPaymentFee =
        TransactionPaymentFeeBuilder.aTransactionPaymentFee()
            .txnPrdtFeeId(transactionProductFeeId)
            .transactionrefnumber(transactionReferenceNumber)
            .txnCodeId(getCommitOrder().getTransactionCode().getTransactioncodeid())//SALES
            .feeId(feeLineItem.getFeeId())
            .feeRuleId(feeRuleId)
            .feeamount(amount)
            .feename(feeLineItem.getFeename())
            .levyby(feeLineItem.getLevyby())
            .chargetype(FeeConstants.FeeChargeType.OUTSIDE_CHARGE.getValue())
            .paymentMethodId(paymentMethodId)
            .build();
    return transactionPaymentFeeRepository.save(transactionPaymentFee);
  }

  private TransactionProductPayment createTransactionProductPayment(
      TransactionLineItemProduct lineItemProduct,
      Long paymentMethodId, BigDecimal unitPrice,
      BigDecimal paidAmount) {
    TransactionProductPayment
        transactionProductPayment =
        TransactionProductPaymentBuilder.aTransactionProductPayment()
            .txnProductId(lineItemProduct.getTxnProductId())
            .transactionrefnumber(lineItemProduct.getTransactionrefnumber())
            .paymentMethodId(paymentMethodId)
            .productValue(unitPrice)
            .paidAmount(paidAmount)
            .build();
    return transactionProductPaymentRepository.save(transactionProductPayment);
  }

  private class CreatePaymentCommand {
    private TransactionLineItemProduct lineItemProduct;
    private TransactionLineItem transactionLineItem;
    private FeeRule feeRule;
    private BigDecimal feePrice;
    private TransactionProductFee transactionProductFee;
    private TransactionPayment transactionPayment;
    private BigDecimal paymentAmount;

    public CreatePaymentCommand(TransactionLineItemProduct lineItemProduct,
                                TransactionLineItem transactionLineItem,
                                FeeRule feeRule, BigDecimal feePrice,
                                TransactionProductFee transactionProductFee,
                                TransactionPayment transactionPayment, BigDecimal paymentAmount) {
      this.lineItemProduct = lineItemProduct;
      this.transactionLineItem = transactionLineItem;
      this.feeRule = feeRule;
      this.feePrice = feePrice;
      this.transactionProductFee = transactionProductFee;
      this.transactionPayment = transactionPayment;
      this.paymentAmount = paymentAmount;
    }

    public BigDecimal getFeePrice() {
      return feePrice;
    }

    public BigDecimal getPaymentAmount() {
      return paymentAmount;
    }

    public CreatePaymentCommand invoke() {
      BigDecimal amountToPay = feePrice;
      if (paymentAmount.compareTo(feePrice) >= 0) {
        paymentAmount = paymentAmount.subtract(feePrice);
        feePrice = BigDecimal.ZERO;
      } else {
        feePrice = feePrice.subtract(paymentAmount);
        amountToPay = paymentAmount;
        paymentAmount = BigDecimal.ZERO;
      }
      if (feeRule == null && transactionProductFee == null) {
        createTransactionProductPayment(lineItemProduct,
            transactionPayment.getPaymentMethodId(),
            transactionLineItem.getUnitprice(), amountToPay);
      } else {
        createTransactionPaymentFee(lineItemProduct.getTransactionrefnumber(),
            transactionLineItem, feeRule.getFeeruleid(), transactionPayment.getPaymentMethodId(),
            transactionProductFee.getTxnproductfeeid(), amountToPay);
      }
      return this;
    }
  }
}
