package com.saltatorv.polaris.flash.cards.web.handler;

public class ErrorType {
    private String type;
    private String code;

    ErrorType(String type, String code) {
        this.type = type;
        this.code = code;
    }

    String getType() {
        return type;
    }

    String getCode() {
        return code;
    }
}