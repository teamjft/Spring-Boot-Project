package com.lms.utils.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.utils.customannotation.annotaion.FieldSize;
import com.lms.utils.customannotation.annotaion.ValidBoolean;

/**
 * Created by bhushan on 24/4/17.
 */
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class PasswordConfirmationBean {
    @FieldSize(nullable = false)
    private String password;
    @FieldSize(nullable = false)
    private String confirmPassword;
    @FieldSize(nullable = false)
    private String token;

    @ValidBoolean(message = "confirm password and password must be same.")
    public boolean valid() {
       return password != null && password.equals(confirmPassword);
    }
}
