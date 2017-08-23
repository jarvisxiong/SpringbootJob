package com.stixcloud.patron.service;

import java.util.Locale;

import com.stixcloud.patron.api.TransactionHistoryResponse;

public interface ITransactionHistoryService {
  public TransactionHistoryResponse getTransactions(Long patronProfileId, int pageSize, int pageNo,
      Locale locale);
}
