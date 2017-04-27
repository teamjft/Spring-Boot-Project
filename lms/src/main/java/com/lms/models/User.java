package com.lms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity
@Getter @Setter
public class User extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 8217506961292491192L;
    public User() {
        super();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 200, message = "The license plate '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 2, max = 200, message = "The license plate '${validatedValue}' must be between {min} and {max} characters long")
    @Column(name = "last_name")
    private String lastName;
    @NotEmpty
    @Email(message = "please enter valid '${validatedValue}")
    @Column(name = "email", unique = true)
    @Size(min = 2, max = 200, message = "The license plate '${validatedValue}' must be between {min} and {max} characters long")
    private String email;
    @NotEmpty
    @Size(min = 2, max = 200, message = "The license plate '${validatedValue}' must be between {min} and {max} characters long")
    @Column(unique = true, name = "username")
    private String username;
    @NotEmpty
    private String password;
    @Type(type = "yes_no")
    @Column(name = "is_enabled", nullable = false, columnDefinition = "char(1) default 'Y'")
    private boolean enabled = true;
    @Type(type = "yes_no")
    @Column(name = "is_accountExpired", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean accountExpired;
    @Type(type = "yes_no")
    @Column(name = "is_accountLocked", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean accountLocked;
    @Type(type = "yes_no")
    @Column(name = "is_passwordExpired", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean passwordExpired;
    @Type(type = "yes_no")
    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean superAdmin;
    private String token;

    @PrePersist
    protected void onPrePersist() {
        super.onPrePersist();
        setPassword(new BCryptPasswordEncoder().encode(this.password));
    }

}
