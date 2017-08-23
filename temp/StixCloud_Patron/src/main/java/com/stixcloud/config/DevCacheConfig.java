package com.stixcloud.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * Created by chongye on 20-Sep-16.
 */
@Configuration
@Profile({"dev", "!test"})
public class DevCacheConfig {
  @Bean
  public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
    return cm -> {
      cm.setDefaultExpiration(900);
    };
  }
}
