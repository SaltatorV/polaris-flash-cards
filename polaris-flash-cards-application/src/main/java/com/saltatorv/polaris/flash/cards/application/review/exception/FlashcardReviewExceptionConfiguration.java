package com.saltatorv.polaris.flash.cards.application.review.exception;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ExceptionConfiguration;

public enum FlashcardReviewExceptionConfiguration implements ExceptionConfiguration {
    NO_FLASHCARD_RECEIVED(409),
    REVIEW_ALREADY_STARTED(409),
    REVIEW_ALREADY_FINISHED(409),
    NO_MORE_QUESTIONS_IN_REVIEW (409);

    private final int statusCode;

    FlashcardReviewExceptionConfiguration(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
