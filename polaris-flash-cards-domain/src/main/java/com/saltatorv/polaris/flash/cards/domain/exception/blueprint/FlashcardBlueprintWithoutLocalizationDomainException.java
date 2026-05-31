package com.saltatorv.polaris.flash.cards.domain.exception.blueprint;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardBlueprintWithoutLocalizationDomainException extends DomainException {
    private final static String MESSAGE = "Flashcard review blueprint must contain at least one localization";

    public FlashcardBlueprintWithoutLocalizationDomainException() {
        super(MESSAGE);
    }
}
