package com.stixcloud.patron.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronProfile;


@Repository
public interface IPatronProfileRepository extends CrudRepository<PatronProfile, Long> {
	
	@Query("select max(p) from PatronProfile p where p.accno = :accno")
	public PatronProfile findByAccNo(@Param("accno") Long accNo);
}
