package com.stixcloud.common.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by chongye on 11/11/2016.
 */
@Profile("!test")
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.stixcloud.cart"})
//@EnableHystrix
public class CloudConfig {
}
