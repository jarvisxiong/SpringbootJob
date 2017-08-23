package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueDetailComponentsLc;

import java.util.List;

/**
 * Created by chongye on 09-Sep-16.
 */
public interface VenueDetailComponentsLcRepositoryCustom {
  List<VenueDetailComponentsLc> retrieveDetailViewComponents(Long productId, Long blockId);
}
