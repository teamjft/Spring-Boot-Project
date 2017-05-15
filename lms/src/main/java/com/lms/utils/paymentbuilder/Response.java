package com.lms.utils.paymentbuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.lms.utils.enums.PaymentMethod;

/**
 * Created by bhushan on 15/5/17.
 */
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Response {
    PaymentMethod paymentMethod;
    String transactionId;
}
