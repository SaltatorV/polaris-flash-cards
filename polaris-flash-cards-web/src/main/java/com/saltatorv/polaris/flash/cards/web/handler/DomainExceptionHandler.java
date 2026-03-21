package com.saltatorv.polaris.flash.cards.web.handler;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class DomainExceptionHandler implements ExceptionHandler<DomainException> {

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    @org.springframework.web.bind.annotation.ExceptionHandler(DomainException.class)
    public ErrorResponse handleException(DomainException exception) {
        return ErrorResponse.create(HttpStatus.UNPROCESSABLE_CONTENT.value(),
                exception.getMessage());
    }
}
