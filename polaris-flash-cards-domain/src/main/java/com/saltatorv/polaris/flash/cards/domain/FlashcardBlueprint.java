package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

@Generated
public class FlashcardBlueprint {
    private final FlashcardBlueprintId flashcardBlueprintId;
    private final String question;
    private final String answer;
    private final FlashcardMetadata metadata;

    public FlashcardBlueprint(String question, String answer, FlashcardMetadata metadata) {
        this.flashcardBlueprintId = FlashcardBlueprintId.generate();
        this.question = question;
        this.answer = answer;
        this.metadata = metadata;
    }

    public FlashcardBlueprintSnapshot generate() {
        return new FlashcardBlueprintSnapshot(flashcardBlueprintId, question, answer, metadata.getSource(), metadata.getTags(), metadata.getLanguage());
    }

    public Flashcard createFlashcard() {
        return new Flashcard(flashcardBlueprintId.getId(), question, answer);
    }

    public FlashcardMetadata getMetadata() {
        return metadata;
    }

    public FlashcardBlueprintId getFlashcardBlueprintId() {
        return flashcardBlueprintId;
    }
}
