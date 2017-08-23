package com.stixcloud.patron.repo;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.patron.api.TenantPropertiesTest;
import com.stixcloud.patron.domain.TransactionReferenceView;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionHistoryRepositoryTest {
  private final Logger LOGGER = LogManager.getLogger(TransactionHistoryRepositoryTest.class);

  @Autowired
  private ITransactionHistoryRepository transactionHistoryRepository;

  List<BigDecimal> txnIdList = new ArrayList<>();

  @Autowired(required = true)
  public void setTransactionHistoryRepository(
      ITransactionHistoryRepository transactionHistoryRepository) {
    this.transactionHistoryRepository = transactionHistoryRepository;
  }

  @Before
  public void setup() throws Exception {
    TenantPropertiesTest.setUp();

    Integer[] txnIdList = new Integer[] {408717, 408701, 408699, 408663, 408610, 408609, 408608,
        408599, 408585, 408582, 408581, 408578, 408577, 408575, 408572, 408569};

    Arrays.asList(txnIdList).forEach(value -> {
      this.txnIdList.add((new BigDecimal(value)));
    });

    Collections.sort(this.txnIdList);
  }

  @Test
  @Sql({"/patron/sql/transactionData.sql", "/patron/sql/transactionCodeData.sql",
  "/patron/sql/masterCodeTableData.sql"})
  public void testGetTransactionIdList() {
    long startTime = System.nanoTime();
    List<String> transctionTypeList = new ArrayList<>();
    transctionTypeList.add("Purchase");
    transctionTypeList.add("Return");
    List<BigDecimal> actual =
        transactionHistoryRepository.getTransactionIdList(11L, 1922192L, transctionTypeList);
    Collections.sort(actual);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(txnIdList, actual);
  }

  @Test
  @Sql({"/patron/sql/transactionData.sql", "/patron/sql/transactionCodeData.sql",
      "/patron/sql/masterCodeTableData.sql", "/patron/sql/transactionReferenceData.sql"})
  public void testGetTransactionReference() {
    long startTime = System.nanoTime();
    List<String> transctionTypeList = new ArrayList<>();
    transctionTypeList.add("Purchase");
    transctionTypeList.add("Return");
    List<BigDecimal> txnIds = new ArrayList<>();
    txnIdList.forEach(value -> {
      txnIds.add(BigDecimal.valueOf(value.longValue()));
    });
    List<TransactionReferenceView> actual = transactionHistoryRepository
        .getTransactionReference(txnIds, 11L, 1922192L, transctionTypeList);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    int actualSize = actual.stream()
        .collect(
            Collectors.groupingBy(TransactionReferenceView::getTransactionId, Collectors.toSet()))
        .size();
    assertEquals(txnIdList.size(), actualSize);
  }

}
