package com.cmcglobal.senseinfosys.service.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.service.IncidentCaseService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：IncidentCaseServiceImpl.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                  (Comment)
 *   Rev 1.00   2017/07/27    IncidentCaseServiceImpl    Create New
 */

@Service("incidentCaseService")
@Transactional
public class IncidentCaseServiceImpl implements IncidentCaseService {
	
	private static final Logger log = Logger.getLogger(IncidentCaseServiceImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public int insertIncidentCase(String sql) {
		return inserIncidentData(sql);
		
	}
	
	private int inserIncidentData(final String sql) {
		return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<Integer>() {
			public Integer execute(Connection con) throws SQLException {
				try {
					Statement st = con.createStatement();
					return st.executeUpdate(sql);
				} catch (Exception e) {
					log.error("Insert incident case data processing failed " + e.getMessage());
					return Utils.FAILED;
				}
			}
		});
	}
}
