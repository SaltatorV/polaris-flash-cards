package com.saltatorv.polaris.flash.cards.application.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

class OptionalUUIDValidator implements ConstraintValidator<OptionalUUID, String> {
    @Override
    public void initialize(OptionalUUID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uuidToValidate, ConstraintValidatorContext constraintValidatorContext) {
        if(uuidToValidate.isEmpty()) {
            return true;
        }

        if(uuidToValidate.length() != 36) {
            return false;
        }

        try {
            UUID.fromString(uuidToValidate);
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
