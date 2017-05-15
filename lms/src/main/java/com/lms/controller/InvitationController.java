package com.lms.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Invitation;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.User;
import com.lms.services.invitation.InvitationService;
import com.lms.services.membership.MembershipService;
import com.lms.services.user.UserService;
import com.lms.utils.beans.InvitationBean;
import com.lms.utils.beans.UserBean;
import com.lms.utils.customannotation.annotaion.XxsFilter;
import com.lms.utils.helper.NotificationUtil;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 25/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("invite")
@Slf4j
public class InvitationController {
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private UserService userService;
    @Value("${lcm.customer.support.email}")
    private String supportEmail;

    @RequestMapping(value = "/inviteUser", method = RequestMethod.GET)
    public ModelAndView inviteUser() {
        InvitationBean invitationBean = new InvitationBean();
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "create");
        modelAndView = addRoles(memberShip, modelAndView);
        return modelAndView;
    }


    @RequestMapping(value = "/inviteUser", method = RequestMethod.POST)
    public ModelAndView invitation(@Valid @ModelAttribute("invitation") InvitationBean invitationBean, BindingResult result, HttpServletRequest httpServletRequest) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        User invitedBy = memberShip.getUser();
        if(result.hasErrors()) {
            ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "create");
            modelAndView = addRoles(memberShip, modelAndView);
            return modelAndView;
        }

        Library library = memberShip.getLibrary();

        User inviteUser  = userService.findByEmail(invitationBean.getEmail());
        if(inviteUser != null) {
            MemberShip inviteUserMemberShip = membershipService.findByLibraryAndUser(library, inviteUser);
            if (inviteUserMemberShip != null) {
                ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "createinvite");
                modelAndView.addObject("error", String.format("User is already registered with email %s", invitationBean.getEmail()));
                return modelAndView;
            }
        }

        if(!memberShip.isAdmin()) {
            invitationBean.setAdmin(false);
        }
        if (!memberShip.isLibrarian()) {
            invitationBean.setLibrarian(false);
        }
        if (invitedBy.isSuperAdmin()) {
            invitationBean.setSuperAdmin(false);
        }
        Invitation invitation = Invitation.builder()
                .admin(invitationBean.isAdmin())
                .superAdmin(invitationBean.isSuperAdmin())
                .librarian(invitationBean.isLibrarian())
                .email(invitationBean.getEmail())
                .invitedBy(invitedBy)
                .inviteLibrary(memberShip.getLibrary())
                .token(UUID.randomUUID().toString())
                .build();
        String invitationUrl = String.format("%s/%s/%s", NotificationUtil.getBaseUrl(httpServletRequest), "invite/accept", invitation.getToken());
        invitationService.inviteUser(invitation, invitationUrl);
        ModelAndView modelAndView = getCreateOrEditModel(new InvitationBean(), "create");
        modelAndView.addObject("success", "Successfully send invitations");
        modelAndView = addRoles(memberShip, modelAndView);
        return  modelAndView;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/accept/{token}")
    public ModelAndView accept(@PathVariable String token) {
        Invitation invitation = invitationService.findByTokenAndNotDeleted(token);
        if(invitation == null) {
            return new ModelAndView("redirect:/");
        }
        UserBean userBean = new UserBean();
        userBean.setToken(invitation.getToken());
        userBean.setEmail(invitation.getEmail());
        ModelAndView modelAndView =  getAcceptModel(userBean);
        return modelAndView;
    }

    @PreAuthorize("permitAll")
    @XxsFilter
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public ModelAndView accept(@Valid @ModelAttribute("user") UserBean userBean, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView modelAndView =  getAcceptModel(userBean);
            return modelAndView;
        }
        User userInstanceByEmail = userService.findByEmail(userBean.getEmail());
        if(userInstanceByEmail != null) {
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("info", "Please account is registered with system, please login and accept the invitation");
            return modelAndView;
        }
        User userInstanceByUserName = userService.loadByUsername(userBean.getUsername());
        if(userInstanceByUserName != null) {
            ModelAndView modelAndView =  getAcceptModel(userBean);
            modelAndView.addObject("error", "username is already register please choose another user name.");
            return modelAndView;
        }
        Invitation invitation = invitationService.findByTokenAndNotDeleted(userBean.getToken());
        if(invitation == null) {
            return new ModelAndView("redirect:/");
        }
        try {
            invitationService.createUser(invitation, userBean);
        } catch (Exception e) {
            log.error("Error occur during save the invitation, invitation token: {}", invitation.getToken());
            ModelAndView modelAndView =  getAcceptModel(userBean);
            modelAndView.addObject("error", String.format("Something went wrong please try again or contact to application provider, email: {}", supportEmail));
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("invitation/accept");
        modelAndView.addObject("success", "successfully created account, please login with account. Thanks");
        return  modelAndView;
    }

    private ModelAndView getCreateOrEditModel(InvitationBean invitationBean, String action) {
        ModelAndView modelAndView = new ModelAndView(String.format("invitation/%s", action));
        modelAndView.addObject("invitation", invitationBean );
        return modelAndView;
    }

    private ModelAndView addRoles(MemberShip memberShip, ModelAndView modelAndView) {
        if(memberShip.isAdmin()) {
            modelAndView.addObject("admin", true);
        }
        if (memberShip.isLibrarian()) {
            modelAndView.addObject("librarian", true);
        }
        if (memberShip.getUser().isSuperAdmin()) {
            modelAndView.addObject("librarian", true);
        }
        return modelAndView;
    }

    private ModelAndView getAcceptModel(UserBean userBean) {
        ModelAndView modelAndView =  new ModelAndView("invitation/accept");
        modelAndView.addObject("user", userBean);
        return modelAndView;
    }
}
