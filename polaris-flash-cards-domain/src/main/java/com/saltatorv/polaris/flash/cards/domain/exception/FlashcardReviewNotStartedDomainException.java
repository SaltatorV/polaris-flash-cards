package com.saltatorv.polaris.flash.cards.domain.exception;

public class FlashcardReviewNotStartedDomainException extends DomainException {

    private final static String MESSAGE = "Review not started";

    public FlashcardReviewNotStartedDomainException() {
        super(MESSAGE);
    }
}
