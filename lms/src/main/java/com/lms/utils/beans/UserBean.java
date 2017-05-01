package com.lms.utils.beans;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

import com.lms.utils.customannotation.annotaion.Email;
import com.lms.utils.customannotation.annotaion.FieldSize;

/**
 * Created by bhushan on 17/4/17.
 */
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class UserBean {
    private Long id;
    @FieldSize(fieldName = "First Name" ,nullable = false)
    private String firstName;
    @FieldSize(fieldName = "Last Name")
    private String lastName;
    private String email;
    @FieldSize(nullable = false, fieldName = "Username")
    private String username;
    @FieldSize(nullable = false, fieldName = "password")
    private String password;
    private boolean enabled = true;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean passwordExpired;
    private boolean superAdmin;
    private String token;
}
