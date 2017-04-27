package com.lms.services.notification;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.models.EmailTemplate;
import com.lms.services.emailtemplate.EmailTemplateService;
import com.lms.utils.enums.NotificationType;
import com.lms.utils.factory.NotificationFactory;
import com.lms.utils.notification.mapper.EmailMapMapper;

/**
 * Created by bhushan on 21/4/17.
 */
@Component
@Slf4j
public class NotificationBuilder {
    @Autowired
    private EmailTemplateService emailTemplateService;
    @Autowired
    private NotificationFactory notificationFactory;


    public Pair<String, String> getNotificationContentAndSubject(NotificationType notificationType, EmailMapMapper emailMapMapper) {
        EmailTemplate emailTemplate = emailTemplateService.findEmailTemplateByNotificationType(notificationType);
        if (emailTemplate  == null) {
          log.error("No template found for notification type {}", notificationType);
          return null;
        }
        String  content = emailTemplate.getContent();

        @SuppressWarnings("unchecked")
        Map<String, String> propertyMap = emailMapMapper.getMap();
        for(Map.Entry<String, String> entry : propertyMap.entrySet()) {
            content = content.replaceAll(entry.getKey(), entry.getValue() != null ? entry.getValue() : StringUtils.EMPTY);
        }
        Pair<String, String> pair = Pair.of(emailTemplate.getSubject(), content);
        return pair;
    }

}
