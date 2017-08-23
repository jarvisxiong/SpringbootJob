package com.stixcloud.patron.repo;

import java.util.List;

import com.stixcloud.patron.domain.PatronSolicitationView;

public interface IPatronSolicitationViewRepository {

  List<PatronSolicitationView> getPatronSolicitationView(Long patronProfileId);
}
