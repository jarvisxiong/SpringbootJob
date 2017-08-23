package com.stixcloud.seatmap.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.VenueDetailComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsPc;
import com.stixcloud.product.util.FileInfoUtil;
import com.stixcloud.seatmap.dto.PriceCategoryDetail;
import com.stixcloud.seatmap.dto.SeatModel;
import com.stixcloud.seatmap.imagerenderer.ImageCreator;
import com.stixcloud.seatmap.repo.PriceCategoryDetailRepository;
import com.stixcloud.seatmap.repo.SeatModelRepository;
import com.stixcloud.seatmap.repo.VenueDetailComponentsLcRepository;
import com.stixcloud.seatmap.repo.VenueOverviewComponentsLcRepository;
import com.stixcloud.seatmap.repo.VenueOverviewComponentsPcRepositoryCustom;
import com.stixcloud.seatmap.repo.VenueSectionPcRepository;

import oracle.core.lmx.CoreException;

@Service(value = "ImageCreationService")
public class ImageCreationService {

  private final String FILE_SEPARATOR = "/";
  @Autowired
  private ImageCreator imgCreator;
  @Autowired
  private PriceCategoryDetailRepository priceCategoryDetailRepository;
  @Autowired
  private VenueSectionPcRepository venueSectionPcRepository;
  @Autowired
  private VenueOverviewComponentsLcRepository venueOverviewComponentsLcRepository;
  @Autowired
  private VenueOverviewComponentsPcRepositoryCustom venueOverviewComponentsPcRepository;
  @Autowired
  private VenueDetailComponentsLcRepository venueDetailComponentsLcRepository;
  @Autowired
  private SeatModelRepository seatModelRepository;

  private Logger logger = LogManager.getLogger(ImageCreationService.class);

  private Map<String, String> layOutImageCacheMap = new ConcurrentHashMap<String, String>();

  /**
   * Gets the overview image.
   * @param seatInvIds the seat inv ids
   * @param productId the show id
   * @param renderBlock the render block
   * @param renderType the render type
   * @param fillPriceSection the fill price section
   * @param imagePath the image path
   * @return the overview image
   * @throws CoreException a business rule logic exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public String getOverviewImage(List<Long> seatInvIds, Long productId, boolean renderBlock,
                                 boolean fillPriceSection, String imagePath) throws IOException {
    logger.info("getOverviewImage(seatInvIds[]) : product id = " + productId);
    Long pcSectionId = null;
    StringJoiner joiner = new StringJoiner(FILE_SEPARATOR);
    
    String tenantId = TenantContextHolder.getTenant().getName();

    imagePath = joiner.add(imagePath).add(productId.toString())
        .add(FileInfoUtil.IMAGE_OVERVIEW + FileInfoUtil.JPGE_IMAGE_EXTENSION).toString();

    if (layOutImageCacheMap.containsKey(tenantId + ":" + productId)) {
      return layOutImageCacheMap.get(tenantId + ":" + productId);
    }

    if (seatInvIds != null) {
      pcSectionId = venueSectionPcRepository.findPcSectionId(productId, seatInvIds);
    }

    List<VenueOverviewComponentsLc> venueOverviewComponentsLcs =
        venueOverviewComponentsLcRepository.findAll(productId);
    List<VenueOverviewComponentsPc> venueOverviewComponentsPcs =
        venueOverviewComponentsPcRepository.findAll(productId);
    List<PriceCategoryDetail> priceCategoryDetails =
        priceCategoryDetailRepository.findPriceCategoryDetailByProductId(productId);
    String layoutImage = imgCreator
        .createOverviewImage(priceCategoryDetails, venueOverviewComponentsLcs,
            venueOverviewComponentsPcs, pcSectionId, renderBlock, fillPriceSection, imagePath)
        .replace("/STIX", "");
    layOutImageCacheMap.put(tenantId+":"+productId, layoutImage);
    return layoutImage;
  }

  @Scheduled(cron = "0 0 */3 * * *")
  public void cleanSessions() {
    logger.debug("Clearing layout image cache");
    layOutImageCacheMap.clear();
  }

  /**
   * Gets the detail images.
   * @param seatInvIds the seat inv ids
   * @param productId the show id
   * @param loadSalesInfo the load sales info
   * @param renderType the render type
   * @param imagePath the image path
   * @return the detail images
   * @throws CoreException a business rule logic exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public String getDetailImages(List<Long> seatInvIds, Long productId, String imagePath)
      throws IOException {
    Long blockId =
        venueOverviewComponentsLcRepository.findSelectedDistinctBlocks(productId, seatInvIds);

    StringJoiner joiner = new StringJoiner(FILE_SEPARATOR);
    imagePath = joiner.add(imagePath).add(productId.toString())
        .add(FileInfoUtil.IMAGE_OVERVIEW + FileInfoUtil.JPGE_IMAGE_EXTENSION).toString();

    List<VenueDetailComponentsLc> venueDetailComponentsList =
        venueDetailComponentsLcRepository.retrieveDetailViewComponents(productId, blockId);
    List<SeatModel> seatModels = seatModelRepository.getSectionsForBlock(productId, blockId);
    List<PriceCategoryDetail> priceCategoryDetails =
        priceCategoryDetailRepository.findPriceCategoryDetailByProductId(productId);
    return imgCreator
        .createDetailImage(seatInvIds, priceCategoryDetails, venueDetailComponentsList, seatModels,
            blockId, imagePath).replace("/STIX", "");
  }
}
