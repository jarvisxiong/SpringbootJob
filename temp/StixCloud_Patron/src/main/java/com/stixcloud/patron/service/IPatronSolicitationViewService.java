package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.patron.domain.PatronSolicitationView;

public interface IPatronSolicitationViewService {

  List<PatronSolicitationView> getPatronSolicitationView(Long patronProfileId);
}
