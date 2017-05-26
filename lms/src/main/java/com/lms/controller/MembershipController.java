package com.lms.controller;

import static com.lms.utils.constants.UrlMappingConstant.INDEX_PATH;
import static com.lms.utils.constants.UrlMappingConstant.MEMBERSHIP_BASE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.MEMBERSHIP_VIEW_PATH;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.MEMBERSHIP_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_MEMBERSHIP_INDEX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.MemberShip;
import com.lms.services.membership.MembershipService;
import com.lms.utils.helper.PaginationHelper;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 25/4/17.
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_LIBRARY_ADMIN', 'ROLE_LIBRARIAN')")
@RequestMapping(value = MEMBERSHIP_BASE_PATH)
public class MembershipController {
    @Autowired
    private MembershipService membershipService;
    @RequestMapping(INDEX_PATH)
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        Page<MemberShip> page = membershipService.getPageRequest(memberShip.getLibrary(), currentPageNumber);
        return PaginationHelper.getModelAndView(MEMBERSHIP_INDEX_VIEW, page, "memberships");
    }

    @RequestMapping(MEMBERSHIP_VIEW_PATH)
    public ModelAndView view(@PathVariable String id) {
        MemberShip memberShip = membershipService.findByUuid(id);
        if (memberShip == null) {
            return  new ModelAndView(REDIRECT_MEMBERSHIP_INDEX);
        }
        ModelAndView modelAndView = new ModelAndView(MEMBERSHIP_VIEW);
        modelAndView.addObject("membership", memberShip);
        return modelAndView;
    }


}
