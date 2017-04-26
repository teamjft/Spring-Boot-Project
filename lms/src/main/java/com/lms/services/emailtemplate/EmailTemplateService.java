package com.lms.services.emailtemplate;

import org.springframework.data.domain.Page;

import com.lms.models.EmailTemplate;
import com.lms.utils.enums.NotificationType;

/**
 * Created by bhushan on 21/4/17.
 */
public interface EmailTemplateService {
    EmailTemplate findEmailTemplateByNotificationType(NotificationType notificationType);
    EmailTemplate create(EmailTemplate emailTemplate);
    EmailTemplate update(EmailTemplate emailTemplate);
    Page<EmailTemplate> getPageRequest(Integer pageNumber);
    EmailTemplate findByUuid(String uuid);
}
