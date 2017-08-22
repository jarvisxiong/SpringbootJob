package com.cmcglobal.senseinfosys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.JobLog;

/**
 * 
 * @author NVAn
 * File name：JobLogRepository.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)         (Comment)
 *   Rev 1.00   2015/07/27    JobLogRepo       Create New
 */
@Repository("jobLogRepository")
public interface JobLogRepository extends JpaRepository<JobLog, String>{
	JobLog findById(String id);
	List<JobLog> findByJobName(String jobName);
	JobLog findFirst1ByOrderByStartTimeDesc();
	List<JobLog> findByGroupId(String groupId);
}
