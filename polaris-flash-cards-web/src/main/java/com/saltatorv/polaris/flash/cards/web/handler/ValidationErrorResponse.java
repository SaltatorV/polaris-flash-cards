package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public record ValidationErrorResponse(int code, List<ValidationError> errors) {
    public static ValidationErrorResponse create(int code, List<ValidationError> errors) {
        return new ValidationErrorResponse(code, errors);
    }
}
