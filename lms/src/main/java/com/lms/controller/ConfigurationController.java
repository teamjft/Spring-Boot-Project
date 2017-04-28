package com.lms.controller;

import static com.lms.models.QEmailTemplate.emailTemplate;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.config.security.SecUser;
import com.lms.models.Category;
import com.lms.models.EmailTemplate;
import com.lms.models.Library;
import com.lms.services.emailtemplate.EmailTemplateService;
import com.lms.services.library.LibraryService;
import com.lms.utils.beans.BookBean;
import com.lms.utils.beans.EmailTemplateBean;
import com.lms.utils.beans.LibraryBean;
import com.lms.utils.converter.NotificationTypeConverter;
import com.lms.utils.converter.SaveImageServiceTypeConverter;
import com.lms.utils.enums.NotificationServiceType;
import com.lms.utils.enums.NotificationType;
import com.lms.utils.enums.SaveImageServiceType;
import com.lms.utils.factory.NotificationFactory;
import com.lms.utils.helper.NotificationUtil;
import com.lms.utils.helper.SecurityUtil;
import com.lms.utils.notification.EmailNotification;
import com.lms.utils.notification.Notification;

/**
 * Created by bhushan on 20/4/17.
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("configuration")
public class ConfigurationController {
    @Autowired
    private NotificationFactory notificationFactory;
    @Value("${localstroge.path}")
    private String LOCALSTROGEPATH;
    @Value("${resource_url}")
    private String CLOUDINARYSTROGEPATH;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private EmailTemplateService emailTemplateService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SaveImageServiceType.class, new SaveImageServiceTypeConverter());
        dataBinder.registerCustomEditor(NotificationType.class, new NotificationTypeConverter());
    }

    @RequestMapping("/imageconfiguration")
    public ModelAndView imageConfiguration() {
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView modelAndView = new ModelAndView("configuration/imageconfiguration");
        LibraryBean libraryBean = new LibraryBean();
        libraryBean.setSaveImageServiceType(library.getSaveImageServiceType());
        libraryBean.setUuid(library.getUuid());
        modelAndView.addObject("local", LOCALSTROGEPATH);
        modelAndView.addObject("cloudinary", CLOUDINARYSTROGEPATH);
        modelAndView.addObject("currentStorageType", library.getSaveImageServiceType());
        modelAndView.addObject("storageType", SaveImageServiceType.values());
        return modelAndView;
    }

    @RequestMapping("/updatestrogetype")
    public ModelAndView updateStrogeType(@RequestParam(value = "saveImageServiceType") SaveImageServiceType saveImageServiceType, Object command) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        library.setSaveImageServiceType(saveImageServiceType);
        libraryService.update(library);
        return new ModelAndView("redirect:/configuration/imageconfiguration");
    }


  /*  @RequestMapping("/email")
    public ModelAndView emailConfiguration() {
        SecUser secUser = SecurityUtil.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView("configuration/emailconfiguration");
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveEmailConfiguration(Map<String, Object> map) {
        Notification<String, String, String> notification = EmailNotification.builder()
                .notificationType(NotificationType.FORGETPASSWORD)
                .to("bhushan@jellyfishtechnologies.com")
                .content("Testing")
                .subject("Testing email").build();

        notificationFactory.getSendContentService(NotificationServiceType.EMAIL).sendNotification(notification);
        return new ModelAndView("redirect:/configuration/email");
    }*/

    @RequestMapping("/template")
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        if (currentPageNumber == null) {
            currentPageNumber =1;
        }
        Page<EmailTemplate> page = emailTemplateService.getPageRequest(currentPageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        ModelAndView modelAndView = new ModelAndView("configuration/templatelist");
        modelAndView.addObject("templates",  page.getContent());
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping("/editTemplate/{id}")
    public ModelAndView editTemplate(@PathVariable(value="id", required = true) String id) {
        EmailTemplate emailTemplate = emailTemplateService.findByUuid(id);
        if(emailTemplate != null) {
            EmailTemplateBean emailTemplateBean = buildEmailTemplateBean(emailTemplate);
            ModelAndView modelAndView = getEmailTemplateModelAndView("edittemplate", emailTemplateBean);
            emailTemplateBean.setAvailableField(NotificationUtil.getParametersListByNotificationType(emailTemplate.getNotificationType()));
            return modelAndView;
        }
        return new ModelAndView("redirect:/configuration/template");
    }

    @RequestMapping("/viewTemplate/{id}")
    public ModelAndView viewTemplate(@PathVariable(value="id", required = true) String id) {
        EmailTemplate emailTemplate = emailTemplateService.findByUuid(id);
        if(emailTemplate != null) {
            EmailTemplateBean emailTemplateBean = buildEmailTemplateBean(emailTemplate);
            ModelAndView modelAndView = getEmailTemplateModelAndView("templateview", emailTemplateBean);
            return modelAndView;
        }
        return new ModelAndView("redirect:/configuration/templateview");
    }

    @RequestMapping("/updateTemplate")
    public ModelAndView updateTemplate(@Valid @ModelAttribute("emailTemplate")EmailTemplateBean emailTemplateBean, BindingResult result, final RedirectAttributes redirectAttributes) {
        EmailTemplate emailTemplate = emailTemplateService.findByUuid(emailTemplateBean.getUuid());
        if (emailTemplate == null) {
            return new ModelAndView("redirect:/");
        }
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            emailTemplateBean.setAvailableField(NotificationUtil.getParametersListByNotificationType(emailTemplate.getNotificationType()));
            ModelAndView modelAndView = getEmailTemplateModelAndView("edittemplate", emailTemplateBean);
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
        emailTemplate.setContent(emailTemplateBean.getContent());
        emailTemplate.setSubject(emailTemplateBean.getSubject());
        emailTemplateService.update(emailTemplate);
        emailTemplateBean = buildEmailTemplateBean(emailTemplate);
        ModelAndView modelAndView = getEmailTemplateModelAndView("templateview", emailTemplateBean);
        redirectAttributes.addFlashAttribute("success", "Successfully edited.");
        return modelAndView;
    }

    private EmailTemplateBean buildEmailTemplateBean(@NotNull EmailTemplate emailTemplate) {
       return EmailTemplateBean.builder()
                .uuid(emailTemplate.getUuid())
                .subject(emailTemplate.getSubject())
                .content(emailTemplate.getContent())
                .notificationType(emailTemplate.getNotificationType())
                .uuid(emailTemplate.getUuid())
                .build();
    }

    private ModelAndView getEmailTemplateModelAndView(String view, EmailTemplateBean emailTemplateBean) {
        ModelAndView modelAndView = new ModelAndView(String.format("configuration/%s", view));
        modelAndView.addObject("emailTemplate", emailTemplateBean);
        return modelAndView;
    }

}
