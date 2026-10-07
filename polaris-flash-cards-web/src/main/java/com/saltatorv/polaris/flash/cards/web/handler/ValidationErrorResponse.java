package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private static final String MESSAGE = "Validation error";
    private final List<ValidationError> errors;

    ValidationErrorResponse(String type, int status, String code, List<ValidationError> errors) {
        super(type, status, code, MESSAGE);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
