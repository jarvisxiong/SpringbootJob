package com.stixcloud.seatmap.repo;

import java.util.List;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface VenueSectionPcRepositoryCustom {
  Long findPcSectionId(Long productId, List<Long> seatInvIds);
}
