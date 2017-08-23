package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueDetailComponentsLc;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chongye on 09-Sep-16.
 */
public interface VenueDetailComponentsLcRepository
    extends CrudRepository<VenueDetailComponentsLc, Long>, VenueDetailComponentsLcRepositoryCustom {
}
