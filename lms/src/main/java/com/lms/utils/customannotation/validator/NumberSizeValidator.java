package com.lms.utils.customannotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lms.utils.customannotation.annotaion.FieldSize;

/**
 * Created by bhushan on 1/5/17.
 */
public class NumberSizeValidator implements ConstraintValidator<FieldSize, Number >{
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
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (nullable == true && value == null ) {
            return true;
        } else if (nullable == false && value == null) {
            return false;
        }
        if (value.longValue() >= min && value.longValue() <= max) {
            return true;
        }
        return false;
    }
}
