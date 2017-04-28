package com.lms.utils.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bhushan on 24/4/17.
 */
public class StringUtil {
    private static final String EMAIL_REGEX  = "^(.+)@(.+)$";


    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher((CharSequence) email);
        return matcher.matches();
    }

}
