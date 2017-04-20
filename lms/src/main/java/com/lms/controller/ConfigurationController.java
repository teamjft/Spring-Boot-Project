package com.lms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lms.config.security.SecUser;
import com.lms.models.Library;
import com.lms.services.mailservice.NotificationService;
import com.lms.utils.beans.LibraryBean;
import com.lms.utils.enums.NotificationServiceType;
import com.lms.utils.enums.NotificationType;
import com.lms.utils.enums.SaveImageServiceType;
import com.lms.utils.factory.NotificationFactory;
import com.lms.utils.notification.EmailNotification;
import com.lms.utils.notification.Notification;

/**
 * Created by bhushan on 20/4/17.
 */
@Controller
@RequestMapping("configuration")
public class ConfigurationController {
    @Autowired
    private NotificationFactory notificationFactory;


    @RequestMapping("/email")
    public ModelAndView imageConfiguration() {
        SecUser secUser =
                (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView("configuration/emailconfiguration");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView updateStrogeType(Map<String, Object> map) {
        Notification<String, String, String> notification = EmailNotification.builder()
                .notificationType(NotificationType.FORGETPASSWORD)
                .to("bhushan@jellyfishtechnologies.com")
                .content("Testing")
                .subject("Testing email").build();

        notificationFactory.getSendContentService(NotificationServiceType.EMAIL).sendNotification(notification);
        System.out.println("==================================="+map);
        return new ModelAndView("redirect:/configuration/email");
    }
}
