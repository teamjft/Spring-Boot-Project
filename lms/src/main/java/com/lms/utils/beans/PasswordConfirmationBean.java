package com.lms.utils.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by bhushan on 24/4/17.
 */
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class PasswordConfirmationBean {
    @NotEmpty(message = "{password.not.empty}")
    private String password;
    @NotEmpty(message = "{confirmPassword.not.empty}")
    private String confirmPassword;
    @NotEmpty(message = "{token.not.empty}")
    private String token;
}
