package com.saltatorv.polaris.flash.cards.domain;

class NoMoreQuestionsInFlashcardReviewDomainException extends DomainException {

    private final static String MESSAGE = "No more questions left";

    public NoMoreQuestionsInFlashcardReviewDomainException() {
        super(MESSAGE);
    }
}
