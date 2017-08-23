package com.sistic.be.membership.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.AuthUtilService;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.membership.api.model.MembershipInfoDetailsList;
import com.sistic.be.membership.api.model.MembershipInfoList;
import com.sistic.be.membership.api.model.UpdateMembershipProfileRequest;
import com.sistic.be.patron.api.auth.service.AuthorisationService;

@Service
public class ApiMembershipService {
	
    private Logger logger = LogManager.getLogger(ApiMembershipService.class);
	
    @Autowired
    private MessageSource messageSource;
    
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private AuthUtilService authUtilService; 
	
	@Autowired
    AuthorisationService authService;
	
	protected String serviceUrl;

	@Autowired
    public ApiMembershipService(@Value("${service.membership.url}") String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
               serviceUrl : "http://" + serviceUrl;
        
        System.out.println("ApiMembershipService: " + serviceUrl);
    }
	
	public MembershipInfoList retrievePatronMembership(Long organizationId, Integer pageNo, Integer pageSize) {
    	//deegix-test
    	//organizationId = 1913L;
    	System.out.println("retrievePatronMembership organizationId: " + organizationId);
    	MembershipInfoList apiResponse = null;
    	if (organizationId == null) {
    		// response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfo/{pageNo}/{pageSize}", HttpMethod.GET, entity, MembershipInfoList.class, pageNo, pageSize);
    	    authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfo/{pageNo}/{pageSize}", HttpMethod.GET, MembershipInfoList.class, null, pageNo, pageSize);
    	} else {
    		// response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfo/{pageNo}/{pageSize}?organizationId={organizationId}", HttpMethod.GET, entity, MembershipInfoList.class, pageNo, pageSize, organizationId);
    		authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfo/{pageNo}/{pageSize}?organizationId={organizationId}", HttpMethod.GET, MembershipInfoList.class, null, pageNo, pageSize, organizationId);
    	}
    	System.out.println("retrievePatronMembership apiResponse: " + apiResponse);
    	
//		HttpEntity<String> sresponse = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfo/1913", HttpMethod.GET, entity, String.class);
//		System.out.println("sresponse = " + sresponse);
//		PatronMembership apiResponse = null;
    	/*
		//deegix-test
		PatronMembership apiResponse = new PatronMembership();
		List<MembershipInfo> membershipInfoList = new ArrayList();
		MembershipInfo membershipInfo = new MembershipInfo();
		membershipInfo.setOrganizationID(21L);
		membershipInfo.setOrganizationAlias("Singapore Symphony Orchestra");
		
		List<MembershipProfile> membershipProfileList = new ArrayList();
		MembershipProfile membershipProfile = new MembershipProfile();
		membershipProfile.setKey("GENRE_THEATRE_MUSICAL");
		membershipProfile.setValue("Musical");
		membershipProfileList.add(membershipProfile);
		
		MembershipPatron membershipPatron = new MembershipPatron();
		membershipPatron.setMembershipPatronId(12345L);
		membershipPatron.setStartDate(OffsetDateTime.now());
		membershipPatron.setEndDate(OffsetDateTime.now());
		membershipPatron.setCardStatus("Printed");
		membershipPatron.setStatus(true);
		membershipPatron.setMembershipTier("Gold Card");

		List<MembershipSubMemberInfo> membershipSubMemberInfoList = new ArrayList();
		MembershipSubMemberInfo membershipSubMemberInfo = new MembershipSubMemberInfo();
		membershipSubMemberInfo.setKey("CHILD_FIRST_NAME");
		membershipSubMemberInfo.setValue("John");
		membershipSubMemberInfoList.add(membershipSubMemberInfo);

		membershipSubMemberInfo = new MembershipSubMemberInfo();
		membershipSubMemberInfo.setKey("CHILD_LAST_NAME");
		membershipSubMemberInfo.setValue("Doe");

		membershipPatron.setSubMemberInfoList(membershipSubMemberInfoList);
		
		membershipInfo.setMembershipPatron(membershipPatron);
		

		membershipSubMemberInfoList.add(membershipSubMemberInfo);

		//membershipInfo.setMembershipProfileList(membershipProfileList);
		
		membershipInfoList.add(membershipInfo);
		apiResponse.setMembershipInfoList(membershipInfoList);
		*/
		
		return apiResponse;
	}
	
	public MembershipInfoDetailsList retrieveProfileConfig(Long organizationId) {
    	System.out.println("organizationId="+organizationId);
    	System.out.println(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfodetails/" + organizationId);
		// HttpEntity<MembershipInfoDetailsList> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfodetails/" + organizationId, HttpMethod.GET, entity, MembershipInfoDetailsList.class);
		MembershipInfoDetailsList apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/membershipinfodetails/" + organizationId, HttpMethod.GET, MembershipInfoDetailsList.class, null);
		System.out.println("apiResponse="+apiResponse);
		
		//deegix-test
    	/*
    	MembershipProfileConfigList apiResponse = new MembershipProfileConfigList();

    	MembershipProfileConfig membershipProfileConfig = new MembershipProfileConfig();
    	membershipProfileConfig.setKey("GENRE");
    	List<MembershipConfigProfile> profiles = new ArrayList();
    	MembershipConfigProfile profile;

    	profile = new MembershipConfigProfile();
    	profile.setAlias("Dance");
    	List<MembershipConfigOption> membershipConfigOption = new ArrayList();
    	
    	MembershipConfigOption option = new MembershipConfigOption();
    	option.setSubKey("GENRE_DANCE_CONTEMPORARY");
    	option.setSubAlias("Contemporary Dance");
    	option.setType("CHECKBOX");
    	option.setOrdering(1);
    	option.setMandatory(true);
    	option.setValidationReq(true);
    	membershipConfigOption.add(option);
    	
    	option = new MembershipConfigOption();
    	option.setSubKey("GENRE_DANCE_TRADITIONAL");
    	option.setSubAlias("Traditional/Classical Dance");
    	option.setType("CHECKBOX");
    	option.setOrdering(2);
    	option.setMandatory(true);
    	option.setValidationReq(true);
    	membershipConfigOption.add(option);
    	
    	profile.setMembershipConfigOption(membershipConfigOption);
    	profiles.add(profile);
    	// END PROFILE1 
    	
    	profile = new MembershipConfigProfile();
    	profile.setAlias("Music");
    	membershipConfigOption = new ArrayList();
    	
    	option = new MembershipConfigOption();
    	option.setSubKey("GENRE_MUSIC_CHORAL_VOCAL");
    	option.setSubAlias("Choral/Vocal");
    	option.setType("CHECKBOX");
    	option.setOrdering(3);
    	option.setMandatory(true);
    	option.setValidationReq(true);
    	membershipConfigOption.add(option);
    	
    	option = new MembershipConfigOption();
    	option.setSubKey("GENRE_MUSIC_CLASSICAL_OPERA");
    	option.setSubAlias("Classical Music/Opera");
    	option.setType("CHECKBOX");
    	option.setOrdering(4);
    	option.setMandatory(true);
    	option.setValidationReq(true);
    	membershipConfigOption.add(option);
    	
    	
    	profile.setMembershipConfigOption(membershipConfigOption);
    	profiles.add(profile);
    	// END PROFILE2
    	
    	
    	membershipProfileConfig.setProfiles(profiles);
    	
    	List<MembershipProfileConfig> membershipProfileConfigList = new ArrayList();
    	membershipProfileConfigList.add(membershipProfileConfig);
    	// end config1
    	
    	membershipProfileConfig = new MembershipProfileConfig();
    	membershipProfileConfig.setKey("OCCUPATION");
    	profiles = new ArrayList();
    	profile = new MembershipConfigProfile();
    	profile.setAlias("Working industry");
    	membershipConfigOption = new ArrayList();
    	option = new MembershipConfigOption();
    	option.setSubKey("OCCUPATION_ARTS_ENTERTAINMENT");
    	option.setSubAlias("Arts & Entertainment");
    	option.setType("RADIO");
    	option.setOrdering(1);
    	option.setMandatory(true);
    	option.setValidationReq(true);
    	membershipConfigOption.add(option);
    	option = new MembershipConfigOption();
    	option.setSubKey("OCCUPATION_CIVIL_PUBLIC");
    	option.setSubAlias("Civil/Public Service");
    	option.setType("RADIO");
    	option.setOrdering(2);
    	option.setMandatory(true);
    	option.setValidationReq(true);
    	membershipConfigOption.add(option);
    	
    	profile.setMembershipConfigOption(membershipConfigOption);
    	profiles.add(profile);
    	membershipProfileConfig.setProfiles(profiles);
    	membershipProfileConfigList.add(membershipProfileConfig);
    	
    	apiResponse.setMembershipProfileConfig(membershipProfileConfigList);
    	*/
    	
		return apiResponse;
	}


	public JsonResponse updateMembershipDetail(UpdateMembershipProfileRequest updateMembershipProfileRequest) {
    	System.out.println("membershipProfileConfigList = " + updateMembershipProfileRequest);
    	// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/profile", HttpMethod.POST, entity, JsonResponse.class);
    	return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/membership/member/profile", HttpMethod.POST, JsonResponse.class, updateMembershipProfileRequest);
	}

}
