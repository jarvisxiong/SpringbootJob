package com.stixcloud.product.event;

import com.stixcloud.domain.RetrieveSectionGaView;

import java.util.List;

/**
 * Created by dbthan on 07-Sep-16.
 */

public interface ISectionGaRepository {
  List<RetrieveSectionGaView> getSectionGaList(Long productId, Long profileInfoId);
}
