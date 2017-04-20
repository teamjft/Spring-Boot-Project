package com.lms.utils.enums;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by bhushan on 20/4/17.
 */
@Getter @Setter @Builder
public class NotificationFields {
    private String userName;
    private String forgetPasswordUrl;
    private String libraryName;
    private String bookName;
    private String userActivationUrl;

}
