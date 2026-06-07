package com.saltatorv.polaris.flash.cards.domain.exception.blueprint;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardContentCannotBeEmptyDomainException extends DomainException {
    private final static String MESSAGE = "Question and answer must be not null";

    public FlashcardContentCannotBeEmptyDomainException() {
        super(MESSAGE);
    }
}
