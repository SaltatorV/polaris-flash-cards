package com.saltatorv.polaris.flash.cards.domain.exception;

public class AnyFlashcardReceivedFromReviewDomainException extends DomainException {

    private final static String MESSAGE = "Review already finished";

    public AnyFlashcardReceivedFromReviewDomainException() {
        super(MESSAGE);
    }

}