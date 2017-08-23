package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueOverviewComponentsLc;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface VenueOverviewComponentsLcRepository
    extends CrudRepository<VenueOverviewComponentsLc, Long>,
    VenueOverviewComponentsLcRepositoryCustom {
}
