package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueOverviewComponentsLc;

import java.util.List;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface VenueOverviewComponentsLcRepositoryCustom {
  List<VenueOverviewComponentsLc> findAll(Long productId);

  Long findSelectedDistinctBlocks(Long productId, List<Long> seatInvIds);
}
