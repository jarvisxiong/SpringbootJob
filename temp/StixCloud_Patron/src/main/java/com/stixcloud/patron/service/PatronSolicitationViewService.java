package com.stixcloud.patron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.patron.domain.PatronSolicitationView;
import com.stixcloud.patron.repo.IPatronSolicitationViewRepository;

@Service
public class PatronSolicitationViewService implements IPatronSolicitationViewService {

  @Autowired
  private IPatronSolicitationViewRepository repo;

  @Override
  public List<PatronSolicitationView> getPatronSolicitationView(Long patronProfileId) {
    return repo.getPatronSolicitationView(patronProfileId);
  }

}
