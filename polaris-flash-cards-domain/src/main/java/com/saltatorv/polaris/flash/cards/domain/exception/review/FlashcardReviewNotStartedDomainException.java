package com.saltatorv.polaris.flash.cards.domain.exception.review;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardReviewNotStartedDomainException extends DomainException {

    private final static String MESSAGE = "Review not started";

    public FlashcardReviewNotStartedDomainException() {
        super(MESSAGE);
    }
}
