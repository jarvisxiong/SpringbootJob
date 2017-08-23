package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.domain.MasterCodeTable;

public interface IMasterCodeTableService {

  List<MasterCodeTable> findByCategory(String category);
}
