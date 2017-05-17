package com.lms.services.payment;

import java.util.List;

import com.lms.models.Payment;

/**
 * Created by bhushan on 17/5/17.
 */
public interface PaymentService {
    Payment get(Long id);
    List<Payment> getAll();
    Payment create(Payment payment);
    void delete(Long id);
    void update(Payment payment);
}
