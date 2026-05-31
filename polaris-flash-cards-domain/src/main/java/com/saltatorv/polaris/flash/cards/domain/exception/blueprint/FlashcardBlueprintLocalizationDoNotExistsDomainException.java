package com.saltatorv.polaris.flash.cards.domain.exception.blueprint;

import com.saltatorv.polaris.flash.cards.domain.exception.DomainException;

public class FlashcardBlueprintLocalizationDoNotExistsDomainException extends DomainException {
    private final static String MESSAGE = "Localization for language: %s does not exist";

    public FlashcardBlueprintLocalizationDoNotExistsDomainException(String languageCode) {
        super(MESSAGE.formatted(languageCode));
    }
}
