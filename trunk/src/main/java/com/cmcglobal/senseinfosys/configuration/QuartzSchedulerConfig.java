package com.cmcglobal.senseinfosys.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.cmcglobal.senseinfosys.quartz.AutowiringSpringBeanJobFactory;

@Configuration
public class QuartzSchedulerConfig {

    
	@Bean
	@ConfigurationProperties(prefix="spring.secondDatasource")
	public DataSource secondaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	

    @Autowired
    private ApplicationContext context;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(secondaryDataSource());

        factory.setQuartzProperties(quartzProperties());

		// custom job factory of spring with DI support for @Autowired!
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(context);
		factory.setJobFactory(jobFactory);

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
