package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.List;

public class FlashcardBlueprintSnapshot {
    private final FlashcardBlueprintId flashcardBlueprintId;
    private final String question;
    private final String definition;
    private final String source;
    private final List<String> tags;
    private final String language;

    public FlashcardBlueprintSnapshot(FlashcardBlueprintId flashcardBlueprintId, String question, String definition, String source, List<String> tags, String language) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.question = question;
        this.definition = definition;
        this.source = source;
        this.tags = tags;
        this.language = language;
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
