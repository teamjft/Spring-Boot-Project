package com.lms.controller;

import static com.lms.utils.constants.ViewConstant.REDIRECT_HOME_VIEW;
import static com.lms.utils.constants.ViewConstant.USER_PLAN_VIEW;
import static com.lms.utils.constants.ViewConstant.RESET_PASSWORD_VIEW;
import static com.lms.utils.constants.UrlMappingConstant.RESET_PASSWORD_PATH;
import static com.lms.utils.constants.UrlMappingConstant.USER_HOME_PATH;
import static com.lms.utils.constants.UrlMappingConstant.USER_PLAN;
import static com.lms.utils.constants.UrlMappingConstant.CREATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.FORGET_PASSWORD_PATH;
import static com.lms.utils.constants.UrlMappingConstant.RESET_PASSWORD_BY_UUID_PATH;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Library;
import com.lms.models.MemberShip;
import com.lms.models.MembershipPlan;
import com.lms.models.User;
import com.lms.services.library.LibraryService;
import com.lms.services.membership.MembershipService;
import com.lms.services.membershipplan.MembershipPlanService;
import com.lms.services.user.UserService;
import com.lms.utils.beans.LibraryDataCount;
import com.lms.utils.beans.PasswordConfirmationBean;
import com.lms.utils.beans.ResponseMessage;
import com.lms.utils.constants.UrlMappingConstant;
import com.lms.utils.constants.ViewConstant;
import com.lms.utils.helper.LibraryUtil;
import com.lms.utils.helper.NotificationUtil;
import com.lms.utils.helper.StringUtil;
import com.lms.utils.modelutil.MembershipStatus;

/**
 * Created by bhushan on 11/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(UrlMappingConstant.USER_PATH)
public class UserController {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private UserService userService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private MembershipPlanService membershipPlanService;
    @Autowired
    private MessageSource messageSource;



    @RequestMapping(USER_HOME_PATH)
    public ModelAndView home() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        ModelAndView modelAndView = new ModelAndView(ViewConstant.USER_HOME_VIEW);
        if (LibraryUtil.isLibraryAdminOrLibrarian(memberShip) || memberShip.getUser().isSuperAdmin()) {
            Library library = memberShip.getLibrary();
            LibraryDataCount libraryDataCount = libraryService.basicCountInfoOfLibrary(library.getUuid());
            modelAndView.addObject("dataCount", libraryDataCount);
            return modelAndView;
        } else if(memberShip.getMembershipStatus().equals(MembershipStatus.SUSPENDED)) {
            return getUserPlansView((memberShip.getLibrary()));
        } else {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
    }


    @RequestMapping(USER_PLAN)
    public ModelAndView userMembershipPlans() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        return getUserPlansView(memberShip.getLibrary());
    }

    @RequestMapping(CREATE_PATH)
    public ModelAndView create() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView modelAndView = new ModelAndView(ViewConstant.USER_HOME_VIEW);
        LibraryDataCount libraryDataCount = libraryService.basicCountInfoOfLibrary(library.getUuid());
        modelAndView.addObject("dataCount", libraryDataCount);
        return modelAndView;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(FORGET_PASSWORD_PATH)
    public  @ResponseBody
    ResponseMessage forgetPassword(@PathVariable String email, HttpServletRequest httpServletRequest ) {
        String token = UUID.randomUUID().toString();
        User user = userService.findByEmail(email);
        Locale locale = LocaleContextHolder.getLocale();
        if(user == null) {

            return new ResponseMessage(messageSource.getMessage("invalid.email.exist", null, locale), ResponseMessage.MessageType.ERROR, "email");
        }
        String forgetPasswordUrl = String.format("%s/%s", NotificationUtil.getBaseUrl(httpServletRequest), RESET_PASSWORD_VIEW);
        if (!StringUtil.isValidEmail(email)) {
            return new ResponseMessage(messageSource.getMessage("invalid.email", null, locale), ResponseMessage.MessageType.ERROR, "email");
        }
        userService.requestForgetPassword(forgetPasswordUrl, user);
        return new ResponseMessage(messageSource.getMessage("verify.forget.password", null, locale), ResponseMessage.MessageType.SUCCESS, "email");
    }

    @PreAuthorize("permitAll")
    @RequestMapping(RESET_PASSWORD_BY_UUID_PATH)
    public ModelAndView forgetPassword(@PathVariable(value = "uuid") String uuid) {
        User user = userService.findByToken(uuid);
        if(user == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        PasswordConfirmationBean passwordConfirmationBean =  PasswordConfirmationBean.builder()
                .token(user.getToken()).build();
        ModelAndView modelAndView = new  ModelAndView(RESET_PASSWORD_VIEW);
        modelAndView.addObject("passwordConfirmation", passwordConfirmationBean);
        return  modelAndView;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = RESET_PASSWORD_PATH, method = RequestMethod.POST)
    public ModelAndView forgetPassword(@Valid @ModelAttribute("passwordConfirmation")PasswordConfirmationBean passwordConfirmationBean, BindingResult result) {
        ModelAndView modelAndView = new  ModelAndView(RESET_PASSWORD_VIEW);
        modelAndView.addObject("passwordConfirmation", passwordConfirmationBean);
        Locale locale = LocaleContextHolder.getLocale();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            modelAndView.addObject("errors", errors);
            return modelAndView;
        } else if(!passwordConfirmationBean.getConfirmPassword().equals(passwordConfirmationBean.getPassword())) {
            modelAndView.addObject("error",messageSource.getMessage("password.mismatch", null, locale));
            return modelAndView;
        }
        User user = userService.findByToken(passwordConfirmationBean.getToken());
        if(user == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(passwordConfirmationBean.getPassword()) );
        user.setToken(null);
        userService.updateUser(user);
        modelAndView.addObject("success", messageSource.getMessage("successfully.updated.password", null, locale));
        return modelAndView;
    }

    private ModelAndView getUserPlansView(Library library) {
        ModelAndView modelAndView = new ModelAndView(USER_PLAN_VIEW);
        List<MembershipPlan> plans = membershipPlanService.findByLibrary(library);
        modelAndView.addObject("plans", plans);
        return modelAndView;
    }

}
