/**
 * 
 */
package com.cmcglobal.senseinfosys.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cmcglobal.senseinfosys.model.IncidentLookup;
import com.cmcglobal.senseinfosys.model.IncidentLookupID;
import com.cmcglobal.senseinfosys.model.TmpIncidentLookup;
import com.cmcglobal.senseinfosys.quartz.job.IncidentLookupDbJob;
import com.cmcglobal.senseinfosys.repository.IncidentLookupRepository;
import com.cmcglobal.senseinfosys.repository.TmpIncidentLookupRepository;
import com.cmcglobal.senseinfosys.service.ApiCallback;
import com.cmcglobal.senseinfosys.service.IncidentLookupService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：IncidentLoopkupServiceImpl.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                 (Comment)
 *   Rev 1.00   2017/07/27    IncidentLookupService    Create New
 */
@Service("incidentLookupService")
@Transactional(propagation = Propagation.REQUIRED)
public class IncidentLoopkupServiceImpl implements IncidentLookupService {
	private static final Logger log = LoggerFactory.getLogger(IncidentLoopkupServiceImpl.class);
	
	@Value("${ifn-get-incident-lookups}")
	String getIncidentLookupUrl;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	int batchSize;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IncidentLookupRepository incidentLookupRepo;
	
	@Autowired
	private TmpIncidentLookupRepository tmpIncidentLookupRepo;
	
	@Autowired
	private ApiCallback callback;

	@Override
	public void getLookupData() throws JobExecutionException {
		List<TmpIncidentLookup> lookupData = new ArrayList<>();
		
		// get incident lookup data from API
		try {
			log.trace("-------Start of getting incident lookup data to database--------");
			lookupData = filterLookupData(getData());
			log.trace("Create import incident lookup data job");
			callback.onSuccess(IncidentLookupDbJob.class, Utils.LOOKUP_EXTRA_DATA, lookupData);
			log.trace("-------End of getting incident lookup data to database--------");
		} catch(Exception ex) {
			// Log error occur when get data from API
			log.error(ex.getMessage());
			throw new JobExecutionException("Get incident lookup data from API failed.");
		}
	}
	
	/**
	 * filter duplicate key data
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<TmpIncidentLookup> filterLookupData(List<TmpIncidentLookup> data) throws JobExecutionException {
		try {
			Set<TmpIncidentLookup> s = new TreeSet<TmpIncidentLookup>(new Comparator<TmpIncidentLookup>() {

		        @Override
		        public int compare(TmpIncidentLookup o1, TmpIncidentLookup o2) {
		            if (o1.getType().equals(o2.getType()) && o1.getValue().equals(o2.getValue())) {
		            	return 0;
		            } else {
		            	return 1;
		            }
		        }
		    });
			s.addAll(data);
		    return (List<TmpIncidentLookup>)(Object)Arrays.asList(s.toArray());
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new JobExecutionException("Failed in filter lookupdata");
		}
	}

	@Override
	public void importData(List<TmpIncidentLookup> tmpLookups) throws JobExecutionException {
		try {
			log.trace("-------Start of importing incident lookup data to database--------");
			// insert data into tmp_ship table
			insertDataToTmpLookupTable(tmpLookups);
			
			// remove deleted lookup in INCIDENT_LOOKUP table
			removeDeletedLookups();
			
			// insert/update data to INCIDENT_LOOKUP table
			insertData(tmpLookups);
			
			// remove data in TMP_INCIDENT_LOOKUP table
			removeDataInTmpLookupTable();
			log.trace("-------End of importing incident lookup data to database--------");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new JobExecutionException("Import incident lookup data to database failed");
		}
		
	}
	
	/***
	 * remove deleted incident lookup in INCIDENT_LOOKUP table
	 * @return
	 */
	public int removeDeletedLookups() {
		String query = "delete from \"INCIDENT_LOOKUP\" where not exists (select * from \"TMP_INCIDENT_LOOKUP\" where \"TYPE\"=\"INCIDENT_LOOKUP\".\"TYPE\" and \"VALUE\"=\"INCIDENT_LOOKUP\".\"VALUE\");";
		
		return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Integer>() {

			@Override
			public Integer execute(Connection connection) throws SQLException {
				try (Statement st = connection.createStatement()) {
					int result = st.executeUpdate(query);
					return result;
				} catch (SQLException e) {
					log.error("Remove deleted incident lookup from TMP_INCIDENT_LOOKUP table failed.");
					throw e;
				}
				
			}

		});
	}
	
	/***
	 * remove data in TMP_INCIDENT_LOOKUP table
	 */
	private void removeDataInTmpLookupTable() {
		tmpIncidentLookupRepo.deleteAllInBatch();
	}
	
	/***
	 * 
	 * @param lookups
	 * @throws Exception
	 */
	private void insertDataToTmpLookupTable(List<TmpIncidentLookup> lookups) throws JobExecutionException {
		try {
			// clean data from TMP_INCIDENT_LOOKUP table before insert new data
			removeDataInTmpLookupTable();
			
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for (TmpIncidentLookup lookup : lookups) {
				int idx = lookups.indexOf(lookup);
				session.save(lookup);
				if (idx % batchSize == 0 && idx != 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		} catch (Exception ex) {
			log.error("Insert data into TMP_INCIDENT_LOOKUP failed.", ex);
			throw new JobExecutionException("Insert data into TMP_INCIDENT_LOOKUP failed.");
		}
	}

	/***
	 * 
	 * @return incident lookup data as list of TmpIncidentLookup
	 * @throws RestClientException
	 */
	private List<TmpIncidentLookup> getData() throws JobExecutionException, RestClientException {
		List<TmpIncidentLookup> lookups = new ArrayList<>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<TmpIncidentLookup[]> response = restTemplate.exchange(getIncidentLookupUrl, HttpMethod.GET, entity,
				TmpIncidentLookup[].class);
		HttpStatus status = response.getStatusCode();
		if (status == HttpStatus.OK) {
			log.info("Get ship data from api success.");
			TmpIncidentLookup[] lookupArr = response.getBody();
			lookups = Arrays.asList(lookupArr);
		} else if (status == HttpStatus.UNAUTHORIZED) {
			throw new JobExecutionException("UNAUTHORIZED get lookup data from API - StatusCode " + status);
		} else if (status == HttpStatus.NOT_FOUND) {
			throw new JobExecutionException("NOT_FOUND lookup data from api - StatusCode " + status);
		} else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new JobExecutionException("Error getting lookup data from api - StatusCode " + status);
		}

		return lookups;
	}

	/**
	 * insert batch data
	 * 
	 * @param incidentLookups
	 */
	public void insertData(List<TmpIncidentLookup> incidentLookups) throws JobExecutionException {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for (TmpIncidentLookup tmpIncidentLookup : incidentLookups) {
				int idx = incidentLookups.indexOf(tmpIncidentLookup);
				IncidentLookup lookup = convertTmpLookupToLookup(tmpIncidentLookup);
				// check need to update rows data
				IncidentLookupID id = new IncidentLookupID(lookup.getType(), lookup.getValue());
				if (incidentLookupRepo.exists(id)) {
					IncidentLookup lookupInDB = incidentLookupRepo.findOne(id);
					if (lookupInDB.equals(lookup)) {
						continue;
					} else {
						session.update(lookup);
					}
				// add new rows data
				} else {
					session.save(lookup);
				}
				if (idx % batchSize == 0 && idx != 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
		} catch(Exception ex) {
			log.error("Insert/update data into INCIDENT_LOOKUP table failed.", ex);
			throw new JobExecutionException("Insert/update data into INCIDENT_LOOKUP table failed.");
		}
	}
	
	/***
	 * convert object from TmpIncidentLookup to IncidentLookup
	 * @param tmpLookup
	 * @return IncidentLookup object
	 */
	private IncidentLookup convertTmpLookupToLookup(TmpIncidentLookup tmpLookup) {
		IncidentLookup lookup = new IncidentLookup();
		lookup.setCatlevel(tmpLookup.getCatLevel());
		lookup.setDescription(tmpLookup.getDescription());
		lookup.setType(tmpLookup.getType());
		lookup.setValue(tmpLookup.getValue());
		return lookup;
	}
}
