package com.lms.utils.helper;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lms.config.security.SecUser;

/**
 * Created by bhushan on 26/4/17.
 */
public class SecurityUtil {
    public static SecUser getCurrentUser() {
        return (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
