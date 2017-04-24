package com.lms.utils.helper;

import java.util.List;

import com.lms.utils.enums.NotificationType;
import com.lms.utils.notification.mapper.ForgetMapPassword;
import com.lms.utils.notification.mapper.InviteUserMapMapper;

/**
 * Created by bhushan on 24/4/17.
 */
public class NotificationUtil {
    public static List<String> getParametersListByNotificationType(NotificationType notificationType) {
        switch (notificationType) {
            case FORGETPASSWORD:
                return ForgetMapPassword.TARGET_PARAMETERS;
            case INVITEUSER:
                return InviteUserMapMapper.TARGET_PARAMETERS;
            default:return null;
        }
    }
}
