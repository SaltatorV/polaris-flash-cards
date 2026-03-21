package com.saltatorv.polaris.flash.cards.web.handler;

public record ErrorResponse(int code, String message) {
    public static ErrorResponse create(int code, String message) {
        return new ErrorResponse(code, message);
    }
}
