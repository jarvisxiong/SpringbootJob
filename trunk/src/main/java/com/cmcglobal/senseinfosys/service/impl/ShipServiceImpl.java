package com.cmcglobal.senseinfosys.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cmcglobal.senseinfosys.model.Ship;
import com.cmcglobal.senseinfosys.model.TmpShip;
import com.cmcglobal.senseinfosys.quartz.job.ShipDbJob;
import com.cmcglobal.senseinfosys.repository.ShipRepository;
import com.cmcglobal.senseinfosys.repository.TmpShipRepository;
import com.cmcglobal.senseinfosys.service.ApiCallback;
import com.cmcglobal.senseinfosys.service.ShipService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：ShipServiceImpl.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)       (Comment)
 *   Rev 1.00   2017/07/27    ShipService    Create New
 */
@Service("shipService")
@Transactional(propagation = Propagation.REQUIRED)
public class ShipServiceImpl implements ShipService {
	private static final Logger log = LoggerFactory.getLogger(ShipServiceImpl.class);

	@Value("${ifn-get-ships}")
	String getShipUrl;

	@Autowired
	RestTemplate restTemplate;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	int batchSize;

	@Autowired
	private ShipRepository shipRepository;

	@Autowired
	private TmpShipRepository tmpShipRepository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ApiCallback callback;
	
	@Override
	public void getShipData() throws JobExecutionException {
		List<TmpShip> shipData = new ArrayList<>();

		// get ship data from API
		try {
			log.trace("-----Start of getting ship data from API------");
			shipData = filterShipData(getData());
			log.trace("Create import ship data job");
			callback.onSuccess(ShipDbJob.class, Utils.SHIP_EXTRA_DATA, shipData);
			log.trace("-----End of getting ship data from API-------");
		} catch (Exception ex) {
			// Log error occur when get data from API
			log.error("Get ship data from API failed." + ex.getMessage());
			throw new JobExecutionException("Get ship data from API failed.");
		}
	}
	
	/**
	 * filter duplicate key data
	 * @param data
	 * @return
	 */
	private List<TmpShip> filterShipData(List<TmpShip> data) throws JobExecutionException {
		try {
			Map<Integer, TmpShip> mapData = new HashMap<>();
			for (TmpShip ship : data) {
				if (!mapData.containsKey(ship.getId())) {
					mapData.put(ship.getId(), ship);
				}
			}
			List<TmpShip> result = new ArrayList<TmpShip>(mapData.values());
			return result;
		} catch (Exception ex) {
			log.error("Failed in filter ship data." + ex.getMessage());
			throw new JobExecutionException("Failed in filter ship data.");
		}
	}

	/***
	 * remove deleted ship in SHIP table
	 * @return
	 */
	public int removeDeletedShips() throws Exception {
		String query = "DELETE FROM \"SHIP\" WHERE \"ID\" IN (select \"ID\" "
				+ "from \"SHIP\" where \"ID\" NOT IN (select \"ID\" from \"TMP_SHIP\"));";
		return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Integer>() {

			@Override
			public Integer execute(Connection connection) throws SQLException{
					try (Statement st = connection.createStatement()) {
						int result = st.executeUpdate(query);
						log.debug("-------Remove deleted SHIP success ----------");
						return result;
					} catch (SQLException e) {
						log.error("Remove deleted ship from SHIP table failed.");
						throw e;
					}
			}

		});
	}

	@Override
	public void importData(List<TmpShip> tmpShips) throws JobExecutionException {
		try {
			log.trace("-------Start of importing ship data to database--------");
			// insert data into tmp_ship table
			insertDataToTmpShipTable(tmpShips);
			
			// remove deleted ship in SHIP table
			removeDeletedShips();
			
			// insert/update data to SHIP table
			insertData(tmpShips);
			
			// remove data in TMP_SHIP table
			removeDataInTmpShipTable();
			
			log.trace("-------End of importing ship data to database--------");
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new JobExecutionException("Import ship data to database failed");
		}
	}

	/***
	 * remove data in TMP_SHIP table
	 */
	private void removeDataInTmpShipTable() {
		tmpShipRepository.deleteAllInBatch();
	}

	/***
	 * 
	 * @param ships
	 * @return
	 * @throws Exception 
	 */
	private void insertDataToTmpShipTable(List<TmpShip> ships) throws JobExecutionException {
		try {
			// clean data from TMP_SHIP table before insert new data
			removeDataInTmpShipTable();
			
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for (TmpShip ship : ships) {
				int idx = ships.indexOf(ship);
				// add new rows data
				session.save(ship);
				if (idx % batchSize == 0 && idx != 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
			log.debug("-------Save TMP SHIP success ----------");
		} catch (Exception ex) {
			log.error("Insert data into TMP_SHIP failed");
			throw new JobExecutionException(ex);
		}
		
	}

	/***
	 * 
	 * @return ship data as list of TmpShip
	 * @throws RestClientException
	 */
	private List<TmpShip> getData() throws JobExecutionException, RestClientException {
		List<TmpShip> ships = new ArrayList<>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<TmpShip[]> response = restTemplate.exchange(getShipUrl, HttpMethod.GET, entity, TmpShip[].class);
		HttpStatus status = response.getStatusCode();
		if (status == HttpStatus.OK) {
			log.info("Get ship data from api success.");
			TmpShip[] shipArr = response.getBody();
			ships = Arrays.asList(shipArr);
		} else if (status == HttpStatus.UNAUTHORIZED) {
			throw new JobExecutionException("UNAUTHORIZED get ship data from API - StatusCode " + status);
		} else if (status == HttpStatus.NOT_FOUND) {
			throw new JobExecutionException("NOT_FOUND ship data from api - StatusCode " + status);
		} else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new JobExecutionException("Error getting ship data from api - StatusCode " + status);
		}
		return ships;
	}

	/**
	 * insert batch ship data
	 * 
	 * @param ships
	 */
	public void insertData(List<TmpShip> ships) throws JobExecutionException {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for (TmpShip tmpShip : ships) {
				int idx = ships.indexOf(tmpShip);
				Ship ship = shipconvertTmpShipToShip(tmpShip);
				// check need to update rows data
				if (shipRepository.exists(ship.getId())) {
					Ship shipInDB = shipRepository.findById(ship.getId());
					if (shipInDB.equals(ship)) {
						continue;
					} else {
						session.update(ship);
					}
					// add new rows data
				} else {
					session.save(ship);
				}
				if (idx % batchSize == 0 && idx != 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			session.close();
			log.debug("-------Insert SHIP success----------");
		} catch (Exception ex) {
			log.error("Insert/update data into SHIP table failed."+  ex.getMessage());
			throw new JobExecutionException("Insert/update data into SHIP table failed.");
		}
	}

	/**
	 * convert object from TmpShip to Ship
	 * @param tmpShip
	 * @return
	 */
	private Ship shipconvertTmpShipToShip(TmpShip tmpShip) {
		Ship ship = new Ship();
		ship.setCreatedBy(tmpShip.getCreatedBy());
		ship.setCreatedDate(tmpShip.getCreatedDate());
		ship.setId(tmpShip.getId());
		ship.setShipBuilt(tmpShip.getShipBuilt());
		ship.setShipCallSign(tmpShip.getShipCallSign());
		ship.setShipFlag(tmpShip.getShipFlag());
		ship.setShipGrossTonnage(tmpShip.getShipGrossTonnage());
		ship.setShipIMONumber(tmpShip.getShipIMONumber());
		ship.setShipInmarsatId(tmpShip.getShipInmarsatId());
		ship.setShipMSSINumber(tmpShip.getShipMSSINumber());
		ship.setShipName(tmpShip.getShipName());
		ship.setShipNameOfCompany(tmpShip.getShipNameOfCompany());
		ship.setShipType(tmpShip.getShipType());
		ship.setUpdatedBy(tmpShip.getUpdatedBy());
		ship.setUpdatedDate(tmpShip.getUpdatedDate());
		return ship;
	}
}
