package com.saltatorv.polaris.flash.cards.application.review.exception;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ExceptionConfiguration;

public enum FlashcardReviewExceptionConfiguration implements ExceptionConfiguration {
    ;

    private final int statusCode;

    FlashcardReviewExceptionConfiguration(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
