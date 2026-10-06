package com.saltatorv.polaris.flash.cards.web.handler;

public record ErrorResponse(String type, int status, String code, String message) {
    public static ErrorResponse validationError(String code, String message) {
        return new ErrorResponse(ErrorType.VALIDATION_ERROR, 422, code, message);
    }

    public static ErrorResponse businessRuleViolation(String code, String message) {
        return new ErrorResponse(ErrorType.BUSINESS_RULE_VIOLATION, 422, code, message);
    }
}
