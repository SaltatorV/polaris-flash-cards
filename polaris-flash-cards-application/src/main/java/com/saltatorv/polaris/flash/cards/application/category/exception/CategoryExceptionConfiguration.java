package com.saltatorv.polaris.flash.cards.application.category.exception;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ExceptionConfiguration;

public enum CategoryExceptionConfiguration implements ExceptionConfiguration {
    ;

    private final int statusCode;

    CategoryExceptionConfiguration(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
