package com.stixcloud.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableZuulProxy
@Controller
public class ZuulServer {
  public static void main(String[] args) {
//    SpringApplication.run(ZuulServer.class, args);
    new SpringApplicationBuilder(ZuulServer.class).web(true).run(args);
  }

}