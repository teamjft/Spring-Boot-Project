package com.lms.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

import com.lms.utils.enums.NotificationType;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 21/4/17.
 */
@Entity
@Setter @Getter
public class EmailTemplate extends AbstractEntity {
    @Column(name = "type", nullable = true)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "subject", length = 1024)
    private String subject;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    private boolean deleted;

    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    @Type(type = "yes_no")
    private boolean defaultEmailTemplates;

}
