package com.lms.utils.exceptions;

/**
 * Created by bhushan on 11/5/17.
 */
public class InvalidPaymentRequestException extends Exception {
    public InvalidPaymentRequestException(String className) {
        super(String.format("Request %s is invalid", className));
    }
}
