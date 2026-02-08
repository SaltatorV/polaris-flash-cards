package com.saltatorv.polaris.flash.cards.application.command.blueprint.dto;

import java.util.List;
import java.util.Locale;

public class FlashcardBlueprintDataDto {
    private final String question;
    private final String definition;
    private final String source;
    private final List<String> tags;
    private final Locale language;

    public FlashcardBlueprintDataDto(String question,
                                     String definition, String source,
                                     List<String> tags, String language) {
        this.question = question;
        this.definition = definition;
        this.source = source;
        this.tags = tags;
        this.language = Locale.forLanguageTag(language);
    }

    public String getQuestion() {
        return question;
    }

    public String getDefinition() {
        return definition;
    }

    public String getSource() {
        return source;
    }

    public List<String> getTags() {
        return tags;
    }

    public Locale getLanguage() {
        return language;
    }
}
