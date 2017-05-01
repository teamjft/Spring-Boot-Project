package com.lms.utils.customannotation.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lms.utils.customannotation.validator.NotNullAndEmptyValidator;


/**
 * Created by bhushan on 28/4/17.
 */
@Documented
@Constraint(validatedBy = NotNullAndEmptyValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyAndNull {
    String message() default "{NotEmptyAndNull}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName() default "";
}

