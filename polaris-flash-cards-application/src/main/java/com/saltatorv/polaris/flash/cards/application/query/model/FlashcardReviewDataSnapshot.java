package com.saltatorv.polaris.flash.cards.application.query.model;

import com.saltatorv.polaris.flash.cards.domain.shared.FlashcardReviewId;

import java.time.LocalDateTime;

public class FlashcardReviewDataSnapshot {
    private final FlashcardReviewId id;
    private final int correctAnswers;
    private final int incorrectAnswers;
    private final int flashcardCount;
    private final LocalDateTime startDate;
    private final LocalDateTime finishDate;

    public FlashcardReviewDataSnapshot(FlashcardReviewId id, int correctAnswers,
                                       int incorrectAnswers, int flashcardCount,
                                       LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.flashcardCount = flashcardCount;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public FlashcardReviewId getId() {
        return id;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public int getFlashcardCount() {
        return flashcardCount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }
}
