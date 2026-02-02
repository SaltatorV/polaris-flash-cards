package com.saltatorv.polaris.flash.cards.domain.exception;

public class NoMoreQuestionsInFlashcardReviewDomainException extends DomainException {

    private final static String MESSAGE = "No more questions left";

    public NoMoreQuestionsInFlashcardReviewDomainException() {
        super(MESSAGE);
    }
}
