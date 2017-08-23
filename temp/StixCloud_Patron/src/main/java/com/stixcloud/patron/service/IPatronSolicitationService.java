package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.patron.domain.SolicitationDto;

public interface IPatronSolicitationService {

  List<PatronSolicitation> findByPatronProfileId(Long patronId, Long userProfileId);

  List<SolicitationDto> retrievePatronSolicitation(Long patronprofileid, Long userProfileId);

  void save(PatronSolicitation patronSolicitation);
  
  void save(Long userInfoId, Long patronProfileId, Long organizationId) throws SisticApiException;

  void update(List<PatronSolicitation> pUpdate);

  void delete(List<PatronSolicitation> pDelete);
}
