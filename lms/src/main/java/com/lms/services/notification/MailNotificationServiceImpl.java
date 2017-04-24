package com.lms.services.notification;

import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.models.MailContent;
import com.lms.services.mail.MailService;
import com.lms.utils.notification.Notification;

/**
 * Created by bhushan on 20/4/17.
 */
@Service
@Slf4j
public class MailNotificationServiceImpl implements NotificationService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailService mailService;


    @Value("${support.email}")
    String supportEmail;

    @Override
    @Transactional
    public void sendNotification(Notification notification) {
        send(notification, null);
    }

    @Override
    @Async
    @Transactional
    public void sendAsyncNotification(Notification notification)  {
        send(notification, null);
    }

    @Override
    @Transactional
    public void retry(Notification notification, Long retryNotificationId) {
        send(notification, retryNotificationId);
    }

    private void send(Notification<String, String, String> notification, Long retryNotificationId)  {
        MailContent mailContent;
        try {
            final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(notification.to());
            message.setFrom(supportEmail);
            message.setSubject(notification.subject());
            message.setText(notification.content());
            javaMailSender.send(message.getMimeMessage());
            if (retryNotificationId != null) {
                mailContent = mailService.get(retryNotificationId);
                mailContent.setDeleted(true);
                mailService.save(mailContent);
            }
        } catch (Exception e) {
            log.error("Exception occur during send email: {}, error: ",notification.to());
            if(retryNotificationId == null) {
                mailContent = new MailContent(notification);
            } else {
                mailContent = mailService.get(retryNotificationId);
            }
            mailContent.setAttemptsCount(mailContent.getAttemptsCount() + 1);
            mailContent.setError(e.getMessage());
            mailService.save(mailContent);
        }

    }
}
