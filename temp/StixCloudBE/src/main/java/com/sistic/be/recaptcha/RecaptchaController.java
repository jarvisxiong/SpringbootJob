package com.sistic.be.recaptcha;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{tenant}")
public class RecaptchaController {
	
	@Autowired
	RecaptchaService recaptchaService;
	
	@RequestMapping(value = "/recaptcha/verify", method = RequestMethod.POST)
    @ResponseBody
    public Boolean verifyRecaptcha(HttpServletResponse response, HttpSession session,
    		@RequestParam(value="g_recaptcha_response") String g_recaptcha_response) {

		Boolean verification = recaptchaService.verifyCaptcha(g_recaptcha_response);
		
		return verification;
    }

}
