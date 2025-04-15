package com.example.Trello_style.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OptionalNotBlankValidator.class)
public @interface OptionalNotBlank {
    String message() default "Field must not be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}