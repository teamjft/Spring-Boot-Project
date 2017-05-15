package com.lms.models;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.enums.CreditCardType;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 9/5/17.
 */
@Entity
@Getter @Setter
public class PaymentInstrument extends AbstractEntity {
    private String number;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cvv2;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;
    @Embedded
    private BillingAddress billingAddress;
    @ManyToOne
    private User user;

}
