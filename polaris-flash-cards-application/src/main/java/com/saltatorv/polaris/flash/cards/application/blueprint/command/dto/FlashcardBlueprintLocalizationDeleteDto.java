package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

import jakarta.validation.constraints.NotNull;

public class FlashcardBlueprintLocalizationDeleteDto {
    @NotNull(message = "Locale cannot be empty")
    private Locale locale;

    public FlashcardBlueprintLocalizationDeleteDto() {
    }

    public FlashcardBlueprintLocalizationDeleteDto(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
