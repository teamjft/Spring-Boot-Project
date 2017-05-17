package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.PaymentInstrument;
import com.lms.models.User;

/**
 * Created by bhushan on 12/5/17.
 */
public interface PaymentInstrumentRepository extends JpaRepository<PaymentInstrument, Long> {
    PaymentInstrument findFirstByUserAndEnabledTrue(User user);
    PaymentInstrument findFirstByUuidAndEnabledTrue(String uuid);
}
