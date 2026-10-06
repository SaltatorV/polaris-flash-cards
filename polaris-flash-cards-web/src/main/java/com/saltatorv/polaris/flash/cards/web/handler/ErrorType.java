package com.saltatorv.polaris.flash.cards.web.handler;

public class ErrorType {

    private static final String URI_PREFIX = "urn:polaris-flash-cards:problem:%s";
    private static final String VALIDATION = "validation-error";
    private static final String BUSINESS = "business-rule-violation";

    public static final String VALIDATION_ERROR = String.format(URI_PREFIX, VALIDATION);
    public static final String BUSINESS_RULE_VIOLATION = String.format(URI_PREFIX, BUSINESS);
}
