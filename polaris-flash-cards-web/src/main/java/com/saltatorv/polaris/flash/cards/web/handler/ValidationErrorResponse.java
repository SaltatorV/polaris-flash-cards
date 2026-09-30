package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public record ValidationErrorResponse(String code, List<ValidationError> errors) {
    public static ValidationErrorResponse create(String code, List<ValidationError> errors) {
        return new ValidationErrorResponse(code, errors);
    }
}
