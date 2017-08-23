package com.sistic.be.app.util;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class DebugUtil {
	
	private final static Logger logger = LogManager.getLogger(DebugUtil.class);
	
	private static ObjectMapper mapper;
	
	@Autowired
	private ObjectMapper mapperBean;
	
	@PostConstruct
	public void initStaticMapper() {
		mapper = this.mapperBean;
	}
	
	/**
	 * Returns string
	 * @param object
	 * @return
	 */
	public static String writeWithPrettyPrinter(Object object) {
		try {
			String patronFormJson = "\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			return patronFormJson;
		} catch (JsonProcessingException e) {
			return "\nCannot pretty print object";
		} catch (Exception e) {
			e.printStackTrace();
			return "\nCannot pretty print object";
		}
	}
	
	/**
	 * write to logs using logger
	 * @param object
	 */
	public static void printLogWithPrettyPrinter(Level level, Object object) {
		try {
			String prettyStr = "\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			logger.log(level, prettyStr);
		} catch (JsonProcessingException e) {
			logger.error("\nCannot pretty print object");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
