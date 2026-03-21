package com.saltatorv.polaris.flash.cards.web.handler;

interface ExceptionHandler<T> {
    ErrorResponse handleException(T exception);
}
