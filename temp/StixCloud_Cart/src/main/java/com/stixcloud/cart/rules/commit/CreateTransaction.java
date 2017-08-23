package com.stixcloud.cart.rules.commit;

import com.stixcloud.cart.FeeConstants;
import com.stixcloud.cart.FeeFiringModeEnum;
import com.stixcloud.cart.TransactionCommitStatus;
import com.stixcloud.cart.TransactionRefIdService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.builder.TransactionBuilder;
import com.stixcloud.cart.repo.ChannelRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.PosRepository;
import com.stixcloud.cart.repo.TransactionRepository;
import com.stixcloud.cart.rules.CommitCartRule;
import com.stixcloud.domain.Channel;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.Pos;
import com.stixcloud.domain.Transaction;
import com.stixcloud.domain.TransactionRefId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.hibernate.HibernateException;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import javax.money.MonetaryAmount;

/**
 * This rule is for all product related validation
 */
@SpringRule
public class CreateTransaction extends CommitCartRule {
  private static final Logger logger = LogManager.getLogger(CreateTransaction.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private TransactionRefIdService txnRefIdService;
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private PatronProfileRepository patronProfileRepository;
  @Autowired
  private PosRepository posRepository;
  @Autowired
  private ChannelRepository channelRepository;

  private Transaction transaction;

  public Transaction getTransaction() {
    return transaction;
  }

  @Priority
  public int getPriority() {
    return 1;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    logger.info("CreateTransaction start time "+System.currentTimeMillis());

    CommitOrder commitOrder = this.getCommitOrder();
    ShoppingCartJson shoppingCartJson = commitOrder.getShoppingCartJson();

    MonetaryAmount lineItemsTotal = shoppingCartJson.getLineItemTotal();

    MonetaryAmount
        deliveryPrice =
        shoppingCartJson.getCommonDeliveryMethod().getDeliveryMethodJsons().stream()
            .filter(dm -> dm.getCode().equals(this.getShoppingCart().getDeliveryMethodCode()))
            .findFirst().get().getCharge();

    BigDecimal totalPayable = Money.from(lineItemsTotal.add(deliveryPrice)).getNumberStripped();

    Integer
        totalTickets =
        shoppingCartJson.getLineItemList().stream().mapToInt(cli -> cli.getQuantity().intValue())
            .sum();

    PatronProfile patronProfile = patronProfileRepository.findOne(commitOrder.getPatronProfileId());

    Pos
        pos =
        posRepository.getPosByProfileAndUserInfoId(commitOrder.getProfileInfoId(),
            commitOrder.getUserProfileId());

    Channel channel = channelRepository.getChannelByProfileInfoId(commitOrder.getProfileInfoId());

    transaction =
        TransactionBuilder.aTransaction()
            .transactionrefnumber(getTransactionReferenceNumber())
            .txnCodeId(commitOrder.getTransactionCode().getTransactioncodeid())//SALES
            .transactedBy(commitOrder.getUserProfileId())
            .ownerId(commitOrder.getUserProfileId())
            .transactedProfile(commitOrder.getProfileInfoId())
            .ownerProfile(commitOrder.getProfileInfoId())
            .posId(pos.getPosid())//SISTIC internet
            .patronId(patronProfile.getPatronprofileid())
            .patronname(patronProfile.getFirstname())
            .patronidentitynum(patronProfile.getIdentityno())
            .orderstatus(TransactionCommitStatus.PENDING.getValue())
            .paymentmode(FeeConstants.PaymentMode.MOTO.getValue())//MOTO
            .feefired(FeeFiringModeEnum.NOT_FIRED.getValue())
            .totalPayable(totalPayable).totalTickets(totalTickets)
            .transactedtime(OffsetDateTime.now())
            .updatedBy(1L)//SYSTEM USER?
            .channeltype(channel.getChannelname())
            .updateddate(OffsetDateTime.now())
            .build();
    transaction = transactionRepository.save(transaction);

    logger.debug("Transaction = " + transaction.toString());
    logger.info("CreateTransaction end time "+System.currentTimeMillis());

    this.getCommitOrder().setTransaction(transaction);
  }

  private String getTransactionReferenceNumber() throws HibernateException {
    TransactionRefId txnRefId = txnRefIdService.getTransactionRefIdForToday();
    String seq = null;
    if (txnRefId != null) {
      logger.debug(
          "Locking and Updating the Sequence number " + txnRefId.getTxnseqid() + " for today "
              + txnRefId.getTxndate());
      seq = txnRefIdService.updateTransactionReference(txnRefId);
    } else {
      txnRefId =
          new TransactionRefId(null,
              OffsetDateTime.now()
                  .format(DateTimeFormatter.ofPattern("yyyyMMdd", LocaleContextHolder.getLocale())),
              1L);
      logger.debug("Creating a new entry for today " + txnRefId.getTxndate());
      try {
        seq = txnRefIdService.createTransactionRefId(txnRefId);
      } catch (DataIntegrityViolationException e) {
        logger.debug("Data Integrity Exception for today " + txnRefId.getTxndate());
        txnRefId = txnRefIdService.getTransactionRefIdForToday();
        logger.debug(
            "Locking and Updating the Sequence number " + txnRefId.getTxnseqid() + " for today "
                + txnRefId.getTxndate());
        seq = txnRefIdService.updateTransactionReference(txnRefId);
      }
    }
    return seq;
  }
}
