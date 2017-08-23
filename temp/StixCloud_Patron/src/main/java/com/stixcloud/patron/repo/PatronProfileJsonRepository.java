package com.stixcloud.patron.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;

@Repository
public interface PatronProfileJsonRepository extends CrudRepository<PatronProfileJson, String> {
  
  @Query("select max(p) from PatronProfile p where p.accno = :accno")
  public PatronRequest findByAccNo(@Param("accno") Long accaccnoNo);
	
}
