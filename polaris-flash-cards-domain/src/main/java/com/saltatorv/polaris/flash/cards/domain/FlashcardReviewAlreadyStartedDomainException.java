package com.saltatorv.polaris.flash.cards.domain;

class FlashcardReviewAlreadyStartedDomainException extends DomainException {

    private final static String MESSAGE = "Review already started";

    public FlashcardReviewAlreadyStartedDomainException() {
        super(MESSAGE);
    }
}
