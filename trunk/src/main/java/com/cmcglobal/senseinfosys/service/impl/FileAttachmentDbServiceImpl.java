package com.cmcglobal.senseinfosys.service.impl;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.FileAttachment;
import com.cmcglobal.senseinfosys.repository.FileAttachmentRepository;
import com.cmcglobal.senseinfosys.service.FileAttachmentDbService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NHLong File FileAttachmentDbServiceImpl.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/28 FileAttachmentDbServiceImpl Create New
 */

@Service("fileAttachmentService")
@Transactional
public class FileAttachmentDbServiceImpl implements FileAttachmentDbService {

	private static final Logger log = Logger.getLogger(FileAttachmentDbServiceImpl.class);

	@Autowired
	private FileAttachmentRepository fileAttachmentRepository;

	@Override
	public void saveFileInfo(FileAttachment fileAttachment) {
		try {
			fileAttachmentRepository.save(fileAttachment);
			log.info("Insert file attachment success " + Utils.logDate());
		} catch (Exception e) {
			log.error(Utils.logDate()+"_FileAttachmentDbServiceImpl", e);
			e.printStackTrace();
			try {
				throw new JobExecutionException("Save file attachment information failed");
			} catch (JobExecutionException e1) {
				log.error(Utils.logDate()+"_FileAttachmentDbServiceImpl", e1);
				e1.printStackTrace();
			}
		}
	}
}
