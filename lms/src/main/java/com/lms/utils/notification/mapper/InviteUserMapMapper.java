package com.lms.utils.notification.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lms.utils.notification.parameterprovider.InviteUserParameter;

/**
 * Created by bhushan on 21/4/17.
 */
public class InviteUserMapMapper implements EmailMapMapper<InviteUserParameter>{
    private final static String USER_NAME = "%USERNAME%";
    private final static String LIBRARY_NAME = "%LIBRARYNAME%";
    private final static String USER_ACTIVATION_URL = "%USERACTIVATIONURL";
    public static final List<String> TARGET_PARAMETERS = Arrays.asList(USER_NAME, LIBRARY_NAME, USER_ACTIVATION_URL );
    private Map<String, String> map;

    @Override
    public List getTargetParameters() {
        return TARGET_PARAMETERS;
    }

    @Override
    public Map<String, String> getMap() {
        return map;
    }

    @Override
    public void setMap(InviteUserParameter inviteUserParameter) {
        map = new HashMap<String, String>();
        map.put(USER_NAME, inviteUserParameter.getUserName());
        map.put(LIBRARY_NAME, inviteUserParameter.getLibraryName());
        map.put(USER_ACTIVATION_URL, inviteUserParameter.getUserActivationUrl());
    }
}
