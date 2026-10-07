package com.saltatorv.polaris.flash.cards.web.handler;

enum ErrorCode {
    VALIDATION_ERROR_CODE("validation-error"),
    BUSINESS_RULE_VIOLATION_CODE("business-rule-violation");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }
}
