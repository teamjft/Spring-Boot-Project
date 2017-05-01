package com.lms.utils.customannotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lms.utils.customannotation.annotaion.FieldSize;

/**
 * Created by bhushan on 28/4/17.
 */
public class FieldSizeValidator implements ConstraintValidator<FieldSize, String>{
    private int min;
    private int max;
    boolean nullable;
    @Override
    public void initialize(FieldSize constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
        nullable =constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (nullable == true && value == null ) {
            return true;
        } else if(value  == null || value.length() == 0) {
            return false;
        } else if(min <= value.length() && max >= value.length()) {
            return true;
        }
        return false;
    }
}
