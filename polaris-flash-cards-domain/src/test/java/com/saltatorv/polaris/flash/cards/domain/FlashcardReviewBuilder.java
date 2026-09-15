package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.shared.CategoryId;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class FlashcardReviewBuilder {
    private List<FlashcardBlueprint> flashcardBlueprints;

    private FlashcardReviewBuilder() {
        flashcardBlueprints = new ArrayList<>();
    }

    public static FlashcardReviewBuilder buildFlashcardReview() {
        return new FlashcardReviewBuilder();
    }

    public FlashcardReviewBuilder addFlashcard(String question, String answer) {

        List<FlashcardLocalization> localizations = new ArrayList<>();
        localizations.add(new FlashcardLocalization(Locale.of("EN"),
                new FlashcardContent(question, answer)));

        flashcardBlueprints.add(new FlashcardBlueprint(CategoryId.generate(), localizations,
                new FlashcardMetadata("source", Set.of("tag"))));
        return this;
    }

    public FlashcardReview create() {
        return new FlashcardReview(flashcardBlueprints);
    }
}
