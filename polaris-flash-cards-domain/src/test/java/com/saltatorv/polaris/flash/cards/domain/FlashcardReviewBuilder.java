package com.saltatorv.polaris.flash.cards.domain;

import java.util.ArrayList;
import java.util.List;

class FlashcardReviewBuilder {
    private List<FlashcardBlueprint> flashcardBlueprints;

    private FlashcardReviewBuilder() {
        flashcardBlueprints = new ArrayList<>();
    }

    static FlashcardReviewBuilder buildFlashcardReview() {
        return new FlashcardReviewBuilder();
    }

    FlashcardReviewBuilder addFlashcard(String question, String answer) {
        flashcardBlueprints.add(new FlashcardBlueprint(question, answer,
                new FlashcardMetadata("source", List.of("tag"), java.util.Locale.ENGLISH)));
        return this;
    }

    FlashcardReview create() {
        return new FlashcardReview(flashcardBlueprints);
    }
}
