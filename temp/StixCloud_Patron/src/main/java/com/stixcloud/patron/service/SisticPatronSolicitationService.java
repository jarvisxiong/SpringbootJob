package com.stixcloud.patron.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.SubscriptionType;
import com.stixcloud.patron.domain.PatronSolicitationView;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ProductLiveRepository;

public class SisticPatronSolicitationService extends AbstractPatronSolicitationService {
  private static final Logger logger = LogManager.getLogger(SisticPatronSolicitationService.class);

  public SisticPatronSolicitationService() {}

  public SisticPatronSolicitationService(IPatronSolicitationService patronSolicitationService,
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
    patronSolicitationList.addAll(getPatronSolicitations(patronProfileId));
    if (productIds != null && !productIds.isEmpty()) {
      List<PatronSolicitationJson> productSubscriptions =
          retrievePatronSolicitation(patronProfileId, productIds);
      patronSolicitationList.addAll(productSubscriptions);
      if (status != null) {
        patronSolicitationList = patronSolicitationList.stream()
            .filter(s -> s.getSubscribed() == status).collect(Collectors.toList());
      }
    } else {
      List<PatronSolicitationView> patronSolicitationView =
          getPatronSolicitationView(patronProfileId);
      // Filter to display only subscribed organization
      if (status != null) {
        patronSolicitationView = patronSolicitationView.stream()
            .filter(p -> p.getSubcribed() == status).collect(Collectors.toList());
      }
      if (patronSolicitationView != null && !patronSolicitationView.isEmpty()) {
        patronSolicitationList.addAll(convertSolicitationToResponse(patronSolicitationView));
      }
    }

    logger.info("End method getPatronSolicitationList");
    return patronSolicitationList;
  }

  private List<PatronSolicitationJson> getPatronSolicitations(Long patronProfileId) {
    logger.info("Start method getPatronSolicitations");
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    List<Object[]> solicitationDtos =
        patronRepository.getPatronSolicitationByTypeTenant(patronProfileId, userInfoId);
    List<PatronSolicitationJson> result = new ArrayList<PatronSolicitationJson>();
    if (solicitationDtos != null && !solicitationDtos.isEmpty()) {
      result =
          convertPatronSolicitation(solicitationDtos, SubscriptionType.TENANT.getMctDescription());
    }
    
    logger.info("End method getPatronSolicitations");
    return result;
  }

  private List<PatronSolicitationJson> convertSolicitationToResponse(
      List<PatronSolicitationView> solicitationViews) {
    logger.info("Start method convertSolicitationToResponse");
    List<PatronSolicitationJson> response = new ArrayList<PatronSolicitationJson>();
    if (solicitationViews != null && !solicitationViews.isEmpty()) {
      solicitationViews.forEach(p -> {
        if (p.getOrganizationId() != null) {
          PatronSolicitationJson pResponse = new PatronSolicitationJson(p.getSolicitationType(),
              p.getOrganizationId(), p.getOrganizationName(), p.getOrganizationUrl(),
              p.getSubcribed(), Integer.valueOf(p.getDescription2()));
          response.add(pResponse);
        }
      });
    }
    
    logger.info("End method convertSolicitationToResponse");
    return response;
  }
}
