package com.saltatorv.polaris.flash.cards.application.query.dto;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;
import java.util.Locale;

public class FlashcardBlueprintDataDto {
    private final FlashcardBlueprintId flashcardBlueprintId;
    private final String question;
    private final String definition;
    private final String source;
    private final List<String> tags;
    private final String language;

    public FlashcardBlueprintDataDto(FlashcardBlueprintId flashcardBlueprintId, String question,
                                     String definition, String source,
                                     List<String> tags, Locale language) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.question = question;
        this.definition = definition;
        this.source = source;
        this.tags = tags;
        this.language = language.getLanguage();
    }

    public FlashcardBlueprintId getFlashcardBlueprintId() {
        return flashcardBlueprintId;
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

    public String getLanguage() {
        return language;
    }
}
