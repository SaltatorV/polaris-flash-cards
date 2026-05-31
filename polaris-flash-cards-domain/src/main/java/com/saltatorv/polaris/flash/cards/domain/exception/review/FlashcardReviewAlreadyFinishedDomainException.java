package com.saltatorv.polaris.flash.cards.domain.exception.review;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardReviewAlreadyFinishedDomainException extends DomainException {

    private final static String MESSAGE = "Review already finished";

    public FlashcardReviewAlreadyFinishedDomainException() {
        super(MESSAGE);
    }
}
