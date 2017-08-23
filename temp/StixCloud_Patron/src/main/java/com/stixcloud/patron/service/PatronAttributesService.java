package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronAttributes;
import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.repo.IPatronAttributesRepository;

@Service
public class PatronAttributesService{
	private static final Logger logger = LogManager.getLogger(PatronAttributesService.class);

	@Autowired
	private IPatronAttributesRepository repo;
	
	public boolean isChanged(PatronRequest request, PatronRequest original) {
		
		if(request.getPatronAttributes() == null && original.getPatronAttributes() != null || request.getPatronAttributes() != null && original.getPatronAttributes() == null){
			return true;
		}else if(request.getPatronAttributes() != null && original.getPatronAttributes() != null){
			
			if(request.getPatronAttributes().size() != original.getPatronAttributes().size()){
				return true;
			}
			
			for(Entry<String, String> requestEntry: request.getPatronAttributes().entrySet()) {
				  String key = requestEntry.getKey();
				  String value = requestEntry.getValue();
				  
				  String originalValue = original.getPatronAttributes().get(key);
				  
				  if(null == originalValue){
					  return true;
				  }
				  
				  if(!value.equals(originalValue)){
					  return true;
				  }
			}
		}
		
		return false;
	  }
	
	public void update(Map<String, String> patronAttributes, Long patronProfileId, Long userInfoId) throws SisticApiException {
		PatronAttributes patronAttributeDb = repo.findOne(patronProfileId);
		//case create
		if(null == patronAttributeDb){
			patronAttributeDb = new PatronAttributes(patronProfileId, patronAttributes.toString(), OffsetDateTime.now(), userInfoId, OffsetDateTime.now(), userInfoId);
		}else{
			patronAttributeDb.setAttributes(patronAttributes.toString());
			patronAttributeDb.setUpdatedBy(userInfoId);
			patronAttributeDb.setUpdateddate(OffsetDateTime.now());
		}
		repo.save(patronAttributeDb);
	}

	public void save(PatronAttributes patronAttributes) throws SisticApiException{
		repo.save(patronAttributes);		
	}
	
	public List<PatronAttributes> getPatronAttributes(Long patronProfileId) throws SisticApiException{
		return repo.findByPatronProfileId(patronProfileId);
	}

}
