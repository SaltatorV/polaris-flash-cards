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

    static ActivityWindow restore(LocalDateTime startTime, LocalDateTime finishTime) {
        return new ActivityWindow(calculateTimeInMilliseconds(startTime),
                calculateTimeInMilliseconds(finishTime));
    }

    private static Long calculateTimeInMilliseconds(LocalDateTime date) {
        return date.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    ActivityWindow begin() {
        return new ActivityWindow(System.currentTimeMillis(), 0);
    }

    ActivityWindow finish() {
        return new ActivityWindow(this.startTime, System.currentTimeMillis());
    }

    LocalDateTime getStartDate() {
        return calculateDate(startTime);
    }

    LocalDateTime getFinishDate() {
        return calculateDate(finishTime);
    }

    Long getStartDateInMilliseconds() {
        return startTime;
    }

    Long getFinishDateInMilliseconds() {
        return finishTime;
    }

    private LocalDateTime calculateDate(Long timeInMilliseconds) {
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timeInMilliseconds),
                java.time.ZoneId.systemDefault());
    }
}
