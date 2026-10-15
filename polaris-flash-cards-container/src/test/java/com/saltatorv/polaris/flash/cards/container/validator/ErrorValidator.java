package com.saltatorv.polaris.flash.cards.container.validator;

import com.saltatorv.polaris.flash.cards.container.model.ErrorResponseDto;
import com.saltatorv.polaris.flash.cards.container.model.ValidationErrorResponseDto;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorValidator {

    private final ErrorResponseDto error;
    private Function<? super ErrorResponseDto, ?>[] extractors;
    private Object[] expectedErrors;

    private ErrorValidator(ErrorResponseDto error) {
        this.error = error;
    }

    public static ErrorValidator assertErrorContainError(ErrorResponseDto error) {
        return new ErrorValidator(error);
    }

    public ErrorValidator elementsToValidate(Function<? super ErrorResponseDto, ?>... fields) {
        this.extractors = fields;
        return this;
    }

    public ErrorValidator expectedData(Object... expectedErrors) {
        this.expectedErrors = expectedErrors;
        return this;
    }

    public void validate() {
        assertThat(error)
                .extracting(extractors)
                .contains(expectedErrors);
    }
}
