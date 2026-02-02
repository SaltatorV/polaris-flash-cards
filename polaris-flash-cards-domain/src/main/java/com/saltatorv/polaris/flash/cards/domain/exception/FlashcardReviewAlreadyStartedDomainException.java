package com.saltatorv.polaris.flash.cards.domain.exception;

public class FlashcardReviewAlreadyStartedDomainException extends DomainException {

    private final static String MESSAGE = "Review already started";

    public FlashcardReviewAlreadyStartedDomainException() {
        super(MESSAGE);
    }
}
