package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.models.Book;
import com.lms.models.Category;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.services.membership.MembershipService;

/**
 * Created by bhushan on 25/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "membership")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        if (currentPageNumber == null) {
            currentPageNumber =1;
        }
        Page<MemberShip> page = membershipService.getPageRequest(currentPageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        ModelAndView modelAndView = new ModelAndView("membership/index");
        modelAndView.addObject("memberships",  page.getContent());
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable String id) {
        MemberShip memberShip = membershipService.findByUuid(id);
        if (memberShip == null) {
            return  new ModelAndView("redirect:/membership/index");
        }
        ModelAndView modelAndView = new ModelAndView("membership/view");
        modelAndView.addObject("membership", memberShip);
        return modelAndView;
    }


}
