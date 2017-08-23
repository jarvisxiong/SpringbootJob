package com.stixcloud.patron.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.SubscriptionType;
import com.stixcloud.patron.repo.IPatronRepository;

public class NgsPatronSolicitationService extends AbstractPatronSolicitationService {
  private static final Logger logger = LogManager.getLogger(NgsPatronSolicitationService.class);

  public NgsPatronSolicitationService() {}

  public NgsPatronSolicitationService(IPatronRepository patronRepository,
      IMasterCodeTableService masterCodeTableService) {
    this.patronSolicitationList = new ArrayList<PatronSolicitationJson>();
    this.patronRepository = patronRepository;
    this.masterCodeTableService = masterCodeTableService;
  }

  @Override
  public List<PatronSolicitationJson> getPatronSolicitationList(Long patronProfileId,
      List<Long> productIds, Boolean status) {
    logger.info("Start method getPatronSolicitationList");
    if (productIds == null || productIds.isEmpty()) {
      List<PatronSolicitationJson> patronSolicitations = getPatronSolicitations(patronProfileId);
      if (patronSolicitations != null && !patronSolicitations.isEmpty()) {
        patronSolicitationList.addAll(patronSolicitations);
      }
    }

    logger.info("End method getPatronSolicitationList");
    return patronSolicitationList;
  }

  private List<PatronSolicitationJson> getPatronSolicitations(Long patronProfileId) {
    logger.info("Start method getPatronSolicitations");
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    List<Object[]> solicitationDtos =
        patronRepository.getPatronSolicitationByTypePromoterSubEmail(patronProfileId, userInfoId);
    List<PatronSolicitationJson> result = new ArrayList<PatronSolicitationJson>();
    if (solicitationDtos != null && !solicitationDtos.isEmpty()) {
      result = convertPatronSolicitation(solicitationDtos,
          SubscriptionType.PROMOTER_SUB_EMAIL.getMctDescription());
    }

    logger.info("Start method getPatronSolicitations");
    return result;
  }
}
