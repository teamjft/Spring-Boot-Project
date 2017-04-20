package com.lms.utils.notification;

import com.lms.utils.enums.NotificationType;

/**
 * Created by bhushan on 20/4/17.
 */
public interface Notification<T, S, C> {
    T to();
    S subject();
    C content();
    NotificationType getNotificationType();
}
