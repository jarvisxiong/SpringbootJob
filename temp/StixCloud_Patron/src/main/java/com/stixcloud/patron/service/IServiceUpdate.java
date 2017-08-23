package com.stixcloud.patron.service;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.patron.api.json.PatronProfileJson;

public interface IServiceUpdate {

  void updatePatron(PatronProfileJson request, Long entityId, Long userInfoId) throws SisticApiException;

  boolean isChanged(PatronProfileJson request, PatronProfileJson original);
}
