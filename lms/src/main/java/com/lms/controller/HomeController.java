package com.lms.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bhushan on 13/4/17.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Welcome................");
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println("aaa gya");
            return new ModelAndView("redirect:/user/home");
        } else {
            System.out.println("ni aaya");
        }
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }



}
