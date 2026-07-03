package com.saltatorv.polaris.flash.cards.application.category.exception;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ExceptionConfiguration;

public enum CategoryExceptionConfiguration implements ExceptionConfiguration {
    CATEGORY_NOT_FOUND(404),
    CATEGORY_DUPLICATED(409);

    private final int statusCode;

    CategoryExceptionConfiguration(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
