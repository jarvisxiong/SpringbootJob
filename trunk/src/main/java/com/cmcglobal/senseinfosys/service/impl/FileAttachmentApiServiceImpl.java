package com.cmcglobal.senseinfosys.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.quartz.JobExecutionException;
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
import org.springframework.web.client.RestTemplate;

import com.cmcglobal.senseinfosys.model.FileAttachment;
import com.cmcglobal.senseinfosys.model.TmpAttachment;
import com.cmcglobal.senseinfosys.quartz.job.FileAttachmentDbJob;
import com.cmcglobal.senseinfosys.repository.TmpAttachmentRepository;
import com.cmcglobal.senseinfosys.service.ApiCallback;
import com.cmcglobal.senseinfosys.service.FileAttachmentApiService;
import com.cmcglobal.senseinfosys.utils.Utils;

@Service("fileAttachmentApiService")
@Transactional
public class FileAttachmentApiServiceImpl implements FileAttachmentApiService {

	private static final Logger log = Logger.getLogger(FileAttachmentApiServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiCallback apiCallback;

	@Autowired
	private TmpAttachmentRepository tmpAttachmentRepository;

	@Autowired
	private SessionFactory sessionFactory;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	int batchSize;

	private HttpHeaders requestHeaders = null;

	/**
	 * fetching each file
	 * 
	 * @param tmpAttachments
	 *            list attachments
	 */
	private void fetchFile(List<TmpAttachment> tmpAttachments) {
		for (TmpAttachment attachment : tmpAttachments) {
			downloadFile(attachment.getId(), attachment.getIncidentCaseId());
		}
	}

	/**
	 * Download file from server
	 * 
	 * @param attachmentId
	 * @param incidentCaseId
	 */
	private void downloadFile(int attachmentId, int incidentCaseId) {
		FileAttachment item = new FileAttachment();
		item.setId(attachmentId);
		item.setIncidentCaseId(incidentCaseId);
		try {
			if (requestHeaders == null) {
				requestHeaders = new HttpHeaders();
				requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			}

			ResponseEntity<byte[]> response = restTemplate.exchange(Utils.getUrlApi(incidentCaseId, attachmentId),
					HttpMethod.GET, new HttpEntity<String>(requestHeaders), byte[].class);

			String urlLog = getFullUrl(incidentCaseId, attachmentId);

			HttpStatus status = response.getStatusCode();
			if (status == HttpStatus.OK) {

				String fileName = getFileNameFromHeader(response.getHeaders());
				if (fileName.contains("\\")) {
					fileName = fileName.substring(fileName.lastIndexOf("\\")).replace("\\", " ");
				}
				if (Utils.isEmpty(fileName)) {
					log.error(getClass().getSimpleName() + "_GET FILE IS NULL " + urlLog);
					item.setFileContent("File not found");
					apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
				} else {
					File parent = new File(Utils.ATTACHMENTS_FILE_FOLDER + File.separator + incidentCaseId);
					if(!parent.exists() || !parent.isDirectory()){
						parent.mkdirs();
					}

					byte[] data = response.getBody();
					Path path = Files.write(Paths.get(parent.getAbsolutePath() + File.separator + fileName), data);

					item.setFileName(fileName);
					item.setFileContent(path.toAbsolutePath().toString());

					apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
					log.info(getClass().getSimpleName() + "_GET FILE SUCCESS " + urlLog);
				}

			} else if (status == HttpStatus.BAD_REQUEST) {
				log.error("BAD_REQUEST_" + status + urlLog);
				
				apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
			} else if (status == HttpStatus.UNAUTHORIZED) {
				log.error("UNAUTHORIZED_" + status + urlLog);
				apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
			} else if (status == HttpStatus.NOT_FOUND) {
				log.error("NOT_FOUND_" + status + urlLog);
				apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
			} else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
				log.error("INTERNAL_SERVER_ERROR_" + status + urlLog);
				apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String message = "Error getting file " + ";incident_id " + incidentCaseId + "; attachment_id: "
					+ attachmentId + "\n" + e.getMessage();
			log.error("Error getting file attachment" + message, e);
			item.setFileContent("File not found");
			apiCallback.onSuccess(FileAttachmentDbJob.class, Utils.ATTACHMENT_EXTRA_DATA, item);
		}
	}

	@Override
	public void execTmpAttachment(List<TmpAttachment> lst) {
		insertTmpAttachment(lst);
	}

	/**
	 * get file name from header
	 * 
	 * @param headers
	 * @return fileName
	 */
	private String getFileNameFromHeader(HttpHeaders headers) {
		String disposition = headers.getFirst("Content-Disposition");
		return disposition.substring(disposition.indexOf("filename=")).replace("filename=", "");
	}

	/**
	 * insert batch temp attachment
	 * 
	 * @param attachments
	 */
	public void insertTmpAttachment(List<TmpAttachment> attachments) {
		List<TmpAttachment> lstTmpUnComplete;
		try {
			if (attachments == null || attachments.isEmpty()) {
				return;
			} else {
				lstTmpUnComplete = getAllAttachmentUnCompleted();
				if (lstTmpUnComplete == null)
					lstTmpUnComplete = new ArrayList<>();

				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				for (TmpAttachment tmp : attachments) {
					if (tmpAttachmentRepository.findById(tmp.getId()) == null) {
						session.save(tmp);
						lstTmpUnComplete.add(tmp);
					}
				}
				session.flush();
				session.clear();
				tx.commit();
				session.close();
			}

			if (lstTmpUnComplete != null && !lstTmpUnComplete.isEmpty()) {
				fetchFile(lstTmpUnComplete);
			}
		} catch (Exception ex) {
			log.error("Insert/update data into TmpAttachment table failed.", ex);
			JobExecutionException exception = new JobExecutionException("Insert/update data into TmpAttachment table failed.");
			try {
				throw exception;
			} catch (JobExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get all files attachment uncompleted from tmp_attachment table to re-run
	 * download
	 * 
	 * @return list uncompleted
	 */
	@SuppressWarnings("unchecked")
	private List<TmpAttachment> getAllAttachmentUnCompleted() {
		try {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TmpAttachment.class, "tmp");
			criteria.add(Restrictions.lt("tmp.status", 1));
			List<TmpAttachment> person = criteria.list();
			return person;
		} catch (HibernateException e) {
			log.error("HibernateException getAllAttachmentUnComplete " + "\n" + e.getMessage() + "\n" + Utils.logDate());
			return null;
		}
	}

	private String getFullUrl(int incidentId, int attachmentId) {
		return "--https://portal.recaap.org/api/incident_cases/" + incidentId + "/attachments/" + attachmentId;
	}
}
