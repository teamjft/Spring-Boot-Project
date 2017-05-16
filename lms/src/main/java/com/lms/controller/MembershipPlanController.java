package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.DELETE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.MEMBERSHIP_PLAN_BASE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.MEMBERSHIP_PLAN_PURCHASE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.SAVE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.VIEW_PATH;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_PLAN_CREATE_VIEW;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_PLAN_INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_PLAN_PURCHASE_VIEW;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_PLAN_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_HOME_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_MEMBERSHIP_PLAN_INDEX;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.lms.utils.beans.OrderCartBean;
import com.lms.utils.customannotation.annotaion.XxsFilter;
import com.lms.utils.enums.PeriodType;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 3/5/17.
 */
@Controller
@RequestMapping(MEMBERSHIP_PLAN_BASE_PATH)
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
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(MEMBERSHIP_PLAN_BASE_PATH)
    public ModelAndView index(Model model) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView(MEMBERSHIP_PLAN_INDEX_VIEW);
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        List<MembershipPlan> plans = membershipPlanService.findByLibrary(memberShip.getLibrary());
        modelAndView.addObject("plans", plans);
        return modelAndView;
    }

    @RequestMapping(CREATE_PATH)
    public ModelAndView create(Model model) {
        ModelAndView modelAndView = getCreateModel(new MembershipPlanBean());
        return modelAndView;
    }

    @XxsFilter
    @RequestMapping(SAVE_PATH)
        public ModelAndView save(@Valid @ModelAttribute("plan") MembershipPlanBean membershipPlanBean, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = getCreateModel(membershipPlanBean);
            return modelAndView;
        }
        MembershipPlan membershipPlan = membershipPlanService.findByName(membershipPlanBean.getName());
        Locale locale = LocaleContextHolder.getLocale();
        if (membershipPlan != null) {
            ModelAndView modelAndView = getCreateModel(membershipPlanBean);
            modelAndView.addObject("error", messageSource.getMessage("plan.name.already.exist", null, locale));
            return modelAndView;
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        membershipPlan = MembershipPlanBean.buildBeanToEntity(membershipPlanBean);
        membershipPlan.setLibrary(library);
        try {
            membershipPlanService.create(membershipPlan);
        } catch (Exception e) {
            log.error("Exception occur during save membership plan user name: {} , membership id {}, error:", secUser.getUsername(), secUser.getMemberShipId(), e);
            ModelAndView modelAndView = getCreateModel(membershipPlanBean);
            modelAndView.addObject("error", messageSource.getMessage("some.thing.going.wrong", new Object[]{ supportEmail }, locale));
        }
         ModelAndView modelAndView = getCreateModel(new MembershipPlanBean());
        modelAndView.addObject("success", messageSource.getMessage("plan.successfully.saved", null, locale));
        return modelAndView;
    }

    @RequestMapping(DELETE_PATH)
    public ModelAndView delete(@PathVariable(required = true) String uuid) {
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(uuid);
        SecUser secUser = SecurityUtil.getCurrentUser();
        if (membershipPlan == null || !membershipPlan.getLibrary().getUuid().equals(secUser.getLibraryId())) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        membershipPlan.setEnabled(false);
        membershipPlanService.update(membershipPlan);
        return  new ModelAndView(REDIRECT_MEMBERSHIP_PLAN_INDEX);
    }

  @RequestMapping(VIEW_PATH)
    public ModelAndView view(@PathVariable String uuid) {
        MembershipPlan membershipPlan = membershipPlanService.findByUuid(uuid);
        SecUser secUser = SecurityUtil.getCurrentUser();
        if (membershipPlan == null || !membershipPlan.getLibrary().getUuid().equals(secUser.getLibraryId())) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
       ModelAndView modelAndView = new ModelAndView(MEMBERSHIP_PLAN_VIEW);
       modelAndView.addObject("plan", membershipPlan);
        return  modelAndView;
    }


    private ModelAndView getCreateModel(MembershipPlanBean membershipPlanBean) {
        ModelAndView modelAndView = new ModelAndView(MEMBERSHIP_PLAN_CREATE_VIEW);
        modelAndView.addObject("plan", membershipPlanBean);
        modelAndView.addObject("periodTypes", PeriodType.values());
        return modelAndView;
    }

    @RequestMapping(value = MEMBERSHIP_PLAN_PURCHASE_PATH)
    public ModelAndView paymentCreate(@PathVariable String membershipPlanId) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        MembershipPlan membershipPlan =  membershipPlanService.findByUuidAndLibrary(membershipPlanId, memberShip.getLibrary());
        if (membershipPlan == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        ModelAndView modelAndView = new ModelAndView(MEMBERSHIP_PLAN_PURCHASE_VIEW);
        modelAndView.addObject("plan", membershipPlan);
        OrderCartBean orderCartBean = OrderCartBean.builder().planUuid(membershipPlanId).quantity(1).build();
        modelAndView.addObject("cart", orderCartBean);
        return modelAndView;
    }
}
