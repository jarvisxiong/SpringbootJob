package com.sistic.be.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sistic.be.app.util.WebUrlUtil;

@ControllerAdvice
public class ServerControllerAdvice {
	
	@ModelAttribute("geoSealURL")
	public String getGeoSealURL()
	{
		return "https://seal.geotrust.com/getgeotrustsslseal?host_name=" + WebUrlUtil.getHost() + "&amp;size=S&amp;lang=en";
	}
}
