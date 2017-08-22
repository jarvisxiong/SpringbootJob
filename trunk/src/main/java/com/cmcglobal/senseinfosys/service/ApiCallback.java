package com.cmcglobal.senseinfosys.service;

import org.quartz.Job;

/**
 * 
 * @author NHLong File ApiCallback.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/26 ApiCallback Create New
 */

public interface ApiCallback {
	void onSuccess(Class <? extends Job> jobClass, String keyData, Object data);
	void onError(String msg);
}
