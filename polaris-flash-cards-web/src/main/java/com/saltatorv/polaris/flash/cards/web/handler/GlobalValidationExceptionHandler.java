package com.saltatorv.polaris.flash.cards.web.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
class GlobalValidationExceptionHandler {
    private static final String UNKNOWN_FIELD_TYPE = "UNKNOWN_FIELD";
    private static final String UNKNOWN_ERROR_CODE = "UNKNOWN_CODE";

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            HandlerMethodValidationException exception) {

        List<ValidationError> errors = exception.getAllErrors().stream()
                .map(error -> {
                    String field = error instanceof FieldError fieldError ? fieldError.getField() : UNKNOWN_FIELD_TYPE;
                    String[] errorCodes = error.getCodes();
                    String code = Objects.requireNonNull(errorCodes).length > 0 ? errorCodes[errorCodes.length - 1] : UNKNOWN_ERROR_CODE;
                    return ValidationError.create(field, code, error.getDefaultMessage());
                })
                .toList();

        ValidationErrorResponse errorResponse = new ValidationErrorResponse("400", errors);
        return ResponseEntity
                .status(400)
                .body(errorResponse);
    }
}
