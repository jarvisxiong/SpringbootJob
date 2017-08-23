package com.stixcloud.patron.repo;

import org.springframework.data.repository.CrudRepository;

import com.stixcloud.domain.TenantConfig;

public interface ITenantConfigRepository extends CrudRepository<TenantConfig, Long> {
}
