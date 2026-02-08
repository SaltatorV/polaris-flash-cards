package com.saltatorv.polaris.flash.cards.domain;

import java.time.LocalDate;
import java.util.List;

public class FlashcardReviewSnapshot {
    private final String flashcardReviewId;
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final List<FlashcardSnapshot> flashcardSnapshots;

    public FlashcardReviewSnapshot(String flashcardReviewId, LocalDate startDate, LocalDate finishDate, List<FlashcardSnapshot> flashcardSnapshots) {
        this.flashcardReviewId = flashcardReviewId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.flashcardSnapshots = flashcardSnapshots;
    }

    public String getFlashcardReviewId() {
        return flashcardReviewId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public List<FlashcardSnapshot> getFlashcardSnapshots() {
        return flashcardSnapshots;
    }
}
