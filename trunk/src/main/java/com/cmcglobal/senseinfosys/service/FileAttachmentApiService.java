package com.cmcglobal.senseinfosys.service;

import java.util.List;

import com.cmcglobal.senseinfosys.model.TmpAttachment;

/**
 * 
 * @author NHLong File FileAttachmentApiService.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/27 FileAttachmentApiService Create New
 */

public interface FileAttachmentApiService {
	public void execTmpAttachment(List<TmpAttachment> lst) throws Exception;
}
