package com.lms.utils.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

import com.lms.utils.enums.NotificationType;

/**
 * Created by bhushan on 24/4/17.
 */
@Builder @Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class EmailTemplateBean {
    private long id;
    private String uuid;
    @NotEmpty(message = "{notificationType.name.notempty}")
    private NotificationType notificationType;
    @NotEmpty(message = "{subject.notempty}")
    private String subject;
    @NotEmpty(message = "{content.notempty}")
    private String content;
    private List aviableField;
}
