package com.saltatorv.polaris.flash.cards.domain;

import java.util.ArrayList;
import java.util.List;

class FlashcardReviewBuilder {
    private List<Flashcard> flashcards;

    private FlashcardReviewBuilder() {
        flashcards = new ArrayList<>();
    }

    static FlashcardReviewBuilder buildFlashcardReview() {
        return new FlashcardReviewBuilder();
    }

    FlashcardReviewBuilder addFlashcard(String question, String answer) {
        flashcards.add(new Flashcard(question, answer));
        return this;
    }

    FlashcardReview create() {
        return new FlashcardReview(flashcards);
    }
}
