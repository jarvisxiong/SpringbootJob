package com.sistic.be.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.exception.constant.ErrorConsts;

@Controller
@RequestMapping(value = "/{tenant}")
public class ErrorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/error/view")
    public String bookingcontent(Model model, HttpServletResponse response, Locale locale,
                                ErrorConstruct errorConstruct) {

    	String templateCode = TenantContextHolder.getTenant().getTemplateCode();

    	//TODO: fix this?
        if (errorConstruct == null) {
            errorConstruct = new ErrorConstruct.Builder()
                    .statusMessage(ErrorConsts.DefaultMessage)
                    .timestamp("")
                    .build();
        }

        logger.error("/error/view was accessed: " + errorConstruct.toString());
        model.addAttribute("error", errorConstruct.getStatusMessage());

        return templateCode+"/view/error";
    }

}
