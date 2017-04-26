package com.lms.utils.converter;

import java.beans.PropertyEditorSupport;

import com.lms.utils.enums.NotificationType;
/**
 * Created by bhushan on 25/4/17.
 */
public class NotificationTypeConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        String capitalized = text.toUpperCase();
        NotificationType notificationType = NotificationType.valueOf(capitalized);
        setValue(notificationType);
    }
}
