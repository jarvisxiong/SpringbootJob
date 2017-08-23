package com.stixcloud.product.api.json;

import java.io.Serializable;

import java.time.OffsetDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.domain.InternetContentCodeListing;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "summaryImagePath",
    "internetContentCode",
    "title",
    "genre",
    "startDate",
    "endDate",
    "venue",
    "language",
    "synopsis",
    "bookingUrl"
})
public class InternetContentCodeListingJson implements Serializable {

	private static final long serialVersionUID = -3482553070968240410L;

	private String summaryImagePath,
		internetContentCode,
		title,
		language,
		synopsis,
		venue, 
		bookingUrl;
	OffsetDateTime startDate;
	OffsetDateTime endDate;

	List<String> genre;

	public InternetContentCodeListingJson(InternetContentCodeListing obj) {
		this.summaryImagePath=obj.getSummaryImagePath();
		this.internetContentCode=obj.getInternetContentCode();
		this.title=obj.getTitle();
		this.language=obj.getLanguage();
		this.synopsis=obj.getSynopsis();
		this.venue=obj.getVenue();
		this.bookingUrl=obj.getBookingUrl();
		this.startDate=obj.getStartDate();
		this.endDate=obj.getEndDate();
	}
	
	public String getSummaryImagePath() {
		return summaryImagePath;
	}

	public void setSummaryImagePath(String summaryImagePath) {
		this.summaryImagePath = summaryImagePath;
	}

	public String getInternetContentCode() {
		return internetContentCode;
	}

	public void setInternetContentCode(String internetContentCode) {
		this.internetContentCode = internetContentCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getBookingUrl() {
		return bookingUrl;
	}

	public void setBookingUrl(String bookingUrl) {
		this.bookingUrl = bookingUrl;
	}

	public OffsetDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}

	public OffsetDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = genre;
	}

	
}
