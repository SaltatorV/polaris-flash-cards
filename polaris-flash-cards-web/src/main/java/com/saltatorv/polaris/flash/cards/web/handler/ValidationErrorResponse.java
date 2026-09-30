package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public record ValidationErrorResponse(String code, List<String> errors) {
    public static ValidationErrorResponse create(String code, List<String> errors) {
        return new ValidationErrorResponse(code, errors);
    }
}
