package com.saltatorv.polaris.flash.cards.application.shared.validation;
import com.saltatorv.polaris.flash.cards.application.shared.validation.UUIDValidator;

import jakarta.validation.Constraint;


import java.lang.annotation.Documented;

@Documented
@Constraint(validatedBy = UUIDValidator.class)
public @interface ValidId {
}
