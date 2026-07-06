package com.saltatorv.polaris.flash.cards.web.handler;

public record ErrorResponse(String code, String message) {
    public static ErrorResponse create(String code, String message) {
        return new ErrorResponse(code, message);
    }
}
