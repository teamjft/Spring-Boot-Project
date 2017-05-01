package com.lms.utils.customannotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lms.utils.customannotation.annotaion.ValidBoolean;

/**
 * Created by bhushan on 1/5/17.
 */
public class BooleanValidator implements ConstraintValidator<ValidBoolean, Boolean> {
    boolean acceptable;
    @Override
    public void initialize(ValidBoolean constraintAnnotation) {
        acceptable = constraintAnnotation.acceptable();
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
       return value == acceptable;
    }
}
