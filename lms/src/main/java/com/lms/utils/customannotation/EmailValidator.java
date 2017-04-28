package com.lms.utils.customannotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lms.utils.helper.StringUtil;

/**
 * Created by bhushan on 28/4/17.
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null || value.length() > 0) {
            return StringUtil.isValidEmail(value);
        }
        return false;
    }
}
