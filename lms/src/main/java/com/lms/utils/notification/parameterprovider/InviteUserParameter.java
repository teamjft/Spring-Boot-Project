package com.lms.utils.notification.parameterprovider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bhushan on 21/4/17.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InviteUserParameter {
    private String firstName;
    private String lastName;
    private String libraryName;
    private String userActivationUrl;

    @Override
    public String toString() {
        return "InviteUserParameter{" +
                "userName='" + firstName + '\'' +
                ", libraryName='" + libraryName + '\'' +
                ", userActivationUrl='" + userActivationUrl + '\'' +
                '}';
    }
}
