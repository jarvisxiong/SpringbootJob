package com.stixcloud.patron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.repo.PatronProfileJsonRepository;

@Service
public class PatronProfileJsonService implements IPatronProfileJsonService {

  @Autowired
  private PatronProfileJsonRepository repo;

  @Override
  public PatronProfileJson getPatronProfileJsonByEmail(String email) {
    return repo.findOne(email);
  }

  @Override
  public void savePatronProfileJson(PatronProfileJson patron) {
    repo.save(patron);
  }

  @Override
  public PatronRequest getPatronProfileByAccNo(Long accNo) {
	  return repo.findByAccNo(accNo);
  }

}
