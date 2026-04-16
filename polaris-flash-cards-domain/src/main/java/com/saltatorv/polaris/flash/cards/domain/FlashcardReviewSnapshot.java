package com.saltatorv.polaris.flash.cards.domain;

import java.util.List;

public class FlashcardReviewSnapshot {
    private final String flashcardReviewId;
    private final Long startDate;
    private final Long finishDate;
    private final List<FlashcardSnapshot> flashcardSnapshots;

    public FlashcardReviewSnapshot(String flashcardReviewId, Long startDate, Long finishDate, List<FlashcardSnapshot> flashcardSnapshots) {
        this.flashcardReviewId = flashcardReviewId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.flashcardSnapshots = flashcardSnapshots;
    }

    public String getFlashcardReviewId() {
        return flashcardReviewId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public Long getFinishDate() {
        return finishDate;
    }

    public List<FlashcardSnapshot> getFlashcardSnapshots() {
        return flashcardSnapshots;
    }

    @Override
    public String toString() {
        return "FlashcardReviewSnapshot{" +
                "startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", flashcardSnapshots=" + flashcardSnapshots +
                '}';
    }
}
