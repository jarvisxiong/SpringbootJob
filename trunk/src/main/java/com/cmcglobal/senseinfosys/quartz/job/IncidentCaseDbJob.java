package com.cmcglobal.senseinfosys.quartz.job;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.IncidentCaseDto;
import com.cmcglobal.senseinfosys.service.ApiCallback;
import com.cmcglobal.senseinfosys.service.IncidentCaseService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：IncidentCaseDbJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                  (Comment)
 *   Rev 1.00   2017/07/27    IncidentCaseDbJob          Create New
 */

@Service
@Transactional
public class IncidentCaseDbJob extends BaseJob{
	
	private static final Logger log = LoggerFactory.getLogger(IncidentCaseDbJob.class);
	
	@Autowired
	private IncidentCaseService incidentCaseService;
	
	@Autowired
	private ApiCallback callback;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		try {
			executeJob();
		} catch (JobExecutionException e) {
			throw e;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void executeJob() throws JobExecutionException {
		try {
			List<IncidentCaseDto>listDto = (List<IncidentCaseDto>) getExtra(Utils.INCIDENT_CASE_EXTRA_DATA);
			int insFlg = 0;
			if (listDto != null && listDto.size() > 0) {
				log.debug("Size of inserted list into database :" + listDto.size());
				// Convert list object into sql string.
				String result = listDto.stream()
									    .map(IncidentCaseDto::toString)
									    .collect(Collectors.joining(""));
				// Insert data into database
				insFlg = incidentCaseService.insertIncidentCase(result);
				if (insFlg > Utils.FAILED) {
					log.debug("Insert data to database successfully with number of record is :" + insFlg);
					// Sent data to job download file.
					Map<Integer, int[]> map =  createMapIncident (listDto);
					callback.onSuccess(FileAttachmentApiJob.class, Utils.ATTACHMENT_EXTRA_DATA, map);
				}else {
					throw new JobExecutionException("IncidentCaseDbJob insert data unsuccesfully");
				}
			}
		} catch (Exception e) {
			log.error("IncidentCaseDbJob failed: ", e);
			throw new JobExecutionException("IncidentCaseDbJob insert data unsuccesfully");
		} 
	}
	
	/**
	 * @param listDto
	 * @return
	 */
	private Map<Integer, int[]> createMapIncident (List<IncidentCaseDto> listDto) {
		Map<Integer, int[]> mapFileAttach =   listDto.stream()
					  .sorted((f1, f2) -> f1.getUpdated_date().compareTo(f2.getUpdated_date()))
					  .filter(p -> p.getAttachments().length > 0)
					  .collect(Collectors
							  		.toMap(IncidentCaseDto::getId, IncidentCaseDto::getAttachments,  (oldValue, newValue) -> newValue));
		return mapFileAttach;
	}
}