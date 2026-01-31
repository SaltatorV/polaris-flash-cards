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
        startTime = 0;
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
        if (startTime == 0) {
            throw new RuntimeException("Review not started");
        }

        if (currentFlashcardIndex >= flashcardCount()) {
            throw new RuntimeException("No more questions left");
        }

        String question = flashcards
                .get(currentFlashcardIndex)
                .getQuestion();

        currentFlashcardIndex++;
        return question;
    }

    public void markFlashcardAsCorrect() {
        flashcards.get(currentFlashcardIndex).markAsSuccess();
    }

    public void markFlashcardAsIncorrect() {
        flashcards.get(currentFlashcardIndex).markAsFailure();
    }

    public int getCorrectAnswers() {
        int correctAnswers = 0;
        for (int i = 0; i < currentFlashcardIndex; i++) {
            if (flashcards.get(i).isSuccessfulAnswer()) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        int answeredQuestions = Math.max(0, currentFlashcardIndex - 1);

        return answeredQuestions - getCorrectAnswers();
    }
}
