package com.lms.utils.paymentbuilder.paypalcreditcard;

import java.math.BigDecimal;

import com.lms.models.MembershipPlan;
import com.lms.utils.beans.MembershipPlanBean;
import com.lms.utils.beans.PaymentInstrumentBean;
import com.lms.utils.exceptions.InvalidPaymentRequestException;
import com.lms.utils.paymentbuilder.PaymentRequest;

/**
 * Created by bhushan on 11/5/17.
 */
public class PapalCreditCardBuilder implements PaymentRequest<PapalCreditRequest> {
    PapalCreditRequest papalCreditRequest;
    public PapalCreditCardBuilder(PaymentInstrumentBean paymentInstrumentBean, MembershipPlanBean membershipPlanBean, BigDecimal amount) {
        papalCreditRequest = new PapalCreditRequest();
        papalCreditRequest.setPaymentInstrumentBean(paymentInstrumentBean);
        papalCreditRequest.setMembershipPlanBean(membershipPlanBean);
        papalCreditRequest.setAmount(amount);
        papalCreditRequest.setCurrency(membershipPlanBean.getCurrency());

    }

    @Override
    public PapalCreditRequest getRequest() {
        return papalCreditRequest;
    }

    @Override
    public void validate() throws InvalidPaymentRequestException {
        if (papalCreditRequest == null) {
            throw new InvalidPaymentRequestException(PapalCreditCardBuilder.class.getName());
        }
    }
}
