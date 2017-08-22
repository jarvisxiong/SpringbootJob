package com.cmcglobal.senseinfosys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.JobLog;
import com.cmcglobal.senseinfosys.repository.JobLogRepository;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.JobStatus;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：JobLogServiceImpl.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)           (Comment)
 *   Rev 1.00   2015/07/27    JobLogService       Create New
 */
@Service("jobLogService")
@Transactional
public class JobLogServiceImpl implements JobLogService{
	
	private static final Logger log = LoggerFactory.getLogger(JobLogServiceImpl.class);
	
	@Autowired
	JobLogRepository jobLogRepository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<JobLog> getJobLogsByGroup() {
		List<JobLog> jobLogs = new ArrayList<>();
		JobLog jobLog = jobLogRepository.findFirst1ByOrderByStartTimeDesc();
		
		if (jobLog == null) {
			return jobLogs;
		}
		List<JobLog> lst = jobLogRepository.findByGroupId(jobLog.getGroupId());
		return lst;
	}
	
	@Override
	public JobLog saveJobLog(JobLog jobLog) {
		JobLog result = jobLogRepository.save(jobLog);
		return result;
	}

	@Override
	public JobLog getJobLogByJobKey(String jobKey) {
		return jobLogRepository.findById(jobKey);
	}

	@Override
	public List<JobLog> getAutomaticJob() {
		return jobLogRepository.findByJobName("AutomaticJob");
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<JobLog> getIncidentJobApi() {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JobLog.class);
			criteria.add(Restrictions.eq("jobName", "IncidentCaseApiJob"));
			criteria.add(Restrictions.eq("status",  JobStatus.SUCCESS.value()));
			List<JobLog> lst = criteria.list();
			return lst;
		} catch (HibernateException e) {
			log.error("Get incidentjobapi " + "\n" + e.getMessage() + "\n" + Utils.logDate());
			return new ArrayList<>();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobLog> getJobLogsWithoutAutoJob() {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JobLog.class);
			criteria.add(Restrictions.ne("jobName", "AutomaticJob"));
			List<JobLog> lst = criteria.list();
			return lst;
		} catch (HibernateException e) {
			log.error("Get not automatic job " + "\n" + e.getMessage() + "\n" + Utils.logDate());
			return null;
		}
	}
	
	@Override
	public List<JobLog> getJobLogByGroupId(String groupId) {
		return jobLogRepository.findByGroupId(groupId);
	}

}
