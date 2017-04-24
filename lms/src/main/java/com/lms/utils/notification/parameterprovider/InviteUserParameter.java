package com.lms.utils.notification.parameterprovider;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by bhushan on 21/4/17.
 */
@Builder
@Setter
@Getter
public class InviteUserParameter {
    private String userName;
    private String libraryName;
    private String userActivationUrl;

    @Override
    public String toString() {
        return "InviteUserParameter{" +
                "userName='" + userName + '\'' +
                ", libraryName='" + libraryName + '\'' +
                ", userActivationUrl='" + userActivationUrl + '\'' +
                '}';
    }
}
