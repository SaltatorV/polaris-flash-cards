package com.saltatorv.polaris.flash.cards.domain;

import java.util.HashMap;

class FlashcardReview {
    private HashMap<String, String> flashcards;

    public FlashcardReview(HashMap<String, String> flashcards) {
        this.flashcards = flashcards;
    }

    public int flashcardCount() {
        return flashcards.size();
    }
}
