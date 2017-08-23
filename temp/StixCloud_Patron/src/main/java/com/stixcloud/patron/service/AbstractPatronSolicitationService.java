package com.stixcloud.patron.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.domain.Organization;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.constant.PatronSubscriptionEnum;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.MasterTableCategoryCode;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.SubscriptionType;
import com.stixcloud.patron.domain.PatronSolicitationView;
import com.stixcloud.patron.domain.SolicitationDto;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ProductLiveRepository;

public class AbstractPatronSolicitationService {

  protected IPatronSolicitationService patronSolicitationService;
  protected ProductLiveRepository productLiveRepository;
  protected IMasterCodeTableService masterCodeTableService;
  protected IPatronRepository patronRepository;
  protected IPatronSolicitationViewService patronSolicitationViewService;
  protected List<PatronSolicitationJson> patronSolicitationList;

  protected List<PatronSolicitationJson> retrievePatronSolicitation(Long patronProfileId,
      List<Long> productIds) {
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    List<SolicitationDto> solicitationList =
        patronSolicitationService.retrievePatronSolicitation(patronProfileId, userInfoId);

    List<Organization> promoterSubscriptionList =
        productLiveRepository.retrieveAvailPromoterList(productIds);

    List<Organization> venueSubscriptionList =
        productLiveRepository.retrieveAvailVenueList(productIds);

    List<MasterCodeTable> mctList = masterCodeTableService
        .findByCategory(MasterTableCategoryCode.SOLICITATION_TYPE.masterCodeTable_CategoryCode());

    List<PatronSolicitationJson> solicitationListJsonList = new ArrayList<PatronSolicitationJson>();
    Map<String, Boolean> solicitationStatusMap = new HashMap<>();

    solicitationList.stream().forEach(solicitationDto -> {
      if (solicitationDto.getSubscriptionstatus()) {
        solicitationStatusMap.put(solicitationDto.getSolicitationType().toUpperCase()
            + solicitationDto.getOrganizationid(), solicitationDto.getSubscriptionstatus());
      }
    });

    String promoterOrder = mctList.stream()
        .filter(p -> p.getDescription1().equals(SubscriptionType.PROMOTER.getMctDescription()))
        .findFirst().orElse(null).getDescription2();

    promoterSubscriptionList.stream().forEach(organization -> {
      PatronSolicitationJson s = new PatronSolicitationJson(
          PatronSubscriptionEnum.SubscriptionType.PROMOTER.getMctDescription(),
          organization.getOrganizationid(), organization.getOrganizationname(),
          organization.getOrganizationurl(), false, Integer.valueOf(promoterOrder));

      solicitationListJsonList.add(s);
    });

    String venueOrder = mctList.stream()
        .filter(p -> p.getDescription1().equals(SubscriptionType.VENUE.getMctDescription()))
        .findFirst().orElse(null).getDescription2();

    venueSubscriptionList.stream().forEach(organization -> {
      PatronSolicitationJson s = new PatronSolicitationJson(
          PatronSubscriptionEnum.SubscriptionType.VENUE.getMctDescription(),
          organization.getOrganizationid(), organization.getOrganizationname(),
          organization.getOrganizationurl(), false, Integer.valueOf(venueOrder));

      solicitationListJsonList.add(s);
    });

    solicitationListJsonList.stream().forEach(s -> {
      String key = s.getSolicitationType().toUpperCase() + s.getOrganizationID();
      if (solicitationStatusMap.containsKey(key)) {
        s.setSubscribed(true);
      }
    });

    return solicitationListJsonList;
  }

  protected List<PatronSolicitationJson> getPatronSolicitationList(Long patronprofileid,
      List<Long> productIds, Boolean status) {
    return new ArrayList<PatronSolicitationJson>();
  }
  
  protected List<PatronSolicitationJson> convertPatronSolicitation(List<Object[]> solicitationDtos,
      String tenantName) {
    List<PatronSolicitationJson> result = new ArrayList<PatronSolicitationJson>();
    List<MasterCodeTable> mctList = masterCodeTableService
        .findByCategory(MasterTableCategoryCode.SOLICITATION_TYPE.masterCodeTable_CategoryCode());
    solicitationDtos.forEach(p -> {
      String order = p[5] == null ? null : p[5].toString();
      if (order == null) {
        order = mctList.stream().filter(p1 -> p1.getDescription1().equals(tenantName)).findFirst()
            .orElse(null).getDescription2();
      }

      result.add(new PatronSolicitationJson(p[0].toString(), Long.valueOf(p[1].toString()),
          p[2].toString(), p[3] == null ? null : p[3].toString(),
          p[4] == null ? false : p[4].toString().equals("1") ? true : false,
          Integer.valueOf(order)));
    });
    return result;
  }
  
  protected List<PatronSolicitationView> getPatronSolicitationView(Long patronProfileId) {
    return patronSolicitationViewService.getPatronSolicitationView(patronProfileId);
  }
}
