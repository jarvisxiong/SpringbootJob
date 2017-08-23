package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.MasterCodeTable;


@Repository
public interface IMasterCodeTableRepository extends CrudRepository<MasterCodeTable, Long> {

  @Query("select m from MasterCodeTable m where m.categorycode = :categoryCode and m.status = true")
  List<MasterCodeTable> findByType(@Param("categoryCode") String categoryCode);
}
