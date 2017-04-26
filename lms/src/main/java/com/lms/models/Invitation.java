package com.lms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;

import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 25/4/17.
 */
@Entity
@Builder @Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Invitation extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 7664308389574589457L;
    @Column(nullable = false)
    private String email;
    @OneToOne
    private User invitedBy;
    @OneToOne
    private Library inviteLibrary;
    @Type(type = "yes_no")
    @Column(name = "is_admin", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean admin;
    @Type(type = "yes_no")
    @Column(name = "is_librarian", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean librarian;
    @Type(type = "yes_no")
    @Column(name = "is_superAdmin", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean superAdmin;
    @Type(type = "yes_no")
    @Column(name = "is_deleted", nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean deleted;
}
