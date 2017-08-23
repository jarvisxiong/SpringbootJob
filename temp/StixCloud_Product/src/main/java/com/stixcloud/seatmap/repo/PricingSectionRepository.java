package com.stixcloud.seatmap.repo;

import com.stixcloud.seatmap.dto.PricingSection;

/**
 * Created by chongye on 06-Sep-16.
 */
public interface PricingSectionRepository {
  PricingSection findOne(Long venueSectionPcId);
}
