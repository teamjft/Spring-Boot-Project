package com.lms.services.payment;

import com.lms.utils.paymentbuilder.PaymentRequest;
import com.lms.utils.paymentbuilder.Response;

/**
 * Created by bhushan on 10/5/17.
 */
public interface PaymentService {
    Response pay(PaymentRequest paymentRequest) throws Exception;
}
