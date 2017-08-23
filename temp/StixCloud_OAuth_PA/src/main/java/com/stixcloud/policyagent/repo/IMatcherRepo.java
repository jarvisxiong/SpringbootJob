package com.stixcloud.policyagent.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stixcloud.policyagent.domain.Matcher;

@Repository
public interface IMatcherRepo extends CrudRepository<Matcher, Long> {

}
