package com.lms.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Category;
import com.lms.models.EmailTemplate;
import com.lms.utils.enums.NotificationType;

/**
 * Created by bhushan on 21/4/17.
 */
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
    List<EmailTemplate> findByNotificationTypeAndDefaultEmailTemplatesTrue(NotificationType notificationType);
    EmailTemplate findByUuid(String uuid);
}
