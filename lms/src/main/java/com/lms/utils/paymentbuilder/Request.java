package com.lms.utils.paymentbuilder;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.beans.PaymentInstrumentBean;
import com.lms.utils.enums.Currency;

/**
 * Created by bhushan on 11/5/17.
 */
@Getter @Setter
public class Request {
    private PaymentInstrumentBean paymentInstrumentBean;
    private BigDecimal amount;
    private BigDecimal tax;
    private Currency currency;
    private MembershipPlanBean membershipPlanBean;

    public BigDecimal getTotalAmount() {
        if (tax == null) {
            return amount;
        }
       return amount.add(tax);
    }

}
