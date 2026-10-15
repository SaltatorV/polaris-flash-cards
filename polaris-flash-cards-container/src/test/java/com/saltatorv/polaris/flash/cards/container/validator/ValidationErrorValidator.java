package com.saltatorv.polaris.flash.cards.container.validator;

import com.saltatorv.polaris.flash.cards.container.model.ValidationErrorDto;
import com.saltatorv.polaris.flash.cards.container.model.ValidationErrorResponseDto;
import org.assertj.core.groups.Tuple;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationErrorValidator {

    private final ValidationErrorResponseDto error;
    private Function<? super ValidationErrorDto, ?>[] extractors;
    private Tuple[] expectedErrors;

    private ValidationErrorValidator(ValidationErrorResponseDto error) {
        this.error = error;
    }

    public static ValidationErrorValidator assertErrorContainValidationError(ValidationErrorResponseDto error) {
        return new ValidationErrorValidator(error);
    }

    public ValidationErrorValidator elementsToValidate(Function<? super ValidationErrorDto, ?>... fields) {
        this.extractors = fields;
        return this;
    }

    public ValidationErrorValidator expectedErrors(Tuple... expectedErrors) {
        this.expectedErrors = expectedErrors;
        return this;
    }

    public void validate() {
        assertThat(error.errors())
                .extracting(extractors)
                .contains(expectedErrors);
    }
}
