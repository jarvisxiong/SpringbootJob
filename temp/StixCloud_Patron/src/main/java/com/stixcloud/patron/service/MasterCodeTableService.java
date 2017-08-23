package com.stixcloud.patron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.patron.repo.IMasterCodeTableRepository;

@Service
public class MasterCodeTableService implements IMasterCodeTableService {

  @Autowired
  private IMasterCodeTableRepository repo;

  @Override
  @Cacheable(value = "findByCategory", key = "{#root.methodName, #category}")
  public List<MasterCodeTable> findByCategory(String category) {
    return repo.findByType(category);
  }

}
