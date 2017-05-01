package com.lms.utils.customannotation.annotaion;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lms.utils.customannotation.validator.FieldSizeValidator;
import com.lms.utils.customannotation.validator.NumberSizeValidator;

/**
 * Created by bhushan on 28/4/17.
 */
@Documented
@Constraint(validatedBy = {FieldSizeValidator.class, NumberSizeValidator.class})
@Target( { ElementType.METHOD, ElementType.FIELD,ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldSize {
    String message() default "{FieldSize}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName() default "";
    int min() default  1;
    int max() default 255;
    boolean nullable()default true;
}
