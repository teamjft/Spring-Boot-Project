package com.lms.utils.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.utils.enums.NotificationType;

/**
 * Created by bhushan on 20/4/17.
 */
@Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class EmailNotification implements Notification<String, String, String> {
    private String to;
    private String subject;
    private String content;
    private NotificationType notificationType;

    @Override
    public String to() {
        return to;
    }

    @Override
    public String subject() {
        return subject;
    }

    @Override
    public String content() {
        return content;
    }

    @Override
    public NotificationType getNotificationType() {
        return notificationType;
    }

}
