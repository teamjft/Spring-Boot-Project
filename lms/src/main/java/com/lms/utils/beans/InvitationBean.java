package com.lms.utils.beans;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Email;

/**
 * Created by bhushan on 25/4/17.
 */
@Builder @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class InvitationBean {
    @NotNull(message = "{email.not.empty}")
    @Email(message = "{email.not.valid}")
    private String email;
    private Long invitedById;
    private Long inviteLibraryId;
    private boolean admin;
    private boolean librarian;
    private boolean superAdmin;

}
