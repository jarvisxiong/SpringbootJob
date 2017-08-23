package com.stixcloud.common.config.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.Filter;
import javax.sql.DataSource;


@Configuration
@ConditionalOnClass({MultiTenantProperties.class, TenantContextHolder.class, Tenant.class,
    MultiTenantDataSource.class})
@EnableConfigurationProperties(MultiTenantProperties.class)
public class MultiTenantConfiguration {
  @Autowired
  private MultiTenantProperties properties;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Bean
  public DataSource dataSource() {
    Map<Object, Object> tenantDataSourceMap =
        properties.getTenants().entrySet().stream()
            .collect(Collectors.toMap(
                ds -> ds.getKey().trim().toUpperCase().intern(),
                ds -> {
                    MultiTenantProperties.Tenant tenant = ds.getValue();
                    org.apache.tomcat.jdbc.pool.DataSource dataSource = (org.apache.tomcat.jdbc.pool.DataSource) DataSourceBuilder.create(properties.getBeanClassLoader()).url(tenant.getUrl())
                            .username(tenant.getUsername()).password(tenant.getPassword())
                            .build();

                    BeanUtils.copyProperties(tenant, tenant.getTomcat());
                    BeanUtils.copyProperties(tenant, dataSource);
                    dataSource.setPoolProperties(tenant.getTomcat());

                    // Just in case
                    String validationQuery = tenant.getTomcat().getValidationQuery();
                    if (validationQuery == null) {
                        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(tenant.getUrl());
                        validationQuery = databaseDriver.getValidationQuery();
                    }
                    if (validationQuery != null) {
                        dataSource.setTestOnBorrow(true);
                        dataSource.setValidationQuery(validationQuery);
                    }

                    dataSource.setName(ds.getKey().trim().toUpperCase().intern());
                    return dataSource;

                }

            ));

    if (!tenantDataSourceMap.containsKey(MultiTenantProperties.DEFAULT_TENANT))
          logger.error("DEFAULT tenant datasource is required but not configured!");

    MultiTenantDataSource multiTenantDataSource = new MultiTenantDataSource();
    multiTenantDataSource.setDefaultTargetDataSource(tenantDataSourceMap.get(MultiTenantProperties.DEFAULT_TENANT));
    multiTenantDataSource.setTargetDataSources(tenantDataSourceMap);
    return multiTenantDataSource;
  }

  @Bean
  public Filter filter() {
    return new MultiTenantFilter(properties);
  }

  @Bean
  @ConditionalOnBean(MultiTenantFilter.class)
  public FilterRegistrationBean myFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(filter());
    return registration;
  }
}