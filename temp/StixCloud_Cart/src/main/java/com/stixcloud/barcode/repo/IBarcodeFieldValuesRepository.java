package com.stixcloud.barcode.repo;

import java.util.List;
import java.util.Set;

import com.stixcloud.barcode.domain.BarcodeFieldValuesDataView;

public interface IBarcodeFieldValuesRepository {

  List<BarcodeFieldValuesDataView> getBarcodeValues(Set<Long> seatInvIdList, Long priceClassId, Long productId);
}
