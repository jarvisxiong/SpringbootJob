package com.stixcloud.seatmap.repo;

import com.stixcloud.seatmap.dto.SeatModel;

import java.util.List;

/**
 * Created by chongye on 15-Sep-16.
 */
public interface SeatModelRepository {
  List<SeatModel> getSectionsForBlock(Long productId, Long blockId);
}
