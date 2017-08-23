/**
 * 
 */
package com.sistic.be.locale;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.FileReaderService;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

/**
 * @author jianhong
 *
 */

@RestController
@RequestMapping(value = "/{tenant}")
public class PropertyController {
	
	@Autowired
	private FileReaderService fileReaderService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/lang/{code}", method = RequestMethod.GET)
	@ResponseBody
	public Resource languageProperty(HttpServletResponse response,
			@PathVariable(value = "code", required = true) String code)
			throws IOException {

		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		Resource resource = null;

		if ("zh_TW".equalsIgnoreCase(code)) {
			resource = fileReaderService.getResource("assets/tenant/" + templateCode +"/zh_TW.json");
		} else {
			resource = fileReaderService.getResource("assets/tenant/" + templateCode + "/en_US.json");
		}

		return resource;
	}
	
	@RequestMapping(value = "/prop", method = RequestMethod.GET)
	@ResponseBody
	public Resource bookingProperty(HttpServletResponse response)
			throws IOException {

		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		Resource resource = null;
		resource = fileReaderService.getResource("assets/tenant/"+ templateCode + "/booking-prop.json");

		return resource;
	}

}
