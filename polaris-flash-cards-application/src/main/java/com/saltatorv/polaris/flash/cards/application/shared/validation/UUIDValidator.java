package com.saltatorv.polaris.flash.cards.application.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

class UUIDValidator implements ConstraintValidator<ValidId, String> {
    @Override
    public void initialize(ValidId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uuidToValidate, ConstraintValidatorContext constraintValidatorContext) {
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
