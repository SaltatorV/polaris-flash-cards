package com.saltatorv.polaris.flash.cards.web.handler;

import static com.saltatorv.polaris.flash.cards.web.handler.ErrorCode.BUSINESS_RULE_VIOLATION_CODE;
import static com.saltatorv.polaris.flash.cards.web.handler.ErrorCode.VALIDATION_ERROR_CODE;

class ErrorType {

    private static final String ERROR_TYPE_URI_PATTERN = "urn:polaris-flash-cards:problem:%s";

    private final String code;

    ErrorType(ErrorCode code) {
        this.code = code.getCode();
    }

    ErrorType(ErrorCode code, String businessViolationCode) {
        this.code = code.getCode() + ":" + businessViolationCode;
    }

    static final ErrorType createValidationTypeError() {
        return new ErrorType(VALIDATION_ERROR_CODE);
    }

    static final ErrorType createBusinessRuleViolationError(String businessViolationCode) {
        return new ErrorType(BUSINESS_RULE_VIOLATION_CODE, businessViolationCode);
    }

    String getType() {
        return ERROR_TYPE_URI_PATTERN.formatted(code);
    }

    String getCode() {
        return code;
    }
}