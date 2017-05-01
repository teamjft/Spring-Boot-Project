package com.lms.utils.notification.mapper;

import static org.apache.commons.lang3.SystemUtils.USER_NAME;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lms.utils.notification.parameterprovider.InviteUserParameter;

/**
 * Created by bhushan on 21/4/17.
 */
public class InviteUserMapMapper extends EmailMapMapper {
    private final static String FIRST_NAME = "%FIRSTNAME%";
    private final static String LAST_NAME = "%LASTNAME%";
    private final static String LIBRARY_NAME = "%LIBRARYNAME%";
    private final static String USER_ACTIVATION_URL = "%USERACTIVATIONURL%";
    public static final List<String> TARGET_PARAMETERS = Arrays.asList(FIRST_NAME, LAST_NAME, LIBRARY_NAME, USER_ACTIVATION_URL );

    public InviteUserMapMapper(InviteUserParameter inviteUserParameter) {
        map = new HashMap<String, String>();
        map.put(LAST_NAME, inviteUserParameter.getLastName());
        map.put(FIRST_NAME, inviteUserParameter.getFirstName());
        map.put(LIBRARY_NAME, inviteUserParameter.getLibraryName());
        map.put(USER_ACTIVATION_URL, inviteUserParameter.getUserActivationUrl());
    }

    @Override
    public List getTargetParameters() {
        return TARGET_PARAMETERS;
    }

}
