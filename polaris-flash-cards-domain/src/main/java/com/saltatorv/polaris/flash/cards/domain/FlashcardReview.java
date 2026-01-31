package com.saltatorv.polaris.flash.cards.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;

class FlashcardReview {
    private HashMap<String, String> flashcards;
    private long startTime;

    public FlashcardReview(HashMap<String, String> flashcards) {
        this.flashcards = flashcards;
    }

    public int flashcardCount() {
        return flashcards.size();
    }

    public void begin() {
        startTime = System.currentTimeMillis();
    }

    public LocalDateTime getStartDate() {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(startTime),
                java.time.ZoneId.systemDefault());
    }
}
