package com.saltatorv.polaris.flash.cards.domain;

import java.time.LocalDateTime;
import java.util.List;

public class FlashcardReviewSnapshot {
    private final String flashcardReviewId;
    private final LocalDateTime startDate;
    private final LocalDateTime finishDate;
    private final List<FlashcardSnapshot> flashcardSnapshots;

    public FlashcardReviewSnapshot(String flashcardReviewId, LocalDateTime startDate, LocalDateTime finishDate, List<FlashcardSnapshot> flashcardSnapshots) {
        this.flashcardReviewId = flashcardReviewId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.flashcardSnapshots = flashcardSnapshots;
    }

    public String getFlashcardReviewId() {
        return flashcardReviewId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public List<FlashcardSnapshot> getFlashcardSnapshots() {
        return flashcardSnapshots;
    }
}
