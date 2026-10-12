package com.saltatorv.polaris.flash.cards.web.handler;

import static com.saltatorv.polaris.flash.cards.web.handler.ErrorCode.BUSINESS_RULE_VIOLATION_CODE;
import static com.saltatorv.polaris.flash.cards.web.handler.ErrorCode.VALIDATION_ERROR_CODE;

class ErrorType {
    private static final String CODE_PLACEHOLDER = "{CODE}";
    private static final String BUSINESS_STATUS_PLACEHOLDER = "{BUSINESS_STATUS}";

    private static final String BASE_ERROR_TYPE_URI_PATTERN = "urn:polaris-flash-cards:problem:%s".formatted(CODE_PLACEHOLDER);
    private static final String BUSINESS_ERROR_TYPE_URI_PATTERN = "urn:polaris-flash-cards:problem:%s:%s".formatted(CODE_PLACEHOLDER, BUSINESS_STATUS_PLACEHOLDER);

    private String code;

    private ErrorType() {
    }

    ErrorType(ErrorCode code) {
        this(BASE_ERROR_TYPE_URI_PATTERN.replace(CODE_PLACEHOLDER, code.getCode()));
    }

    ErrorType(ErrorCode code, String businessStatusCode) {
        this(BUSINESS_ERROR_TYPE_URI_PATTERN
                .replace(CODE_PLACEHOLDER, code.getCode())
                .replace(BUSINESS_STATUS_PLACEHOLDER, businessStatusCode));
    }

    ErrorType(String code) {
        this.code = code;
    }

    static final ErrorType createValidationTypeError() {
        return new ErrorType(VALIDATION_ERROR_CODE);
    }

    static final ErrorType createBusinessRuleViolationError(String businessStatusCode) {
        return new ErrorType(BUSINESS_RULE_VIOLATION_CODE, businessStatusCode);
    }

    String getType() {
        return code;
    }

    String getCode() {
        return code;
    }
}