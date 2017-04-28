package com.lms.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.lms.utils.beans.PasswordConfirmationBean;
import com.lms.utils.beans.UserBean;
import com.lms.utils.helper.NotificationUtil;
import com.lms.utils.helper.SecurityUtil;

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
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/inviteUser", method = RequestMethod.GET)
    public ModelAndView inviteUser() {
        InvitationBean invitationBean = new InvitationBean();
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        User user = memberShip.getUser();
        ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "create");
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
    public ModelAndView invitation(@Valid @ModelAttribute("invitation") InvitationBean invitationBean, BindingResult result, HttpServletRequest httpServletRequest) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView = getCreateOrEditModel(invitationBean, "create");
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
        SecUser secUser = SecurityUtil.getCurrentUser();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
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
        User invitedBy = memberShip.getUser();
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
        String invitationUrl = String.format("%s/%s/%s", NotificationUtil.getBaseUrl(httpServletRequest), "inviteUser/accept", invitation.getToken());
        invitationService.inviteUser(invitation, invitationUrl);
        ModelAndView modelAndView = getCreateOrEditModel(new InvitationBean(), "create");
        modelAndView.addObject("success", "Successfully send invitations");
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
        userBean.setPasswordConfirmationBean(new PasswordConfirmationBean());
        userBean.getPasswordConfirmationBean().setToken(invitation.getToken());
        ModelAndView modelAndView =  new ModelAndView("invitation/accept");
        modelAndView.addObject("user", userBean);
        return modelAndView;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    public ModelAndView accept(@Valid @ModelAttribute("user") UserBean userBean, BindingResult result) {
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            ModelAndView modelAndView =  new ModelAndView("invitation/accept");
            modelAndView.addObject("user", userBean);
            return modelAndView;
        }
       /* UserBean userBean = new UserBean();
        userBean.setPasswordConfirmationBean(new PasswordConfirmationBean());
        userBean.getPasswordConfirmationBean().setToken(invitation.getToken());
        ModelAndView modelAndView =  new ModelAndView("invitation/accept");
        modelAndView.addObject("user", userBean);*/
        return  new ModelAndView("invitation/accept");
    }

    private ModelAndView getCreateOrEditModel(InvitationBean invitationBean, String action) {
        ModelAndView modelAndView = new ModelAndView(String.format("invitation/%s", action));
        modelAndView.addObject("invitation", invitationBean );
        return modelAndView;
    }
}
