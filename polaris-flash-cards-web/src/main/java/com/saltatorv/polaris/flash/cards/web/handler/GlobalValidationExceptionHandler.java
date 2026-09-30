package com.saltatorv.polaris.flash.cards.web.handler;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
class GlobalValidationExceptionHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorListResponse> handleValidation(
            HandlerMethodValidationException exception) {

        List<String> errors = exception.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorListResponse errorResponse = new ErrorListResponse("400", errors);
        return ResponseEntity
                .status(400)
                .body(errorResponse);
    }
}
