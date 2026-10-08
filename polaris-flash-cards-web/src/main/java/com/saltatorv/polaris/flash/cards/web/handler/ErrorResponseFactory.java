package com.saltatorv.polaris.flash.cards.web.handler;

import java.util.List;

public class ErrorResponseFactory {

    public static ErrorResponse businessRuleViolation(String code, String message) {
        return new ErrorResponse(ErrorType.createBusinessRuleViolationError(code), Integer.parseInt(code), message);
    }

    public static ValidationErrorResponse validationRuleViolation(List<ValidationError> errors) {
        return new ValidationErrorResponse(ErrorType.createValidationTypeError(), 422, errors);
    }


}
