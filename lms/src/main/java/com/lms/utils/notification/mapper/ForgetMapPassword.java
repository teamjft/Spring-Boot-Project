package com.lms.utils.notification.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lms.utils.notification.parameterprovider.ForgetPasswordParameter;

/**
 * Created by bhushan on 21/4/17.
 */
public class ForgetMapPassword extends EmailMapMapper {
    private static final String FORGET_PASSWORD =  "%FORGETPASSWORDURL%";
    private static final String USERNAME   ="%USERNAME%";
    public static final List<String> TARGET_PARAMETERS = Arrays.asList(FORGET_PASSWORD, USERNAME);

    public ForgetMapPassword(ForgetPasswordParameter forgetPasswordParameter) {
        map = new HashMap<String, String>();
        map.put(FORGET_PASSWORD, forgetPasswordParameter.getForgetPasswordUrl());
        map.put(USERNAME, forgetPasswordParameter.getUserName());
    }

    @Override
    public List getTargetParameters() {
        return TARGET_PARAMETERS;
    }

}
