package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.ISSUE_BOOK__BASE_PATH;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bhushan on 19/5/17.
 */
@Controller
@RequestMapping(value = ISSUE_BOOK__BASE_PATH)
public class IssueBookController {

    @RequestMapping(value = CREATE_PATH)
    public ModelAndView create() {
        return null;
    }

}
