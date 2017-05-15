package com.lms.utils.paymentbuilder.paypalcreditcard;

import java.util.UUID;

import com.lms.utils.paymentbuilder.Request;

/**
 * Created by bhushan on 11/5/17.
 */
public class PapalCreditRequest extends Request {
    private String invoiceNumber =  UUID.randomUUID().toString();

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
