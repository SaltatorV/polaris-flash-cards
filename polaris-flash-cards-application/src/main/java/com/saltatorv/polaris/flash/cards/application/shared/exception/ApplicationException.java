package com.saltatorv.polaris.flash.cards.application.shared.exception;

public class ApplicationException extends RuntimeException {
    private final ExceptionConfiguration exceptionConfiguration;
    private final String message;

    public ApplicationException(ExceptionConfiguration exceptionConfiguration, String message) {
        this.exceptionConfiguration = exceptionConfiguration;
        this.message = message;
    }

    public int getHttpStatusCode() {
        return exceptionConfiguration.getStatusCode();
    }

    public String getErrorCode() {
        return exceptionConfiguration.name();
    }

    public String getMessage() {
        return message;
    }
}
