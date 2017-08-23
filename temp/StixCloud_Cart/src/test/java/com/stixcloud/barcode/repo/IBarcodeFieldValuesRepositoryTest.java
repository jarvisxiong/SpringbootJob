package com.stixcloud.barcode.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.barcode.domain.BarcodeFieldValuesData;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IBarcodeFieldValuesRepositoryTest {

  @Autowired
  IBarcodeFieldValuesRepository repo;

  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void getBarcodeFieldValues() {
//    BarcodeFieldValuesData expected = new BarcodeFieldValuesData(1L, "Block M", 70501L, "C", 50L);
//    BarcodeFieldValuesData actual = repo.getBarcodeValues(774L, 4L, 3L);
//    assertEquals(expected, actual);
  }

}
