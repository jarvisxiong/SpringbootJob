package com.stixcloud.patron.repo;

import java.math.BigDecimal;
import java.util.List;

import com.stixcloud.patron.domain.TransactionReferenceView;

public interface ITransactionHistoryRepository {
  List<TransactionReferenceView> getTransactionReference(List<BigDecimal> transactionIds,
      Long profileInfoId, Long patronProfileId, List<String> transctionTypeList);

  public List<BigDecimal> getTransactionIdList(Long profileInfoId, Long patronProfileId,
      List<String> transctionTypeList);
}
