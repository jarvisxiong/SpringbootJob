package com.stixcloud.barcode.repo;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.barcode.domain.BarcodeFieldView;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BarcodeGenerationRepositoryTest {

  private final Logger LOGGER = LogManager.getLogger(BarcodeGenerationRepositoryTest.class);

  @Autowired
  private BarcodeGenerationRepository repo;

  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void getBarcodeFieldList() {
//    List<BarcodeFieldView> expected = new ArrayList<>();
//    expected.add(new BarcodeFieldView(3l, 4l, 2300L, "EVENT_ID", 3, "0", null, 1, 1, 0));
//    expected
//        .add(new BarcodeFieldView(3l, 4l, 2300L, "ACSBARCODEREGENCOUNT", -1, "0", null, 1, 1, 1));
//    long startTime = System.nanoTime();
//    List<BarcodeFieldView> actual = repo.getBarcodeFieldList(3l, 4l, 2300L);
//    LOGGER.info("Time taken "
//        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
//        + " ms");
//    LOGGER.info(Arrays.toString(actual.toArray()));
//    assertFalse(actual.isEmpty());
//    assertEquals(2, actual.size());
//    assertEquals(expected, actual);
  }
  
  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void getBarcodeFieldListCaseEmpty() {
    long startTime = System.nanoTime();
    List<BarcodeFieldView> actual = repo.getBarcodeFieldList(105266L, 5884L, 2300L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(actual.toArray()));
    assertTrue(actual.isEmpty());
  }
}
