package com.stixcloud.product.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import com.google.common.base.Preconditions;
import com.stixcloud.product.api.json.IccListPage;
import com.stixcloud.product.api.json.LinkJsonObject;

@Component
public class PaginatedResultsRetrievedDiscoverabilityListener implements ApplicationListener<PaginatedResultsRetrievedEvent> {

	private List<LinkJsonObject> linkJsonObjList;
	
  public PaginatedResultsRetrievedDiscoverabilityListener(){
    super();
    
  }
    
  @Override
  public void onApplicationEvent(PaginatedResultsRetrievedEvent event) {
    Preconditions.checkNotNull(event);
    CustomPaginationPage customResponse = addLinkHeaderOnPagedResourceRetrieval(event.getUriBuilder(), event.getPage(), event.getPagable(), event.getClazz(), event.getTotalPages());

    IccListPage e=(IccListPage) event.getPage();
    if (e!=null) {
    	customResponse.setHttpStatus(e.getHttpStatus());
    	customResponse.setUrl(e.getUrl());
    }

    event.setPage(customResponse);
  }

  protected CustomPaginationPage addLinkHeaderOnPagedResourceRetrieval(final UriComponentsBuilder uriBuilder, Page page, Pageable pageable,
       final Class clazz, final int totalPages) {
    linkJsonObjList = new ArrayList<LinkJsonObject>();
    CustomPaginationPage response = new CustomPaginationPage(page.getContent() , pageable, page.getTotalElements());
    linkJsonObjList.add(createLinkHeader(uriBuilder.build().encode().toUriString(), "self"));
    
    if (page.hasNext()) {
      final String uriForNextPage = constructPageUri(uriBuilder, page.nextPageable().getPageNumber(), totalPages);
      linkJsonObjList.add(createLinkHeader(uriForNextPage, "next"));
    }
    if (page.hasPrevious()) {
      final String uriForPrevPage = constructPageUri(uriBuilder, page.previousPageable().getPageNumber(), totalPages);
      linkJsonObjList.add(createLinkHeader(uriForPrevPage, "prev"));
    }
    
    if (!page.isFirst()) {
      final String uriForFirstPage = constructPageUri(uriBuilder, 0, totalPages);
      linkJsonObjList.add(createLinkHeader(uriForFirstPage, "first"));
    }
    
    if (!page.isLast() && !page.isFirst()) {
      final String uriForLastPage = constructPageUri(uriBuilder, totalPages, totalPages);
      linkJsonObjList.add(createLinkHeader(uriForLastPage, "last"));
    }
    response.setLinks(linkJsonObjList);
    return response;
  }

  String constructPageUri(final UriComponentsBuilder uriBuilder, final int page,
      final int size) {
    return uriBuilder.replaceQueryParam("page", page).replaceQueryParam("size", size).build()
        .encode().toUriString();
  }

  public static LinkJsonObject createLinkHeader(final String uri, final String rel) {
    LinkJsonObject linkObj = new LinkJsonObject();
    linkObj.setRel(rel);
    linkObj.setHref(uri);
    return linkObj;
  }
  
}
