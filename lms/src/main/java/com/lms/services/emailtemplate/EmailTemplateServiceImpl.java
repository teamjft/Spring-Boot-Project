package com.lms.services.emailtemplate;

import static com.lms.utils.constants.Constant.PAGE_SIZE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.dao.repository.EmailTemplateRepository;
import com.lms.models.EmailTemplate;
import com.lms.utils.enums.NotificationType;

/**
 * Created by bhushan on 21/4/17.
 */
@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {
    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Override
    @Transactional(readOnly = true)
    public EmailTemplate findEmailTemplateByNotificationType(NotificationType notificationType) {
        List<EmailTemplate> emailTemplates = emailTemplateRepository.findByNotificationTypeAndDefaultEmailTemplatesTrue(notificationType);
        if (emailTemplates == null || emailTemplates.size() == 0) {
            return null;
        }
        return emailTemplates.get(0);
    }

    @Override
    @Transactional
    public EmailTemplate create(EmailTemplate emailTemplate) {
        return emailTemplateRepository.save(emailTemplate);
    }

    @Override
    @Transactional
    public EmailTemplate update(EmailTemplate emailTemplate) {
        return emailTemplateRepository.save(emailTemplate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmailTemplate> getPageRequest(Integer pageNumber) {
            PageRequest request =
                    new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");
            return emailTemplateRepository.findAll(request);
        }

    @Override
    @Transactional(readOnly = true)
    public EmailTemplate findByUuid(String uuid) {
        return emailTemplateRepository.findByUuid(uuid);
    }
}
