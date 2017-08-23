package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.patron.api.json.PatronSolicitationJson;

public class EsplanadePatronSolicitationService extends AbstractPatronSolicitationService {

  public EsplanadePatronSolicitationService() {}

  @Override
  public List<PatronSolicitationJson> getPatronSolicitationList(Long patronprofileid,
      List<Long> productIds, Boolean status) {
    return super.getPatronSolicitationList(patronprofileid, productIds, status);
  }
}
