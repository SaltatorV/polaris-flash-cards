package com.saltatorv.polaris.flash.cards.web.handler;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalApplicationExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handlerApplicationException(ApplicationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage());

        return ResponseEntity
                .status(ex.getHttpStatusCode())
                .body(errorResponse);
    }
}
