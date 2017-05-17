package com.lms.utils.beans;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.models.Address;
import com.lms.models.BillingAddress;
import com.lms.models.PaymentInstrument;
import com.lms.models.User;
import com.lms.utils.enums.CreditCardType;
import com.lms.utils.enums.PaymentMethod;

/**
 * Created by bhushan on 11/5/17.
 */
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentInstrumentBean {
    private Long id;
    private String uuid;
    @NotNull
    private String number;
    @NotNull
    private Integer expirationMonth;
    @NotNull
    private Integer expirationYear;
    @NotNull
    private String cvv2;
    private String firstName;
    private String lastName;
    private Long userId;
    private String line1;
    private String city;
    private String state;
    private String postalCode;
    private String countryCode;
    private String country;
    private OrderCartBean orderCart;
    @NotNull
    private CreditCardType creditCardType;
    @NotNull
    private PaymentMethod paymentMethod = PaymentMethod.PAYPAL_CREDIT_CARD;
    @NotNull
    private String membershipPlanId;

    public static PaymentInstrumentBean buildEntityToBean(PaymentInstrument paymentInstrument) {
        BillingAddress address = paymentInstrument.getBillingAddress();

        PaymentInstrumentBean paymentInstrumentBean = new PaymentInstrumentBean();
        paymentInstrumentBean.setId(paymentInstrument.getId());
        paymentInstrumentBean.setUuid(paymentInstrument.getUuid());
        paymentInstrumentBean.setFirstName(paymentInstrument.getFirstName());
        paymentInstrumentBean.setLastName(paymentInstrument.getLastName());
        paymentInstrumentBean.setExpirationMonth(paymentInstrument.getExpirationMonth());
        paymentInstrumentBean.setExpirationYear(paymentInstrument.getExpirationYear());
        paymentInstrumentBean.setCreditCardType(paymentInstrument.getCreditCardType());
        paymentInstrumentBean.setUserId(paymentInstrument.getUser().getId());
        paymentInstrumentBean.setNumber(paymentInstrument.getNumber());

        if(address != null) {
            paymentInstrumentBean.setCity(address.getCity());
            paymentInstrumentBean.setLine1(address.getLine1());
            paymentInstrumentBean.setState(address.getState());
            paymentInstrumentBean.setCountryCode(address.getCountryCode());
            paymentInstrumentBean.setPostalCode(address.getPostalCode());
        }

        return paymentInstrumentBean;
    }

    public static PaymentInstrument buildBeanToEntity(PaymentInstrumentBean paymentInstrumentBean) {

        PaymentInstrument paymentInstrument = new PaymentInstrument();
        paymentInstrument.setFirstName(paymentInstrumentBean.getFirstName());
        paymentInstrument.setLastName(paymentInstrumentBean.getLastName());
        paymentInstrument.setExpirationMonth(paymentInstrumentBean.getExpirationMonth());
        paymentInstrument.setExpirationYear(paymentInstrumentBean.getExpirationYear());
        paymentInstrument.setCreditCardType(paymentInstrumentBean.getCreditCardType());
        paymentInstrument.setNumber(paymentInstrumentBean.getNumber());

        BillingAddress address = new BillingAddress();
        if(paymentInstrumentBean.getCountry() != null) {
            address.setCity(paymentInstrumentBean.getCity());
            address.setLine1(paymentInstrumentBean.getLine1());
            address.setState(paymentInstrumentBean.getState());
            address.setCountryCode(paymentInstrumentBean.getCountryCode());
            address.setPostalCode(paymentInstrumentBean.getPostalCode());
            paymentInstrument.setBillingAddress(address);
        }

        return paymentInstrument;
    }
}
