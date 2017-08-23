package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueOverviewComponentsPc;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface VenueOverviewComponentsPcRepository
    extends CrudRepository<VenueOverviewComponentsPc, Long>,
    VenueOverviewComponentsPcRepositoryCustom {
}
