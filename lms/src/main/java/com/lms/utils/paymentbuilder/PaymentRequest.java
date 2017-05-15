package com.lms.utils.paymentbuilder;

import com.lms.utils.exceptions.InvalidPaymentRequestException;

/**
 * Created by bhushan on 11/5/17.
 */
public interface PaymentRequest<R> {
    R getRequest();
    void validate() throws InvalidPaymentRequestException;
}
