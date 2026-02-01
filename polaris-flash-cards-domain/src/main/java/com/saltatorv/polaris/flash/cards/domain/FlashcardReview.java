package com.saltatorv.polaris.flash.cards.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

class FlashcardReview {
    private List<Flashcard> flashcards;
    private int currentFlashcardIndex;
    private long startTime;
    private long finishTime;

    FlashcardReview(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
        startTime = 0;
        finishTime = 0;
    }

    void begin() {
        ensureReviewIsNotAlreadyStarted();

        startTime = System.currentTimeMillis();
    }

    void finish() {
        ensureReviewIsStarted();
        ensureReviewIsNotFinished();

        finishTime = System.currentTimeMillis();
    }

    LocalDateTime getStartDate() {
        return calculateDate(startTime);
    }

    LocalDateTime getFinishDate() {
        return calculateDate(finishTime);
    }

    int flashcardCount() {
        return flashcards.size();
    }

    String next() {
        ensureReviewIsStarted();
        ensureReviewIsNotFinished();
        ensureThereAreFlashcardsLeft();

        String question = flashcards
                .get(currentFlashcardIndex)
                .getQuestion();

        currentFlashcardIndex++;
        return question;
    }

    void markFlashcardAsCorrect() {
        flashcards.get(currentFlashcardIndex).markAsSuccess();
    }

    void markFlashcardAsIncorrect() {
        flashcards.get(currentFlashcardIndex).markAsFailure();
    }

    int getCorrectAnswers() {
        int correctAnswers = 0;
        for (int i = 0; i < currentFlashcardIndex; i++) {
            if (flashcards.get(i).isSuccessfulAnswer()) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    int getIncorrectAnswers() {
        int answeredQuestions = Math.max(0, currentFlashcardIndex - 1);

        return answeredQuestions - getCorrectAnswers();
    }

    private LocalDateTime calculateDate(Long timeInMilliseconds) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timeInMilliseconds),
                java.time.ZoneId.systemDefault());
    }

    private void ensureReviewIsNotAlreadyStarted() {
        if (startTime != 0) {
            throw new RuntimeException("Review already started");
        }
    }

    private void ensureReviewIsStarted() {
        if (startTime == 0) {
            throw new RuntimeException("Review not started");
        }
    }

    private void ensureReviewIsNotFinished() {
        if (finishTime != 0) {
            throw new RuntimeException("Review already finished");
        }
    }

    private void ensureThereAreFlashcardsLeft() {
        if (currentFlashcardIndex >= flashcardCount()) {
            throw new RuntimeException("No more questions left");
        }
    }
}
