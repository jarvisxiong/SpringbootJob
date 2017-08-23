package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stixcloud.domain.PatronAttributes;

public interface IPatronAttributesRepository extends CrudRepository<PatronAttributes, Long>{

	  @Query("select p from PatronAttributes p where p.patronProfileId = :patronProfileId")
	  public List<PatronAttributes> findByPatronProfileId(@Param("patronProfileId") Long patronProfileId);
}
