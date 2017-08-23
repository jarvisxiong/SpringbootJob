package com.stixcloud.patron.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.patron.api.TenantPropertiesTest;
import com.stixcloud.patron.api.TransactionHistoryResponse;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionHistoryServiceTest {
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  TransactionHistoryService transactionHistoryService;

  private JacksonTester<TransactionHistoryResponse> txnHistoryJacksonTester;

  @Before
  public void setup() throws Exception {
    TenantPropertiesTest.setUp();
    JacksonTester.initFields(this, objectMapper);

  }

  @Test
  @Sql({"/patron/sql/transactionData.sql", "/patron/sql/transactionCodeData.sql",
      "/patron/sql/masterCodeTableData.sql", "/patron/sql/transactionReferenceData.sql"})
  public void getTransactions() throws IOException {
    TransactionHistoryResponse actual =
        transactionHistoryService.getTransactions(1922192L, 5, 1, LocaleContextHolder.getLocale());
    assertThat(txnHistoryJacksonTester.write(actual))
        .isEqualToJson("/patron/json/transactionHistoryResponse.json");
  }
}
