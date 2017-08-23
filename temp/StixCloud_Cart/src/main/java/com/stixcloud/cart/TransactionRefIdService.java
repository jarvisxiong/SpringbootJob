package com.stixcloud.cart;

import com.stixcloud.cart.repo.TransactionRefIdRepository;
import com.stixcloud.domain.TransactionRefId;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service(value = "TransactionRefIdService")
@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
public class TransactionRefIdService {

  private static final Logger logger = LogManager.getLogger(TransactionRefIdService.class);
  @Autowired
  private TransactionRefIdRepository transactionRefIdRepository;

  /**
   * Creates the transaction ref id.
   * @param transactionRefId the transaction ref id
   * @return the long
   * @throws CoreException a business rule logic exception
   */
  public String createTransactionRefId(TransactionRefId transactionRefId) throws
      HibernateException {
    logger.debug("Creating a new transaction reference number entry entry for date " +
        transactionRefId.getTxndate());
    String seq = transactionRefId.getTxndate() + "-" + StringUtils.leftPad("1", 6, '0');
    transactionRefIdRepository.save(transactionRefId);
    return seq;
  }

  /**
   * Update transaction reference.
   * @param transactionRefId the transaction ref id
   * @throws CoreException a business rule logic exception
   */
  public String updateTransactionReference(TransactionRefId transactionRefId)
      throws HibernateException {
    logger.debug("updating the transaction reference number details for date " + transactionRefId
        .getTxndate());
    TransactionRefId
        txnRefId =
        transactionRefIdRepository.getWithLock(transactionRefId.getTransactionrefid());
    String
        seq =
        txnRefId.getTxndate() + "-" + StringUtils
            .leftPad(String.valueOf(txnRefId.getTxnseqid() + 1), 6, '0');
    txnRefId.setTxnseqid(txnRefId.getTxnseqid() + 1);
    transactionRefIdRepository.save(txnRefId);
    return seq;
  }

  /**
   * Gets the transaction ref id for today.
   * @return the transaction ref id for today
   * @throws CoreException a business rule logic exception
   */
  public TransactionRefId getTransactionRefIdForToday() throws HibernateException {
    logger.debug("Retrieving the transaction reference details for today " + new Date());
    return transactionRefIdRepository
        .getTransactionRefIdForToday(
            OffsetDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd", LocaleContextHolder.getLocale())));
  }

}