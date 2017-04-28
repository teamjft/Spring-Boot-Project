package com.lms.utils.notification.parameterprovider;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by bhushan on 21/4/17.
 */
@Builder
@Setter @Getter
public class ForgetPasswordParameter {
    private String forgetPasswordUrl;
    private String userName;

    @Override
    public String toString() {
        return "ForgetPasswordParameter{" +
                "forgetPasswordUrl='" + forgetPasswordUrl + '\'' +
                ", email='" + userName + '\'' +
                '}';
    }
}
