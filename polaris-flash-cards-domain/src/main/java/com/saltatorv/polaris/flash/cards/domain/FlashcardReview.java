package com.saltatorv.polaris.flash.cards.domain;

import com.saltatorv.polaris.flash.cards.domain.exception.*;
import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlashcardReview {
    private final FlashcardReviewId id;
    private final List<Flashcard> flashcards;

    private int currentFlashcardIndex;
    private long startTime;
    private long finishTime;

    public FlashcardReview(List<FlashcardBlueprint> flashcardBlueprints) {
        this.id = FlashcardReviewId.generate();

        this.flashcards = flashcardBlueprints
                .stream()
                .map(FlashcardBlueprint::createFlashcard)
                .toList();
        this.currentFlashcardIndex = 0;
        this.startTime = 0;
        this.finishTime = 0;
    }

    private FlashcardReview(FlashcardReviewId flashcardReviewId, List<Flashcard> flashcards,
                            long startDate, long finishDate) {

        this.id = flashcardReviewId;
        this.flashcards = flashcards;
        this.startTime = startDate;
        this.finishTime = finishDate;

        this.currentFlashcardIndex = 0;

        for (Flashcard flashcard : flashcards) {
            if (!flashcard.isNotAnswered()) {
                currentFlashcardIndex++;
            }
        }
    }


    public static FlashcardReview restore(FlashcardReviewSnapshot reviewSnapshot) {
        List<FlashcardSnapshot> flashcardSnapshots = reviewSnapshot.getFlashcardSnapshots();

        List<Flashcard> flashcards = flashcardSnapshots
                .stream()
                .map(Flashcard::restore)
                .toList();

        FlashcardReviewId reviewId = new FlashcardReviewId
                (reviewSnapshot.getFlashcardReviewId());

        long startTime = reviewSnapshot.getStartDate().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
        long finishTime = reviewSnapshot.getFinishDate().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();

        return new FlashcardReview(reviewId,
                flashcards, startTime,
                finishTime);
    }

    public FlashcardReviewSnapshot generateSnapshot() {
        List<FlashcardSnapshot> flashcardSnapshots = new ArrayList<>();

        for (Flashcard flashcard : flashcards) {
            flashcardSnapshots.add(flashcard.generateSnapshot());
        }

        return new FlashcardReviewSnapshot(id.getId(), getStartDate(), getFinishDate(), flashcardSnapshots);
    }

    public void begin() {
        ensureReviewIsNotFinished();
        ensureReviewIsNotAlreadyStarted();

        startTime = System.currentTimeMillis();
    }

    public void finish() {
        ensureReviewIsStarted();
        ensureReviewIsNotFinished();

        for (Flashcard flashcard : flashcards) {
            if(!flashcard.isCorrectAnswer()) {
                flashcard.markAsFailure();
            }
        }
        finishTime = System.currentTimeMillis();
    }

    public Flashcard next() {
        ensureReviewIsStarted();
        ensureReviewIsNotFinished();
        ensureThereAreFlashcardsLeft();
        markPreviousFlashcardAsIncorrectIfNotAnswered();

        Flashcard flashcard = flashcards
                .get(currentFlashcardIndex);

        flashcard.markAsReviewed();

        currentFlashcardIndex++;
        return flashcard;
    }

    public void markFlashcardAsCorrect() {
        ensureAtLeastOneFlashcardWasReceived();
        flashcards.get(currentFlashcardIndex-1).markAsSuccess();
    }

    public void markFlashcardAsIncorrect() {
        ensureAtLeastOneFlashcardWasReceived();
        flashcards.get(currentFlashcardIndex-1).markAsFailure();
    }

    public int getCorrectAnswers() {
        int correctAnswers = 0;
        for (int i = 0; i < currentFlashcardIndex; i++) {
            if (flashcards.get(i).isCorrectAnswer()) {
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        int incorrectAnswers = 0;
        for (int i = 0; i < currentFlashcardIndex; i++) {
            if (flashcards.get(i).isIncorrectAnswer()) {
                incorrectAnswers++;
            }
        }

        return incorrectAnswers;
    }

    public LocalDateTime getStartDate() {
        return calculateDate(startTime);
    }

    public LocalDateTime getFinishDate() {
        return calculateDate(finishTime);
    }

    public int flashcardCount() {
        return flashcards.size();
    }

    public FlashcardReviewId getId() {
        return id;
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

    private void ensureAtLeastOneFlashcardWasReceived() {
        if(currentFlashcardIndex == 0) {
            throw new NoFlashcardReceivedFromReviewDomainException();
        }
    }

    private void markPreviousFlashcardAsIncorrectIfNotAnswered() {
        if (currentFlashcardIndex > 0) {
            Flashcard previousFlashcard = flashcards.get(currentFlashcardIndex - 1);
            if (previousFlashcard.isReviewed()) {
                previousFlashcard.markAsFailure();
            }
        }
    }
}
