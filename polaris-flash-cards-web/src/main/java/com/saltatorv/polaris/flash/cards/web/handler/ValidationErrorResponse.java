package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private static final String MESSAGE = "Validation error";
    private static final int STATUS = 422;
    private final List<ValidationError> errors;

    ValidationErrorResponse(ErrorType type, List<ValidationError> errors) {
        super(type, STATUS, MESSAGE);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
