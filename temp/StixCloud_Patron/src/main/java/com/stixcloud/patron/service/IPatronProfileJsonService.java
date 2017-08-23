package com.stixcloud.patron.service;

import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;

public interface IPatronProfileJsonService {

  PatronProfileJson getPatronProfileJsonByEmail(String email);

  void savePatronProfileJson(PatronProfileJson patron);

  PatronRequest getPatronProfileByAccNo(Long accNo);
}
