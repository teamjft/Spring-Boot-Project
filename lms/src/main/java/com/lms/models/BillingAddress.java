package com.lms.models;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by bhushan on 9/5/17.
 */
@Embeddable
@Getter @Setter
public class BillingAddress {
    private String line1;
    private String city;
    private String state;
    private String postalCode;
    private String countryCode;
}
