package com.lms.utils.customannotation.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lms.utils.customannotation.validator.BooleanValidator;

/**
 * Created by bhushan on 1/5/17.
 */
@Documented
@Constraint(validatedBy = BooleanValidator.class)
@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBoolean {
    String message() default "{ValidBoolean}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean acceptable() default true;
}
