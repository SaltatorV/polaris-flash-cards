package com.saltatorv.polaris.flash.cards.domain;

class FlashcardReviewNotStartedDomainException extends DomainException {

    private final static String MESSAGE = "Review not started";

    public FlashcardReviewNotStartedDomainException() {
        super(MESSAGE);
    }
}
