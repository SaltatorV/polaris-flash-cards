package com.saltatorv.polaris.flash.cards.application.shared.exception;

public class ApplicationException {
    private final String httpStatusCode;
    private final String errorCode;
    private final String message;

    public ApplicationException(String httpStatusCode, String errorCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
