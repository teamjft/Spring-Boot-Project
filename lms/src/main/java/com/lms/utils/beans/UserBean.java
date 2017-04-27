package com.lms.utils.beans;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by bhushan on 17/4/17.
 */
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class UserBean {
    private Long id;
    @Size(max = 255, message = "{isbn.max}")
    @NotEmpty(message = "{firstname.notempty}" )
    private String firstName;
    @Size(max = 255)
    private String lastName;
    @Size(max = 255)
    private String email;
    @NotEmpty(message = "{username.notempty}")
    @Size(max = 255)
    private String username;
    private String password;
    private boolean enabled = true;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean passwordExpired;
    private boolean superAdmin;
    private String token;
    private PasswordConfirmationBean passwordConfirmationBean;
}
