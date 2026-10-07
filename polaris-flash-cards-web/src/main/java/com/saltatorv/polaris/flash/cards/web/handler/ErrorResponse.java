package com.saltatorv.polaris.flash.cards.web.handler;

public class ErrorResponse {
    private final String type;
    private final int status;
    private final String code;
    private final String message;

    ErrorResponse(String type, int status, String code, String message) {
        this.type = type;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse validationError(String code, String message) {
        return new ErrorResponse(ErrorType.VALIDATION_ERROR, 422, code, message);
    }

    public static ErrorResponse businessRuleViolation(String code, String message) {
        return new ErrorResponse(ErrorType.BUSINESS_RULE_VIOLATION, 422, code, message);
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
