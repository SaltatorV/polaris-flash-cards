package com.saltatorv.polaris.flash.cards.web.handler;

public class ErrorResponse {
    private ErrorType type;
    private int status;
    private String message;

    public ErrorResponse(ErrorType type, int status, String message) {
        this.type = type;
        this.status = status;
        this.message = message;
    }

    private ErrorResponse() {
    }

    public String getType() {
        return type.getType();
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return type.getCode();
    }

    public String getMessage() {
        return message;
    }
}
