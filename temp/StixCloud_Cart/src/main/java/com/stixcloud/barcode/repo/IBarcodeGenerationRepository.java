package com.stixcloud.barcode.repo;

import java.util.List;

import com.stixcloud.barcode.domain.BarcodeFieldView;

public interface IBarcodeGenerationRepository {
  List<BarcodeFieldView> getBarcodeFieldList(Long productId, Long priceClassId,
      Long priceCategoryId);

}
