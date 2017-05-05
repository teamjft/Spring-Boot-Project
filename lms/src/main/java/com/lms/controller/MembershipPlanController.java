package com.lms.controller;

import java.util.List;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.services.library.LibraryService;
import com.lms.services.membership.MembershipService;
import com.lms.services.membershipplan.MembershipPlanService;
import com.lms.services.user.UserService;
import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.customannotation.annotaion.XxsFilter;
import com.lms.utils.enums.PeriodType;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 3/5/17.
 */
@Controller
@RequestMapping("/plan")
@Slf4j
public class MembershipPlanController {
    @Value("${lcm.customer.support.email}")
    private String supportEmail;
    @Autowired
    private MembershipPlanService membershipPlanService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private UserService userService;
    @Autowired
    private LibraryService libraryService;

    @RequestMapping("/index")
    public ModelAndView index(Model model) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("plan/index");
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        List<MembershipPlan> plans = membershipPlanService.findByLibrary(memberShip.getLibrary());
        modelAndView.addObject("plans", plans);
        return modelAndView;
    }

    @RequestMapping("/create")
    public ModelAndView create(Model model) {
        ModelAndView modelAndView = getCreateModel(new MembershipPlanBean());
        return modelAndView;
    }

    @XxsFilter
    @RequestMapping("/save")
        public ModelAndView save(@Valid @ModelAttribute("plan") MembershipPlanBean membershipPlanBean, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = getCreateModel(membershipPlanBean);
            return modelAndView;
        }
        MembershipPlan membershipPlan = membershipPlanService.findByName(membershipPlanBean.getName());
        if (membershipPlan != null) {
            ModelAndView modelAndView = getCreateModel(membershipPlanBean);
            modelAndView.addObject("error", "Plane name already exist, please enter new plane name");
            return modelAndView;
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        membershipPlan = new MembershipPlan();
        membershipPlan.setMaxNumberOfBookAllow(membershipPlanBean.getMaxNumberOfBookAllow());
        membershipPlan.setName(membershipPlanBean.getName());
        membershipPlan.setLibrary(library);
        membershipPlan.setDescription(membershipPlanBean.getDescription());
        membershipPlan.setPrice(membershipPlanBean.getPrice());
        membershipPlan.setUnit(membershipPlanBean.getUnit());
        membershipPlan.setPeriodType(membershipPlanBean.getPeriodType());
        try {
            membershipPlanService.create(membershipPlan);
        } catch (Exception e) {
            log.error("Exception occur during save membership plan user name: {} , membership id {}, error:", secUser.getUsername(), secUser.getMemberShipId(), e);
            ModelAndView modelAndView = getCreateModel(membershipPlanBean);
            modelAndView.addObject("error", String.format("Something went wrong during save plan, please try again or contact to our support: %s", supportEmail));
            return modelAndView;
        }
         ModelAndView modelAndView = getCreateModel(new MembershipPlanBean());
        modelAndView.addObject("success","Successfully Added.");
        return modelAndView;
    }

    @RequestMapping("/delete/{uuid}")
    public ModelAndView delete(@PathVariable(required = true) String uuid) {
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(uuid);
        SecUser secUser = SecurityUtil.getCurrentUser();
        if (membershipPlan == null || !membershipPlan.getLibrary().getUuid().equals(secUser.getLibraryId())) {
            return new ModelAndView("forward:/");
        }
        membershipPlan.setEnabled(false);
        membershipPlanService.update(membershipPlan);
        return  new ModelAndView("forward:/plan/index");
    }

  @RequestMapping("/view/{uuid}")
    public ModelAndView view(@PathVariable(required = true) String uuid) {
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(uuid);
        SecUser secUser = SecurityUtil.getCurrentUser();
        if (membershipPlan == null || !membershipPlan.getLibrary().getUuid().equals(secUser.getLibraryId())) {
            return new ModelAndView("forward:/");
        }
       ModelAndView modelAndView = new ModelAndView("plan/view");
       modelAndView.addObject("plan", membershipPlan);
        return  modelAndView;
    }


    private ModelAndView getCreateModel(MembershipPlanBean membershipPlanBean) {
        ModelAndView modelAndView = new ModelAndView("plan/create");
        modelAndView.addObject("plan", membershipPlanBean);
        modelAndView.addObject("periodTypes", PeriodType.values());
        return modelAndView;
    }
}
