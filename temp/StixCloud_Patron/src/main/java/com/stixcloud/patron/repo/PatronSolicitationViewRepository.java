package com.stixcloud.patron.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.patron.domain.PatronSolicitationView;

@Repository
public class PatronSolicitationViewRepository implements IPatronSolicitationViewRepository {

  @Autowired
  EntityManager em;

  @Override
  public List<PatronSolicitationView> getPatronSolicitationView(Long patronProfileId) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT p FROM PatronSolicitationView p ");
    queryStr.append("WHERE p.patronProfileId = :patronProfileId ");
    List<PatronSolicitationView> result =
        em.createQuery(queryStr.toString(), PatronSolicitationView.class)
            .setParameter("patronProfileId", patronProfileId).getResultList();
    return result;
  }

}
