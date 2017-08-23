package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueOverviewComponentsPc;

import java.util.List;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface VenueOverviewComponentsPcRepositoryCustom {
  List<VenueOverviewComponentsPc> findAll(Long productId);
}
