package com.lms.services.notification;

import com.lms.utils.notification.Notification;

/**
 * Created by bhushan on 20/4/17.
 */
public interface NotificationService {
    void sendNotification(Notification notification);
    void sendAsyncNotification(Notification notification);
    public void retry(Notification notification, Long retryNotificationId);
}
