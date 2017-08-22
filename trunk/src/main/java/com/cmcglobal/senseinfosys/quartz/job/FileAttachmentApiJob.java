package com.cmcglobal.senseinfosys.quartz.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.TmpAttachment;
import com.cmcglobal.senseinfosys.service.FileAttachmentApiService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NHLong File FileAttachmentApiJob.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/27 FileAttachmentApiJob Create New
 */

@Service
@Transactional
public class FileAttachmentApiJob extends BaseJob {

	private static final Logger log = Logger.getLogger(FileAttachmentApiJob.class);

	private Map<Integer, int[]> mAttachmentData;

	@Autowired
	private FileAttachmentApiService fileAttachmentApiService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		try {
			executeJob();
		} catch (JobExecutionException e) {
			log.error("FileAttachmentApiJob", e);
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void executeJob() throws JobExecutionException {
		try {
			mAttachmentData = (Map<Integer, int[]>) getExtra(Utils.ATTACHMENT_EXTRA_DATA);

			if (mAttachmentData != null || !mAttachmentData.isEmpty()) {
				List<TmpAttachment> tmpAttachments = getLstAttachment(mAttachmentData);
				fileAttachmentApiService.execTmpAttachment(tmpAttachments);
			}
		} catch (Exception e) {
			log.error("FileAttachmentApiJob execute failed: ", e);
			throw new JobExecutionException("FileAttachmentApiJob execute failed");
		}
		
	}

	private List<TmpAttachment> getLstAttachment(Map<Integer, int[]> mAttachmentData2) {
		TmpAttachment item;
		int incidentCaseId;
		List<TmpAttachment> tmpAttachments = new ArrayList<>();
		int attachments[];

		for (Map.Entry<Integer, int[]> entry : mAttachmentData2.entrySet()) {
			incidentCaseId = entry.getKey();
			attachments = entry.getValue();
			if (attachments != null && (attachments.length > 0)) {
				for (Integer attachmentId : attachments) {
					item = new TmpAttachment();
					item.setIncidentCaseId(incidentCaseId);
					item.setId(attachmentId);
					tmpAttachments.add(item);
				}
			}
		}

		return tmpAttachments;
	}

}
