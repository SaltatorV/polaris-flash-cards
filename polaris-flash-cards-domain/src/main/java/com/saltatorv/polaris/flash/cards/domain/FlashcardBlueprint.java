package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardBlueprintId;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

@Generated
public class FlashcardBlueprint {
    private final FlashcardBlueprintId reviewId;
    private final String question;
    private final String answer;
    private final FlashcardMetadata metadata;

    public FlashcardBlueprint(String question, String answer, FlashcardMetadata metadata) {
        this.reviewId = FlashcardBlueprintId.generate();
        this.question = question;
        this.answer = answer;
        this.metadata = metadata;
    }

    public Flashcard createFlashcard() {
        return new Flashcard(question, answer);
    }

    public FlashcardMetadata getMetadata() {
        return metadata;
    }

    public FlashcardBlueprintId getReviewId() {
        return reviewId;
    }
}
