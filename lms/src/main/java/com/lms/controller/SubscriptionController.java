package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.lms.utils.constants.UrlMappingConstant.INDEX_PATH;
import static com.lms.utils.constants.UrlMappingConstant.SUBSCRIPTION_BASE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.VIEW_PATH;
import static com.lms.utils.constants.ViewConstant.BOOK_INDEX_VIEW;
import static com.lms.utils.constants.ViewConstant.ISSUE_BOOK_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_HOME_VIEW;
import static com.lms.utils.constants.ViewConstant.SUBSCRIPTION_INDEX;
import static com.lms.utils.constants.ViewConstant.SUBSCRIPTION_VIEW;

import com.lms.config.security.SecUser;
import com.lms.models.IssueBook;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipSubscription;
import com.lms.services.membership.MembershipService;
import com.lms.services.membershipsubscription.MembershipSubscriptionService;
import com.lms.utils.helper.PaginationHelper;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 25/5/17.
 */
@Controller
@RequestMapping(value = SUBSCRIPTION_BASE_PATH)
public class SubscriptionController {
    @Autowired
    private MembershipSubscriptionService membershipSubscriptionService;
    @Autowired
    private MembershipService membershipService;


    @RequestMapping(INDEX_PATH)
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        Page<MembershipSubscription> page = membershipSubscriptionService.getPageRequest(secUser.getLibraryId(), currentPageNumber);
        return PaginationHelper.getModelAndView(SUBSCRIPTION_INDEX, page, "subscriptions");
    }

    @RequestMapping(value = VIEW_PATH)
    public ModelAndView view(@PathVariable String uuid) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        MembershipSubscription membershipSubscription = membershipSubscriptionService.findByUuid(secUser.getLibraryId(), uuid);
        if (membershipSubscription == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        return new ModelAndView(SUBSCRIPTION_VIEW, "membershipSubscription", membershipSubscription);
    }
}
