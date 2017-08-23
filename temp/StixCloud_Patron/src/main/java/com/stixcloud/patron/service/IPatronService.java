package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.PatronSolicitationUpdateRequest;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.domain.PatronProfileView;

public interface IPatronService {

  boolean checkExistEmailAddress(String emailAddress);

  List<PatronProfileView> getPatronProfile(Long patronProfileId);
  
  PatronProfileJson convertToResponse(List<PatronProfileView> patronProfileViews);

  /**
   * update patron profile
   * @param request
   * @param patronProfile
   * @param patronId
   * @param userInfoId
   * @throws SisticApiException
   */
  void updatePatronProfile(PatronUpdateRequest request, PatronProfile patronProfile, Long patronId, Long userInfoId) throws SisticApiException;
  
  /**
   * create patron from external client calls
   * @param request
   * @param userInfoId
   * @return patron accNo
   * @throws SisticApiException
   */
  Long createPatronProfile(PatronRequest request, Long userInfoId) throws SisticApiException;
  
  Long createUpdatePatronProfile(PatronUpdateRequest request, Long userInfoId) throws SisticApiException;
  
  List<PatronSolicitationJson> getPatronSolicitationList(String tenantName, Long patronprofileid,
      List<Long> productIds, Boolean status);

  void updatePatronSolicitation(PatronSolicitationUpdateRequest request, Long patronId)
      throws SisticApiException;

  Object isPatronLock(String email);

  List<PatronSolicitationJson> removeDuplicateSolicitation(
      List<PatronSolicitationJson> solicitationList);

  List<PatronProfileView> getPatronProfileByAccNum(Long accNum) throws SisticApiException;

}
