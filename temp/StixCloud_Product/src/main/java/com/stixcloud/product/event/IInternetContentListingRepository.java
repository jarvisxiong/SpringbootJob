package com.stixcloud.product.event;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.stixcloud.domain.InternetContentCodeListing;

public interface IInternetContentListingRepository extends PagingAndSortingRepository<InternetContentCodeListing, Long>{
	@Query(value="select ilv.icfileid, ilv.summaryImagePath, ilv.code internetContentCode, ilv.title, "
			+ "ilv.startDate, ilv.endDate, ilv.venuename venue, ilv.language, ilv.synopsis, ilv.bookingUrl from icc_list_view ilv", nativeQuery=true)
	List<InternetContentCodeListing> getInternetCodeListing();
		    
	@Query(
			" select new InternetContentCodeListing(ilv.summaryImagePath, ilv.internetContentCode, ilv.title, ilv.language, ilv.synopsis, ilv.genreStr, "
			+" ilv.venue, ilv.bookingUrl, min(plStartDate) as startDate, max(plStartDate) as endDate) "
			+" from InternetContentCodeListing ilv where :queryDate between ilv.iclStartDate and ilv.iclEndDate "
			+" group by ilv.summaryImagePath, ilv.internetContentCode, ilv.title, ilv.venue, ilv.language, ilv.synopsis, ilv.bookingUrl, ilv.genreStr "
		)	
	Page<InternetContentCodeListing> getInternetCodeListingByPage(
			@Param("queryDate") OffsetDateTime queryDate,
			Pageable pageable);
	
	@Query("Select ilv from InternetContentCodeListing ilv "
			+" where :queryDate between iclStartDate and iclEndDate "
			)
	Page<InternetContentCodeListing> getInternetCodeListingByPage_(
			@Param("queryDate") OffsetDateTime queryDate,
			Pageable pageable);

}
