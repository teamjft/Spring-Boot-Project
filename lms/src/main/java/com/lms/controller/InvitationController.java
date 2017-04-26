package com.lms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Invitation;
import com.lms.models.MemberShip;
import com.lms.models.User;
import com.lms.services.invitation.InvitationService;
import com.lms.services.membership.MembershipService;
import com.lms.utils.beans.InvitationBean;
import com.lms.utils.helper.NotificationUtil;

/**
 * Created by bhushan on 25/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("invite")
public class InvitationController {
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private MembershipService membershipService;

    @RequestMapping(value = "/inviteUser", method = RequestMethod.GET)
    public ModelAndView inviteUser() {
        InvitationBean invitationBean = new InvitationBean();
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        User user = memberShip.getUser();
        ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "createinvite");
        if(memberShip.isAdmin()) {
            modelAndView.addObject("admin", true);
        }
        if (memberShip.isLibrarian()) {
            modelAndView.addObject("librarian", true);
        }
        if (user.isSuperAdmin()) {
            modelAndView.addObject("librarian", true);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/inviteUser", method = RequestMethod.POST)
    public ModelAndView invitation(@ModelAttribute InvitationBean invitationBean, BindingResult result, HttpServletRequest httpServletRequest) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "createinvite");
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        User user = memberShip.getUser();
        ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "createinvite");
        if(!memberShip.isAdmin()) {
            invitationBean.setAdmin(false);
        }
        if (!memberShip.isLibrarian()) {
            invitationBean.setLibrarian(false);
        }
        if (user.isSuperAdmin()) {
            invitationBean.setSuperAdmin(false);
        }
        Invitation invitation = Invitation.builder()
                .admin(invitationBean.isAdmin())
                .superAdmin(invitationBean.isSuperAdmin())
                .librarian(invitationBean.isLibrarian())
                .email(invitationBean.getEmail())
                .invitedBy(user)
                .inviteLibrary(memberShip.getLibrary())
                .build();
        invitation = invitationService.create(invitation);
        String invitationUrl = String.format("%s/%s/%s", NotificationUtil.getBaseUrl(httpServletRequest), "inviteUser/resetpassword", invitation.getUuid());

        return null ;
    }

    private ModelAndView getCreateOrEditModel(InvitationBean invitationBean, String action) {
        ModelAndView modelAndView = new ModelAndView(String.format("invitation/%s", action));
        modelAndView.addObject("category", invitationBean );
        return modelAndView;
    }
}
