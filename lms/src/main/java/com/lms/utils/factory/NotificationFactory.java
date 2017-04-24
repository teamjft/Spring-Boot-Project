package com.lms.utils.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.services.notification.NotificationService;
import com.lms.utils.enums.NotificationServiceType;

/**
 * Created by bhushan on 20/4/17.
 */
@Component
public class NotificationFactory {
    @Autowired
    private NotificationService notificationService;

    public NotificationService getSendContentService(NotificationServiceType notificationServiceType) {
        switch (notificationServiceType) {
            case EMAIL:
                return notificationService;
            default:
                return notificationService;
        }
    }

}
