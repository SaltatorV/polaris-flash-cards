package com.saltatorv.polaris.flash.cards.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

class FlashcardReview {
    private List<Flashcard> flashcards;
    private int currentFlashcardIndex;
    private long startTime;

    public FlashcardReview(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public void begin() {
        startTime = System.currentTimeMillis();
    }

    public LocalDateTime getStartDate() {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(startTime),
                java.time.ZoneId.systemDefault());
    }

    public int flashcardCount() {
        return flashcards.size();
    }

    public String next() {
        if (currentFlashcardIndex >= flashcardCount()) {
            throw new RuntimeException("No more questions left");
        }

        String question = flashcards
                .get(currentFlashcardIndex)
                .getQuestion();

        currentFlashcardIndex++;
        return question;
    }
}
