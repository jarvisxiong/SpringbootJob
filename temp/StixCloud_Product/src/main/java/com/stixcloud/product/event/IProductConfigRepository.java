package com.stixcloud.product.event;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stixcloud.domain.AddonMapping;
import com.stixcloud.domain.ProductConfig;
import com.stixcloud.domain.ProductLive;

public interface IProductConfigRepository {
	  List<ProductConfig> getUpdatedShows(@Param("updatedDate") LocalDate updatedDate);

	List<String> getEventTypes(Long productId, Long venueId, Long venueLayoutId);

}
