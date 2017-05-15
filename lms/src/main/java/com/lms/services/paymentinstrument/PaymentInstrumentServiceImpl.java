package com.lms.services.paymentinstrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.dao.repository.PaymentInstrumentRepository;
import com.lms.models.PaymentInstrument;

/**
 * Created by bhushan on 12/5/17.
 */
@Service
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {
    @Autowired
    private PaymentInstrumentRepository paymentInstrumentRepository;

    @Override
    public PaymentInstrument findFirstEnableTrue() {
        return paymentInstrumentRepository.findFirstByEnabledTrue();
    }
}
