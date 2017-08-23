package com.stixcloud.seatmap.repo;

import com.stixcloud.domain.VenueSectionPc;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface VenueSectionPcRepository
    extends CrudRepository<VenueSectionPc, Long>, VenueSectionPcRepositoryCustom {
}
