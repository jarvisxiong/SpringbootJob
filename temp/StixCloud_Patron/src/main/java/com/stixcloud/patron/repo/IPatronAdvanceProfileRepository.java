package com.stixcloud.patron.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronAdvanceProfile;


@Repository
public interface IPatronAdvanceProfileRepository
    extends CrudRepository<PatronAdvanceProfile, Long> {

  @Query("select p from PatronAdvanceProfile p where p.patronProfileId = :patronProfileId")
  PatronAdvanceProfile findByPatronProfileId(@Param("patronProfileId") Long patronProfileId);
}
