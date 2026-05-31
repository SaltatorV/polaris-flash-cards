package com.saltatorv.polaris.flash.cards.domain.exception.review;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class NoMoreQuestionsInFlashcardReviewDomainException extends DomainException {

    private final static String MESSAGE = "No more questions left";

    public NoMoreQuestionsInFlashcardReviewDomainException() {
        super(MESSAGE);
    }
}
