package com.lms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

import com.lms.utils.enums.NotificationType;
import com.lms.utils.modelutil.AbstractEntity;
import com.lms.utils.notification.Notification;

/**
 * Created by bhushan on 20/4/17.
 */
@Entity
@Getter @Setter
public class MailContent extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -5815280582242687925L;

    public MailContent() {
        super();
    }

    @Column(nullable = false)
    private String toEmail;

    @Column(name = "content", columnDefinition = "TEXT")
    private String text;

    private String subject;

    private String replyTo;

    @Type(type = "yes_no")
    @Column(nullable = false, columnDefinition = "char(1) default 'N'")
    private boolean deleted;

    @Type(type = "yes_no")
    @Column(nullable = false, columnDefinition = "char(1) default 'Y'")

    private boolean markedFail = true;
    @Type(type = "yes_no")

    @Column(nullable = false, columnDefinition = "char(1) default 'Y'")
    private boolean html = true;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(length = 1020)
    private String error;

    private Integer attemptsCount = 0;

    public MailContent(Notification<String, String, String> notification) {
        super();
        this.setToEmail(notification.to());
        this.setNotificationType(notification.getNotificationType());
        this.setSubject(notification.subject());
        this.setText(notification.content());
    }

}
