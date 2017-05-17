package com.lms.services.transaction;

import com.lms.utils.paymentbuilder.PaymentRequest;
import com.lms.utils.paymentbuilder.Response;

/**
 * Created by bhushan on 10/5/17.
 */
public interface PaymentTransactionService {
    Response pay(PaymentRequest paymentRequest) throws Exception;
}
