package com.saltatorv.polaris.flash.cards.domain;

import java.time.Instant;
import java.time.LocalDateTime;

class ActivityWindow {

    private final long startTime;
    private final long finishTime;

    private ActivityWindow(long startTime, long finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    static ActivityWindow create() {
        return new ActivityWindow(0, 0);
    }

    static ActivityWindow restore(long startTime, long finishTime) {
        return new ActivityWindow(startTime, finishTime);
    }

    ActivityWindow begin() {
        return new ActivityWindow(System.currentTimeMillis(), 0);
    }

    ActivityWindow finish() {
        return new ActivityWindow(this.startTime, System.currentTimeMillis());
    }

    FlashcardReviewLifecycle calculateLifecycleStatus() {
        if (startTime != 0 && finishTime != 0) {
            return FlashcardReviewLifecycle.FINISHED;
        } else if (startTime != 0) {
            return FlashcardReviewLifecycle.STARTED;
        } else {
            return FlashcardReviewLifecycle.CREATED;
        }
    }

    public LocalDateTime getStartDate() {
        return calculateDate(startTime);
    }

    public LocalDateTime getFinishDate() {
        return calculateDate(finishTime);
    }

    private LocalDateTime calculateDate(Long timeInMilliseconds) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timeInMilliseconds),
                java.time.ZoneId.systemDefault());
    }
}
