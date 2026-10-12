package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public class ErrorResponseFactory {

    public static ErrorResponse businessRuleViolation(String code, int statusCode, String message) {
        return new ErrorResponse(ErrorTypeFactory.businessErrorType(code), statusCode, message);
    }

    public static ValidationErrorResponse validationRuleViolation(List<ValidationError> errors) {
        return new ValidationErrorResponse(ErrorTypeFactory.validationErrorType(), errors);
    }


}
