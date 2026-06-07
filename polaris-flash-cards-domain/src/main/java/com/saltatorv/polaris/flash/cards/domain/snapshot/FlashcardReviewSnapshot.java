package com.saltatorv.polaris.flash.cards.domain.snapshot;

import com.saltatorv.polaris.flash.cards.domain.FlashcardSnapshot;
import com.saltatorv.polaris.flash.cards.domain.Generated;

import java.util.List;

@Generated
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FlashcardReviewSnapshot that = (FlashcardReviewSnapshot) o;
        return flashcardReviewId.equals(that.flashcardReviewId) && startDate.equals(that.startDate) && finishDate.equals(that.finishDate) && flashcardSnapshots.equals(that.flashcardSnapshots);
    }

    @Override
    public int hashCode() {
        int result = flashcardReviewId.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + flashcardSnapshots.hashCode();
        return result;
    }
}
