package com.stixcloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ICC_LIST_VIEW")
public class InternetContentCodeListing implements Serializable{

	private static final long serialVersionUID = -4383602978296683786L;
	
	@Id
	Long icfileid;
	String summaryImagePath,
	internetContentCode,
	title,
	language,
	synopsis;
	OffsetDateTime iclStartDate, iclEndDate;
	
	@Column(name="GENRE", nullable=true)
	String genreStr;
	
	Long productid;
	String venue, 
		bookingUrl;
	OffsetDateTime plStartDate;
	@Transient
	OffsetDateTime startDate;
	@Transient
	OffsetDateTime endDate;

	@Transient
	List<String> genre;
	
	public InternetContentCodeListing() {
//		this.genre=new ArrayList<String>();
	}

	public InternetContentCodeListing(String summaryImagePath, String internetContentCode, String title,
			String language, String synopsis, String genreStr, String venue, String bookingUrl, OffsetDateTime startDate,
			OffsetDateTime endDate) {
		super();
		this.summaryImagePath = summaryImagePath;
		this.internetContentCode = internetContentCode;
		this.title = title;
		this.language = language;
		this.synopsis = synopsis;
		this.genreStr=genreStr;
		this.venue = venue;
		this.bookingUrl = bookingUrl;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Column(name="ICFILEID", nullable=false)
	public Long getIcfileid() {
		return icfileid;
	}

	public void setIcfileid(Long icfileid) {
		this.icfileid = icfileid;
	}

	@Column(name="SUMMARY_IMAGE_PATH", nullable=true)
	public String getSummaryImagePath() {
		return summaryImagePath;
	}

	public void setSummaryImagePath(String summaryImagePath) {
		this.summaryImagePath = summaryImagePath;
	}

	@Column(name="INTERNET_CONTENT_CODE", nullable=false)
	public String getInternetContentCode() {
		return internetContentCode;
	}

	public void setInternetContentCode(String internetContentCode) {
		this.internetContentCode = internetContentCode;
	}

	@Column(name="TITLE", nullable=false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="LANGUAGE", nullable=false)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name="SYNOPSIS", nullable=false)
	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Column(name="ICL_START_DATE", nullable=false)
	public OffsetDateTime getIclStartDate() {
		return iclStartDate;
	}

	public void setIclStartDate(OffsetDateTime iclStartDate) {
		this.iclStartDate = iclStartDate;
	}

	@Column(name="ICL_END_DATE", nullable=false)
	public OffsetDateTime getIclEndDate() {
		return iclEndDate;
	}

	public void setIclEndDate(OffsetDateTime iclEndDate) {
		this.iclEndDate = iclEndDate;
	}


	@Column(name="VENUE", nullable=true)
	public String getVenue() {
		return venue;
	}


	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Column(name="BOOKINGURL", nullable=true)
	public String getBookingUrl() {
		return bookingUrl;
	}


	public void setBookingUrl(String bookingUrl) {
		this.bookingUrl = bookingUrl;
	}


	@Column(name="productid", nullable=true)
	public Long getProductid() {
		return productid;
	}


	public void setProductid(Long productid) {
		this.productid = productid;
	}


	@Column(name="pl_start_date", nullable=true)
	public OffsetDateTime getPlStartDate() {
		return plStartDate;
	}


	public void setPlStartDate(OffsetDateTime plStartDate) {
		this.plStartDate = plStartDate;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}


	public void setStartdate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}


	public OffsetDateTime getEndDate() {
		return endDate;
	}


	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	@Column(name="GENRE", nullable=true)
	public String getGenreStr() {
		return genreStr;
	}

	public void setGenreStr(String genreStr) {
		this.genreStr = genreStr;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	/*public void addGenre(String genre) {
		if (this.genre!=null)
			this.genre.add(genre);
	}*/

}
