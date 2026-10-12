package com.saltatorv.polaris.flash.cards.web.handler;

public class ErrorTypeFactory {
    private static final String CODE_PLACEHOLDER = "{CODE}";
    private static final String BUSINESS_STATUS_PLACEHOLDER = "{BUSINESS_STATUS}";

    private static final String BASE_ERROR_TYPE_URI_PATTERN = "urn:polaris-flash-cards:problem:%s".formatted(CODE_PLACEHOLDER);
    private static final String BUSINESS_ERROR_TYPE_URI_PATTERN = "urn:polaris-flash-cards:problem:%s:%s".formatted(CODE_PLACEHOLDER, BUSINESS_STATUS_PLACEHOLDER);

    public static ErrorType businessErrorType(String businessStatusCode) {
        return new ErrorType(BUSINESS_ERROR_TYPE_URI_PATTERN
                .replace(CODE_PLACEHOLDER, ErrorCode.BUSINESS_RULE_VIOLATION_CODE.getCode())
                .replace(BUSINESS_STATUS_PLACEHOLDER, businessStatusCode));
    }

    public static ErrorType validationErrorType() {
        return new ErrorType(BASE_ERROR_TYPE_URI_PATTERN.replace(CODE_PLACEHOLDER, ErrorCode.VALIDATION_ERROR_CODE.getCode()));
    }
}
