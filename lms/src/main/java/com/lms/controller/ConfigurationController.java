package com.lms.controller;

import java.util.List;

import static com.lms.utils.constants.UrlMappingConstant.CONFIGURATION_EDIT_TEMPLATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CONFIGURATION_IMAGECONFIGURATION_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CONFIGURATION_TEMPLATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CONFIGURATION_UPDATE_STORAGE_TYPE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CONFIGURATION_UPDATE_TEMPLATE_PATH;
import static com.lms.utils.constants.UrlMappingConstant.CONFIGURATION_VIEW_TEMPLATE_PATH;
import static com.lms.utils.constants.ViewConstant.CONFIGURATION_EDIT_TEMPLATE_VIEW;
import static com.lms.utils.constants.ViewConstant.CONFIGURATION_REDIRECT_IMAGECONFIGURATION;
import static com.lms.utils.constants.ViewConstant.CONFIGURATION_REDIRECT_TEMPLATE;
import static com.lms.utils.constants.ViewConstant.CONFIGURATION_TEMPLATE_LIST_VIEW;
import static com.lms.utils.constants.ViewConstant.CONFIGURATION_TEMPLATE_VIEW;
import static com.lms.utils.constants.ViewConstant.CONFIGURATION__IMAGECONFIGURATION_VIEW;
import static com.lms.utils.constants.ViewConstant.REDIRECT_HOME_VIEW;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.config.security.SecUser;
import com.lms.models.EmailTemplate;
import com.lms.models.Library;
import com.lms.services.emailtemplate.EmailTemplateService;
import com.lms.services.library.LibraryService;
import com.lms.utils.beans.EmailTemplateBean;
import com.lms.utils.beans.LibraryBean;
import com.lms.utils.converter.NotificationTypeConverter;
import com.lms.utils.converter.SaveImageServiceTypeConverter;
import com.lms.utils.customannotation.annotaion.XxsFilter;
import com.lms.utils.enums.NotificationType;
import com.lms.utils.enums.SaveImageServiceType;
import com.lms.config.factory.NotificationFactory;
import com.lms.utils.helper.NotificationUtil;
import com.lms.utils.helper.SecurityUtil;

/**
 * Created by bhushan on 20/4/17.
 */
@Controller
@RequestMapping("configuration")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
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
    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SaveImageServiceType.class, new SaveImageServiceTypeConverter());
        dataBinder.registerCustomEditor(NotificationType.class, new NotificationTypeConverter());
    }

    @RequestMapping(CONFIGURATION_IMAGECONFIGURATION_PATH)
    public ModelAndView imageConfiguration() {
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        ModelAndView modelAndView = new ModelAndView(CONFIGURATION__IMAGECONFIGURATION_VIEW);
        LibraryBean libraryBean = new LibraryBean();
        libraryBean.setSaveImageServiceType(library.getSaveImageServiceType());
        libraryBean.setUuid(library.getUuid());
        modelAndView.addObject("local", LOCALSTROGEPATH);
        modelAndView.addObject("cloudinary", CLOUDINARYSTROGEPATH);
        modelAndView.addObject("currentStorageType", library.getSaveImageServiceType());
        modelAndView.addObject("storageType", SaveImageServiceType.values());
        return modelAndView;
    }

    @RequestMapping(CONFIGURATION_UPDATE_STORAGE_TYPE_PATH)
    public ModelAndView updateStrogeType(@RequestParam(value = "saveImageServiceType") SaveImageServiceType saveImageServiceType) {
        SecUser secUser = SecurityUtil.getCurrentUser();
        Library library = libraryService.findByUuid(secUser.getLibraryId());
        library.setSaveImageServiceType(saveImageServiceType);
        libraryService.update(library);
        return new ModelAndView(CONFIGURATION_REDIRECT_IMAGECONFIGURATION);
    }


    @RequestMapping(CONFIGURATION_TEMPLATE_PATH)
    public ModelAndView index(@RequestParam(value="currentPageNumber", required = false) Integer currentPageNumber) {
        if (currentPageNumber == null) {
            currentPageNumber =1;
        }
        Page<EmailTemplate> page = emailTemplateService.getPageRequest(currentPageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        ModelAndView modelAndView = new ModelAndView(CONFIGURATION_TEMPLATE_LIST_VIEW);
        modelAndView.addObject("templates",  page.getContent());
        modelAndView.addObject("beginIndex", begin);
        modelAndView.addObject("endIndex", end);
        modelAndView.addObject("currentIndex", current);
        return modelAndView;
    }

    @RequestMapping(CONFIGURATION_EDIT_TEMPLATE_PATH)
    public ModelAndView editTemplate(@PathVariable(value="id", required = true) String id) {
        EmailTemplate emailTemplate = emailTemplateService.findByUuid(id);
        if(emailTemplate != null) {
            EmailTemplateBean emailTemplateBean = buildEmailTemplateBean(emailTemplate);
            ModelAndView modelAndView = getEmailTemplateModelAndView(CONFIGURATION_EDIT_TEMPLATE_VIEW, emailTemplateBean);
            emailTemplateBean.setAvailableField(NotificationUtil.getParametersListByNotificationType(emailTemplate.getNotificationType()));
            return modelAndView;
        }
        return new ModelAndView(CONFIGURATION_REDIRECT_TEMPLATE);
    }

    @RequestMapping(CONFIGURATION_VIEW_TEMPLATE_PATH)
    public ModelAndView viewTemplate(@PathVariable(value="id", required = true) String id) {
        EmailTemplate emailTemplate = emailTemplateService.findByUuid(id);
        if(emailTemplate != null) {
            EmailTemplateBean emailTemplateBean = buildEmailTemplateBean(emailTemplate);
            ModelAndView modelAndView = getEmailTemplateModelAndView(CONFIGURATION_TEMPLATE_VIEW, emailTemplateBean);
            return modelAndView;
        }
        return new ModelAndView(CONFIGURATION_REDIRECT_TEMPLATE);
    }

    @XxsFilter
    @RequestMapping(CONFIGURATION_UPDATE_TEMPLATE_PATH)
    public ModelAndView updateTemplate(@Valid @ModelAttribute("emailTemplate")EmailTemplateBean emailTemplateBean, BindingResult result, final RedirectAttributes redirectAttributes) {
        EmailTemplate emailTemplate = emailTemplateService.findByUuid(emailTemplateBean.getUuid());
        if (emailTemplate == null) {
            return new ModelAndView(REDIRECT_HOME_VIEW);
        }
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            emailTemplateBean.setAvailableField(NotificationUtil.getParametersListByNotificationType(emailTemplate.getNotificationType()));
            ModelAndView modelAndView = getEmailTemplateModelAndView(CONFIGURATION_EDIT_TEMPLATE_VIEW, emailTemplateBean);
            modelAndView.addObject("errors", errors);
            return modelAndView;
        }
        emailTemplate.setContent(emailTemplateBean.getContent());
        emailTemplate.setSubject(emailTemplateBean.getSubject());
        emailTemplateService.update(emailTemplate);
        emailTemplateBean = buildEmailTemplateBean(emailTemplate);
        ModelAndView modelAndView = getEmailTemplateModelAndView(CONFIGURATION_TEMPLATE_VIEW, emailTemplateBean);
        redirectAttributes.addFlashAttribute("success", messageSource.getMessage("configuration.successfully.edited", null, LocaleContextHolder.getLocale()));
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
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("emailTemplate", emailTemplateBean);
        return modelAndView;
    }

}
