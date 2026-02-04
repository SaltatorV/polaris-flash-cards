package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyFinishedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewAlreadyStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.FlashcardReviewNotStartedDomainException;
import com.saltatorv.polaris.flash.cards.domain.exception.NoMoreQuestionsInFlashcardReviewDomainException;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

class FlashcardReview {
    private final FlashcardReviewId id;
    private final List<Flashcard> flashcards;

    private int currentFlashcardIndex;
    private long startTime;
    private long finishTime;

    FlashcardReview(List<FlashcardBlueprint> flashcardBlueprints) {
        this.id = FlashcardReviewId.generate();

        this.flashcards = flashcardBlueprints
                .stream()
                .map(FlashcardBlueprint::createFlashcard)
                .toList();
        this.currentFlashcardIndex = 0;
        this.startTime = 0;
        this.finishTime = 0;
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
            throw new FlashcardReviewAlreadyStartedDomainException();
        }
    }

    private void ensureReviewIsStarted() {
        if (startTime == 0) {
            throw new FlashcardReviewNotStartedDomainException();
        }
    }

    private void ensureReviewIsNotFinished() {
        if (finishTime != 0) {
            throw new FlashcardReviewAlreadyFinishedDomainException();
        }
    }

    private void ensureThereAreFlashcardsLeft() {
        if (currentFlashcardIndex >= flashcardCount()) {
            throw new NoMoreQuestionsInFlashcardReviewDomainException();
        }
    }
}
