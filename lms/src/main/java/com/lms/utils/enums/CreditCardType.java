package com.lms.utils.enums;

/**
 * Created by bhushan on 9/5/17.
 */
public enum  CreditCardType {
    VISA("visa"),
    MASTERCARD("mastercard"),
    DISCOVER("discover"),
    AMEX("amex");

    private String name;

    CreditCardType(String name){
        this.name =name;
    }

    public void setValue(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
