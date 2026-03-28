package com.saltatorv.polaris.flash.cards.domain;

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

    ActivityWindow start() {
        return new ActivityWindow(System.currentTimeMillis(), 0);
    }

    ActivityWindow finish() {
        return new ActivityWindow(this.startTime, System.currentTimeMillis());
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }
}
