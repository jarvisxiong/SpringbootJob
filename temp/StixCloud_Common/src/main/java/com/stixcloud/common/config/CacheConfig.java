package com.stixcloud.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 * Created by chongye on 20-Sep-16.
 */
@Configuration
@Profile("!test")
@ConditionalOnProperty(name = "cache.default.timeout")
public class CacheConfig {
  @Value("${cache.default.timeout}")
  private long timeout;

  @Bean
  public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
    return cm -> {
      cm.setDefaultExpiration(timeout);
    };
  }
}
