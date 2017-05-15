package com.lms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by bhushan on 13/4/17.
 */
@Controller
@Slf4j
@PreAuthorize("isAuthenticated()")
public class HomeController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/")
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/user/home";
        }
        return "index";
    }

    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request, HttpServletResponse response) {
        log.error("Something going wrong status: {} ", response.getStatus());
        getErrorAttributes(request, true);
        if(response.getStatus() == 404 ) {
            return "404";
        } else if (response.getStatus() == 500) {
            return "500";
        } else if (response.getStatus() == 403) {
            return "403";
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private void getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> map = errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            log.error("Something going wrong key: {} , value: {}", entry.getKey(),  entry.getValue());
        }
    }
}
