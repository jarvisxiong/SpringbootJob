package com.cmcglobal.senseinfosys;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import com.cmcglobal.senseinfosys.joblistener.JobListenerImpl;
import com.cmcglobal.senseinfosys.quartz.job.AutomaticJob;
import com.cmcglobal.senseinfosys.quartz.job.JobsManagement;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.JobStatus;

@SpringBootApplication
@ComponentScan
public class IFNApplication extends SpringBootServletInitializer {
	
	private static final Logger log = Logger.getLogger(IFNApplication.class);

	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	private JobLogService jobLogService;
	
	@Value("${ifn-dateFrom}")
	 public String dateFromConfig;
	
	@Autowired
	JobsManagement jobManager;
	
	public static void main(String[] args) {
		SpringApplication.run(IFNApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doAfterStartup() throws SchedulerException {
		scheduler.clear();
		
		// Setting automatic jobs
		jobManager.setInitFlag(true);
		jobManager.setInitDate(new Date());
		log.debug("DateIFN" + jobManager.getInitDate());
		jobManager.setJobStatus(scheduler, jobLogService, JobStatus.AUTO);
		
		
		JobListenerImpl jobListener = new JobListenerImpl();
		scheduler.getContext().put("listener", jobListener);
		
		ListenerManager listenerManager = scheduler.getListenerManager();
		listenerManager.addJobListener(jobListener, GroupMatcher.anyJobGroup());
		
		// Call automatic job.
		jobManager.createAutomaticJob(AutomaticJob.class);
	}
}
