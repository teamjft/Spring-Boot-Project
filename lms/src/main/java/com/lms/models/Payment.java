package com.lms.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.enums.Currency;
import com.lms.utils.enums.PaymentMethod;
import com.lms.utils.enums.PaymentStatus;
import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 3/5/17.
 */
@Entity
@Getter @Setter
public class Payment extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -582990260043830142L;
    private Date paymentDate;
    private String transactionId;
    private BigDecimal payAmount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToOne
    private Invoice invoice;

}
