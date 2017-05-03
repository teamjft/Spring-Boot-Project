package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.services.membership.MembershipService;
import com.lms.services.membershipplan.MembershipPlanService;
import com.lms.services.user.UserService;
import com.lms.utils.beans.LibraryDataCount;

/**
 * Created by bhushan on 3/5/17.
 */
@Controller
public class MembershipPlanController {
    @Autowired
    private MembershipPlanService membershipPlanService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public ModelAndView home() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        membershipPlanService.findByUuid(memberShip.getLibrary().getUuid());
        ModelAndView modelAndView = new ModelAndView("user/home");
        Library library = memberShip.getLibrary();
        return modelAndView;
    }

}
