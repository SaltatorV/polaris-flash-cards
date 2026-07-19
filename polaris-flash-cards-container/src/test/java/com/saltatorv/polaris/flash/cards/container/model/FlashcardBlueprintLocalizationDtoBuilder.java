package com.saltatorv.polaris.flash.cards.container.model;

import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.FlashcardLocalizationCreateDto;
import com.saltatorv.polaris.flash.cards.application.blueprint.command.dto.Locale;

public class FlashcardBlueprintLocalizationDtoBuilder {
    private Locale locale;
    private String question;
    private String answer;

    private FlashcardBlueprintCreationDtoBuilder parent;

    public FlashcardBlueprintLocalizationDtoBuilder(FlashcardBlueprintCreationDtoBuilder parent) {
        this.parent = parent;
    }

    public FlashcardBlueprintLocalizationDtoBuilder withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public FlashcardBlueprintLocalizationDtoBuilder withQuestion(String question) {
        this.question = question;
        return this;
    }

    public FlashcardBlueprintLocalizationDtoBuilder withAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public FlashcardBlueprintCreationDtoBuilder createLocalization() {
        parent.addLocalization(new FlashcardLocalizationCreateDto(locale, question, answer));
        return parent;
    }

}
