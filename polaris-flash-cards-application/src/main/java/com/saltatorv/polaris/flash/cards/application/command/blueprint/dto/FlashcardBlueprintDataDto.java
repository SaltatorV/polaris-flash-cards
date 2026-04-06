package com.saltatorv.polaris.flash.cards.application.command.blueprint.dto;

import java.util.List;
import java.util.Locale;

public class FlashcardBlueprintDataDto {
    private String question;
    private String definition;
    private String source;
    private List<String> tags;
    private String language;

    public FlashcardBlueprintDataDto(String question,
                                     String definition, String source,
                                     List<String> tags, String language) {
        this.question = question;
        this.definition = definition;
        this.source = source;
        this.tags = tags;
        this.language = language;
    }

    public FlashcardBlueprintDataDto() {
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
        return Locale.forLanguageTag(language);
    }
}
