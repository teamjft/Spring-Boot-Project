package com.lms.controller;

import java.util.Map;

import static com.lms.utils.constants.UrlMappingConstant.ERROR_PATH;
import static com.lms.utils.constants.UrlMappingConstant.HOME_PATH;
import static com.lms.utils.constants.ViewConstant.ERROR_VIEW;
import static com.lms.utils.constants.ViewConstant.INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_USER_HOME;
import static com.lms.utils.constants.ViewConstant._403_VIEW;
import static com.lms.utils.constants.ViewConstant._404_VIEW;
import static com.lms.utils.constants.ViewConstant._500_VIEW;

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

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(HOME_PATH)
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return REDIRECT_USER_HOME;
        }
        return INDEX_VIEW;
    }

    @RequestMapping(value = ERROR_PATH)
    public String error(HttpServletRequest request, HttpServletResponse response) {
        log.error("Something going wrong status: {} ", response.getStatus());
        getErrorAttributes(request, true);
        if(response.getStatus() == 404 ) {
            return _404_VIEW;
        } else if (response.getStatus() == 500) {
            return _500_VIEW;
        } else if (response.getStatus() == 403) {
            return _403_VIEW;
        }
        return ERROR_VIEW;
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
