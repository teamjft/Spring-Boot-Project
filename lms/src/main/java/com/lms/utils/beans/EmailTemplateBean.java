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
    private NotificationType notificationType;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String content;
    private List availableField;
}
