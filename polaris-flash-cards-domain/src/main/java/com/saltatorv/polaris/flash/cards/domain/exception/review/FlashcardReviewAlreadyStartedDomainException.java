package com.saltatorv.polaris.flash.cards.domain.exception.review;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardReviewAlreadyStartedDomainException extends DomainException {

    private final static String MESSAGE = "Review already started";

    public FlashcardReviewAlreadyStartedDomainException() {
        super(MESSAGE);
    }
}
