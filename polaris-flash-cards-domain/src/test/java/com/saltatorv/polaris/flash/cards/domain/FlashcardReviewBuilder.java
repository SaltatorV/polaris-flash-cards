package com.saltatorv.polaris.flash.cards.domain;

import java.util.HashMap;

class FlashcardReviewBuilder {
    private HashMap<String, String> flashcards;

    private FlashcardReviewBuilder() {
        flashcards = new HashMap<>();
    }

    static FlashcardReviewBuilder buildFlashcardReview() {
        return new FlashcardReviewBuilder();
    }


    FlashcardReviewBuilder addFlashcard(String question, String answer) {
        flashcards.put(question, answer);
        return this;
    }

    FlashcardReview create() {
        return new FlashcardReview(flashcards);
    }
}
