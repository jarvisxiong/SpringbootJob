package com.stixcloud.cart.rules.postcommit;

import com.stixcloud.cart.EVoucherConstants;
import com.stixcloud.cart.repo.EVoucherRepository;
import com.stixcloud.cart.rules.PostCommitCartRule;
import com.stixcloud.domain.Evoucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@SpringRule
public class EvoucherRedemptionRule extends PostCommitCartRule {
  @Autowired
  private EVoucherRepository eVoucherRepository;
  private static final Logger logger = LogManager.getLogger(EvoucherRedemptionRule.class);

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE+2;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty() && this.getShoppingCart().getEvoucherIdList()!=null && this.getShoppingCart().getEvoucherIdList().size() >0 ;
  }

  @Action
  public void then() {
	  logger.info("PostCommit Rule EvoucherRedemptionRule ");
	  List<Evoucher> evouchers = this.getShoppingCart().getEvoucherIdList().stream()
		        .map(eVoucherId ->
		            eVoucherRepository.findOne(eVoucherId)).collect(Collectors.toList());
	  logger.info("evouchers "+evouchers);
      if (evouchers != null && !evouchers.isEmpty()) {
			evouchers = evouchers.stream().map(evoucher -> {
				evoucher.setStatus(Integer.valueOf(EVoucherConstants.REDEEMED.value()));
				evoucher.setRedeemTxnId(this.getShoppingCart().getTransactionReferenceNumber());
				return evoucher;
			}).collect(Collectors.toList());
			eVoucherRepository.save(evouchers);
     }
  }
}
