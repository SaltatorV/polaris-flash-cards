package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private List<ValidationError> errors;

    ValidationErrorResponse(String type, int status, String code, String message, List<ValidationError> errors) {
        super(type, status, code, message);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
