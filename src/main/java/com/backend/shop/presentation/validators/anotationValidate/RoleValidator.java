package com.backend.shop.presentation.validators.anotationValidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.backend.shop.presentation.validators.RoleValidatorImpl;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy =RoleValidatorImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleValidator {
    String message() default "Invalid role value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
