package com.lms.services.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.repository.PaymentRepository;
import com.lms.models.Payment;

/**
 * Created by bhushan on 17/5/17.
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment get(Long id) {
        return paymentRepository.getOne(id);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.delete(id);
    }

    @Override
    public void update(Payment payment) {
        paymentRepository.save(payment);
    }
}
