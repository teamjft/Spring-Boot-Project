package com.lms.utils.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by bhushan on 24/4/17.
 */
@Setter @Getter @AllArgsConstructor
public class ResponseMessage {
    private String message;
    private MessageType type;
    private String field;

    public enum MessageType {
        SUCCESS, INFO, WARNING, ERROR
    }
}
