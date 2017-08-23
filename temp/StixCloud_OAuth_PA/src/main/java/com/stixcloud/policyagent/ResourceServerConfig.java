package com.stixcloud.policyagent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

//import com.stixcloud.policyagent.provider.SisticCustomUserAuthenticationConverter;


@Configuration
@EnableResourceServer
@PropertySource("classpath:pa_configurations.properties")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  private static final Logger logger = LogManager.getLogger(ResourceServerConfig.class);

  @Autowired
  private Environment env;

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http.requestMatcher(new OrRequestMatcher(
    	new AntPathRequestMatcher("/**/auth/**"),
        new AntPathRequestMatcher("/**/api/**"),
        new AntPathRequestMatcher("/**/cart/**"),
        new AntPathRequestMatcher("/**/products/**"),
        new AntPathRequestMatcher("/**/icc/**"),
        new AntPathRequestMatcher("/**/payment/**"),
        new AntPathRequestMatcher("/**/patrons/**"),
        new AntPathRequestMatcher("/**/orders/**")
    	))

       .authorizeRequests()
       .antMatchers("/**/api/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
       .antMatchers("/**/products/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
       .antMatchers("/**/icc/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
       .antMatchers("/**/auth/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
       .antMatchers("/**/cart/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write') and (#oauth2.hasScope('patron') or #oauth2.hasScope('guest'))")       
       .antMatchers("/**/orders/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")       
       .antMatchers("/**/payment/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
       .antMatchers("/**/patrons/account").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')")
	   .antMatchers("/**/patrons/account/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write') and #oauth2.hasScope('patron')")
       .antMatchers("/**/patrons/**").access("#oauth2.hasScope('read') and #oauth2.hasScope('write')");
  }


  @Override
  public void configure(final ResourceServerSecurityConfigurer config) {
    config.tokenServices(tokenServices());
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  } 
 
  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(env.getProperty("signing.key"));
    DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
//    defaultAccessTokenConverter
//        .setUserTokenConverter(new SisticCustomUserAuthenticationConverter());
    converter.setAccessTokenConverter(defaultAccessTokenConverter);
    return converter;
  }

  @Bean
  @Primary
  public DefaultTokenServices tokenServices() {
    final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());

    return defaultTokenServices;
  }
}
