package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.PaymentInstrument;

/**
 * Created by bhushan on 12/5/17.
 */
public interface PaymentInstrumentRepository extends JpaRepository<PaymentInstrument, Long> {
    PaymentInstrument findFirstByEnabledTrue();
}
