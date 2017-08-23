/**
 * 
 */
package com.sistic.be.membership.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.DebugUtil;
import com.sistic.be.app.util.SessionUtilService;
import com.sistic.be.app.util.SisticUtil;
import com.sistic.be.app.util.ValidationUtilService;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.service.ApiCartService;
import com.sistic.be.exception.InvalidPatronLoginException;
import com.sistic.be.membership.api.model.MembershipInfoDetailsList;
import com.sistic.be.membership.api.model.MembershipInfoList;
import com.sistic.be.membership.api.model.MembershipProfile;
import com.sistic.be.membership.api.model.UpdateMembershipProfileRequest;
import com.sistic.be.membership.api.service.ApiMembershipService;
import com.sistic.be.membership.form.MembershipProfileForm;
import com.sistic.be.patron.api.service.ApiPatronService;
import com.sistic.be.patron.form.PatronFormMapper;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.session.OnlineUserSession;
import com.sistic.be.recaptcha.RecaptchaService;

/**
 * @author deegix This controller will return the template views related to
 *         membership management
 *
 */

@Validated
@Controller
public class MembershipController {

	private Logger logger = LogManager.getLogger(MembershipController.class);
	
	@Autowired
	ObjectMapper mapper;
	@Autowired
	Environment env;
	
	@Autowired
	SessionUtilService sessionUtil;
	@Autowired
	ValidationUtilService validationService;

	@Autowired
	ApiPatronService patronApi;
	@Autowired
	ApiCartService cartApi;
	@Autowired
	ApiMembershipService membershipApi;
	@Autowired
	RecaptchaService recaptchaService;

	@Autowired
	PatronFormMapper patronFormMapper;
	
	@Value("${membership.listing.page.size}")
	private Integer pageSize;

	@RequestMapping(value = "/patron/membership", method = RequestMethod.GET)
	public String showPatronMembership(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@ModelAttribute("patronMembership") MembershipInfoList patronMembership,
			@ModelAttribute("success") String successMessage,
			Locale locale, Model model, HttpSession session) {

		response.setHeader("Access-Control-Allow-Origin", "*");

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}		
		
		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		if (pageNo == null) {
			pageNo = 1;
		}

		String errorMessage = "";
		if (patronId != null) {
			try {
				// retrieve all membership
				patronMembership = membershipApi.retrievePatronMembership(null, pageNo, pageSize);
			} catch (HttpStatusCodeException e) {
				JsonResponse errorResponse;
				try {
					errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
					errorMessage = errorResponse.getStatusMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		model.addAttribute("patronMembership", patronMembership);
		model.addAttribute("hasMembership", patronLogin.isHasMembership());
		model.addAttribute("success", successMessage);
		model.addAttribute("error", errorMessage);

		return "sistic/view/sidebar/memberships";
	}
	
	@RequestMapping(value = "/patron/membership/{organizationId}", method = RequestMethod.GET)
	public String showMembershipDetail(HttpServletResponse response, HttpServletRequest request,
			@PathVariable(value = "organizationId", required = false) Long organizationId,
			@ModelAttribute("success") String successMessage,
			Locale locale, Model model, HttpSession session) {

		response.setHeader("Access-Control-Allow-Origin", "*");

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}		

		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		MembershipInfoDetailsList membershipInfoDetailsList = null;
		
		String errorMessage = "";
		if (patronId != null) {
			try {
				membershipInfoDetailsList = membershipApi.retrieveProfileConfig(organizationId);
				
			} catch (HttpStatusCodeException e) {
				JsonResponse errorResponse;
				try {
					errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
					errorMessage = errorResponse.getStatusMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		model.addAttribute("membershipInfoDetailsList", membershipInfoDetailsList);
		model.addAttribute("organizationId", organizationId);
		model.addAttribute("hasMembership", patronLogin.isHasMembership());
		model.addAttribute("success", successMessage);
		model.addAttribute("error", errorMessage);

		return "sistic/view/sidebar/membership-details";
	}
	
	@RequestMapping(value = "/patron/membership/{organizationId}", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView manageMembershipDetail(HttpServletResponse response, HttpServletRequest request,
			@PathVariable(value = "organizationId", required = false) Long organizationId,
			@ModelAttribute("membershipProfileForm") MembershipProfileForm membershipProfileForm,
			RedirectAttributes ra,
			Model model, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		
		String errorMessage = "";

		response.setHeader("Access-Control-Allow-Origin", "*");

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}
		
//		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();
		
		try {
			logger.info("update membershipDetail request");
			logger.info("membershipProfileForm = " + DebugUtil.writeWithPrettyPrinter(membershipProfileForm));
			
			UpdateMembershipProfileRequest updateMembershipProfileRequest = new UpdateMembershipProfileRequest();
			if (membershipProfileForm != null) {
				updateMembershipProfileRequest.setOrganizationId(organizationId);
				
				if (membershipProfileForm.getMembershipProfileFields() != null) {
					String[] memberProfiles = membershipProfileForm.getMembershipProfileFields().split("%%");
					
					for(String mem: memberProfiles) {
						String[] keyval = mem.split("\\|\\|");						
						MembershipProfile profile = new MembershipProfile();
						profile.setKey(keyval[0]);
						profile.setValue(keyval[1]);
						updateMembershipProfileRequest.addProfiles(profile);
					}
				}
			}
			
			JsonResponse updateMembershipDetailResponse = membershipApi.updateMembershipDetail(updateMembershipProfileRequest);
			logger.info("update response: " + DebugUtil.writeWithPrettyPrinter(updateMembershipDetailResponse));
			//System.out.println(DebugUtil.writeWithPrettyPrinter(updateMembershipDetailResponse));
			ra.addFlashAttribute("success", "Membership details updated successfully.");	// redirect attributes
		} catch (HttpClientErrorException e) {
			try {
				logger.error("Update Membership details failed", e);
				JsonResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
				errorMessage = errorResponse.getStatusMessage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		ra.addFlashAttribute("error", errorMessage);	// redirect attributes

		return new ModelAndView("redirect:/patron/membership/" + organizationId);
	}
}
