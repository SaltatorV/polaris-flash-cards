package com.saltatorv.polaris.flash.cards.web.handler;

public record ValidationError(String field, String errorCode, String message) {
    public static ValidationError create(String field, String errorCode, String message) {
        return new ValidationError(field, errorCode, message);
    }
}