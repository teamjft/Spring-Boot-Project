package com.lms.config.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.services.payment.PaymentService;
import com.lms.services.payment.PaypalCreditCardPaymentServiceImpl;
import com.lms.utils.enums.PaymentMethod;

/**
 * Created by bhushan on 12/5/17.
 */
@Component
public class PaymentFactory {
    @Autowired
    private PaypalCreditCardPaymentServiceImpl paypalCreditCardPaymentService;

    public PaymentService getPaymentService(PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.PAYPAL_CREDIT_CARD) {
            return paypalCreditCardPaymentService;
        }
        return null;
    }
}
