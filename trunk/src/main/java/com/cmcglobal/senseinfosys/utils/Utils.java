package com.cmcglobal.senseinfosys.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmcglobal.senseinfosys.model.JobLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {
	
	private static final Logger log = LoggerFactory.getLogger(Utils.class);
	
	public static final String MODE_KEY = "mode";
	
	public static final String INCIDENT_CASE_EXTRA_DATA = "incident_case_extra_data";
	
	public static final String INCIDENT_CASE_EXTRA_DATA_1 = "incident_case_extra_data_1";
	
	public static final String SHIP_EXTRA_DATA = "ship_extra_data";
	
	public static final String LOOKUP_EXTRA_DATA = "lookup_extra_data";
	
	public static final String ATTACHMENT_EXTRA_DATA = "attachment_extra_data";
	
	public static final String BASE_URL = "https://portal.recaap.org/api";
	
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	
	private static final String DATE_FORMAT_PARTTERN_1 = "dd/MM/yyyy hhmmss";
	
	private static final String DATE_FORMAT_PARTTERN_2 = "yyyyMMdd_HHmmss";
	
	public static final String DATE_FORMAT_PARTTERN_3 = "dd/MM/yyyy";
	
	public static final String DATE_FORMAT_PARTTERN_4 = "MM/dd/yyyy";
	
	public static final String FOLDER = "src/main/resources/data";
	
	public static final String FOLDER_SHIP = "ship";
	
	public static final String PREFIX_SHIP_FILE_NAME = "ship_";
	
	public static final String PREFIX_INCEIDENT_LOOKUP_FILE_NAME = "incident_lookup_";
	
	public static final String POSFIX_FILE_NAME = ".json";
	
	private static final String INCIDENT_CASES_URL = "/incident_cases/";
	
	public static final String FILE_FLODER_INCIDENT = "/incident/";
	
	private static final String ATTACHMENTS_URL = "/attachments/";
	
	private static final String DATE_FROM_URL = "from=";
	
	private static final String DATE_TO_URL = "&to=";
	
	public static final String COMMA = ",";
	
	public static final String EMPTY = "";
	
	public static final String ATTACHMENTS_FILE_FOLDER = "resources" + File.separator
			+ "documents";
	
	public static final int ZERO = 0;
	
	public static final int ONE = 1;
	
	public static final int BATCH_SIZE = 100;
	
	public static final int FAILED = -1;
	
	public static final int AUTO_MODE = 10;
	
	public static final int MANUAL_MODE = 11;
	
	/**
	 * @param listData
	 * @param prefixFileName
	 * @param nameFolder
	 */
	public static <T> void writeListObjectToJsonFile(List<T> listData, String prefixFileName, String nameFolder) {
		ObjectMapper objectMapper = new ObjectMapper();
		String content = "";
		String fileName = "";
			try {
				// Map data to json file.
				content = objectMapper.writeValueAsString(listData);
				log.info("Mapping list object to json string successfully.");
				
				// Create file name json.
				fileName = creatFileName(prefixFileName, nameFolder);
				log.debug("Create file name :"  + fileName);
				// Write content to file.
				writeDataToJonFile(content, nameFolder, fileName);
			} catch (JsonProcessingException e) {
				log.error("Error in write list data to string json at the file: " + fileName + e.getMessage() );
				e.printStackTrace();
			}
			
	}
	
	/**
	 * @param url
	 * @param id1
	 * @param id2
	 * @return String url
	 */
	public static String getUrlApi(int id1, int id2){
		return new StringBuilder()
					.append(BASE_URL)
					.append(INCIDENT_CASES_URL)
					.append(id1)
					.append(ATTACHMENTS_URL)
					.append(id2).toString();
	}
	
	/**
	 * @param url
	 * @param str1
	 * @param str2
	 * @return String url
	 */
	public static String getUrlApi(String url, String str1, String str2) {
		
		StringBuilder strBld = new StringBuilder();
		strBld.append(url);
		if(str1 != null && str2 != null){
			strBld.append(DATE_FROM_URL)
				  .append(str1)
				  .append(DATE_TO_URL)
				  .append(str2);
		}
		return strBld.toString();
	}
	
	/**
	 * @param prefixFileName
	 * @param nameFolder
	 * @param index
	 * @param dateStr
	 * @return file name
	 */
	private static String creatFileName(String prefixFileName, String nameFolder){
		return new StringBuilder()
					.append(prefixFileName)
					.append( formatDate(new Date(), DATE_FORMAT_PARTTERN_2))
					.toString();
	}
	
	/**
	 * @param nameFolder
	 * @param fileName
	 * @return
	 */
	private static String createFilePath(String nameFolder, String fileName){
		return new StringBuilder()
					.append(FOLDER)
					.append(nameFolder)
					.append(fileName)
					.append(POSFIX_FILE_NAME).toString();
	}
	
	/**
	 * @param jsonFile
	 * @param typeFolder (option depend on table)
	 * @param fileName
	 */
	private static void writeDataToJonFile(String jsonFile,String nameFolder, String fileName) {
		BufferedOutputStream outStream = null;
		File file = null;
		try {
			// Create file path.
			String path = createFilePath(nameFolder, fileName);
			log.info("Create path file sussessfully: " + path);
			
			file = new File (path);
			File parentDir = file.getParentFile();
			// Check file does not exists, then create it
			if (!parentDir.exists()) { 
				parentDir.mkdirs();
			}
			
			outStream = new BufferedOutputStream(new FileOutputStream(file));
			// Get the content in bytes
			byte[] contentInBytes = jsonFile.getBytes();
			outStream.write(contentInBytes);
			
			outStream.flush();
			outStream.close();
			log.info("Write file successfully: " + file.getName());
		} catch (FileNotFoundException e) {
			log.error("File not found when write data to json file " + file.getName() + e.getMessage());
		} catch (IOException e) {
			log.error("File i/o exception " + file.getName()  +  e.getMessage());
		} finally {
			try {
				if (outStream != null) {
					outStream.close();
					log.info("Write file successfully : " + file.getName());
				}
			} catch (IOException ex) {
				log.error("File i/o exception when close stream "+ file.getName()  + ex.getMessage());
			}

		}
	}
	
	/**
	 * @param date
	 * @param formatDate
	 * @return formated date
	 */
	public static String formatDate(Date date, String formatDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
		return sdf.format(date);
	}
	
	/**
	 * @param date
	 * @param formateDate
	 * @return formated date
	 */
	public static Date parseDate(String date, String formateDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formateDate);
		try {
			log.info("Parse date successfully :" + date);
			return sdf.parse(date);
		} catch (ParseException e) {
			log.error("parse date error :" + e.getMessage());
			return null;
		}
	}
	
	public static Date getLogDate(){
		String logDate = logDate();
		return parseDate(logDate, DATE_FORMAT_PARTTERN_1);
	}
	
	/**
	 * @param folderName
	 * @param fileName
	 * @return
	 */
	public static String buildFilePath(String folderName, String fileName){
		File file = new File(ATTACHMENTS_FILE_FOLDER);
		if(!file.exists()){
			file.mkdir();
		}
		return new StringBuilder().append(ATTACHMENTS_FILE_FOLDER)
				.append(folderName)
				.append(fileName)
				.append(POSFIX_FILE_NAME).toString();
	}
	
	/**
	 * @return current date
	 */
	public static String logDate() {
		return Utils.formatDate(new Date(), Utils.DATE_FORMAT_PARTTERN_1);
	}
	
	/**
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static List<String> splitTime(Date fromDate, Date toDate, int splitDuration) {

		Calendar now = Calendar.getInstance();
		now.setTime(fromDate);
		List<String> listDate = new ArrayList<>();
		listDate.add(formatDateYYYMMdd(now.getTime()));
		Date dateDv = null;
		while(true){
			now.add(Calendar.MONTH, splitDuration);
			dateDv = now.getTime();
			if (dateDv.before(toDate)) {
				listDate.add(formatDateYYYMMdd(dateDv));
				listDate.add(formatDateYYYMMdd(dateDv));
				now.setTime(dateDv);
			} else {
				break;
			}
		}
		listDate.add(formatDateYYYMMdd(toDate));
		return listDate;
	}
	
	/**
	 * @param input
	 * @param chunkSize
	 * @return sub list of list
	 */
	public static <T> List<List<T>> chunk(List<T> input, int chunkSize) {
		int inputSize = input.size();
		int chunkCount = (int) Math.ceil(inputSize / (double) chunkSize);
		Map<Integer, List<T>> map = new HashMap<>(chunkCount);
		List<List<T>> chunks = new ArrayList<>(chunkCount);
		for (int i = 0; i < inputSize; i++) {
			map.computeIfAbsent(i / chunkSize, (ignore) -> {
				List<T> chunk = new ArrayList<>();
				chunks.add(chunk);
				return chunk;

			}).add(input.get(i));
		}

		return chunks;
	}
	
	/**
	 * @param dt
	 * @return date is formated YYYYMMDD
	 */
	private static String formatDateYYYMMdd(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
		return sdf.format( dt);
	}
	
	/**
	 * @param dateToValidate
	 * @param dateFromat
	 * @return true if valid date or else
	 */
	public static boolean isDateValid(String dateToValidate, String dateFromat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateToValidate);
			log.info("Date paramter" + date);
		} catch (ParseException e) {
			log.error("Error parse date paramter" + dateToValidate);
			return false;
		}
		return true;
	}
	
	/**
	 * @param dateStr
	 * @return string after formated
	 */
	public static String formatDateIncident(Date date) {
		SimpleDateFormat sdfDestination = new SimpleDateFormat(DATE_FORMAT_PARTTERN_3);
		return sdfDestination.format(date);

	}
	
	public static boolean isEmpty(String value){
		return (value == null || value.trim().length() == 0);
	}
	
	/**
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	public static String createJobParameter(String dateFrom, String dateTo) {
		return new StringBuilder().append(dateFrom).append(Utils.COMMA).append(dateTo).toString();
	}
	
	/**
	 * 
	 * @param jobLogs
	 * @return object
	 */
	public static JobLog getLastJob(List<JobLog> jobLogs) {
		jobLogs.sort(
		         (JobLog h1, JobLog h2) -> h1.getStartTime().compareTo(h2.getStartTime()));
		if(jobLogs.size() > 0){
			JobLog jobLog = jobLogs.get(jobLogs.size() - 1);
			return jobLog;
		}else {
			return null;
		}
	}
}
