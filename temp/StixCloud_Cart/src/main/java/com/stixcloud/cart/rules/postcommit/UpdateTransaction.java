package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.TransactionCommitStatus;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.repo.TransactionRepository;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.domain.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;

@SpringRule
public class UpdateTransaction extends PostCommitCartRule {
  @Autowired
  private TransactionRepository transactionRepository;
  private static final Logger logger = LogManager.getLogger(UpdateTransaction.class);

  @Priority
  public int getPriority() {
    return 3;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);
    logger.debug("updating Transaction ");
    Transaction
        transaction =
        transactionRepository
            .findByTransactionRefNumber(this.getShoppingCart().getTransactionReferenceNumber());
    if (isPaymentApproved()) {
      transaction.setOrderstatus(TransactionCommitStatus.COMMITTED.getValue());
    } else {
      transaction.setOrderstatus(TransactionCommitStatus.NOT_COMMITTED.getValue());
    }
    transaction = transactionRepository.save(transaction);
    this.getPostCommitOrder().setTransaction(transaction);
    logger.debug("Finished updating Transaction ");
  }
}
