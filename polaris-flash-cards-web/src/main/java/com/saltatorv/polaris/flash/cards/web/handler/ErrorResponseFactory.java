package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

class ErrorResponseFactory {

    static ErrorResponse businessRuleViolation(String code, String message) {
        return new ErrorResponse(ErrorType.BUSINESS_RULE_VIOLATION, 422, code, message);
    }

    static ValidationErrorResponse businessRuleViolation(String code, String message, List<ValidationError> errors) {
        return new ValidationErrorResponse(ErrorType.VALIDATION_ERROR, 422, code, message, errors);
    }


}
