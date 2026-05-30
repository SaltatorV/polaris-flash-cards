package com.saltatorv.polaris.flash.cards.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class FlashcardReviewBuilder {
    private List<FlashcardBlueprint> flashcardBlueprints;

    private FlashcardReviewBuilder() {
        flashcardBlueprints = new ArrayList<>();
    }

    static FlashcardReviewBuilder buildFlashcardReview() {
        return new FlashcardReviewBuilder();
    }

    FlashcardReviewBuilder addFlashcard(String question, String answer) {

        List<FlashcardLocalization> localizations = new ArrayList<>();
        localizations.add(new FlashcardLocalization(Locale.of("EN"), question, answer));

        flashcardBlueprints.add(new FlashcardBlueprint(localizations,
                new FlashcardMetadata("source", Set.of("tag"))));
        return this;
    }

    FlashcardReview create() {
        return new FlashcardReview(flashcardBlueprints);
    }
}
