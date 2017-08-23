package com.stixcloud.barcode.service;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.domain.TransactionProduct;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BarcodeGenerationServiceTest {

  @Autowired
  private BarcodeGenerationService service;

  private TransactionProduct transactionProduct;

  @Before
  public void setup() {
    transactionProduct = new TransactionProduct();
    transactionProduct.setTxnproductid(99L);
    transactionProduct.setProductId(44L);
    transactionProduct.setPriceCatId(33L);
    transactionProduct.setPriceClassId(22L);
    transactionProduct.setSalesInvId(774l);
    transactionProduct.setReprintedcount(0L);
  }

  @Test(expected = ValidateCartException.class)
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void generateBarcodeCaseBarcodeFieldViewException() throws ValidateCartException {
//    service.generateBarcode(99L, 88L, transactionProduct);
  }

  @Test(expected = ValidateCartException.class)
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void generateBarcodeCaseBarcodeFieldValuesViewException() throws ValidateCartException {
    transactionProduct.setProductId(3l);
    transactionProduct.setPriceCatId(1801L);
    transactionProduct.setPriceClassId(44l);
//    service.generateBarcode(99L, 88L, transactionProduct);
  }

  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void generateBarcodeCaseNotEncrypt() throws ValidateCartException {
    transactionProduct.setProductId(3L);
    transactionProduct.setPriceCatId(1801L);
    transactionProduct.setPriceClassId(4l);
    transactionProduct.setTxnproductid(235L);
//    String barcode = service.generateBarcode(63254125L, 88L, transactionProduct);
//    assertEquals("00000023500000070501Bl00000000C063254125000050", barcode);
  }

  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void generateBarcodeCaseEncrypt() throws ValidateCartException {
    transactionProduct.setProductId(3l);
    transactionProduct.setPriceCatId(2300L);
    transactionProduct.setPriceClassId(4l);
    transactionProduct.setTxnproductid(235L);
//    String barcode = service.generateBarcode(63254125L, 88L, transactionProduct);
//    assertEquals("7566", barcode);
  }

  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void generateBarcodeCaseEventIsACSStar() throws ValidateCartException {
    transactionProduct.setProductId(3l);
    transactionProduct.setPriceCatId(2300L);
    transactionProduct.setPriceClassId(4l);
    transactionProduct.setTxnproductid(236L);
//    String barcode = service.generateBarcode(63254125L, 88L, transactionProduct);
//    assertEquals("7566", barcode);
  }

  @Test
  @Sql({"/barcode/barcodeFieldValuesData.sql"})
  public void generateBarcodeCaseEncryptWithPrefixAndChecksum() throws ValidateCartException {
    transactionProduct.setProductId(3l);
    transactionProduct.setPriceCatId(2307L);
    transactionProduct.setPriceClassId(4l);
    transactionProduct.setTxnproductid(235L);
//    String barcode = service.generateBarcode(63254125L, 88L, transactionProduct);
//    assertEquals("5648", barcode);
  }
}
