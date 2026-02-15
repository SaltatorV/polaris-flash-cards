package com.saltatorv.polaris.flash.cards.domain.exception;

public class NoFlashcardReceivedFromReviewDomainException extends DomainException {

    private final static String MESSAGE = "No flashcards were returned from the review process.";

    public NoFlashcardReceivedFromReviewDomainException() {
        super(MESSAGE);
    }

}