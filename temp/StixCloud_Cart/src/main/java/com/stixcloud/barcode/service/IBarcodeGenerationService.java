package com.stixcloud.barcode.service;

import java.util.List;

import com.stixcloud.barcode.domain.BarcodeFieldValuesDataView;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.domain.TransactionProduct;

public interface IBarcodeGenerationService {

  String generateBarcode(Long transactionId, Long patronProfileId,
      TransactionProduct transactionProduct, List<BarcodeFieldValuesDataView> barcodeFieldDataList) throws ValidateCartException;
}
