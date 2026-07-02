package com.saltatorv.polaris.flash.cards.application.blueprint.command.exception;

import com.saltatorv.polaris.flash.cards.application.shared.exception.ExceptionConfiguration;

public enum FlashcardBlueprintExceptionConfiguration implements ExceptionConfiguration {
    FLASHCARD_BLUEPRINT_HAS_NO_LOCALIZATION(409);

    private final int statusCode;

    FlashcardBlueprintExceptionConfiguration(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
