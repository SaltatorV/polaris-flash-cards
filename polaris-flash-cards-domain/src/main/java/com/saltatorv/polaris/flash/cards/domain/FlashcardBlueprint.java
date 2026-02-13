package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;

import java.util.Locale;
import java.util.UUID;

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

    private FlashcardBlueprint(FlashcardBlueprintId flashcardBlueprintId, String question, String answer, FlashcardMetadata metadata) {
        this.flashcardBlueprintId = flashcardBlueprintId;
        this.question = question;
        this.answer = answer;
        this.metadata = metadata;
    }

    public static FlashcardBlueprint restore(FlashcardBlueprintSnapshot snapshot) {

        FlashcardMetadata flashcardMetadata = new FlashcardMetadata(snapshot.getSource(),
                snapshot.getTags(),
                Locale.forLanguageTag(snapshot.getLanguage()));

        return new FlashcardBlueprint(new FlashcardBlueprintId(UUID.fromString(snapshot.getFlashcardBlueprintId())),
                snapshot.getQuestion(),
                snapshot.getDefinition(),
                flashcardMetadata);
    }

    public FlashcardBlueprintSnapshot generateSnapshot() {
        return new FlashcardBlueprintSnapshot(flashcardBlueprintId.getId(), question, answer, metadata.getSource(), metadata.getTags(), metadata.getLanguage());
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
