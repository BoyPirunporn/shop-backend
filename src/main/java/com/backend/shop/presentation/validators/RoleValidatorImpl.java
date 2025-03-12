package com.backend.shop.presentation.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.backend.shop.domains.enums.ERole;
import com.backend.shop.presentation.validators.anotationValidate.RoleValidator;

import java.util.Set;

public class RoleValidatorImpl implements ConstraintValidator<RoleValidator, Set<ERole>> {

    private String message;

    @Override
    public void initialize(RoleValidator constraintAnnotation) {
        // Get the custom message from the annotation
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Set<ERole> roles, ConstraintValidatorContext context) {
        // If roles is null or empty, return false
        if (roles == null || roles.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                   .addConstraintViolation();
            return false;
        }

        // Check each role if it matches the enum values
        for (ERole role : roles) {
            if (role == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message)
                       .addConstraintViolation();
                return false; // Null value not allowed
            }
            try {
                // Ensure role exists in enum ERole
                ERole.valueOf(role.name());
            } catch (IllegalArgumentException ex) {
                // If role does not exist in ERole enum, return false and set custom message
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid role: " + role.name())
                       .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
