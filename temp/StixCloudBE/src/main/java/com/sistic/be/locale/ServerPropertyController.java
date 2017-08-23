package com.sistic.be.locale;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.FileReaderService;
import com.sistic.be.configuration.profile.ProfileConsts;
import com.sistic.be.configuration.profile.runtime.RunProfile;

@RestController
public class ServerPropertyController {

	@Autowired
	private RunProfile runProfile;
	@Autowired
	private FileReaderService fileReaderService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(value = "/prop/server", method = RequestMethod.GET)
	@ResponseBody
	public Resource serverProperty(HttpServletResponse response)
			throws IOException {
		
		String profile = runProfile.getProfile();
		if (profile == null || profile.isEmpty()) {
			profile = ProfileConsts.UAT;
		}
		Resource resource = null;
		resource = fileReaderService.getResource("assets/server-prop-" + profile + ".json");

		return resource;
	}
}
