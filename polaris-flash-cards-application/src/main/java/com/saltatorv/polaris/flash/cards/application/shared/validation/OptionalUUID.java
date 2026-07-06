package com.saltatorv.polaris.flash.cards.application.shared.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = OptionalUUIDValidator.class)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface OptionalUUID {
    String message() default "Provided ID must be empty or valid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
