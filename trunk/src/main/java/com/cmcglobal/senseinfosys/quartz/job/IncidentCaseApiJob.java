package com.cmcglobal.senseinfosys.quartz.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cmcglobal.senseinfosys.model.IncidentCaseDto;
import com.cmcglobal.senseinfosys.service.ApiCallback;
import com.cmcglobal.senseinfosys.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

/**
 * 
 * @author NVAn
 * File name：IncidentCaseApiJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                  (Comment)
 *   Rev 1.00   2017/07/27    IncidentCaseApiJob         Create New
 */

@Service
@Transactional
public class IncidentCaseApiJob extends BaseJob {
	
	private static final Logger log = LoggerFactory.getLogger(IncidentCaseApiJob.class);
	
	@Value("${ifn-get-incidentcase}")
	public String incidentUrl;
	
	@Value("${ifn-duration}") 
	public int splitDuration;
	
	@Value("${ifn-dateFrom}")
	public String dateFromConfig;
	
	@Value("${ifn-maximum-data}")
	public int maximumData;
	
	@Value("${ifn-medium-data-upper}")
	public int mediumDataUpper;
	
	@Value("${ifn-medium-data-lower}")
	public int mediumDataLower;
	
	@Value("${ifn-maximum-batch}")
	public int maximumDataPerBatch;
	
	@Value("${ifn-medium-batch}")
	public int mediumDataPerBatch;
	
	@Value("${ifn-minimum-batch}")
	public int minimumDataPerBatch;
	
	@Value("${ifn-maximum-jobs}")
	public int maximumNumberJobs;
	
	@Value("${ifn-medium-jobs}")
	public int mediumNumberJobs;
	
	@Value("${ifn-minimum-jobs}")
	public int minimumNumberJobs;
	
	@Autowired
	private RestTemplate restemplate;
	
	@Autowired
	private ApiCallback callback;
	
	private ObjectMapper objectMapper;
	
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
	public void executeJob() throws JobExecutionException{

		// Get parameter
		String dateFrom =  ((String) getExtra(Utils.INCIDENT_CASE_EXTRA_DATA)).split(Utils.COMMA)[Utils.ZERO];
		String dateTo = ((String) getExtra(Utils.INCIDENT_CASE_EXTRA_DATA)).split(Utils.COMMA)[Utils.ONE];
		
		// Parse date
		Date dt1 = Utils.parseDate(dateFrom, Utils.DATE_FORMAT_PARTTERN_3);
		Date dt2 = Utils.parseDate(dateTo, Utils.DATE_FORMAT_PARTTERN_3);
		
		// Split date to durations 
		List<String> lstDate = Utils.splitTime(dt1, dt2, splitDuration);
		List<List<String>> listTotal = Lists.partition(lstDate, 2);
		log.debug("List all duration :" + listTotal.get(0));
		
		// Get list object follow durations.
		List<IncidentCaseDto> listDtos = getListIncidentCaseFromApi(listTotal);
		
		long listSize = listDtos.stream().count();
		if (listSize > 0l) {
			// Perform database job.
			doCallIncidentDbJob(listSize, listDtos );
		} else {
			log.debug("Number of record is empty " + listSize);
		}
		
	}

	/**
	 * @param listSize
	 */
	private void doCallIncidentDbJob(long listSize, List<IncidentCaseDto> listDto) {
		List<List<IncidentCaseDto>> lst = null;
		if (listSize >= mediumDataUpper && listSize < maximumData) {
			lst = Utils.chunk(listDto, mediumDataPerBatch);
			// Create ten jobs, each job perform size of list is max 1000.
			spiltDataToJobs(lst);
		} else if (listSize >= mediumDataLower && listSize < mediumDataUpper) {
			lst = Utils.chunk(listDto, minimumDataPerBatch);
			// Create five jobs, each job perform size of list is max 500.
			spiltDataToJobs(lst);
		} else {
			// Use one job and perform size of list is max 500.
			log.debug("Call job to insert to database");
			callback.onSuccess(IncidentCaseDbJob.class, Utils.INCIDENT_CASE_EXTRA_DATA, listDto);
		}

	}
	
	/**
	 * Create jobs follow input data.
	 * @param limit
	 * @param listDto
	 */
	private void spiltDataToJobs(List<List<IncidentCaseDto>> listDto) {
		int limit = listDto.size();
		for (int i = 0; i < limit; i++) {
			callback.onSuccess(IncidentCaseDbJob.class, Utils.INCIDENT_CASE_EXTRA_DATA, listDto.get(i));
		}
	}
	
	/**
	 * @param listTotal
	 * @return
	 * @throws Exception 
	 */
	private List<IncidentCaseDto> getListIncidentCaseFromApi(List<List<String>> listTotal) throws JobExecutionException {
		List<IncidentCaseDto> listDtos = new ArrayList<>();
		List<IncidentCaseDto> listDto = new ArrayList<>();
		for(List<String> lstStr : listTotal) {
			// Get url.
			String url = Utils.getUrlApi(incidentUrl, lstStr.get(Utils.ZERO), lstStr.get(Utils.ONE));
			log.debug("Date from " + lstStr.get(Utils.ZERO) + "Date to" + lstStr.get(Utils.ONE));
			// Get data from api.
			String data = getDataFromIncidentCaseApi(url);
			if(!Utils.EMPTY.equals(data)){
				// Read data from string.
				listDto = readDataFromJsonString(data);
				log.debug("From +" + lstStr.get(Utils.ZERO) + "to" + lstStr.get(Utils.ONE) + "have number of record is :" + listDto.size());
				// Add to list total.
				if(listDto != null){
					listDtos.addAll(listDto);
				}
			}
		}
		return listDtos;
	}
	
	/**
	 * Read data from json file.
	 * @param String jsonData from API
	 * @return list dto
	 */
	private List<IncidentCaseDto> readDataFromJsonString(String jsonData)   throws JobExecutionException{
		objectMapper = new ObjectMapper();
		List<IncidentCaseDto> lstData = null;
		try {
			lstData = objectMapper.readValue(jsonData, new TypeReference<List<IncidentCaseDto>>(){});
			log.info("Mapp data from json file to list Incident successfull");
		} catch (IOException e) {
			log.error("IncidentCaseApiJob :Errors mapping data from json: ", e);
			throw new JobExecutionException("IncidentCaseApiJob: Errors mapping data from json");
		}
		return lstData;
	}
	
	
	
	/**
	 * @param String url
	 * @return String data
	 * @throws Exception 
	 */
	public String getDataFromIncidentCaseApi(String url) throws JobExecutionException {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> resp = null;;
		try {
			resp = restemplate
							.exchange(url, HttpMethod.GET, new HttpEntity<String>(requestHeaders),
							String.class);
			log.debug("Url success: " + url);
			// Check error response from api.
			if(HttpStatus.OK.equals(resp.getStatusCode())) {
				log.info("Get data successfully from api :" + url);
				return resp.getBody();
			} else if (HttpStatus.BAD_REQUEST.equals(resp.getStatusCode())) {
				log.error("Invalid request with" + url);
				throw new JobExecutionException("Incident case api invalid request with");
			} else if (HttpStatus.UNAUTHORIZED.equals(resp.getStatusCode())) {
				log.error("Unauthorized access with: " + url);
				throw new JobExecutionException("Incident case api unauthorized access with");
			} else if (HttpStatus.NOT_FOUND.equals(resp.getStatusCode())) {
				log.error("The request is not found : " + url);
				throw new JobExecutionException("Incident case api the request is not found");
			} else if (HttpStatus.INTERNAL_SERVER_ERROR.equals(resp.getStatusCode())) {
				log.error("Internal Server Error : " + url);
				throw new JobExecutionException("Incident case api internal server error");
			} 
		} catch (RestClientException e) {
			log.error("Incident case api url failed: " + url, e);
			throw new JobExecutionException("Get incident case api failed");
		} 
		return Utils.EMPTY;
	}
}