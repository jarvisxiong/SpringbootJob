package com.cmcglobal.senseinfosys.quartz.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.FileAttachment;
import com.cmcglobal.senseinfosys.model.TmpAttachment;
import com.cmcglobal.senseinfosys.repository.TmpAttachmentRepository;
import com.cmcglobal.senseinfosys.service.FileAttachmentDbService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NHLong File FileAttachmentDbJob.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/27 FileAttachmentDbJob Create New
 */

@Service
@Transactional
public class FileAttachmentDbJob extends BaseJob {

	private static final Logger log = Logger.getLogger(FileAttachmentDbJob.class);

	private FileAttachment mFileInfo;

	@Autowired
	FileAttachmentDbService fileAttachmentDbService;
	
	@Autowired
	private TmpAttachmentRepository tmpAttachmentRepository;
	
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
	public void executeJob() throws JobExecutionException {

		try {
			mFileInfo = (FileAttachment) getExtra(Utils.ATTACHMENT_EXTRA_DATA);
			if (mFileInfo != null) {
				fileAttachmentDbService.saveFileInfo(mFileInfo);
				updateTmpAttachmentStatus(mFileInfo);
				log.info(Utils.logDate()+"FileAttachmentDbJob_Insert file attachment success ");
			}
			
		} catch (Exception e) {
			log.error("FileAttachmentDbJob ", e);
			throw new JobExecutionException("FileAttachmentDbJob:insert file attachment failed");
		}
	}

	private void updateTmpAttachmentStatus(FileAttachment file){
		TmpAttachment tmpAttachment = new TmpAttachment();
		tmpAttachment.setStatus(file.getFileName() == null || file.getFileName().trim() == "" ? - 1 : 1);
		tmpAttachment.setIncidentCaseId(file.getIncidentCaseId());
		tmpAttachment.setId(file.getId());
		tmpAttachment.setUpdatedDate(new Date());
		tmpAttachmentRepository.save(tmpAttachment);
	}
	
	
}
