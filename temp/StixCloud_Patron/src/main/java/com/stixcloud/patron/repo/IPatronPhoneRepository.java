package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronPhone;


@Repository
public interface IPatronPhoneRepository extends CrudRepository<PatronPhone, Long> {

  @Query("select p from PatronPhone p where p.patronProfileId = :patronProfileId")
  public List<PatronPhone> findByPatronProfileId(@Param("patronProfileId") Long patronProfileId);
}
