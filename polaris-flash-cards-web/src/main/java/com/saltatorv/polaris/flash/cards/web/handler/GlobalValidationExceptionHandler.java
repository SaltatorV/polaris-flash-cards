package com.saltatorv.polaris.flash.cards.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

@RestControllerAdvice
class GlobalValidationExceptionHandler {
    private static final String UNKNOWN_FIELD_TYPE = "UNKNOWN_FIELD";
    private static final String UNKNOWN_ERROR_CODE = "UNKNOWN_CODE";

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            HandlerMethodValidationException exception) {

        List<ValidationError> errors = exception.getAllErrors().stream()
                .map(error -> (FieldError) error)
                .map(this::mapValidationError)
                .toList();

        ValidationErrorResponse errorResponse = ErrorResponseFactory.validationRuleViolation(errors);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(errorResponse);
    }

    private ValidationError mapValidationError(FieldError error) {
        return ValidationError.create(error.getField(), error.getCode(), error.getDefaultMessage());
    }
}
