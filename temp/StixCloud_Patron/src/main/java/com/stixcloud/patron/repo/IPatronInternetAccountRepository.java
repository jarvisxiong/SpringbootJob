package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronInternetAccount;

@Repository
public interface IPatronInternetAccountRepository extends CrudRepository<PatronInternetAccount, Long> {

  @Query("select p from PatronInternetAccount p where LOWER(p.userid) = LOWER(:emailAddress)")
  PatronInternetAccount findByEmailAddress(@Param("emailAddress") String emailAddress);
  
  @Query("select p from PatronInternetAccount p where p.patronProfileId = :patronProfileId or LOWER(p.userid) = LOWER(:emailAddress)")
  List<PatronInternetAccount> findByPatronIdEmailAddress(@Param("patronProfileId") Long patronProfileId, @Param("emailAddress") String emailAddress);
}
