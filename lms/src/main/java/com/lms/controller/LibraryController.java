package com.lms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by bhushan on 19/4/17.
 */

@Controller
@RequestMapping(value = "/library")
@PreAuthorize("isAuthenticated()")
public class LibraryController {

}
