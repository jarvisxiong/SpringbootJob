package com.stixcloud.product.api.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import com.stixcloud.domain.InternetContentCodeListing;

public class IccListPage extends PageImpl {

	String httpStatus;
	String url;
	
	public IccListPage(List content) {
		super(content);
		
		setHttpStatus(HttpStatus.OK.toString());
		setUrl("");
	}
	public IccListPage(List content, HttpServletRequest req) {
		this(content);
		setUrl(req.getRequestURL().toString());
	}

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
