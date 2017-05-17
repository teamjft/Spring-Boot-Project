package com.lms.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lms.models.MemberShip;
import com.lms.services.membership.MembershipService;
import com.lms.utils.modelutil.MembershipStatus;

/**
 * Created by bhushan on 17/5/17.
 */
@Component("userMembershipSecurityServiceImpl")
public class UserMembershipSecurityServiceImpl implements UserMembershipSecurityService {
    @Autowired
    private MembershipService membershipService;

    @Override
    public boolean canUserBuyPlan() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecUser) {
            SecUser secUser = (SecUser)principal;
            MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
            return !memberShip.getMembershipStatus().equals(MembershipStatus.ACTIVE);
        }
        return false;
    }
}
