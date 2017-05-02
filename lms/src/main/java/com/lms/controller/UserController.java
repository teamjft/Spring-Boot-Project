package com.lms.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lms.models.User;
import com.lms.services.issue.IssueService;
import com.lms.services.library.LibraryService;
import com.lms.services.membership.MembershipService;
import com.lms.services.user.UserService;
import com.lms.utils.beans.LibraryDataCount;
import com.lms.utils.beans.PasswordConfirmationBean;
import com.lms.utils.beans.ResponseMessage;
import com.lms.utils.beans.UserBean;
import com.lms.utils.helper.LibraryUtil;
import com.lms.utils.helper.NotificationUtil;
import com.lms.utils.helper.StringUtil;
import com.lms.utils.modelutil.MembershipStatus;

/**
 * Created by bhushan on 11/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private UserService userService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private IssueService issueService;

    @RequestMapping("/home")
    public ModelAndView home() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberShip memberShip = membershipService.findByUuid(secUser.getMemberShipId());
        ModelAndView modelAndView = new ModelAndView("user/home");
        if (LibraryUtil.isLibraryAdminOrLibrarian(memberShip) || memberShip.getUser().isSuperAdmin()) {
            Library library = memberShip.getLibrary();
            LibraryDataCount libraryDataCount = libraryService.basicCountInfoOfLibrary(library.getUuid());
            modelAndView.addObject("dataCount", libraryDataCount);
            return modelAndView;
        } else if(memberShip.getMembershipStatus().equals(MembershipStatus.SUSPENDED)) {

            return null;
        } else {
            return null;
        }
    }

    @RequestMapping("/create")
    public ModelAndView create() {
        UserBean userBean = new UserBean();
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView modelAndView = new ModelAndView("user/home");
        LibraryDataCount libraryDataCount = libraryService.basicCountInfoOfLibrary(library.getUuid());
        modelAndView.addObject("dataCount", libraryDataCount);
        return modelAndView;
    }

    @PreAuthorize("permitAll")
    @RequestMapping("/forgetPassword/{email:.+}")
    public  @ResponseBody
    ResponseMessage forgetPassword(@PathVariable String email, HttpServletRequest httpServletRequest ) {
        String token = UUID.randomUUID().toString();
        User user = userService.findByEmail(email);
        if(user == null) {
            return new ResponseMessage("Not a valid email register with system", ResponseMessage.MessageType.ERROR, "email");
        }
        String forgetPasswordUrl = String.format("%s/%s", NotificationUtil.getBaseUrl(httpServletRequest), "user/resetpassword");
        if (!StringUtil.isValidEmail(email)) {
            return new ResponseMessage("invalid email", ResponseMessage.MessageType.ERROR, "email");
        }
        userService.requestForgetPassword(forgetPasswordUrl, user);
        return new ResponseMessage("Please verify your forget password link.", ResponseMessage.MessageType.SUCCESS, "email");
    }

    @PreAuthorize("permitAll")
    @RequestMapping("/resetpassword/{uuid}")
    public ModelAndView forgetPassword(@PathVariable(value = "uuid") String uuid) {
        User user = userService.findByToken(uuid);
        if(user == null) {
            return new ModelAndView("redirect:/book/index");
        }
        PasswordConfirmationBean passwordConfirmationBean =  PasswordConfirmationBean.builder()
                .token(user.getToken()).build();
        ModelAndView modelAndView = new  ModelAndView("user/resetpassword");
        modelAndView.addObject("passwordConfirmation", passwordConfirmationBean);
        return  modelAndView;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public ModelAndView forgetPassword(@Valid @ModelAttribute("passwordConfirmation")PasswordConfirmationBean passwordConfirmationBean, BindingResult result) {
        ModelAndView modelAndView = new  ModelAndView("user/resetpassword");
        modelAndView.addObject("passwordConfirmation", passwordConfirmationBean);
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            modelAndView.addObject("errors", errors);
            return modelAndView;
        } else if(!passwordConfirmationBean.getConfirmPassword().equals(passwordConfirmationBean.getPassword())) {
            modelAndView.addObject("error","new password and confirm password must be same");
            return modelAndView;
        }
        User user = userService.findByToken(passwordConfirmationBean.getToken());
        if(user == null) {
            return new ModelAndView("redirect:/book/index");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(passwordConfirmationBean.getPassword()) );
        user.setToken(null);
        userService.updateUser(user);
        modelAndView.addObject("success", "Successfully updated password, please login with new password, Thank you!");
        return modelAndView;
    }

}
