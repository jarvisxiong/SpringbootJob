package com.stixcloud.patron.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.constant.PatronSubscriptionEnum;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.SubscriptionType;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ProductLiveRepository;

public class VizproPatronSolicitationService extends AbstractPatronSolicitationService {
  private static final Logger logger = LogManager.getLogger(VizproPatronSolicitationService.class);

  public VizproPatronSolicitationService() {}
  
  public VizproPatronSolicitationService(IPatronSolicitationService patronSolicitationService,
      ProductLiveRepository productLiveRepository, MasterCodeTableService masterCodeTableService,
      IPatronRepository patronRepository,
      IPatronSolicitationViewService patronSolicitationViewService) {
    this.patronSolicitationList = new ArrayList<PatronSolicitationJson>();
    this.masterCodeTableService = masterCodeTableService;
    this.patronSolicitationService = patronSolicitationService;
    this.productLiveRepository = productLiveRepository;
    this.patronRepository = patronRepository;
    this.patronSolicitationViewService = patronSolicitationViewService;
  }

  @Override
  public List<PatronSolicitationJson> getPatronSolicitationList(Long patronProfileId,
      List<Long> productIds, Boolean status) {
    logger.info("Start method getPatronSolicitationList");
    if (CollectionUtils.isEmpty(productIds)) {
      // Only get subscriptions of promoter
      List<PatronSolicitationJson> patronSolicitations = getPatronSolicitations(patronProfileId);
      if (patronSolicitations != null && !patronSolicitations.isEmpty()) {
        patronSolicitationList.addAll(patronSolicitations);
      }
    } else {
      List<PatronSolicitationJson> productSubscriptions =
          retrievePatronSolicitation(patronProfileId, productIds);
      patronSolicitationList.addAll(productSubscriptions);
    }

    patronSolicitationList = patronSolicitationList.stream().filter(s -> StringUtils.equals(
        PatronSubscriptionEnum.SubscriptionType.PROMOTER.getMctDescription(),
        s.getSolicitationType()))
    .collect(Collectors.toList());
    
    if (status != null) {
      patronSolicitationList = patronSolicitationList.stream()
          .filter(s -> s.getSubscribed() == status).collect(Collectors.toList());
    }

    logger.info("End method getPatronSolicitationList");
    return patronSolicitationList;
  }

  /**
   * Retrieve subscriptions of promoter
   * @param patronProfileId
   * @return
   */
  private List<PatronSolicitationJson> getPatronSolicitations(Long patronProfileId) {
    logger.info("Start method getPatronSolicitations");
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    List<Object[]> solicitationDtos =
        patronRepository.getPatronSolicitationByTypePromoter(patronProfileId, userInfoId);
    List<PatronSolicitationJson> result = new ArrayList<PatronSolicitationJson>();
    if (solicitationDtos != null && !solicitationDtos.isEmpty()) {
      result = convertPatronSolicitation(solicitationDtos,
          SubscriptionType.PROMOTER.getMctDescription());
    }

    logger.info("End method getPatronSolicitations");
    return result;
  }
}
