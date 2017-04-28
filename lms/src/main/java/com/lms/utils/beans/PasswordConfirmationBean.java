package com.lms.utils.beans;


import javax.validation.constraints.Size;

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
    @NotEmpty
    @Size(max = 255)
    private String password;
    @Size(max = 255)
    @NotEmpty
    private String confirmPassword;
    @NotEmpty
    private String token;
}
