package com.saltatorv.polaris.flash.cards.application.blueprint.command.dto;

public class FlashcardBlueprintLocalizationDeleteDto {

    private String locale;

    public FlashcardBlueprintLocalizationDeleteDto() {
    }


    public FlashcardBlueprintLocalizationDeleteDto(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}
