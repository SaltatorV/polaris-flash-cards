package com.saltatorv.polaris.flash.cards.domain.exception.blueprint;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardBlueprintLocalizationAlreadyExistsDomainException extends DomainException {
    private final static String MESSAGE = "Localization for language: %s already exist";

    public FlashcardBlueprintLocalizationAlreadyExistsDomainException(String languageCode) {
        super(MESSAGE.formatted(languageCode));
    }
}
