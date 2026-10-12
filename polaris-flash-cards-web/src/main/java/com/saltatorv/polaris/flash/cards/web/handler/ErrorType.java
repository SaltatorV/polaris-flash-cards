package com.saltatorv.polaris.flash.cards.web.handler;

public class ErrorType {
    private final String code;

    ErrorType(String code) {
        this.code = code;
    }

    String getType() {
        return code;
    }

    String getCode() {
        return code;
    }
}